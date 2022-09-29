CREATE TABLE blood_pressure
(
    id          INTEGER AUTO_INCREMENT PRIMARY KEY,
    upper_value VARCHAR(128) NOT NULL,
    lower_value VARCHAR(128) NOT NULL,
    pulse       INTEGER      NOT NULL,
    patient_id  INTEGER,
    created_at  DATETIME
) ENGINE = INNODB;
ALTER TABLE blood_pressure
    ADD CONSTRAINT patient_blood_pressure_fk FOREIGN KEY (patient_id) REFERENCES user (id);