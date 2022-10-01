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
@Table(name = "appointment")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "reason")
    private String reason;
    @Column(name = "date")
    private LocalDateTime date;
    @Column(name = "approved")
    private Boolean approved;
    @ManyToOne()
    @JoinColumn(name = "reason_id")
    private LabTest labTest;
    @ManyToOne()
    @JoinColumn(name = "doctor_id")
    private User doctor;
    @ManyToOne()
    @JoinColumn(name = "patient_id")
    private User patient;
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private LocalDateTime createdAt;
}
