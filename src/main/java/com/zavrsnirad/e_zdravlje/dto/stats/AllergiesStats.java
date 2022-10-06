package com.zavrsnirad.e_zdravlje.dto.stats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AllergiesStats {
    private long numOfAllergiesPreviousMonth;
    private long numOfAllergiesThisMonth;
    private long numOfTotalAllergies;
}
