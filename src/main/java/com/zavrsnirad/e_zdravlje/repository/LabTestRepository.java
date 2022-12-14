package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.LabTest;
import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface LabTestRepository extends JpaRepository<LabTest, Long> {
  Page<LabTest> findAllByPatient(User patient, Pageable pageable);

  @Query(
      "SELECT lb FROM LabTest lb JOIN lb.doctor JOIN lb.patient WHERE  lb.doctor.firstName "
          + "LIKE %:keyword% OR lb.doctor.lastName LIKE %:keyword% OR lb.patient.firstName LIKE %:keyword% "
          + "OR lb.patient.lastName LIKE %:keyword% ")
  Page<LabTest> findLabTestPaginatedAndFilterable(
      Pageable pageable, @Param("keyword") String keyword);

  @Query(
      "SELECT lb FROM LabTest lb INNER JOIN lb.doctor INNER JOIN  lb.patient WHERE lb.patient=:patient AND"
          + " (lb.doctor.firstName  "
          + "LIKE %:keyword% OR lb.doctor.lastName LIKE %:keyword% OR lb.patient.firstName "
          + "LIKE %:keyword% OR lb.patient.lastName LIKE %:keyword% ) ")
  Page<LabTest> findAllByPatientFilterable(
      @Param("patient") User user, Pageable pageable, @Param("keyword") String keyword);

  @Query("SELECT COUNT(lt) FROM LabTest lt WHERE lt.createdAt BETWEEN :startDate AND :endDate")
  long getCountOfLabTestsBetween(
      @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
