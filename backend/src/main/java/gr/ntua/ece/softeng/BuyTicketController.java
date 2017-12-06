package gr.ntua.ece.softeng;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.Event;
import gr.ntua.ece.softeng.EventRepository;

@Controller
@RequestMapping(path="/buyticket")
public class BuyTicketController {
	@Autowired
	private EventRepository eventRepository;
	

	
	@RequestMapping(path="/new")
	public @ResponseBody String buynewticket (@RequestParam Long id) {
		
		Event event      = eventRepository.findOne(id) ;
		Integer capacity;
		
		capacity = event.getCapacity();
		if(capacity > 0) {
			event.setCapacity(capacity - 1);
			eventRepository.save(event);
			return "OK, ticket bought\t" + eventRepository.findOne(id).getCapacity() + " left. Hurry!";
		}
		else
			return "Sorry, event is full";

		
	}
	
}
