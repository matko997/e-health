CREATE TABLE lab_test
(
    id           INTEGER AUTO_INCREMENT PRIMARY KEY,
    urea         DECIMAL(10, 1) NULL,
    glucose      DECIMAL(10, 1) NULL,
    creatinine   INTEGER        NULL,
    cholesterol  DECIMAL(10, 1) NULL,
    triglyceride DECIMAL(10, 1) NULL,
    hemoglobin   INTEGER        NULL,
    ALP          INTEGER        NULL,
    doctor_id    INTEGER,
    patient_id   INTEGER,
    created_at   DATETIME
) ENGINE = INNODB;
ALTER TABLE lab_test
    ADD CONSTRAINT doctor_lab_test_fk FOREIGN KEY (doctor_id) REFERENCES user (id);
ALTER TABLE lab_test
    ADD CONSTRAINT patient_lab_test_fk FOREIGN KEY (patient_id) REFERENCES user (id);