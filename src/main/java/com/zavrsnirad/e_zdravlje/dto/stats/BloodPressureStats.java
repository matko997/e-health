package com.zavrsnirad.e_zdravlje.dto.stats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BloodPressureStats {
    private long numOfBloodPressuresPreviousMonth;
    private long numOfBloodPressuresThisMonth;
    private long numOfTotalBloodPressures;
}
