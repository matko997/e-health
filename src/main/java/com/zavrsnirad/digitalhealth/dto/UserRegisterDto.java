package com.zavrsnirad.digitalhealth.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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

    @Size(message = "Molimo vas da uneste lozinku s minimalno 8 znakova", min = 8)
    private String password;

    private String repeatPassword;
}
