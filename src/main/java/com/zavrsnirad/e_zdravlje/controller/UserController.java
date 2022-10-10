package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.dto.UserProfileDto;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.service.UserService;
import com.zavrsnirad.e_zdravlje.util.Validator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Objects;
import java.util.Optional;

@Controller
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/racun")
  public String showUserProfile(Model model) {
    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    String email = userDetails.getUsername();

    Optional<User> optionalUser = userService.findByEmail(email);
    User user = optionalUser.get();

    UserProfileDto profileDto = userService.buildProfileDto(user);

    model.addAttribute("user", profileDto);

    return "update-user-profile";
  }

  @PostMapping(value = "/racun")
  public String updateUserProfile(
      @Valid @ModelAttribute("user") UserProfileDto userProfileDto,
      BindingResult bindingResult,
      RedirectAttributes redirectAttributes) {

    if (Objects.nonNull(userProfileDto.getJmbg())
        && !userProfileDto.getJmbg().equals("")
        && Validator.isInvalidJmbg(userProfileDto.getJmbg())) {
      bindingResult.rejectValue("jmbg", "jmbg.invalid");
    }

    if (bindingResult.hasErrors()) {
      redirectAttributes.addFlashAttribute(
          "error", "Došlo je do pogreške, molimo vas da pokušate ponovno");
      return "update-user-profile";
    }

    Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    UserDetails userDetails = (UserDetails) auth.getPrincipal();
    String email = userDetails.getUsername();

    Optional<User> user = userService.findByEmail(email);

    userService.saveUserProfile(user.get(), userProfileDto);
    redirectAttributes.addFlashAttribute("success", "Račun ažuriran uspješno");

    return "redirect:/racun";
  }
}
