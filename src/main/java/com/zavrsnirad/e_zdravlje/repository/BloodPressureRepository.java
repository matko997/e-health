package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.BloodPressure;
import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface BloodPressureRepository extends JpaRepository<BloodPressure, Long> {
  Page<BloodPressure> findAllByPatient(User patient, Pageable pageable);

  @Query(
      "SELECT bp FROM BloodPressure bp JOIN bp.patient WHERE bp.patient.firstName "
          + "LIKE %:keyword% OR bp.patient.lastName LIKE %:keyword%")
  Page<BloodPressure> findBloodPressuresPaginatedAndFilterable(
      Pageable pageable, @Param("keyword") String keyword);

  @Query(
      "SELECT bp FROM BloodPressure bp JOIN bp.patient WHERE bp.patient=:patient AND (bp.patient.firstName "
          + "LIKE %:keyword% OR bp.patient.lastName LIKE %:keyword%) ")
  Page<BloodPressure> findAllByPatientFilterable(
      @Param("patient") User user, Pageable pageable, @Param("keyword") String keyword);

  @Query(
      "SELECT COUNT(bp) FROM BloodPressure bp WHERE bp.createdAt BETWEEN :startDate AND :endDate")
  long getCountBloodPressureBetween(
      @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
