CREATE TABLE diabetes
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    value       DECIMAL(10,1) NOT NULL,
    patient_id INTEGER,
    created_at DATETIME
) ENGINE = INNODB;
ALTER TABLE diabetes
    ADD CONSTRAINT patient_diabetes_fk FOREIGN KEY (patient_id) REFERENCES user (id);