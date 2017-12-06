package gr.ntua.ece.softeng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.Event;
import gr.ntua.ece.softeng.EventRepository;



@Controller    // This means that this class is a Controller
@RequestMapping(path="/event_announce")
public class EventController {
	@Autowired
	private EventRepository eventRepository;
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser (
			  @RequestParam Integer capacity,
			  @RequestParam String provider,
			  @RequestParam String category,
			  @RequestParam Integer price) {
		
		Event event = new Event();
		event.setCapacity(capacity);
		event.setProvider(provider);
		event.setCategory(category);
		event.setPrice(price);
		eventRepository.save(event);
		return "OK";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Event> getAllEvents() {
		// This returns a JSON or XML with the users
		return eventRepository.findAll();
	}
		
}
