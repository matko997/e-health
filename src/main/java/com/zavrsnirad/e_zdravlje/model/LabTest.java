package com.zavrsnirad.e_zdravlje.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "lab_test")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LabTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;

  @Column(name = "urea")
  private Double urea;

  @Column(name = "glucose")
  private Double glucose;

  @Column(name = "creatinine")
  private Integer creatinine;

  @Column(name = "cholesterol")
  private Double cholesterol;

  @Column(name = "triglyceride")
  private Double triglyceride;

  @Column(name = "hemoglobin")
  private Integer hemoglobin;

  @Column(name = "ALP")
  private Integer ALP;

  @ManyToOne()
  @JoinColumn(name = "doctor_id")
  private User doctor;

  @ManyToOne()
  @JoinColumn(name = "patient_id")
  private User patient;

  @CreationTimestamp
  @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime createdAt;
}
