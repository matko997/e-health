package com.zavrsnirad.e_zdravlje.service;

import com.zavrsnirad.e_zdravlje.dto.stats.*;
import com.zavrsnirad.e_zdravlje.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;

@Service
public class StatisticsService {
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;
    private final AllergiesRepository allergiesRepository;
    private final VaccineRepository vaccineRepository;
    private final DiabetesRepository diabetesRepository;
    private final LabTestRepository labTestRepository;
    private final BloodPressureRepository bloodPressureRepository;

    public StatisticsService(AppointmentRepository appointmentRepository, UserRepository userRepository,
                             AllergiesRepository allergiesRepository, VaccineRepository vaccineRepository,
                             DiabetesRepository diabetesRepository, LabTestRepository labTestRepository,
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
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return appointmentRepository.getCountOfAppointmentsBetween(startDate, endDate);
    }

    public long getCountOfAppointmentsThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return appointmentRepository.getCountOfAppointmentsBetween(startDate, endDate);
    }

    public long getCountOfVaccines() {
        return vaccineRepository.count();
    }

    public long getCountOfVaccinesInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return vaccineRepository.getCountOfVaccinesBetween(startDate, endDate);
    }

    public long getCountOfVaccinesThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return vaccineRepository.getCountOfVaccinesBetween(startDate, endDate);
    }

    // DIABETES
    public long getCountOfDiabetes() {
        return diabetesRepository.count();
    }

    public long getCountOfDiabetesInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return diabetesRepository.getCountOfDiabetesBetween(startDate, endDate);
    }

    public long getCountOfDiabetesThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return diabetesRepository.getCountOfDiabetesBetween(startDate, endDate);
    }

    //DOCTORS

    public long getCountOfDoctors() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return userRepository.getCountOfDoctors("DOCTOR");
    }

    public long getCountOfDoctorsInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return userRepository.getCountOfUserByRoleBetween(startDate, endDate, "DOCTOR");
    }

    public long getCountOfDoctorsThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return userRepository.getCountOfUserByRoleBetween(startDate, endDate, "DOCTORS");
    }

    //PATIENTS
    public long getCountOfPatients() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return userRepository.getCountOfDoctors("PATIENT");
    }

    public long getCountOfPatientsInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return userRepository.getCountOfUserByRoleBetween(startDate, endDate, "PATIENT");
    }

    public long getCountOfPatientsThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return userRepository.getCountOfUserByRoleBetween(startDate, endDate, "PATIENT");
    }

    //LAB TESTS

    public long getCountOfLabTests() {
        return labTestRepository.count();
    }

    public long getCountOfLabTestsInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return labTestRepository.getCountOfLabTestsBetween(startDate, endDate);
    }

    public long getCountOfLabTestsThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return labTestRepository.getCountOfLabTestsBetween(startDate, endDate);
    }

    //ALLERGIES

    public long getCountOfAllergies() {
        return allergiesRepository.count();
    }

    public long getCountOfAllergiesInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return allergiesRepository.getCountOfAllergiesBetween(startDate, endDate);
    }

    public long getCountAllergiesThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return allergiesRepository.getCountOfAllergiesBetween(startDate, endDate);
    }

    //BLOOD PRESSURE

    public long getCountBloodPressures() {
        return bloodPressureRepository.count();
    }

    public long getCountOfBloodPressuresInLastMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue() - 1).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return bloodPressureRepository.getCountBloodPressureBetween(startDate, endDate);
    }

    public long getCountBloodPressuresThisMonth() {
        LocalDateTime currentDay = getLocalDateTime();
        Month currentMonth = currentDay.getMonth();
        LocalDateTime startDate = currentDay.withDayOfMonth(1).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(0).withMinute(0);
        LocalDateTime endDate = currentDay.withDayOfMonth(currentMonth.maxLength()).withMonth(currentDay.getMonthValue()).withYear(currentDay.getYear()).withHour(23).withMinute(59);
        return bloodPressureRepository.getCountBloodPressureBetween(startDate, endDate);
    }

    private static LocalDateTime getLocalDateTime() {
        return LocalDateTime.now();
    }

    public AllStats getAllStats() {
        AllStats allStats = new AllStats();
        allStats.setDoctorsStats(new DoctorsStats(getCountOfDoctorsInLastMonth(), getCountOfDoctorsThisMonth(), getCountOfDoctors()));
        allStats.setPatientStats(new PatientStats(getCountOfPatientsInLastMonth(), getCountOfPatientsThisMonth(), getCountOfPatients()));
        allStats.setAllergiesStats(new AllergiesStats(getCountOfAllergiesInLastMonth(), getCountAllergiesThisMonth(), getCountOfAllergies()));
        allStats.setLabTestsStats(new LabTestsStats(getCountOfLabTestsInLastMonth(), getCountOfLabTestsThisMonth(), getCountOfLabTests()));
        allStats.setBloodPressureStats(new BloodPressureStats(getCountOfBloodPressuresInLastMonth(), getCountBloodPressuresThisMonth(), getCountBloodPressures()));
        allStats.setDiabetesStats(new DiabetesStats(getCountOfDiabetesInLastMonth(), getCountOfDiabetesThisMonth(), getCountOfDiabetes()));
        allStats.setVaccineStats(new VaccineStats(getCountOfVaccinesInLastMonth(), getCountOfVaccinesThisMonth(), getCountOfVaccines()));
        allStats.setAppointmentStats(new AppointmentStats(getCountOfAppointmentsInLastMonth(), getCountOfAppointmentsThisMonth(), getCountOfAppointments()));
        return allStats;
    }
}
