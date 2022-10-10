package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.dto.UserProfileDto;
import com.zavrsnirad.e_zdravlje.dto.UserRegisterDto;
import com.zavrsnirad.e_zdravlje.model.Role;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.RoleRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

  public UserService(
      UserRepository userRepository,
      RoleRepository roleRepository,
      PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.roleRepository = roleRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public Optional<User> findById(long id) {
    return userRepository.findById(id);
  }

  public Optional<User> findByEmail(String email) {
    return userRepository.findByEmail(email);
  }

  public void registerNewDoctor(User user) {
    Optional<Role> doctorRole = roleRepository.findByName("DOCTOR");
    doctorRole.ifPresent(user::setRole);
    user.setPassword(passwordEncoder.encode(user.getPassword()));
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

  public void registerNewPatient(UserRegisterDto userRegisterDto) {
    User user = new User();

    user.setFirstName(userRegisterDto.getFirstName());
    user.setLastName(userRegisterDto.getLastName());
    user.setEmail(userRegisterDto.getEmail());
    user.setBirthDay(userRegisterDto.getBirthDay());
    user.setGender(userRegisterDto.getGender());
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
        .graduationUniversity(
            Objects.isNull(user.getGraduationUniversity()) ? "" : user.getGraduationUniversity())
        .graduationYear(user.getGraduationYear())
        .specialization(user.getSpecialization())
        .specializationYear(user.getSpecializationYear())
        .build();
  }

  public Paged<User> findDoctorsPaginated(int pageNumber, int size) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<User> doctorPage = userRepository.findAllDoctors("DOCTOR", request);
    return new Paged<>(doctorPage, Paging.of(doctorPage.getTotalPages(), pageNumber, size));
  }

  public Paged<User> findDoctorsPaginatedAndFiltered(int pageNumber, int size, String keyword) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<User> doctorPage = userRepository.findAllDoctorsFilterable("DOCTOR", keyword, request);
    return new Paged<>(doctorPage, Paging.of(doctorPage.getTotalPages(), pageNumber, size));
  }

  public Paged<User> findPatientsPaginated(int pageNumber, int size) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<User> patientPage = userRepository.findAllDoctors("PATIENT", request);
    return new Paged<>(patientPage, Paging.of(patientPage.getTotalPages(), pageNumber, size));
  }

  public Paged<User> findPatientsPaginatedAndFiltered(int pageNumber, int size, String keyword) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<User> doctorPage = userRepository.findAllDoctorsFilterable("PATIENT", keyword, request);
    return new Paged<>(doctorPage, Paging.of(doctorPage.getTotalPages(), pageNumber, size));
  }

  public void editUser(User user) {
    Optional<User> optionalUser = userRepository.findById(user.getId());
    User doctor = optionalUser.get();

    doctor.setFirstName(user.getFirstName());
    doctor.setLastName(user.getLastName());
    doctor.setCity(user.getCity());
    doctor.setAddress(user.getAddress());
    doctor.setPhoneNumber(user.getPhoneNumber());
    doctor.setSpecialization(user.getSpecialization());
    doctor.setSpecializationYear(user.getSpecializationYear());
    doctor.setGraduationUniversity(user.getGraduationUniversity());
    doctor.setGraduationYear(user.getGraduationYear());

    if (Objects.nonNull(user.getGender())) {
      doctor.setGender(user.getGender());
    }
    if (Objects.nonNull(user.getEmail())) {
      doctor.setEmail(user.getEmail());
    }

    userRepository.save(doctor);
  }

  public List<User> findAllPatients() {
    return userRepository.findAllByRoleName("PATIENT");
  }

  public List<User> findAllDoctors() {
    return userRepository.findAllByRoleName("DOCTOR");
  }
}
