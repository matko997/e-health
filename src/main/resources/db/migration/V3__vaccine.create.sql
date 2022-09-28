CREATE TABLE vaccine
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(128) NOT NULL,
    doctor_id  INTEGER,
    patient_id INTEGER,
    created_at DATETIME
) ENGINE = INNODB;
ALTER TABLE vaccine
    ADD CONSTRAINT doctor_id_fk FOREIGN KEY (doctor_id) REFERENCES user (id);
ALTER TABLE vaccine
    ADD CONSTRAINT patient_id_fk FOREIGN KEY (patient_id) REFERENCES user (id);