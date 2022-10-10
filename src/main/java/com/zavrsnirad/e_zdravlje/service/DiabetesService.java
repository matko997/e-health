package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.DiabetesDto;
import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.model.Diabetes;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.DiabetesRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class DiabetesService {
  private final DiabetesRepository diabetesRepository;
  private final UserRepository userRepository;

  public DiabetesService(DiabetesRepository diabetesRepository, UserRepository userRepository) {
    this.diabetesRepository = diabetesRepository;
    this.userRepository = userRepository;
  }

  public Paged<Diabetes> findDiabetesPaginated(int pageNumber, int size) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Diabetes> diabetesPage = diabetesRepository.findAll(request);
    return new Paged<>(diabetesPage, Paging.of(diabetesPage.getTotalPages(), pageNumber, size));
  }

  public Paged<Diabetes> findDiabetesPaginatedFilterable(int pageNumber, int size, String keyword) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Diabetes> diabetesPage =
        diabetesRepository.findDiabetesPaginatedAndFilterable(request, keyword);
    return new Paged<>(diabetesPage, Paging.of(diabetesPage.getTotalPages(), pageNumber, size));
  }

  public Paged<Diabetes> findDiabetesPaginatedForPatient(int pageNumber, int size, User user) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Diabetes> diabetesPage = diabetesRepository.findAllByPatient(user, request);
    return new Paged<>(diabetesPage, Paging.of(diabetesPage.getTotalPages(), pageNumber, size));
  }

  public Paged<Diabetes> findDiabetesPaginatedAndFilterableForPatient(
      int pageNumber, int size, User user, String keyword) {
    PageRequest request = PageRequest.of(pageNumber - 1, size);
    Page<Diabetes> diabetesPage =
        diabetesRepository.findAllByPatientFilterable(user, request, keyword);
    return new Paged<>(diabetesPage, Paging.of(diabetesPage.getTotalPages(), pageNumber, size));
  }

  public void deleteById(long id) {
    diabetesRepository.deleteById(id);
  }

  public void addNewDiabetes(DiabetesDto diabetesDto) {
    Optional<User> optionalPatient = userRepository.findById(diabetesDto.getPatientId());

    Diabetes diabetes = new Diabetes();
    diabetes.setValue(diabetesDto.getValue());
    diabetes.setPatient(optionalPatient.get());
    ZoneId zone = ZoneId.of("Europe/Zagreb");
    diabetes.setCreatedAt(LocalDateTime.now(zone));
    diabetesRepository.save(diabetes);
  }

  public void editDiabetes(Diabetes diabetes) {
    Optional<Diabetes> diabetesOptional = diabetesRepository.findById(diabetes.getId());
    diabetesOptional.get().setValue(diabetes.getValue());
    diabetesRepository.save(diabetesOptional.get());
  }

  public Optional<Diabetes> getById(long id) {
    return diabetesRepository.findById(id);
  }
}
