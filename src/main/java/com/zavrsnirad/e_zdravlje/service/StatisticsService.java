package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.stats.*;
import com.zavrsnirad.e_zdravlje.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StatisticsService {
  public static final String DOCTOR_ROLE = "DOCTOR";
  public static final String PATIENT_ROLE = "PATIENT";
  private final AppointmentRepository appointmentRepository;
  private final UserRepository userRepository;
  private final AllergiesRepository allergiesRepository;
  private final VaccineRepository vaccineRepository;
  private final DiabetesRepository diabetesRepository;
  private final LabTestRepository labTestRepository;
  private final BloodPressureRepository bloodPressureRepository;
  private final LocalDateTime currentDay = LocalDateTime.now();
  private final LocalDateTime startDatePreviousMonth =
      currentDay
          .withDayOfMonth(1)
          .withMonth(currentDay.getMonthValue() - 1)
          .withYear(currentDay.getYear())
          .withHour(0)
          .withMinute(0)
          .withSecond(0)
          .withNano(0);
  private final LocalDateTime endDatePreviousMonth =
      currentDay
          .withDayOfMonth(currentDay.getMonth().maxLength())
          .withMonth(currentDay.getMonthValue() - 1)
          .withYear(currentDay.getYear())
          .withHour(23)
          .withMinute(59)
          .withSecond(59)
          .withNano(999999999);
  private final LocalDateTime startDateThisMonth =
      currentDay
          .withDayOfMonth(1)
          .withMonth(currentDay.getMonthValue())
          .withYear(currentDay.getYear())
          .withHour(0)
          .withMinute(0)
          .withSecond(0)
          .withNano(0);
  private final LocalDateTime endDateThisMonth =
      currentDay
          .withDayOfMonth(currentDay.getMonth().maxLength())
          .withMonth(currentDay.getMonthValue())
          .withYear(currentDay.getYear())
          .withHour(23)
          .withMinute(59)
          .withSecond(59)
          .withNano(999999999);

  public StatisticsService(
      AppointmentRepository appointmentRepository,
      UserRepository userRepository,
      AllergiesRepository allergiesRepository,
      VaccineRepository vaccineRepository,
      DiabetesRepository diabetesRepository,
      LabTestRepository labTestRepository,
      BloodPressureRepository bloodPressureRepository) {
    this.appointmentRepository = appointmentRepository;
    this.userRepository = userRepository;
    this.allergiesRepository = allergiesRepository;
    this.vaccineRepository = vaccineRepository;
    this.diabetesRepository = diabetesRepository;
    this.labTestRepository = labTestRepository;
    this.bloodPressureRepository = bloodPressureRepository;
  }

  public long getCountOfAppointments() {
    return appointmentRepository.count();
  }

  public long getCountOfAppointmentsInLastMonth() {
    return appointmentRepository.getCountOfAppointmentsBetween(
        startDatePreviousMonth, endDatePreviousMonth);
  }

  public long getCountOfAppointmentsThisMonth() {
    return appointmentRepository.getCountOfAppointmentsBetween(
        startDateThisMonth, endDateThisMonth);
  }

  public long getCountOfVaccines() {
    return vaccineRepository.count();
  }

  public long getCountOfVaccinesInLastMonth() {
    return vaccineRepository.getCountOfVaccinesBetween(
        startDatePreviousMonth, endDatePreviousMonth);
  }

  public long getCountOfVaccinesThisMonth() {
    return vaccineRepository.getCountOfVaccinesBetween(startDateThisMonth, endDateThisMonth);
  }

  // DIABETES
  public long getCountOfDiabetes() {
    return diabetesRepository.count();
  }

  public long getCountOfDiabetesInLastMonth() {
    return diabetesRepository.getCountOfDiabetesBetween(
        startDatePreviousMonth, endDatePreviousMonth);
  }

  public long getCountOfDiabetesThisMonth() {
    return diabetesRepository.getCountOfDiabetesBetween(startDateThisMonth, endDateThisMonth);
  }

  // DOCTORS

  public long getCountOfDoctors() {
    return userRepository.getCountOfDoctors(DOCTOR_ROLE);
  }

  public long getCountOfDoctorsInLastMonth() {
    return userRepository.getCountOfUserByRoleBetween(
        startDatePreviousMonth, endDatePreviousMonth, DOCTOR_ROLE);
  }

  public long getCountOfDoctorsThisMonth() {
    return userRepository.getCountOfUserByRoleBetween(
        startDateThisMonth, endDateThisMonth, DOCTOR_ROLE);
  }

  // PATIENTS
  public long getCountOfPatients() {
    return userRepository.getCountOfDoctors(PATIENT_ROLE);
  }

  public long getCountOfPatientsInLastMonth() {
    return userRepository.getCountOfUserByRoleBetween(
        startDatePreviousMonth, endDatePreviousMonth, PATIENT_ROLE);
  }

  public long getCountOfPatientsThisMonth() {
    return userRepository.getCountOfUserByRoleBetween(
        startDateThisMonth, endDateThisMonth, PATIENT_ROLE);
  }

  // LAB TESTS

  public long getCountOfLabTests() {
    return labTestRepository.count();
  }

  public long getCountOfLabTestsInLastMonth() {
    return labTestRepository.getCountOfLabTestsBetween(
        startDatePreviousMonth, endDatePreviousMonth);
  }

  public long getCountOfLabTestsThisMonth() {
    return labTestRepository.getCountOfLabTestsBetween(startDateThisMonth, endDateThisMonth);
  }

  // ALLERGIES

  public long getCountOfAllergies() {
    return allergiesRepository.count();
  }

  public long getCountOfAllergiesInLastMonth() {
    return allergiesRepository.getCountOfAllergiesBetween(
        startDatePreviousMonth, endDatePreviousMonth);
  }

  public long getCountAllergiesThisMonth() {
    return allergiesRepository.getCountOfAllergiesBetween(startDateThisMonth, endDateThisMonth);
  }

  // BLOOD PRESSURE

  public long getCountBloodPressures() {
    return bloodPressureRepository.count();
  }

  public long getCountOfBloodPressuresInLastMonth() {
    return bloodPressureRepository.getCountBloodPressureBetween(
        startDatePreviousMonth, endDatePreviousMonth);
  }

  public long getCountBloodPressuresThisMonth() {
    return bloodPressureRepository.getCountBloodPressureBetween(
        startDateThisMonth, endDateThisMonth);
  }

  public AllStats getAllStats() {
    AllStats allStats = new AllStats();
    allStats.setDoctorsStats(
        new DoctorsStats(
            getCountOfDoctorsInLastMonth(), getCountOfDoctorsThisMonth(), getCountOfDoctors()));
    allStats.setPatientStats(
        new PatientStats(
            getCountOfPatientsInLastMonth(), getCountOfPatientsThisMonth(), getCountOfPatients()));
    allStats.setAllergiesStats(
        new AllergiesStats(
            getCountOfAllergiesInLastMonth(), getCountAllergiesThisMonth(), getCountOfAllergies()));
    allStats.setLabTestsStats(
        new LabTestsStats(
            getCountOfLabTestsInLastMonth(), getCountOfLabTestsThisMonth(), getCountOfLabTests()));
    allStats.setBloodPressureStats(
        new BloodPressureStats(
            getCountOfBloodPressuresInLastMonth(),
            getCountBloodPressuresThisMonth(),
            getCountBloodPressures()));
    allStats.setDiabetesStats(
        new DiabetesStats(
            getCountOfDiabetesInLastMonth(), getCountOfDiabetesThisMonth(), getCountOfDiabetes()));
    allStats.setVaccineStats(
        new VaccineStats(
            getCountOfVaccinesInLastMonth(), getCountOfVaccinesThisMonth(), getCountOfVaccines()));
    allStats.setAppointmentStats(
        new AppointmentStats(
            getCountOfAppointmentsInLastMonth(),
            getCountOfAppointmentsThisMonth(),
            getCountOfAppointments()));
    return allStats;
  }
}
