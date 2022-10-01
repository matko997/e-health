package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.AddAppointmentDto;
import com.zavrsnirad.e_zdravlje.dto.Paged;
import com.zavrsnirad.e_zdravlje.dto.Paging;
import com.zavrsnirad.e_zdravlje.model.Appointment;
import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.repository.AppointmentRepository;
import com.zavrsnirad.e_zdravlje.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;

    public AppointmentService(AppointmentRepository appointmentRepository, UserRepository userRepository) {
        this.appointmentRepository = appointmentRepository;
        this.userRepository = userRepository;
    }

    public Paged<Appointment> findAppointmentsPaginated(int pageNumber, int size) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Appointment> appointmentPage = appointmentRepository.findAll(request);
        return new Paged<>(appointmentPage, Paging.of(appointmentPage.getTotalPages(), pageNumber, size));
    }

    public Paged<Appointment> findAppointmentsPaginatedFilterable(int pageNumber, int size, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Appointment> appointmentPage = appointmentRepository.findAppointmentPaginatedAndFilterable(request, keyword);
        return new Paged<>(appointmentPage, Paging.of(appointmentPage.getTotalPages(), pageNumber, size));
    }

    public Paged<Appointment> findAppointmentsPaginatedForPatient(int pageNumber, int size, User user) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Appointment> appointmentPage = appointmentRepository.findAllByPatient(user, request);
        return new Paged<>(appointmentPage, Paging.of(appointmentPage.getTotalPages(), pageNumber, size));
    }

    public Paged<Appointment> findAppointmentsPaginatedAndFilterableForPatient(int pageNumber, int size, User user, String keyword) {
        PageRequest request = PageRequest.of(pageNumber - 1, size);
        Page<Appointment> appointmentPage = appointmentRepository.findAllByPatientFilterable(user, request, keyword);
        return new Paged<>(appointmentPage, Paging.of(appointmentPage.getTotalPages(), pageNumber, size));
    }

    public void addAppointment(AddAppointmentDto addAppointmentDto) {
        Optional<User> optionalPatient = userRepository.findById(addAppointmentDto.getPatientId());
        Optional<User> optionalDoctor = userRepository.findById(addAppointmentDto.getDoctorId());

        Appointment appointment = new Appointment();

        appointment.setDate(addAppointmentDto.getDate());
        appointment.setDoctor(optionalDoctor.get());
        appointment.setPatient(optionalPatient.get());
        appointment.setReason(addAppointmentDto.getReason());
        ZoneId zone = ZoneId.of("Europe/Zagreb");
        appointment.setCreatedAt(LocalDateTime.now(zone));
        appointmentRepository.save(appointment);
    }
}