package gr.ntua.ece.softeng.controllers;

import gr.ntua.ece.softeng.entities.Parent;
import gr.ntua.ece.softeng.entities.Providers;
import gr.ntua.ece.softeng.entities.Role;
import gr.ntua.ece.softeng.entities.User;
import gr.ntua.ece.softeng.error.CustomResponse;
import gr.ntua.ece.softeng.repositories.ParentRepository;
import gr.ntua.ece.softeng.repositories.ProvidersRepository;
import gr.ntua.ece.softeng.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParentRepository parentRepository;

    @Autowired
    private ProvidersRepository providersRepository;

    @GetMapping(path="/user_info")
    @PreAuthorize("hasAnyAuthority('PARENT') or hasAuthority('PROVIDER') or hasAuthority('ADMIN')")
    public @ResponseBody CustomResponse userInfo(@RequestParam String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) {
            List<Role> roles;
            roles = user.getRoles();

            switch (roles.get(0).getName()) {
                case "PARENT": {
                    CustomResponse<Parent> cr = new CustomResponse<>();
                    cr.setMessage(parentRepository.findByUsername(username));
                    return cr;
                }
                case "PROVIDER": {
                    CustomResponse<Providers> cr = new CustomResponse<>();
                    cr.setMessage(providersRepository.findByUserName(username));
                    return cr;
                }
                default: {
                    CustomResponse<String> cr = new CustomResponse<>();
                    cr.setMessage("Something's wrong with providers' roles");
                    return cr;
                }
            }

        }
        else {
            CustomResponse<String> cr = new CustomResponse<>();
            cr.setMessage("Username does not exist in User");
            return cr;
        }
    }

}
