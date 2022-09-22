package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.UserProfileDto;
import com.zavrsnirad.e_zdravlje.dto.UserRegisterDto;
import com.zavrsnirad.e_zdravlje.model.Role;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.model.enumeration.Gender;
import com.zavrsnirad.e_zdravlje.repository.RoleRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import javassist.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(long id) throws NotFoundException {
        return userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with id " + id + " not found"));
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public void saveUserProfile(User user, UserProfileDto userProfileDto) {
        user.setFirstName(userProfileDto.getFirstName());
        user.setLastName(userProfileDto.getLastName());
        user.setAddress(userProfileDto.getAddress());
        user.setCity(userProfileDto.getCity());
        user.setJmbg(userProfileDto.getJmbg());
        user.setPhoneNumber(userProfileDto.getPhoneNumber());
        user.setPostalCode(userProfileDto.getPostalCode());
        user.setBloodType(userProfileDto.getBloodType());
        user.setGraduationUniversity(userProfileDto.getGraduationUniversity());
        user.setGraduationYear(userProfileDto.getGraduationYear());
        user.setSpecialization(userProfileDto.getSpecialization());
        user.setSpecializationYear(userProfileDto.getSpecializationYear());
        userRepository.save(user);
    }

    public void registerNewUser(UserRegisterDto userRegisterDto) {

        User user = new User();
        Gender gender;
        if (userRegisterDto.getGender().equalsIgnoreCase("male")) {
            gender = Gender.M;
        } else {
            gender = Gender.Z;
        }

        user.setFirstName(userRegisterDto.getFirstName());
        user.setLastName(userRegisterDto.getLastName());
        user.setEmail(userRegisterDto.getEmail());
        user.setBirthDay(userRegisterDto.getBirthDay());
        user.setGender(gender);
//        encrypt the password using spring security
        user.setPassword(passwordEncoder.encode(userRegisterDto.getPassword()));

        Optional<Role> patientRole = roleRepository.findByName("PATIENT");
        patientRole.ifPresent(user::setRole);
        userRepository.save(user);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public UserProfileDto buildProfileDto(User user) {
        return UserProfileDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .jmbg(Objects.isNull(user.getJmbg()) ? "" : user.getJmbg())
                .birthDay(user.getBirthDay())
                .city(Objects.isNull(user.getCity()) ? "" : user.getCity())
                .bloodType(Objects.isNull(user.getBloodType()) ? "" : user.getBloodType())
                .address(Objects.isNull(user.getAddress()) ? "" : user.getAddress())
                .postalCode(Objects.isNull(user.getPostalCode()) ? "" : user.getPostalCode())
                .phoneNumber(Objects.isNull(user.getPhoneNumber()) ? "" : user.getPhoneNumber())
                .graduationUniversity(Objects.isNull(user.getGraduationUniversity()) ? "" : user.getGraduationUniversity())
                .graduationYear(user.getGraduationYear())
                .specialization(user.getSpecialization())
                .specializationYear(user.getSpecializationYear())
                .build();
    }

    public List<User> findAllDoctors() {
        return userRepository.findAllByRole("DOCTOR");
    }

}
