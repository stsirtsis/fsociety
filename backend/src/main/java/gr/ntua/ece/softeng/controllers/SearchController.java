package gr.ntua.ece.softeng.controllers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.repositories.ERepository;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.entities.Filters;
import gr.ntua.ece.softeng.entities.Parent;


@Controller  
@RequestMapping(path="/search")
public class SearchController {

	@Autowired
	private ERepository eRepository;
	
	@Autowired
	private ParentRepository pRepository;
	
	
	
	public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}
	
	@PostMapping(path="/text")
	public @ResponseBody List<E> textsearch(@RequestBody Filters filters)
	{
		TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny(filters.getText());
	    List<E> results1 = eRepository.findAllByOrderByScoreDesc(criteria);	
		List<E> results2;
		List<E> results3;
		List<E> results4;
	    Parent p=pRepository.findByUsername(filters.getUsername());
		if (filters.getPrice() == 1) 
			results2= eRepository.findByPriceBetween(-1,11);
		else if (filters.getPrice() == 2) 
			results2=eRepository.findByPriceBetween(9,21);
		else if (filters.getPrice()==3) 
			results2=eRepository.findByPriceBetween(19,51);
		else 
			results2=eRepository.findByPriceBetween(49,10000);
		results1.retainAll(results2);
	
		if (filters.getAgeGroup() == 1) 
			results3= eRepository.findByAgeGroup(1);
		else if (filters.getAgeGroup() == 2) 
			results3=eRepository.findByAgeGroup(2);
		else if (filters.getAgeGroup()==3) 
			results3=eRepository.findByAgeGroup(3);
		else 
			results3=eRepository.findByAgeGroup(4);
		results1.retainAll(results3);
		
		if (filters.getCategory() == 1) 
			results4= eRepository.findByCategory(1);
		else if (filters.getCategory() == 2) 
			results4=eRepository.findByCategory(2);
		else if (filters.getCategory()==3) 
			results4=eRepository.findByCategory(3);
		else 
			results4=eRepository.findByCategory(4);
		results1.retainAll(results4);
		
		
		List<E> list = new CopyOnWriteArrayList<E>(results1);
		
		if (filters.getDistance() == 1) 
			for(E e : list) {
				if ( distance(Double.parseDouble(e.getLatitude()),Double.parseDouble(p.getLatitude()),
			Double.parseDouble(e.getLongitude()) , Double.parseDouble(p.getLatitude()) ,0,0) > 5)
					list.remove(e);		
			}
		else if (filters.getDistance() == 2) 
			for(E e : results1) {
				if ( distance(Double.parseDouble(e.getLatitude()),Double.parseDouble(p.getLatitude()),
			Double.parseDouble(e.getLongitude()) , Double.parseDouble(p.getLatitude()) ,0,0) > 8)
					list.remove(e);	
			}
		else if (filters.getDistance()==3) 
			for(E e : results1) {
				if ( distance(Double.parseDouble(e.getLatitude()),Double.parseDouble(p.getLatitude()),
			Double.parseDouble(e.getLongitude()) , Double.parseDouble(p.getLatitude()) ,0,0) > 10)
					list.remove(e);	
			}
		else 
			for(E e : results1) {
				if ( distance(Double.parseDouble(e.getLatitude()),Double.parseDouble(p.getLatitude()),
			Double.parseDouble(e.getLongitude()) , Double.parseDouble(p.getLatitude()) ,0,0) <=10)
					list.remove(e);
			}
		return list;
	}
	     
	
}
