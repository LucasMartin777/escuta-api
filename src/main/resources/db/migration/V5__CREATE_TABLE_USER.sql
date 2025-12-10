CREATE TABLE USER (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_photo VARCHAR(500),
    description VARCHAR(500),
    name VARCHAR(255),
    date_of_birth DATE,

    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    login_id BIGINT NOT NULL,

    CONSTRAINT fk_user_login
        FOREIGN KEY (login_id) REFERENCES LOGIN(login_id),

    CONSTRAINT uk_user_login UNIQUE (login_id)
);
