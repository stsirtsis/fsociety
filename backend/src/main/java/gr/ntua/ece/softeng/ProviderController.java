package gr.ntua.ece.softeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.Providers;
import gr.ntua.ece.softeng.ProvidersRepository;

@Controller 
@RequestMapping(path="/providers-registartion")
public class ProviderController {
	@Autowired
	private ProvidersRepository providersRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewProvider (@RequestParam String companyName,@RequestParam String firstName,
			@RequestParam  String lastName, @RequestParam String userName, @RequestParam String password,@RequestParam 
			String category, @RequestParam  String description, @RequestParam String area,@RequestParam String streetName,
			@RequestParam  Integer streetNumber, @RequestParam String telNumber, @RequestParam  String mail,@RequestParam String iban) 
	{
		// @ResponseBody means the returned String is the response, not a view name
		// @RequestParam means it is a parameter from the GET or POST request
		
		Providers n = new Providers();
		n.setCompanyName(companyName);
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
		providersRepository.save(n);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Providers> getAllUsers() {
		// This returns a JSON or XML with the users
		return providersRepository.findAll();
	}
}



