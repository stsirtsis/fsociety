package gr.ntua.ece.softeng.controllers;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;
import java.util.Set;

import org.codehaus.jackson.map.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.repositories.ERepository;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;


import gr.ntua.ece.softeng.services.StorageService;


@Controller
@RequestMapping(path="/provider")
public class ProviderController {
	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ProvidersRepository providersRepository;

	@Autowired
	private ERepository eRepository;

	@Autowired
	StorageService storageService;

	List<String> files = new ArrayList<String>();

	@PostMapping(path="/addNewEvent/{providerCompanyName}")
	@PreAuthorize("hasAuthority('PROVIDER') or hasAuthority('ADMIN')")
	public ResponseEntity<String> addNewEvent_post (@RequestParam("event") String str, @PathVariable String providerCompanyName,@RequestParam("file") MultipartFile file) throws IOException, JSONException {
		Providers provider = providersRepository.findByCompanyName(providerCompanyName);
		ObjectMapper mapper = new ObjectMapper();
		Event e = mapper.readValue(str, Event.class);
		Integer initialcap = e.getCapacity();
		e.setClicks(0);
		e.setInitial(initialcap);
		e.setProvider(provider);
		System.out.println(e.getEventname());
		provider.getEvents().add(e);
		providersRepository.save(provider);

		String message = "";
			try {
				storageService.store(file);
				e.setPhotoUri(file.getOriginalFilename());
				files.add(file.getOriginalFilename());

				message = "You successfully uploaded " + file.getOriginalFilename() + "!";


		eventRepository.save(e);

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
		Resource photoBody=null;
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

		String response = httpConnection.getResponseMessage();
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
		Double latitude=location.getDouble("lat");
		Double longitude=location.getDouble("lng");

		eRepository.save(new E(eventname, description , Area , StreetName , StreetNumber , AgeGroup , capacity , price , category , company_name, latitude, longitude, date, state, photoUri, photoBody ));

		return ResponseEntity.status(HttpStatus.OK).body(message);
			}
		catch (Exception e2) {
			message = "FAIL to upload " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		}
	}

	@PostMapping(path="/events/{providerCompanyName}")
	@PreAuthorize("hasAuthority('PROVIDER') or hasAuthority('ADMIN')")
	public @ResponseBody Set<Event> getEvents (@PathVariable String providerCompanyName) {
		return providersRepository.findByCompanyName(providerCompanyName).getEvents();
	}

}
