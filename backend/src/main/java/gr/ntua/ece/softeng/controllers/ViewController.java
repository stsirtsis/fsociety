package gr.ntua.ece.softeng.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ViewController {

@RequestMapping({ 
	"/parent-events",
	"/parent-register",
	"/provider-register",
	"/login-page",
	"/map",
	"/one-event/{id:\\\\w+}",
	"/add-event"	
})
   public String index() {
       return "forward:/index.html";
   }
}