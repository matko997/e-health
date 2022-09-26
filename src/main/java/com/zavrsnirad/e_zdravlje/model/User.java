package com.zavrsnirad.e_zdravlje.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zavrsnirad.e_zdravlje.model.enumeration.Gender;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "user")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "email")
    private String email;
    @Column(name = "blood_type")
    private String bloodType;
    @Column(name = "password")
    private String password;
    @Column(name = "birth_date")
    private LocalDate birthDay;
    @Column(name = "gender")
    private String gender;
    @Column(name = "jmbg")
    private String jmbg;
    @Column(name = "city")
    private String city;
    @Column(name = "address")
    private String address;
    @Column(name = "postal_code")
    private String postalCode;
    @Column(name = "phone_number")
    private String phoneNumber;
    @Column(name = "graduation_university")
    private String graduationUniversity;
    @Column(name = "graduation_year")
    private Integer graduationYear;
    @Column(name = "specialization")
    private String specialization;
    @Column(name = "specialization_year")
    private Integer specializationYear;
    @CreationTimestamp
    private LocalDateTime createdAt;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "role_id")
    private Role role;
}
