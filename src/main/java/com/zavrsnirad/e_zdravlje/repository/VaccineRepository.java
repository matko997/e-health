package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.User;
import com.zavrsnirad.e_zdravlje.model.Vaccine;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface VaccineRepository extends JpaRepository<Vaccine, Long> {

    Page<Vaccine> findAllByPatient(User patient, Pageable pageable);

    @Query("SELECT v FROM Vaccine v JOIN v.doctor JOIN v.patient WHERE v.name LIKE %:keyword% OR v.doctor.firstName " +
            "LIKE %:keyword% OR v.doctor.lastName LIKE %:keyword% OR v.patient.firstName LIKE %:keyword% " +
            "OR v.patient.lastName LIKE %:keyword% ")
    Page<Vaccine> findVaccinePaginatedAndFilterable(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT v FROM Vaccine v INNER JOIN v.doctor INNER JOIN  v.patient WHERE v.patient=:patient AND  (v.doctor.firstName " +
            "LIKE %:keyword% OR v.patient.firstName LIKE %:keyword% OR v.createdAt= :keyword) ")
    Page<Vaccine> findAllByPatientFilterable(@Param("patient") User user, Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT COUNT(v) FROM Vaccine v WHERE v.createdAt BETWEEN :startDate AND :endDate")
    long getCountOfVaccinesBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
