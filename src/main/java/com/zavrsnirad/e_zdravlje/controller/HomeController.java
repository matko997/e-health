package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.dto.UserRegisterDto;
import com.zavrsnirad.e_zdravlje.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String showHomePage() {
        return "index";
    }

    @GetMapping("/naslovnica")
    public String showDashboard() {
        return "layout";
    }

    @GetMapping("/registracija")
    public String showSignupForm(Model model) {
        model.addAttribute("userRegisterDto", new UserRegisterDto());
        return "/register";
    }
}
