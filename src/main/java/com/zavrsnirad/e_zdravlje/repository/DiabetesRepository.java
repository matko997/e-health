package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.Diabetes;
import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface DiabetesRepository extends JpaRepository<Diabetes, Long> {

    Page<Diabetes> findAllByPatient(User patient, Pageable pageable);

    @Query("SELECT d FROM Diabetes d JOIN d.patient WHERE d.patient.firstName LIKE %:keyword% OR d.patient.lastName " +
            "LIKE %:keyword% ")
    Page<Diabetes> findDiabetesPaginatedAndFilterable(Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT d FROM Diabetes d JOIN d.patient WHERE d.patient=:patient AND (d.patient.firstName " +
            "LIKE %:keyword% OR d.patient.lastName LIKE %:keyword% ) ")
    Page<Diabetes> findAllByPatientFilterable(@Param("patient") User user, Pageable pageable, @Param("keyword") String keyword);

    @Query("SELECT COUNT(d) FROM Diabetes d WHERE d.createdAt BETWEEN :startDate AND :endDate")
    long getCountOfDiabetesBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

}
