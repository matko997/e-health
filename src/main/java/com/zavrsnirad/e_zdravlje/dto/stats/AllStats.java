package com.zavrsnirad.e_zdravlje.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AllStats {
  private AllergiesStats allergiesStats;
  private AppointmentStats appointmentStats;
  private BloodPressureStats bloodPressureStats;
  private DoctorsStats doctorsStats;
  private PatientStats patientStats;
  private LabTestsStats labTestsStats;
  private VaccineStats vaccineStats;
  private DiabetesStats diabetesStats;
}
