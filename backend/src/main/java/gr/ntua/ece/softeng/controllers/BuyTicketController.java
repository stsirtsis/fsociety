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
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import gr.ntua.ece.softeng.entities.E;
import gr.ntua.ece.softeng.repositories.ERepository;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import gr.ntua.ece.softeng.error.CustomResponse;




@Controller
@RequestMapping(path="/buyticket")
@PreAuthorize("hasAuthority('PARENT') or hasAuthority('ADMIN')")
public class BuyTicketController {

    @Autowired
    private ERepository eRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private ParentRepository parentRepository;
    
    @Autowired
    private ProvidersRepository providerRepository;

    @Autowired
    private JavaMailSender sender;

    private static Integer eventprice;
    private static String eventname;
    private static Integer TotalPrice;
    private static Integer Tickets;
    private static Providers provider;
    private static String mail;
    private static String firstname;
    private static String lastname;
    
    @RequestMapping(path="/new")
    public synchronized @ResponseBody CustomResponse buynewticket (@RequestParam String parent_username,
                                                           @RequestParam Long event_id,
                                                           @RequestParam Integer quantity) throws MessagingException {
        Event event = eventRepository.findOne(event_id);
        String id = Long.toString(event_id);
        E e = eRepository.findOne(id);
        eventname=event.getEventname();
        eventprice=event.getPrice();
        provider=event.getProvider();
        mail=provider.getMail();
        TotalPrice=eventprice*quantity;
        Tickets=quantity;
        Parent parent    = parentRepository.findByUsername(parent_username);
        Integer capacity;
        firstname=parent.getFirstName();
        lastname=parent.getLastName();


        capacity = event.getCapacity();
        if(capacity > 0) {

            Integer price = event.getPrice();
            Integer wallet = parent.getWallet();
            Integer Fpoints = parent.getFpoints();
            Integer finwallet = wallet;
            Integer new_Fpoints = Fpoints;
            Integer new_price = price * quantity;
            Integer new_capacity = capacity - quantity;

            if (wallet>0) {
                Integer new_wallet = wallet - new_price;
                if (new_wallet < 0) {
                    Integer rest = new_price - wallet;
                    finwallet = 0;
                    new_Fpoints = Fpoints + (10 * rest);
                }
                else finwallet = new_wallet;
            }
            else {
                new_Fpoints = Fpoints + (10 * new_price);
            }
            Integer result1 = new_Fpoints / 1000;
            if (result1 > 0) {
                finwallet = finwallet + (result1 * 10);
                new_Fpoints = new_Fpoints % 1000;
            }
            parent.setFpoints(new_Fpoints);
            parent.setWallet(finwallet);

            event.setCapacity(new_capacity);
            e.setCapacity(new_capacity);
            event.getParents().add(parent);
            eventRepository.save(event);
            eRepository.save(e);

            parent.getEvents().add(event);
            parentRepository.save(parent);
            if(new_capacity <= 0){
            	MimeMessage message = sender.createMimeMessage();
    			MimeMessageHelper helper = new MimeMessageHelper(message,true);

    			helper.setTo(mail);
    			helper.setSubject("Aγορά Εισιτηρίου");
    			helper.setText("Αγοράστηκαν "+Tickets + " εισιτήρια με συνολική τιμή "+TotalPrice+"$. Τα κέρδη θα προστεθούν στο λογαριασμό σας στο τέλος του μήνα");
    			sender.send(message);

              CustomResponse res = new CustomResponse();
              res.setMessage("H αγορά ολοκληρώθηκε με επιτυχία. Δεν υπάρχουν άλλες διαθέσιμες θέσεις. Έχετε " + new_Fpoints + " Fpoints και " + finwallet + " € στο πορτοφόλι σας");
              return res;

          }
        	MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message,true);
            helper.setTo(mail);
			helper.setSubject("Aγορά Εισιτηρίου");
			helper.setText("Αγοράστηκαν "+Tickets + " εισιτήρια με συνολική τιμή "+TotalPrice+"$. Τα κέρδη θα προστεθούν στο λογαριασμό σας στο τέλος του μήνα");
			sender.send(message);
          CustomResponse res = new CustomResponse();
          res.setMessage("Η αγορά ολοκληρώθηκε με επιτυχία. Υπάρχουν ακόμα " + new_capacity + " διαθέσιμες θέσεις. Έχετε " + new_Fpoints + " Fpoints και " + finwallet + " € στο πορτοφόλι σας.");
          return res;
        }
        else{
          CustomResponse res = new CustomResponse();
          res.setMessage("Συγγνώμη. Δεν υπάρχουν διαθέσιμες θέσεις.");
          return res;
          }
    }

    @RequestMapping("/sendEmail")
    public  @ResponseBody  CustomResponse  home(@RequestParam String username) {
        try {
            sendEmail(username);
            CustomResponse res = new CustomResponse();
            res.setMessage("Το εισιτήριο στάλθηκε με email.");
            return res;

        }catch(Exception ex) {

        	CustomResponse res1 = new CustomResponse();
            res1.setMessage("Error in sending email: "+ex);
            return res1;



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

            PDFConfig.createPDF(fileName,firstName,lastName,eventname,Tickets,TotalPrice);

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
