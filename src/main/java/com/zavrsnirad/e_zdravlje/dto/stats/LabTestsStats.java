package com.zavrsnirad.e_zdravlje.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LabTestsStats {
  private long numOfLabTestsPreviousMonth;
  private long numOfLabTestsThisMonth;
  private long numOfTotalLabTests;
}
