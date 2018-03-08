package gr.ntua.ece.softeng;

import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.entities.Role;
import gr.ntua.ece.softeng.entities.User;
import gr.ntua.ece.softeng.repositories.ERepository;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import java.awt.Event;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Scanner;

import javax.annotation.Resource;
import org.springframework.boot.CommandLineRunner;
import gr.ntua.ece.softeng.services.StorageService;

@SpringBootApplication
@EntityScan(basePackages = {"gr.ntua.ece.softeng.entities"})
@EnableJpaRepositories(basePackages = {"gr.ntua.ece.softeng.repositories"})
public class DemoApplication implements CommandLineRunner{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ERepository eRepository;

    @Autowired
    private EventRepository eventRepository;


		@Resource
		StorageService storageService;
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

		@Override
		public void run(String... arg) throws Exception {
		//	storageService.deleteAll();
			storageService.init();
		}



    @Bean
    InitializingBean sendDatabase() {
        return () -> {

        	eventRepository.findAll().forEach(e -> {

				Long myId = e.getId();
				String id = Long.toString(myId);
				String eventname = e.getEventname();
				String description = e.getDescription();
				String Area = e.getArea();
				String StreetName = e.getStreetName().replaceAll("\\s+","");
				Integer StreetNumber = e.getStreetNumber();
				Integer AgeGroup = e.getAgeGroup();
				Integer capacity = e.getCapacity();
				Integer price = e.getPrice();
				Integer category = e.getCategory();
				String company_name = e.getProvider().getcompanyName();
				String date=e.getDate().toString();
				String state="OPEN";
				String photoUri=e.getPhotoUri();
				String photoBody="";
				final String TARGET_URL =
	               "https://maps.googleapis.com/maps/api/geocode/json?address=";
				final String help1= "+";
				final String help2=",";
				final String API_KEY =
						"&key=AIzaSyCi-UTmdLdEpurrr8A5Ou5I17cihpelPcI";
				URL serverUrl = null;
				try {
					serverUrl = new URL(TARGET_URL+StreetNumber+help1+StreetName+help2+Area+API_KEY);
				} catch (MalformedURLException e5) {
					// TODO Auto-generated catch block
					e5.printStackTrace();
				}

				URLConnection urlConnection = null;
				try {
					urlConnection = serverUrl.openConnection();
				} catch (IOException e4) {
					// TODO Auto-generated catch block
					e4.printStackTrace();
				}
				HttpURLConnection httpConnection = (HttpURLConnection)urlConnection;

				try {
					httpConnection.setRequestMethod("GET");
				} catch (ProtocolException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				httpConnection.setRequestProperty("Content-Type", "application/json");
				httpConnection.setDoOutput(true);

				try {
					if (httpConnection.getInputStream() == null) {
						System.out.println("No stream");
					}
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}

				Scanner httpResponseScanner = null;
				try {
					httpResponseScanner = new Scanner (httpConnection.getInputStream());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
				Double latitude=location.getDouble("lat");
				Double longitude=location.getDouble("lng");
				e.setLatitude(latitude);
				e.setLongitude(longitude);
				eventRepository.save(e);
				eRepository.save(new E(id, eventname, description , Area , StreetName , StreetNumber , AgeGroup , capacity , price , category , company_name, latitude, longitude, date, state, photoUri, photoBody ));
            }
        	);


        	if(userRepository.findByUsername("admin") == null) {
            String password = org.apache.commons.codec.digest.DigestUtils.sha256Hex("admin");
            userRepository.save(new User("admin", password, Arrays.asList(new Role("ADMIN"))));
          }
        };
    }
}
