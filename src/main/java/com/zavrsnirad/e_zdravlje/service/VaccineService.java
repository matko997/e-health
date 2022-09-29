package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.AddVaccineDto;
import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.model.Vaccine;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import com.zavrsnirad.e_zdravlje.repository.VaccineRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class VaccineService {
    private final VaccineRepository vaccineRepository;
    private final UserRepository userRepository;

    public VaccineService(VaccineRepository vaccineRepository, UserRepository userRepository) {
        this.vaccineRepository = vaccineRepository;
        this.userRepository = userRepository;
    }

    public List<Vaccine> findAllVaccine() {
        return vaccineRepository.findAll();
    }

    public Paged<Vaccine> findVaccinePaginated(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Vaccine> vaccinePage = vaccineRepository.findAll(request);
        return new Paged<>(vaccinePage, Paging.of(vaccinePage.getTotalPages(), pageNumber, size));
    }

    public Paged<Vaccine> findVaccinePaginatedForPatient(int pageNumber, int size, User user) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Vaccine> vaccinePage = vaccineRepository.findAllByPatient(user, request);
        return new Paged<>(vaccinePage, Paging.of(vaccinePage.getTotalPages(), pageNumber, size));
    }

    public Paged<Vaccine> findVaccinePaginatedAndFilterableForPatient(int pageNumber, int size, User user,String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Vaccine> vaccinePage = vaccineRepository.findAllByPatientFilterable(user, request, keyword);
        return new Paged<>(vaccinePage, Paging.of(vaccinePage.getTotalPages(), pageNumber, size));
    }

    public Paged<Vaccine> findVaccinePaginatedAndFiltered(int pageNumber, int size, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Vaccine> vaccinePage = vaccineRepository.findVaccinePaginatedAndFilterable(request, keyword);
        return new Paged<>(vaccinePage, Paging.of(vaccinePage.getTotalPages(), pageNumber, size));
    }

    public void deleteById(long id) {
        vaccineRepository.deleteById(id);
    }

    public void addNewVaccine(User user, AddVaccineDto addVaccineDto) {
        Optional<User> optionalPatient = userRepository.findById(addVaccineDto.getPatientId());

        Vaccine vaccine = new Vaccine();
        vaccine.setName(addVaccineDto.getName());
        vaccine.setDoctor(user);
        vaccine.setPatient(optionalPatient.get());
        ZoneId zone = ZoneId.of("Europe/Zagreb");
        vaccine.setCreatedAt(LocalDateTime.now(zone));
        vaccineRepository.save(vaccine);
    }

    public void editVaccine(Vaccine vaccine) {
        Optional<Vaccine> vaccineOptional = vaccineRepository.findById(vaccine.getId());
        vaccineOptional.get().setName(vaccine.getName());
        vaccineRepository.save(vaccineOptional.get());
    }

    public Optional<Vaccine> getById(long id) {
        return vaccineRepository.findById(id);
    }
}
