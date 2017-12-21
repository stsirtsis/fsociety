package gr.ntua.ece.softeng;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.ntua.ece.softeng.Event;
import gr.ntua.ece.softeng.EventRepository;
import gr.ntua.ece.softeng.ProvidersRepository;
import gr.ntua.ece.softeng.Providers;
import gr.ntua.ece.softeng.E;
import gr.ntua.ece.softeng.ERepository;



@RestController    
@RequestMapping(path="/event_announce")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ProvidersRepository providersRepository;
	
	@Autowired
	private ERepository eRepository;
	
	
/*	@GetMapping(path="/add/{providerCompanyName}")
	public @ResponseBody String addNewEvent (
			  @PathVariable String providerCompanyName,
			  @RequestParam String eventname,
			  @RequestParam String description,
			  @RequestParam String Area,
			  @RequestParam String StreetName,
			  @RequestParam Integer StreetNumber,
			  @RequestParam Integer capacity,
			  @RequestParam String category,
			  @RequestParam Integer price) {
		
		Event event = new Event();
		Providers provider = providersRepository.findByCompanyName(providerCompanyName);
		event.setEventname(eventname);
		event.setDescription(description);
		event.setArea(Area);
		event.setStreetName(StreetName);
		event.setStreetNumber(StreetNumber);
		event.setCapacity(capacity);
		event.setCategory(category);
		event.setPrice(price);
		event.setState(state.OPEN);
		event.setParents(new HashSet<>());
		event.setProvider(provider);
		eventRepository.save(event);
		provider.getEvents().add(event);
		providersRepository.save(provider);
		return "OK";
	} */
	
	@PostMapping(path="/addNewEvent/{providerCompanyName}")
	public @ResponseBody String addNewEvent_post (@RequestBody Event e, @PathVariable String providerCompanyName) {
		Providers provider = providersRepository.findByCompanyName(providerCompanyName);
		provider.getEvents().add(e);
		providersRepository.save(provider);
		e.setProvider(provider);
		eventRepository.save(e);
/* bugfix 
		String eventname=e.getEventname();
		String description=e.getDescription();
		String Area=e.getArea();
		String StreetName=e.getStreetName();
		String StreetNumber=e.getStreetNumber().toString();
		String AgeGroup=e.getAgeGroup().toString();
		String capacity=e.getCapacity().toString();
		String price=e.getPrice().toString();
		String category=e.getCategory();
		String company_name=e.getProvider().getcompanyName();
		eRepository.save(new E(eventname, description , Area , StreetName , StreetNumber , AgeGroup , capacity , price , category , company_name ));
*/
		return "OK with post";
	}
	
	@GetMapping(path="testutf")
	public @ResponseBody void test(@RequestParam String test_str) {
		System.out.println(test_str);
	}
	
	@GetMapping(path="/all")
	public @ResponseBody List<Event> getAllEvents() {
		// This returns a JSON or XML with the users
		return eventRepository.findAll();
	}
		
}
