CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_photo VARCHAR(500),
    description VARCHAR(500),
    name VARCHAR(255),
    date_of_birth DATE,
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    login_id BIGINT NOT NULL,

    CONSTRAINT fk_users_logins
        FOREIGN KEY (login_id) REFERENCES logins(login_id),

    CONSTRAINT uk_users_logins UNIQUE (login_id)
);
