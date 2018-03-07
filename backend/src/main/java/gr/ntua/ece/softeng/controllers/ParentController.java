package gr.ntua.ece.softeng.controllers;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.ParentRepository;

@Controller
@RequestMapping(path="/parents")
public class ParentController {

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private EventRepository eventRepository;


	@PostMapping(path="/eventParent/{parent_username}")
	@PreAuthorize("hasAuthority('PARENT') or hasAuthority('ADMIN')")
	public @ResponseBody Set<Event> findEvents (@PathVariable String parent_username) {
		return parentRepository.findByUsername(parent_username).getEvents();
	}

	@PostMapping(path="/all_events")
	@PreAuthorize("!hasAuthority('PROVIDER')")
	public @ResponseBody List<Event> getAllEvents() {
		return eventRepository.findAll();
	}


}
