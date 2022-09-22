CREATE TABLE user
(
    id                    INTEGER AUTO_INCREMENT PRIMARY KEY,
    first_name            VARCHAR(255)        NOT NULL,
    last_name             VARCHAR(255)        NOT NULL,
    birth_date            DATE,
    email                 VARCHAR(128) UNIQUE NOT NULL,
    password              VARCHAR(255)        NOT NULL,
    gender                CHAR(1)             NOT NULL,
    jmbg                  VARCHAR(13) NULL,
    city                  VARCHAR(255) NULL,
    blood_type            VARCHAR(5) NULL,
    address               VARCHAR(255) NULL,
    postal_code           VARCHAR(255) NULL,
    phone_number          VARCHAR(50) NULL,
    graduation_university VARCHAR(255) NULL,
    graduation_year       INTEGER NULL,
    specialization        VARCHAR(128) NULL,
    specialization_year   INTEGER NULL,
    role_id               INTEGER             NOT NULL,
    created_at            DATETIME
) ENGINE=INNODB;
ALTER TABLE user
    ADD CONSTRAINT role_id_fk FOREIGN KEY (role_id) REFERENCES role (id);