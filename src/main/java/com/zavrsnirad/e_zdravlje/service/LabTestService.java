package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.AddLabTestDto;
import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.model.LabTest;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.LabTestRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class LabTestService {
    private final LabTestRepository labTestRepository;
    private final UserRepository userRepository;

    public LabTestService(LabTestRepository labTestRepository, UserRepository userRepository) {
        this.labTestRepository = labTestRepository;
        this.userRepository = userRepository;
    }

    public Paged<LabTest> findLabTestsPaginated(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LabTest> labTestPage = labTestRepository.findAll(request);
        return new Paged<>(labTestPage, Paging.of(labTestPage.getTotalPages(), pageNumber, size));
    }

    public Paged<LabTest> findLabTestsPaginatedFilterable(int pageNumber, int size, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LabTest> labTestPage = labTestRepository.findLabTestPaginatedAndFilterable(request, keyword);
        return new Paged<>(labTestPage, Paging.of(labTestPage.getTotalPages(), pageNumber, size));
    }

    public Paged<LabTest> findLabTestsPaginatedForPatient(int pageNumber, int size, User user) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LabTest> labTestPage = labTestRepository.findAllByPatient(user, request);
        return new Paged<>(labTestPage, Paging.of(labTestPage.getTotalPages(), pageNumber, size));
    }

    public Paged<LabTest> findLabTestsPaginatedAndFilterableForPatient(int pageNumber, int size, User user, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<LabTest> labTestPage = labTestRepository.findAllByPatientFilterable(user, request, keyword);
        return new Paged<>(labTestPage, Paging.of(labTestPage.getTotalPages(), pageNumber, size));
    }

    public void addLabTest(AddLabTestDto addLabTestDto, User doctor) {
        Optional<User> optionalPatient = userRepository.findById(addLabTestDto.getPatientId());

        LabTest labTest = new LabTest();
        labTest.setCholesterol(addLabTestDto.getCholesterol());
        labTest.setGlucose(addLabTestDto.getGlucose());
        labTest.setCreatinine(addLabTestDto.getCreatinine());
        labTest.setHemoglobin(addLabTestDto.getHemoglobin());
        labTest.setALP(addLabTestDto.getALP());
        labTest.setTriglyceride(addLabTestDto.getTriglyceride());
        labTest.setUrea(addLabTestDto.getUrea());
        labTest.setPatient(optionalPatient.get());
        labTest.setDoctor(doctor);
        ZoneId zone = ZoneId.of("Europe/Zagreb");
        labTest.setCreatedAt(LocalDateTime.now(zone));
        labTestRepository.save(labTest);
    }

    public Optional<LabTest> findById(long id) {
        return labTestRepository.findById(id);
    }

    public void deleteById(long id) {
        labTestRepository.deleteById(id);
    }

    public void editLabTest(LabTest labTest) {
        Optional<LabTest> labTestOptional = labTestRepository.findById(labTest.getId());

        labTestOptional.get().setUrea(labTest.getUrea());
        labTestOptional.get().setCreatinine(labTest.getCreatinine());
        labTestOptional.get().setGlucose(labTest.getGlucose());
        labTestOptional.get().setALP(labTest.getALP());
        labTestOptional.get().setTriglyceride(labTest.getTriglyceride());
        labTestOptional.get().setHemoglobin(labTest.getHemoglobin());
        labTestOptional.get().setCholesterol(labTest.getCholesterol());

        labTestRepository.save(labTestOptional.get());
    }
}
