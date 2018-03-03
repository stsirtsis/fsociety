package gr.ntua.ece.softeng.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
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
	public @ResponseBody List<E> textsearch(@RequestBody Filters filters)
	{	
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
		Double lat1;
		Double lat2;
		Double long1;
		Double long2;
		Double d;
	    Parent p=pRepository.findByUsername(filters.getUsername());
		if (filters.getPrice() == 1)
			results2= eRepository.findByPriceBetween(-1,11);
		else if (filters.getPrice() == 2)
			results2=eRepository.findByPriceBetween(9,21);
		else if (filters.getPrice()==3)
			results2=eRepository.findByPriceBetween(19,51);
		else if (filters.getPrice()==4)
			results2=eRepository.findByPriceBetween(49,10000);
		else results2=results1;
		results1.retainAll(results2);

		if (filters.getAgeGroup() == 1)
			results3= eRepository.findByAgeGroup(1);
		else if (filters.getAgeGroup() == 2)
			results3=eRepository.findByAgeGroup(2);
		else if (filters.getAgeGroup()==3)
			results3=eRepository.findByAgeGroup(3);
		else if (filters.getAgeGroup()==4)
			results3=eRepository.findByAgeGroup(4);
		else results3=results1;
		results1.retainAll(results3);

		if (filters.getCategory() == 1)
			results4= eRepository.findByCategory(1);
		else if (filters.getCategory() == 2)
			results4=eRepository.findByCategory(2);
		else if (filters.getCategory()==3)
			results4=eRepository.findByCategory(3);
		else if (filters.getCategory()==4)
			results4=eRepository.findByCategory(4);
		else results4=results1;
		results1.retainAll(results4);


		if (filters.getDistance() == 1)
			for(E e:results1) {
				lat1=e.getLatitude();
				lat2=p.getLatitude();
				long1=e.getLongitude();
				long2=p.getLongitude();
				 d= distance(lat1,lat2,long1,long2,0 ,0 );
				if ( d > 5000.00)
					results5.add(e);
			}
		else if (filters.getDistance() == 2)
			for(E e:results1) {
				if (distance((e.getLatitude()),(p.getLatitude()),
			(e.getLongitude()) ,(p.getLongitude()) ,0,0) > 8000.00)
					results5.add(e);
			}

		else if (filters.getDistance()==3)
			for(E e:results1) {
				if (distance((e.getLatitude()),(p.getLatitude()),
			(e.getLongitude()) ,(p.getLongitude()) ,0,0) > 10000.00)
					results5.add(e);
			}
		else if (filters.getCategory()==4)
			for(E e:results1) {
				if (distance((e.getLatitude()),(p.getLatitude()),
				(e.getLongitude()) ,(p.getLongitude()) ,0,0) <=10000.00)
					results5.add(e);
			}
		else results5=null;
		results1.removeAll(results5);
		for (E e1:results1) {
			String uri=e1.getPhotoUri();
			String relpath="./upload-dir/";
			File thefile=new File(relpath+uri);
			e1.setPhotoBody(encodeFileToBase64Binary(thefile));
		}
		return results1;
	}


}
