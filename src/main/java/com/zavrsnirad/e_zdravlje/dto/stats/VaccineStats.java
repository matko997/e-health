package com.zavrsnirad.e_zdravlje.dto.stats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class VaccineStats {
    private long numOfVaccinesPreviousMonth;
    private long numOfVaccinesThisMonth;
    private long numOfTotalVaccines;
}
