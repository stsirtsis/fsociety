package gr.ntua.ece.softeng.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import org.apache.tomcat.util.codec.binary.Base64;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.repositories.ERepository;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.services.StorageService;
import gr.ntua.ece.softeng.entities.Filters;
import gr.ntua.ece.softeng.entities.Parent;


@Controller
@RequestMapping(path="/search")
public class SearchController {

	@Autowired
	private ERepository eRepository;

	@Autowired
	private ParentRepository pRepository;

	@Autowired
	StorageService storageService;

	public static Double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    Double latDistance = Math.toRadians(lat2 - lat1);
	    Double lonDistance = Math.toRadians(lon2 - lon1);
	    Double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    Double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    Double distance = R * c * 1000; // convert to meters

	    Double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}



     private static String encodeFileToBase64Binary(File file){
          String encodedfile = null;
          try {
              FileInputStream fileInputStreamReader = new FileInputStream(file);
              byte[] bytes = new byte[(int)file.length()];
              fileInputStreamReader.read(bytes);
              encodedfile = new String(Base64.encodeBase64(bytes), "UTF-8");
          } catch (FileNotFoundException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IOException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }

          return encodedfile;
      }
	
	
 	@PostMapping(path="/text")
 	public @ResponseBody List<E> textsearch(@RequestBody Filters filters) throws IOException, ParseException
 	{	
 		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
 		Date date1 = new Date();
 		Date date2;
 		Double latitude;
 		Double longitude;
 		if (filters.getUsername()=="") {
 			String Area =filters.getArea();
 			if (Area!="") {
 				String StreetName =filters.getStreetName().replaceAll("\\s+","");
 				String StreetNumber =filters.getStreetNumber().toString();
 				final String TARGET_URL =
 						"https://maps.googleapis.com/maps/api/geocode/json?address=";
 				final String help1= "+";
 				final String help2=",";
 				final String API_KEY =
 						"&key=AIzaSyCi-UTmdLdEpurrr8A5Ou5I17cihpelPcI";
 				URL serverUrl = new URL(TARGET_URL+StreetNumber+help1+StreetName+help2+Area+API_KEY);
 				URLConnection urlConnection = serverUrl.openConnection();
 				HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;
 				httpConnection.setRequestMethod("GET");
 				httpConnection.setRequestProperty("Content-Type", "application/json");
 				httpConnection.setDoOutput(true);

 				if (httpConnection.getInputStream() == null) {
 					System.out.println("No stream");
 				}

 				Scanner httpResponseScanner = new Scanner (httpConnection.getInputStream());
 				String resp = "";
 				while (httpResponseScanner.hasNext()) {
 					String line = httpResponseScanner.nextLine();
 					resp += line;
 				}
 				httpResponseScanner.close();

 				JSONObject json = new JSONObject(resp);
 				JSONArray results =  json.getJSONArray("results");
 				JSONObject sessionobj=results.getJSONObject(0);
 				JSONObject geometry=sessionobj.getJSONObject("geometry");
 				JSONObject location=geometry.getJSONObject("location");
 				latitude=location.getDouble("lat");
 				longitude=location.getDouble("lng");
 			}
 			else {
 				latitude=0.0;
 				longitude=0.0;
 			}
 		}
 		else  {
 			Parent p=pRepository.findByUsername(filters.getUsername());
 			latitude=p.getLatitude();
 			longitude=p.getLongitude();
 		}
 		List<E> results1;
 		if (filters.getText()=="") {
 			results1=eRepository.findAll();
 		}
 		else {
 			TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(filters.getText());
 			results1 = eRepository.findAllByOrderByScoreDesc(criteria);
 		}
 		List<E> results2;
 		List<E> results3;
 		List<E> results4;
 		List<E> results5=new ArrayList<>();
 		List<E> results6=new ArrayList<>();
 		if (filters.getPrice() == 1) {
 			results2= eRepository.findByPriceBetween(-1,11);
 			results1.retainAll(results2); }
 		else if (filters.getPrice() == 2) {
 			results2=eRepository.findByPriceBetween(9,21);
 			results1.retainAll(results2);}
 		else if (filters.getPrice()==3) {
 			results2=eRepository.findByPriceBetween(19,51);
 			results1.retainAll(results2);}
 		else if (filters.getPrice()==4) {
 			results2=eRepository.findByPriceBetween(49,10000);
 			results1.retainAll(results2);}
 		else {}
 		
 		if (filters.getAgeGroup() == 1) {
 			results3= eRepository.findByAgeGroup(1);
 			results1.retainAll(results3);}
 		else if (filters.getAgeGroup() == 2) {
 			results3=eRepository.findByAgeGroup(2);
 			results1.retainAll(results3);}
 		else if (filters.getAgeGroup()==3) {
 			results3=eRepository.findByAgeGroup(3);
 			results1.retainAll(results3);}
 		else if (filters.getAgeGroup()==4) {
 			results3=eRepository.findByAgeGroup(4);
 			results1.retainAll(results3);}
 		else {}
 	
 		if (filters.getCategory() == 1) {
 			results4= eRepository.findByCategory(1);
 			results1.retainAll(results4);}
 		else if (filters.getCategory() == 2) {
 			results4=eRepository.findByCategory(2);
 			results1.retainAll(results4);}
 		else if (filters.getCategory()==3) {
 			results4=eRepository.findByCategory(3);
 			results1.retainAll(results4);}
 		else {}

 		if (filters.getDistance() == 1) {
 			for(E e:results1) {
 				if(distance((e.getLatitude()),latitude,(e.getLongitude()),longitude,0 ,0) > 5000.00)
 					results5.add(e);
 			}
 			results1.removeAll(results5);}
 		else if (filters.getDistance() == 2) {
 			for(E e:results1) {
 				if (distance((e.getLatitude()),latitude,
 			(e.getLongitude()) ,longitude ,0,0) > 8000.00)
 					results5.add(e);
 			}
 			results1.removeAll(results5);}
 		else if (filters.getDistance()==3) {
 			for(E e:results1) {
 				if (distance((e.getLatitude()),latitude,
 			(e.getLongitude()) ,longitude ,0,0) > 10000.00)
 					results5.add(e);
 			}
 			results1.removeAll(results5);}
 		else if (filters.getDistance()==4) {
 			for(E e:results1) {
 				if (distance((e.getLatitude()),latitude,
 				(e.getLongitude()) ,longitude ,0,0) <=10000.00)
 					results5.add(e);
 			}
 			results1.removeAll(results5);}
 		else {}
 		
 		for (E e1:results1) {
 			date2=dateFormat.parse(e1.getDate());
 			if (date1.compareTo(date2)<=0) {
 				String uri=e1.getPhotoUri();
 				String relpath="./upload-dir/";
 				File thefile=new File(relpath+uri);
 				e1.setPhotoBody(encodeFileToBase64Binary(thefile));
 			}
 			else results6.add(e1);
 		}
 		 results1.removeAll(results6);
 		 return results1;
 	}


 }

