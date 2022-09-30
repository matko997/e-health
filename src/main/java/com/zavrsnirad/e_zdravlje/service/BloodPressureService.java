package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.BloodPressureDto;
import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.model.BloodPressure;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.BloodPressureRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class BloodPressureService {
    private final BloodPressureRepository bloodPressureRepository;
    private final UserRepository userRepository;

    public BloodPressureService(BloodPressureRepository bloodPressureRepository, UserRepository userRepository) {
        this.bloodPressureRepository = bloodPressureRepository;
        this.userRepository = userRepository;
    }

    public Paged<BloodPressure> findBloodPressuresPaginated(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<BloodPressure> bloodPressurePage = bloodPressureRepository.findAll(request);
        return new Paged<>(bloodPressurePage, Paging.of(bloodPressurePage.getTotalPages(), pageNumber, size));
    }

    public Paged<BloodPressure> findBloodPressuresPaginatedFilterable(int pageNumber, int size, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<BloodPressure> bloodPressurePage = bloodPressureRepository.findBloodPressuresPaginatedAndFilterable(request, keyword);
        return new Paged<>(bloodPressurePage, Paging.of(bloodPressurePage.getTotalPages(), pageNumber, size));
    }

    public Paged<BloodPressure> findBloodPressuresPaginatedForPatient(int pageNumber, int size, User user) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<BloodPressure> bloodPressurePage = bloodPressureRepository.findAllByPatient(user, request);
        return new Paged<>(bloodPressurePage, Paging.of(bloodPressurePage.getTotalPages(), pageNumber, size));
    }

    public Paged<BloodPressure> findBloodPressuresPaginatedAndFilterableForPatient(int pageNumber, int size, User user, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<BloodPressure> bloodPressurePage = bloodPressureRepository.findAllByPatientFilterable(user, request, keyword);
        return new Paged<>(bloodPressurePage, Paging.of(bloodPressurePage.getTotalPages(), pageNumber, size));
    }

    public void addNewBloodPressure(BloodPressureDto bloodPressureDto) {
        Optional<User> optionalPatient = userRepository.findById(bloodPressureDto.getPatientId());

        BloodPressure bloodPressure = new BloodPressure();
        bloodPressure.setLowerValue(bloodPressureDto.getLowerValue());
        bloodPressure.setUpperValue(bloodPressureDto.getUpperValue());
        bloodPressure.setPatient(optionalPatient.get());
        bloodPressure.setPulse(bloodPressureDto.getPulse());
        ZoneId zone = ZoneId.of("Europe/Zagreb");
        bloodPressure.setCreatedAt(LocalDateTime.now(zone));
        bloodPressureRepository.save(bloodPressure);
    }

    public void deleteById(long id) {
        bloodPressureRepository.deleteById(id);
    }

    public void editBloodPressure(BloodPressure bloodPressure) {
        Optional<BloodPressure> bloodPressureOptional = bloodPressureRepository.findById(bloodPressure.getId());
        bloodPressureOptional.get().setLowerValue(bloodPressure.getLowerValue());
        bloodPressureOptional.get().setUpperValue(bloodPressure.getUpperValue());
        bloodPressureOptional.get().setPulse(bloodPressure.getPulse());
        bloodPressureRepository.save(bloodPressureOptional.get());
    }

    public Optional<BloodPressure> getById(long id) {
        return bloodPressureRepository.findById(id);
    }
}
