package com.zavrsnirad.e_zdravlje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BloodPressureDto {
  private String lowerValue;
  private String upperValue;
  private int pulse;
  private long patientId;
}
