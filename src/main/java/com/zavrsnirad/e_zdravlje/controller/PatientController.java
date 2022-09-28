package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
public class PatientController {

    private final UserService userService;

    public PatientController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/pacijenti")
    public String findPatientsPaginated(@RequestParam(value = "stranica", required = false, defaultValue = "1") int pageNumber,
                                        @RequestParam(value = "velicina", required = false, defaultValue = "10") int size,
                                        @RequestParam(value = "filter", required = false) String keyword, Model model) {
        if (Objects.isNull(keyword)) {
            model.addAttribute("patients", userService.findPatientsPaginated(pageNumber, size));
            return "patient-index";
        }
        model.addAttribute("patients", userService.findPatientsPaginatedAndFiltered(pageNumber, size, keyword));
        return "patient-index";
    }

    @RequestMapping(value = "/pacijenti/obrisi", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deletePatient(long id, RedirectAttributes redirectAttributes) {
        try {
            userService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Pacijent uspješno obrisan.");
            return "redirect:/pacijenti";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ups, došlo je do pogreške, molimo vas da pokušate ponovno");
            return "redirect:/pacijenti";
        }
    }

    @RequestMapping(value = "/pacijent")
    @ResponseBody
    public Optional<User> findOneDoctor(long id) {
        return userService.findById(id);
    }

    @RequestMapping(value = "/pacijenti/json")
    @ResponseBody
    public List<User> findAllPatients() {
        return userService.findAllPatients();
    }
}
