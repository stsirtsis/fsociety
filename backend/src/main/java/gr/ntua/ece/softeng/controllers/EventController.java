package gr.ntua.ece.softeng.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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
public class EventController {

	@Autowired
	private EventRepository eventRepository;

	@RequestMapping(path="/event_click/{event_id}")
	public synchronized @ResponseBody void countclicks (@PathVariable Long event_id) {

		Event event = eventRepository.findOne(event_id);
		Integer clicks;

		clicks=event.getClicks();
		clicks++;
		event.setClicks(clicks);
		eventRepository.save(event);
	}

	@RequestMapping(path="{eventId}")
	public @ResponseBody Event findEvent (@PathVariable Long eventId) {
		return eventRepository.findOne(eventId);
	}

}
