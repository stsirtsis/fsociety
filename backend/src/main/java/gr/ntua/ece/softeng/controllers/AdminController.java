package gr.ntua.ece.softeng.controllers;

import java.security.SecureRandom;
import java.util.List;
import java.util.Set;

import javax.mail.internet.MimeMessage;

import gr.ntua.ece.softeng.entities.*;
import gr.ntua.ece.softeng.error.CustomResponse;
import gr.ntua.ece.softeng.repositories.EventRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;

@RestController
@RequestMapping(path = "/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class AdminController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProvidersRepository providersRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private JavaMailSender sender;

    @GetMapping(path = "/all_users")
    public @ResponseBody
    Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/all_providers")
    public @ResponseBody
    Iterable<Providers> getAllProviders() {
        return providersRepository.findAll();
    }

    @GetMapping(path = "/all_parents")
    public @ResponseBody
    List<Parent> getAllParents() {
        return parentRepository.findAll();
    }




    @GetMapping(path = "/lock_user")
    @Transactional
    public @ResponseBody
    CustomResponse<String> lockUser(@RequestParam String username) {

        CustomResponse cr = new CustomResponse<>();
        cr.setMessage("");

        User user = userRepository.findByUsername(username);
        if (user == null) {
            cr.setMessage("user does not exist in table user");
            return cr;
        }

        user.getRoles().forEach(role -> {
            if (role.getName().equals("BLOCKED")) {
                cr.setMessage("user is already blocked");
               
            }

        });
        
        if(cr.getMessage()!="") return cr;

      

        String role = user.getRoles().get(0).getName();

        switch (role) {
            case "PARENT":
                user.getRoles().add(new Role("BLOCKED"));
                userRepository.save(user);
                break;
            case "PROVIDER":
                user.getRoles().add(new Role("BLOCKED"));
                userRepository.save(user);
                break;
            default:
                cr.setMessage("Not expected user role");
                break;
        }


        cr.setMessage("ok: username " + role);
        return cr;
    }

    @GetMapping(path = "/unlock_user")
    @Transactional
    public CustomResponse<String> unlockUser(String username) {
        CustomResponse cr = new CustomResponse<>();

        User user = userRepository.findByUsername(username);
        if (user != null) {
            List<Role> roles = user.getRoles();
            Role role = roles.get(roles.size() - 1);
            if (!role.getName().equals("BLOCKED")) {
                cr.setMessage("User not locked: " + role.getName());
                return cr;
            }

            roles.remove(roles.size() - 1);
            userRepository.save(user);
            cr.setMessage("ok");
            return cr;
        } else
            cr.setMessage("user does not exist in the table");

        return cr;
    }




}
