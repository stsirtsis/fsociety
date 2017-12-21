package gr.ntua.ece.softeng;


import java.util.HashSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.Providers;
import gr.ntua.ece.softeng.ProvidersRepository;

@Controller 
@RequestMapping(path="/providers-registration")
public class ProviderController {
	@Autowired
	private ProvidersRepository providersRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewProvider (@RequestParam String companyName,@RequestParam String firstName,
			@RequestParam  String lastName, @RequestParam String userName, @RequestParam String password,@RequestParam 
			String category, @RequestParam  String description, @RequestParam String area,@RequestParam String streetName,
			@RequestParam  Integer streetNumber, @RequestParam String telNumber, @RequestParam  String mail,@RequestParam String iban) 
	{
		
		Providers n = new Providers();
		n.setcompanyName(companyName);
		n.setFirstName(firstName);
		n.setLastName(lastName);
		n.setUserName(userName);
		n.setPassword(password);
		n.setCategory(category);
		n.setDescription(description);
		n.setArea(area);
		n.setStreetName(streetName);
		n.setStreetNumber(streetNumber);
		n.setTelNumber(telNumber);
		n.setMail(mail);
		n.setIban(iban);
		n.setEvents(new HashSet<>());
		providersRepository.save(n);
		return "Saved";
	}

private final static String POST_PARENT_URL = "/addNewProvider";
	
	@PostMapping(POST_PARENT_URL)
	public @ResponseBody String createParent(@RequestBody Providers pro) {
		System.out.println("Creat Provider: " + pro);
		providersRepository.save(pro);
		return "ok with post";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Providers> getAllUsers() {
		return providersRepository.findAll();
	}
}



