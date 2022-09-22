package com.zavrsnirad.e_zdravlje.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserProfileDto {
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate birthDay;
    private String jmbg;
    private String bloodType;
    private String city;
    private String address;
    private String postalCode;
    private String phoneNumber;
    private String graduationUniversity;
    private Integer graduationYear;
    private String specialization;
    private Integer specializationYear;
}
