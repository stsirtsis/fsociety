package gr.ntua.ece.softeng;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.Event;
import gr.ntua.ece.softeng.EventRepository;
import gr.ntua.ece.softeng.ParentRepository;


@Controller
@RequestMapping(path="/buyticket")
public class BuyTicketController {

	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ParentRepository parentRepository;

	public String book(Event event, Integer capacity) {
		event.setCapacity(capacity - 1);
		eventRepository.save(event);
		if(capacity <= 0)
			return "Sorry, event is full";
		return "OK, ticket bought\t" + event.getCapacity() + " left. Hurry!";
	}
	
	
	@RequestMapping(path="/new/{parent_username}/{event_id}")
	public synchronized @ResponseBody String buynewticket (@PathVariable String parent_username, 
														   @PathVariable Long event_id) {
		Event event      = eventRepository.findOne(event_id) ;
		Parent parent    = parentRepository.findByUsername(parent_username);
		Integer capacity;
		 

		capacity = event.getCapacity();
		if(capacity > 0) {
			Integer new_capacity = capacity - 1;
			event.setCapacity(new_capacity);
			
			
			
			
			event.getParents().add(parent);
			eventRepository.save(event);
			parent.getEvents().add(event);
			parentRepository.save(parent);
//			int size = event.getParents().size();
//			int size1 = parent.getEvents().size();
//			System.out.println(size);
//			System.out.println(size1);
			if(new_capacity <= 0)
				return "OK, ticket bought\t and now event is full!";
			return "OK, ticket bought\t" + new_capacity + " left. Hurry!";
		}
		else
			return "Sorry, event is full";

	}

}
