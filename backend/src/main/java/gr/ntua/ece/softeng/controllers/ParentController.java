package gr.ntua.ece.softeng.controllers;

import java.util.List;
import java.util.Set;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.ParentRepository;

@Controller
@RequestMapping(path="/parents")
public class ParentController {

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

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private EventRepository eventRepository;


	@PostMapping(path="/eventParent/{parent_username}")
	@PreAuthorize("hasAuthority('PARENT') or hasAuthority('ADMIN')")
	public @ResponseBody Set<Event> findEvents (@PathVariable String parent_username) {
		Set<Event> ev=parentRepository.findByUsername(parent_username).getEvents();
		for (Event e: ev) {
	  	String uri=e.getPhotoUri();
			String relpath="./upload-dir/";
			File thefile=new File(relpath+uri);
			e.setPhotoBody(encodeFileToBase64Binary(thefile));
		}
		return ev;
	}

	@PostMapping(path="/all_events")
	@PreAuthorize("!hasAuthority('PROVIDER')")
	public @ResponseBody List<Event> getAllEvents() {
		return eventRepository.findAll();
	}


}
