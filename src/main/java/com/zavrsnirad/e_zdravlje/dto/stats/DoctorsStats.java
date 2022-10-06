package com.zavrsnirad.e_zdravlje.dto.stats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DoctorsStats {
    private long numOfDoctorsPreviousMonth;
    private long numOfDoctorsThisMonth;
    private long numOfTotalDoctors;
}
