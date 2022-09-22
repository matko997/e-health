package com.zavrsnirad.digitalhealth.controller;

import com.zavrsnirad.digitalhealth.dto.UserRegisterDto;
import com.zavrsnirad.digitalhealth.model.User;
import com.zavrsnirad.digitalhealth.service.UserService;
import com.zavrsnirad.digitalhealth.util.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthController {

    private final UserService userService;


    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/registracija")
    public String registerUser(@Valid UserRegisterDto userRegisterDto, BindingResult bindingResult) {

        Optional<User> existingUser = userService.findByEmail(userRegisterDto.getEmail());

        if (Validator.isInUse(existingUser)) {
            bindingResult.rejectValue("email",
                    "email.taken");
        }
        if (!Validator.validEmail(userRegisterDto.getEmail())) {
            bindingResult.rejectValue("email",
                    "email.invalid");
        }
        if (!userRegisterDto.getPassword().equals(userRegisterDto.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword",
                    "password.mismatch");
        }

        if (bindingResult.hasErrors()) {
            return "register";
        }

        userService.registerNewUser(userRegisterDto);
        return "redirect:/naslovnica";
    }

    @GetMapping("/prijava")
    public String loginUser() {
        return "index";
    }

}
