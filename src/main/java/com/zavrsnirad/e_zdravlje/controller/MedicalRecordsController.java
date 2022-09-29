package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.dto.AddVaccineDto;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.model.Vaccine;
import com.zavrsnirad.e_zdravlje.service.UserService;
import com.zavrsnirad.e_zdravlje.service.VaccineService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;
import java.util.Optional;

@Controller
public class MedicalRecordsController {

    private final VaccineService vaccineService;
    private final UserService userService;


    public MedicalRecordsController(VaccineService vaccineService, UserService userService) {
        this.vaccineService = vaccineService;
        this.userService = userService;
    }

    @GetMapping("/cjepiva")
    public String findVaccinePaginated(@RequestParam(value = "stranica", required = false, defaultValue = "1") int pageNumber,
                                       @RequestParam(value = "velicina", required = false, defaultValue = "10") int size,
                                       @RequestParam(value = "filter", required = false) String keyword, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();

        Optional<User> userOptional = userService.findByEmail(email);

        if (Objects.isNull(keyword)) {
            if (userOptional.get().getRole().getName().equals("DOCTOR") || userOptional.get().getRole().getName().equals("ADMIN")) {
                model.addAttribute("vaccines", vaccineService.findVaccinePaginated(pageNumber, size));
                return "vaccine-index";
            } else {
                model.addAttribute("vaccines", vaccineService.findVaccinePaginatedForPatient(pageNumber, size, userOptional.get()));
                return "vaccine-index";
            }
        }
        if (userOptional.get().getRole().getName().equals("DOCTOR") || userOptional.get().getRole().getName().equals("ADMIN")) {
            model.addAttribute("vaccines", vaccineService.findVaccinePaginatedAndFiltered(pageNumber, size, keyword));
            return "vaccine-index";
        } else {
            model.addAttribute("vaccines", vaccineService.findVaccinePaginatedAndFilterableForPatient(pageNumber, size, userOptional.get(), keyword));
            return "vaccine-index";
        }

    }

    @RequestMapping(value = "/cjepiva/obrisi", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String deleteVaccine(long id, RedirectAttributes redirectAttributes) {
        try {
            vaccineService.deleteById(id);
            redirectAttributes.addFlashAttribute("success", "Zapis o cijepljenju uspješno obrisan.");
            return "redirect:/cjepiva";

        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Ups, došlo je do pogreške, molimo vas da pokušate ponovno");
            return "redirect:/cjepiva";
        }
    }

    @PostMapping("/cjepiva")
    public String addNewVaccine(AddVaccineDto addVaccineDto, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();

        Optional<User> doctor = userService.findByEmail(email);

        vaccineService.addNewVaccine(doctor.get(), addVaccineDto);

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("success", "Ups, došlo je do pogreške, molimo vas da pokušate ponovno");
            return "redirect:/cjepiva";
        }
        redirectAttributes.addFlashAttribute("success", "Novi zapis o cijepljenju uspješno dodan");

        return "redirect:/cjepiva";
    }

    @RequestMapping(value = "/cjepiva/uredi", method = {RequestMethod.GET, RequestMethod.PUT})
    public String updateDoctor(Vaccine vaccine, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("error", "Ups, došlo je do pogreške, molimo vas da pokušate ponovno");
            return "redirect:/cjepiva";
        }
        vaccineService.editVaccine(vaccine);
        redirectAttributes.addFlashAttribute("success", "Cjepivo uspješno ažurirano");
        return "redirect:/cjepiva";
    }

    @RequestMapping(value = "/cjepivo")
    @ResponseBody
    public Optional<Vaccine> findOneVaccine(long id) {
        return vaccineService.getById(id);
    }

    @GetMapping("/alergije")
    public String findAllergiesPaginated(@RequestParam(value = "stranica", required = false, defaultValue = "1") int pageNumber,
                                         @RequestParam(value = "velicina", required = false, defaultValue = "10") int size,
                                         @RequestParam(value = "filter", required = false) String keyword, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String email = userDetails.getUsername();

        Optional<User> userOptional = userService.findByEmail(email);

        if (Objects.isNull(keyword)) {
            if (userOptional.get().getRole().getName().equals("DOCTOR") || userOptional.get().getRole().getName().equals("ADMIN")) {
                model.addAttribute("vaccines", vaccineService.findVaccinePaginated(pageNumber, size));
                return "vaccine-index";
            } else {
                model.addAttribute("vaccines", vaccineService.findVaccinePaginatedForPatient(pageNumber, size, userOptional.get()));
                return "vaccine-index";
            }
        }
        if (userOptional.get().getRole().getName().equals("DOCTOR") || userOptional.get().getRole().getName().equals("ADMIN")) {
            model.addAttribute("vaccines", vaccineService.findVaccinePaginatedAndFiltered(pageNumber, size, keyword));
            return "vaccine-index";
        } else {
            model.addAttribute("vaccines", vaccineService.findVaccinePaginatedAndFilterableForPatient(pageNumber, size, userOptional.get(), keyword));
            return "vaccine-index";
        }

    }
}
