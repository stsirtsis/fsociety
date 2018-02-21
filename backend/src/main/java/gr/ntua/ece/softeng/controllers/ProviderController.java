package gr.ntua.ece.softeng.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.repositories.ERepository;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;


@Controller 
@RequestMapping(path="/provider")
public class ProviderController {
	@Autowired
	private EventRepository eventRepository;
	
	@Autowired
	private ProvidersRepository providersRepository;
	
	@Autowired
	private ERepository eRepository;
	

	@PostMapping(path="/addNewEvent/{providerCompanyName}")
	@PreAuthorize("hasAuthority('PROVIDER') or hasAuthority('ADMIN')")
	public @ResponseBody String addNewEvent_post (@RequestBody Event e, @PathVariable String providerCompanyName) {
		Providers provider = providersRepository.findByCompanyName(providerCompanyName);
		provider.getEvents().add(e);
		providersRepository.save(provider);
		e.setProvider(provider);
		eventRepository.save(e);

		String eventname = e.getEventname();
		String description = e.getDescription();
		String Area = e.getArea();
		String StreetName = e.getStreetName();
		String StreetNumber = e.getStreetNumber().toString();
		String AgeGroup = e.getAgeGroup().toString();
		String capacity = e.getCapacity().toString();
		String price = e.getPrice().toString();
		String category = e.getCategory();
		String company_name = e.getProvider().getcompanyName();
		eRepository.save(new E(eventname, description , Area , StreetName , StreetNumber , AgeGroup , capacity , price , category , company_name ));

		return "OK with post from event registration";
	}
	
	@PostMapping(path="/events/{providerCompanyName}")
	@PreAuthorize("hasAuthority('PROVIDER') or hasAuthority('ADMIN')")
	public @ResponseBody Set<Event> getEvents (@PathVariable String providerCompanyName) {
		return providersRepository.findByCompanyName(providerCompanyName).getEvents();
	}

}



