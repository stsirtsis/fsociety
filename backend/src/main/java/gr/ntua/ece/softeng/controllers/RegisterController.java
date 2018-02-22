package gr.ntua.ece.softeng.controllers;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.ntua.ece.softeng.entities.Parent;
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.entities.Role;
import gr.ntua.ece.softeng.entities.User;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;

@RestController
@RequestMapping(path="/register")
public class RegisterController {
	
	@Autowired 
	private ParentRepository parentRepository;
	
	@Autowired
	private ProvidersRepository providersRepository;

	@Autowired
	private UserRepository userRepository;

	private final static String POST_PARENT_URL = "/parent";
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
	
	private final static String POST_PROVIDER_URL = "/provider";
	@PostMapping(POST_PROVIDER_URL)
	public @ResponseBody String createProvider(@RequestBody Providers provider) {
		String username = provider.getUserName();
		String password = provider.getPassword();
		
		String sha256hex = org.apache.commons.codec.digest.DigestUtils.sha256Hex(password);
		
		userRepository.save(new User(username, sha256hex, Arrays.asList(new Role("PROVIDER"))));
		provider.setPassword(sha256hex);
		providersRepository.save(provider);
		return "ok with post from provider";
	}
	
	
	
	
}
