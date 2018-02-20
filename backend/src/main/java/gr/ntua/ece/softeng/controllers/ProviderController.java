package gr.ntua.ece.softeng.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.entities.Role;
import gr.ntua.ece.softeng.entities.User;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;

@Controller 
@RequestMapping(path="/providers-registration")
public class ProviderController {
	@Autowired
	private ProvidersRepository providersRepository;
	
	@Autowired 
	private UserRepository userRepository;


	private final static String POST_PARENT_URL = "/addNewProvider";
	@PostMapping(POST_PARENT_URL)
	public @ResponseBody String createParent(@RequestBody Providers provider) {
		String username = provider.getUserName();
		String password = provider.getPassword();
		
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
		
		userRepository.save(new User(username, sha256hex, Arrays.asList(new Role("PARENT"))));
		provider.setPassword(sha256hex);
		providersRepository.save(provider);
		return "ok with post from provider";
	}

	@GetMapping(path="/all")
	@PreAuthorize("hasAuthority('ADMIN')")
	public @ResponseBody Iterable<Providers> getAllUsers() {
		return providersRepository.findAll();
	}
}



