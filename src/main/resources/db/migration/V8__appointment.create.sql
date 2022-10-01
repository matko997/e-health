CREATE TABLE appointment
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    doctor_id  INTEGER,
    patient_id INTEGER,
    reason_id  INTEGER,
    reason     TEXT,
    date       DATETIME,
    created_at DATETIME
) ENGINE = INNODB;
ALTER TABLE appointment
    ADD CONSTRAINT doctor_appointment__fk FOREIGN KEY (doctor_id) REFERENCES user (id);
ALTER TABLE appointment
    ADD CONSTRAINT patient_appointment_fk FOREIGN KEY (patient_id) REFERENCES user (id);
ALTER TABLE appointment
    ADD CONSTRAINT reason_lab_test_id_fk FOREIGN KEY (reason_id) REFERENCES lab_test (id);
