package com.zavrsnirad.e_zdravlje.dto.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class AppointmentStats {
    private long numOfAppointmentsPreviousMonth;
    private long numOfAppointmentsThisMonth;
    private long numOfTotalAppointments;
}
