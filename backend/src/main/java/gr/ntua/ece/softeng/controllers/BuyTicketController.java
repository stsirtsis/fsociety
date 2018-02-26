package gr.ntua.ece.softeng.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import gr.ntua.ece.softeng.config.PDFConfig;
import gr.ntua.ece.softeng.entities.Event;
import gr.ntua.ece.softeng.entities.Parent;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;



@Controller
@RequestMapping(path="/buyticket")
@PreAuthorize("hasAuthority('PARENT') or hasAuthority('ADMIN')")
public class BuyTicketController {

	@Autowired
	private EventRepository eventRepository;

	@Autowired
	private ParentRepository parentRepository;

	@Autowired
	private JavaMailSender sender;

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

			Integer price = event.getPrice();
		  Integer wallet = parent.getWallet();
			Integer Fpoints = parent.getFpoints();
			Integer finwallet = wallet;
			Integer new_Fpoints = Fpoints;

			Integer new_capacity = capacity - 1;

			if (wallet>0) {
				Integer new_wallet = wallet - price;
				if (new_wallet < 0) {
					Integer rest = price - wallet;
					finwallet = 0;
					new_Fpoints = Fpoints + (10 * rest);
				}
				else finwallet = new_wallet;
			}
			else {
				new_Fpoints = Fpoints + (10 * price);
			}
			Integer result1 = new_Fpoints / 1000;
			if (result1 > 0) {
				finwallet = finwallet + (result1 * 10);
				new_Fpoints = new_Fpoints % 1000;
			}
			parent.setFpoints(new_Fpoints);
			parent.setWallet(finwallet);

			event.setCapacity(new_capacity);
			event.getParents().add(parent);
			eventRepository.save(event);

			parent.getEvents().add(event);
			parentRepository.save(parent);
			if(new_capacity <= 0)
				return "OK, ticket bought and now event is full! You have \t" + new_Fpoints + " Fpoints and\t" + finwallet + "$ in your wallet";
			return "OK, ticket bought\t" + new_capacity + " left. Hurry! You have \t" + new_Fpoints + " Fpoints and\t" + finwallet + "$ in your wallet";
		}
		else
			return "Sorry, event is full";

	}

    @RequestMapping("/sendEmail")
    public  @ResponseBody String home(@RequestParam String username) {
    	try {
            sendEmail(username);
            return "Email Sent!";
        }catch(Exception ex) {
            return "Error in sending email: "+ex;
        }
    }

    public void sendEmail(String username) throws Exception{
    	Parent parent=parentRepository.findByUsername(username);
    	String email;
    	email=parent.getEmail();

    	String firstName=parent.getFirstName();
    	String lastName=parent.getLastName();

    	MimeMessage message = sender.createMimeMessage();
    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
        // Enable the multipart flag!
        MimeMessageHelper helper = new MimeMessageHelper(message,true);

        helper.setTo(email);
        helper.setText("");
        helper.setSubject("Ticket-Report");
        String fileName = "ticket.pdf";

	    try {

	        PDFConfig.createPDF(fileName,firstName,lastName);

	        baos = convertPDFToByteArrayOutputStream(fileName);
	       } catch (Exception e1) {
	        e1.printStackTrace();
	    }

        final byte[] data=baos.toByteArray();


        helper.addAttachment("ticket.pdf",new ByteArrayResource(data));

        sender.send(message);
    }


	private ByteArrayOutputStream convertPDFToByteArrayOutputStream(String fileName) {

		InputStream inputStream = null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {

			inputStream = new FileInputStream(fileName);
			byte[] buffer = new byte[1024];
			baos = new ByteArrayOutputStream();

			int bytesRead;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				baos.write(buffer, 0, bytesRead);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (inputStream != null) {
				try {
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return baos;
	}



}
