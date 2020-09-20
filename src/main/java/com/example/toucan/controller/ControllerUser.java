package com.example.toucan.controller;

import com.example.toucan.exception.UsernameAlreadyTakenException;
import com.example.toucan.model.dto.DtoUserSignUp;
import com.example.toucan.service.ServiceUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("toucan/user")
public class ControllerUser {

    private final ServiceUser serviceUser;

    @Autowired
    public ControllerUser(ServiceUser serviceUser) {
        this.serviceUser = serviceUser;
    }

    @PostMapping("/signup")
    public void signUp(@Valid @NonNull @RequestBody DtoUserSignUp dtoUserSignUp){
        try {
            serviceUser.createUser(dtoUserSignUp.getUsername(),
                                   dtoUserSignUp.getPassword(),
                                   dtoUserSignUp.getRole());
        } catch (UsernameAlreadyTakenException e) {
            //todo zrób aby do usera byłą wysyłanda wiadomość gy złapie wyjątek
            e.printStackTrace();
        }
    }

    //@DeleteMapping("/delete")
    //public void delete(Principal principal)


}
