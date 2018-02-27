package gr.ntua.ece.softeng.controllers;

import java.security.SecureRandom;
import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    
    @Autowired
    private JavaMailSender sender;
	
	@GetMapping(path="/all_providers")
	public @ResponseBody Iterable<Providers> getAllUsers() {
		return providersRepository.findAll();
	}
	
	@GetMapping(path="/all_parents")
	public @ResponseBody List<Parent> getAllParents() {
		return parentRepository.findAll();
	}
	
	
    private static SecureRandom random = new SecureRandom();
    private static final String ALPHA_CAPS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String ALPHA = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMERIC = "0123456789";
    

    
	 @GetMapping(path="/resetpassword")
	 public  @ResponseBody String PasswordGenerator(@RequestParam String username) {
		 
			try {
	            sendPassword(username);
	            return "Email Sent!";
	        }catch(Exception ex) {
	            return "Error in sending emails: "+ex;
	        }
	    }
	 
	 public void sendPassword(String username) throws Exception{
		 
	    	Parent parent=parentRepository.findByUsername(username);
	    	String email=parent.getEmail();
	    	String password=generatePassword(10,ALPHA_CAPS+ALPHA+NUMERIC);
	    	parent.setPassword(password);
	    	parentRepository.save(parent);
	    	
	    	
	    	MimeMessage message = sender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message,true);

	        helper.setTo(email);
	        helper.setSubject("Αλλαγή κωδικού");
	        helper.setText("Ο νέος σας κωδικός είναι: "+password);
	        sender.send(message);

		 
		 
		 
	 }
	 
	 
	 
	 public static String generatePassword(int len, String dic) {
			String result = "";
			for (int i = 0; i < len; i++) {
			    int index = random.nextInt(dic.length());
			    result += dic.charAt(index);
			}
			return result;
		    }

	
	
	
}
