package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.AddAllergieDto;
import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.model.Allergie;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.AllergiesRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class AllergieService {
  private final AllergiesRepository allergiesRepository;
  private final UserRepository userRepository;

  public AllergieService(AllergiesRepository allergiesRepository, UserRepository userRepository) {
    this.allergiesRepository = allergiesRepository;
    this.userRepository = userRepository;
  }

  public Paged<Allergie> findAllergiesPaginated(int pageNumber, int size) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Allergie> allergiesPage = allergiesRepository.findAll(request);
    return new Paged<>(allergiesPage, Paging.of(allergiesPage.getTotalPages(), pageNumber, size));
  }

  public Paged<Allergie> findAllergiesPaginatedFilterable(
      int pageNumber, int size, String keyword) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Allergie> vaccinePage =
        allergiesRepository.findAllergiesPaginatedAndFilterable(request, keyword);
    return new Paged<>(vaccinePage, Paging.of(vaccinePage.getTotalPages(), pageNumber, size));
  }

  public Paged<Allergie> findAllergiesPaginatedForPatient(int pageNumber, int size, User user) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Allergie> allergiePage = allergiesRepository.findAllByPatient(user, request);
    return new Paged<>(allergiePage, Paging.of(allergiePage.getTotalPages(), pageNumber, size));
  }

  public Paged<Allergie> findAllergiesPaginatedAndFilterableForPatient(
      int pageNumber, int size, User user, String keyword) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Allergie> allergiePage =
        allergiesRepository.findAllByPatientFilterable(user, request, keyword);
    return new Paged<>(allergiePage, Paging.of(allergiePage.getTotalPages(), pageNumber, size));
  }

  public void addNewVaccine(AddAllergieDto addAllergieDto) {
    Optional<User> optionalPatient = userRepository.findById(addAllergieDto.getPatientId());

    Allergie allergie = new Allergie();
    allergie.setName(addAllergieDto.getName());
    allergie.setPatient(optionalPatient.get());
    ZoneId zone = ZoneId.of("Europe/Zagreb");
    allergie.setCreatedAt(LocalDateTime.now(zone));
    allergiesRepository.save(allergie);
  }

  public Optional<Allergie> getById(long id) {
    return allergiesRepository.findById(id);
  }

  public void editAllergie(Allergie allergie) {
    Optional<Allergie> allergieOptional = allergiesRepository.findById(allergie.getId());
    allergieOptional.get().setName(allergie.getName());
    allergiesRepository.save(allergieOptional.get());
  }

  public void deleteById(long id) {
    allergiesRepository.deleteById(id);
  }
}
