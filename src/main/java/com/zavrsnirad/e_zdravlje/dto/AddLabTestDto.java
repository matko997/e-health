package com.zavrsnirad.e_zdravlje.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddLabTestDto {
    private long patientId;
    private Double urea;
    private Double glucose;
    private Integer creatinine;
    private Double cholesterol;
    private Double triglyceride;
    private Integer hemoglobin;
    private Integer ALP;
}
