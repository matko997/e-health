package com.zavrsnirad.e_zdravlje.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DiabetesStats {
  private long numOfDiabetesPreviousMonth;
  private long numOfDiabetesThisMonth;
  private long numOfTotalDiabetes;
}
