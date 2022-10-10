package com.zavrsnirad.e_zdravlje.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PatientStats {
  private long numOfPatientsPreviousMonth;
  private long numOfPatientsThisMonth;
  private long numOfTotalPatients;
}
