package com.zavrsnirad.digitalhealth.controller;

import com.zavrsnirad.digitalhealth.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {

    private final UserService userService;

    public DoctorController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/doktori")
    public String findAllDoctors(Model model) {
        model.addAttribute("doctors", userService.findAllDoctors());

        return "doctor-index";
    }
}
