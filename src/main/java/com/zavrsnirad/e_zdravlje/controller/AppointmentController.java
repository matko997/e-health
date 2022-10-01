package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.dto.AddAppointmentDto;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.service.AppointmentService;
import com.zavrsnirad.e_zdravlje.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
public class AppointmentController {

    private final AppointmentService appointmentService;
    private final UserService userService;

    public AppointmentController(AppointmentService appointmentService, UserService userService) {
        this.appointmentService = appointmentService;
        this.userService = userService;
    }

    @GetMapping("/sastanci")
    public String findAllLabTestsPaginated(@RequestParam(value = "stranica", required = false, defaultValue = "1") int pageNumber,
                                           @RequestParam(value = "velicina", required = false, defaultValue = "10") int size,
                                           @RequestParam(value = "filter", required = false) String keyword, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();

        Optional<User> userOptional = userService.findByEmail(email);

        if (Objects.isNull(keyword)) {
            if (userOptional.get().getRole().getName().equals("DOCTOR") || userOptional.get().getRole().getName().equals("ADMIN")) {
                model.addAttribute("appointments", appointmentService.findAppointmentsPaginated(pageNumber, size));
                return "appointment-index";
            } else {
                model.addAttribute("appointments", appointmentService.findAppointmentsPaginatedForPatient(pageNumber, size, userOptional.get()));
                return "appointment-index";
            }
        }
        if (userOptional.get().getRole().getName().equals("DOCTOR") || userOptional.get().getRole().getName().equals("ADMIN")) {
            model.addAttribute("appointments", appointmentService.findAppointmentsPaginatedFilterable(pageNumber, size, keyword));
            return "appointment-index";
        } else {
            model.addAttribute("appointments", appointmentService.findAppointmentsPaginatedAndFilterableForPatient(pageNumber, size, userOptional.get(), keyword));
            return "appointment-index";
        }
    }

    @PostMapping("/sastanci")
    public String addNewLabTest(AddAppointmentDto addAppointmentDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {


        appointmentService.addAppointment(addAppointmentDto);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Ups, došlo je do pogreške, molimo vas da pokušate ponovno");
            return "redirect:/sastanci";
        }
        redirectAttributes.addFlashAttribute("success", "Novi sastanak uspješno rezerviran");

        return "redirect:/sastanci";
    }
}