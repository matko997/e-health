package com.zavrsnirad.e_zdravlje.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "blood_pressure")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BloodPressure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "upper_value")
    private String upperValue;
    @Column(name = "lower_value")
    private String lowerValue;
    @Column(name = "pulse")
    private int pulse;
    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private User patient;
    @CreationTimestamp
    @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
