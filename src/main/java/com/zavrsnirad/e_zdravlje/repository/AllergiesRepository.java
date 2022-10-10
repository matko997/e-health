package com.zavrsnirad.e_zdravlje.repository;

import com.zavrsnirad.e_zdravlje.model.Allergie;
import com.zavrsnirad.e_zdravlje.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;

public interface AllergiesRepository extends JpaRepository<Allergie, Long> {

  Page<Allergie> findAllByPatient(User patient, Pageable pageable);

  @Query(
      "SELECT a FROM Allergie a JOIN a.patient WHERE a.name LIKE %:keyword% OR a.patient.firstName "
          + "LIKE %:keyword% OR a.patient.lastName LIKE %:keyword%")
  Page<Allergie> findAllergiesPaginatedAndFilterable(
      Pageable pageable, @Param("keyword") String keyword);

  @Query(
      "SELECT a FROM Allergie a JOIN a.patient WHERE a.patient=:patient AND (a.patient.firstName "
          + "LIKE %:keyword% OR a.patient.lastName LIKE %:keyword% OR a.name= :keyword) ")
  Page<Allergie> findAllByPatientFilterable(
      @Param("patient") User user, Pageable pageable, @Param("keyword") String keyword);

  @Query("SELECT COUNT(a) FROM Allergie a WHERE a.createdAt BETWEEN :startDate AND :endDate")
  long getCountOfAllergiesBetween(
      @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
