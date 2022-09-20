package com.zavrsnirad.digitalhealth.dto;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
    @NotBlank(message = "Molimo vas unesite valjano ime")
    private String firstName;
    @NotBlank(message = "Molimo vas unesite valjano prezime")
    private String lastName;
    private String email;
    private LocalDate birthDay;
    @Size(message = "Molimo vas unesite valjan JMBG",min = 13,max = 13)
    private String jmbg;
    private String bloodType;
    private String rhFactor;
    @NotBlank(message = "Molimo vas unesite valjan grad")
    private String city;
    @NotBlank(message = "Molimo vas unesite valjanu adresu")
    private String address;
    @NotBlank(message = "Molimo vas unesite valjan poštanski broj")
    private String postalCode;
    @NotBlank(message = "Molimo vas unesite valjan broj telefona")
    private String phoneNumber;
    private String graduationUniversity;
    private LocalDate graduationDate;
    private String specialization;
    private LocalDate specializationDate;
}
