package com.zavrsnirad.e_zdravlje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterDto {
  @NotEmpty(message = "Molimo vas da unesete valjano ime")
  private String firstName;

  @NotEmpty(message = "Molimo vas da unesete valjano prezime")
  private String lastName;

  private String email;

  private String gender;

  @DateTimeFormat(pattern = "yyyy-MM-dd")
  private LocalDate birthDay;

  @Size(message = "Molimo vas da uneste lozinku s minimalno 8 znakova", min = 8)
  private String password;

  private String repeatPassword;
}
