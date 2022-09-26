package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import com.zavrsnirad.e_zdravlje.service.UserService;
import com.zavrsnirad.e_zdravlje.util.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
public class DoctorController {

    private final UserService userService;
    private final UserRepository userRepository;

    public DoctorController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/doktori")
    public String addNewDoctor(User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Optional<User> existingUser = userService.findByEmail(user.getEmail());

        if (Validator.isInUse(existingUser)) {
            bindingResult.rejectValue("email",
                    "email.taken");
            redirectAttributes.addFlashAttribute("error", "Email se već koristi...");
        }
        if (Validator.isInvalidEmail(user.getEmail())) {
            bindingResult.rejectValue("email",
                    "email.invalid");
            redirectAttributes.addFlashAttribute("error", "Email nije valjan...");

        }
        if (Validator.isInvalidPassword(user.getPassword())) {
            bindingResult.rejectValue("password", "password.mismatch");
            redirectAttributes.addFlashAttribute("error", "Password mora sadržavati minimalno 8 znakova");
        }

        if (Objects.isNull(user.getGraduationYear())) {
            bindingResult.rejectValue("graduationYear", "graduationYear");
            redirectAttributes.addFlashAttribute("error", "Molimo unesite valjanu vrijednost za godinu završetka studija");
        }

        if (bindingResult.hasErrors()) {
            return "redirect:/doktori";
        }

        userService.registerNewDoctor(user);
        redirectAttributes.addFlashAttribute("success", "Novi doktor dodan uspješno");

        return "redirect:/doktori";
    }

    @GetMapping("/doktori")
    public String findAllDoctors(Model model) {
        model.addAttribute("doctors", userService.findAllDoctors());

        return "doctor-index";
    }

    @RequestMapping(value = "/doktor")
    @ResponseBody
    public Optional<User> findOneDoctor(long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/doktori/uredi", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateDoctor(User user, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Ups, došlo je do pogreške, molimo vas da pokušate ponovno");
            return "redirect:/doktori";
        }
        userService.editUser(user);
        redirectAttributes.addFlashAttribute("success", "Doktor uspješno ažuriran");
        return "redirect:/doktori";
    }
}
