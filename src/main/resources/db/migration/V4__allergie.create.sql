CREATE TABLE allergie
(
    id         INTEGER AUTO_INCREMENT PRIMARY KEY,
    name       VARCHAR(128) NOT NULL,
    patient_id INTEGER,
    created_at DATETIME
) ENGINE = INNODB;
ALTER TABLE allergie
    ADD CONSTRAINT patient_fk FOREIGN KEY (patient_id) REFERENCES user (id);