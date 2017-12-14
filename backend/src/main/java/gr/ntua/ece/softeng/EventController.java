package gr.ntua.ece.softeng;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.Event;
import gr.ntua.ece.softeng.EventRepository;
import gr.ntua.ece.softeng.ProvidersRepository;
import gr.ntua.ece.softeng.Providers;



@Controller    
@RequestMapping(path="/event_announce")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ProvidersRepository providersRepository;
	
	@GetMapping(path="/add/{providerCompanyName}")
	public @ResponseBody String addNewEvent (
			  @PathVariable String providerCompanyName, 
			  @RequestParam Integer capacity,
			  @RequestParam String category,
			  @RequestParam Integer price) {
		
		Event event = new Event();
		Providers provider = providersRepository.findByCompanyName(providerCompanyName);
		
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
