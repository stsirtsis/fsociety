package gr.ntua.ece.softeng.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.ntua.ece.softeng.entities.Parent;
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;

@RestController
@RequestMapping(path="/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
	@Autowired
	private ProvidersRepository providersRepository;
	
	@Autowired 
	private ParentRepository parentRepository;
	
	@GetMapping(path="/all_providers")
	public @ResponseBody Iterable<Providers> getAllUsers() {
		return providersRepository.findAll();
	}
	
	@GetMapping(path="/all_parents")
	public @ResponseBody List<Parent> getAllParents() {
		return parentRepository.findAll();
	}
}
