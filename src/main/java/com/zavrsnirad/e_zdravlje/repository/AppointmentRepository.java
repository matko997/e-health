package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.Appointment;
import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Page<Appointment> findAllByPatient(User patient, Pageable pageable);

    @Query("SELECT a FROM Appointment a JOIN a.doctor JOIN a.patient WHERE a.patient.firstName LIKE %:keyword% OR a.patient.lastName " +
            "LIKE %:keyword% OR a.doctor.firstName LIKE %:keyword% OR a.doctor.lastName LIKE %:keyword% ")
    Page<Appointment> findAppointmentPaginatedAndFilterable(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT a FROM Appointment a JOIN a.doctor JOIN a.patient WHERE a.patient=:patient AND  (a.doctor.firstName " +
            "LIKE %:keyword% OR a.patient.firstName LIKE %:keyword% OR a.createdAt= :keyword) ")
    Page<Appointment> findAllByPatientFilterable(@Param("patient") User user, Pageable pageable, @Param("keyword") String keyword);
}
