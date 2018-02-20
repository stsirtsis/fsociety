package gr.ntua.ece.softeng.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.entities.Parent;
import gr.ntua.ece.softeng.entities.Role;
import gr.ntua.ece.softeng.entities.User;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;

@Controller
@RequestMapping(path="/parents")
public class ParentController {
	
	@Autowired 
	private ParentRepository parentRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	private final static String POST_PARENT_URL = "/addNewParent";
	
	@PostMapping(POST_PARENT_URL)
	public @ResponseBody String createParent(@RequestBody Parent parent) {
		
		String username = parent.getUsername();
		String password = parent.getPassword();
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
		
		userRepository.save(new User(username, sha256hex, Arrays.asList(new Role("PARENT"))));
		
		parent.setPassword(sha256hex);
		parent.setFpoints(0);
		parentRepository.save(parent);
		
		return "ok with post from parent";
	}
		
	@GetMapping(path="/{parent_username}/events")
	public @ResponseBody Set<Event> findEvents (@PathVariable String parent_username) {
		return parentRepository.findByUsername(parent_username).getEvents();
	}

	@GetMapping(path="/all")
	public @ResponseBody List<Parent> getAllParents() {
		return parentRepository.findAll();
	}

}
