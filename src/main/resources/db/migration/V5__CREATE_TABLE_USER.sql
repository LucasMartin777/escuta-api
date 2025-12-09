CREATE TABLE USER (
    user_id BIGINT PRIMARY KEY AUTO_INCREMENT,
    profile_photo VARCHAR(500),
    description VARCHAR(500),
    name VARCHAR(255),
    date_of_birth DATE,
    is_active TINYINT DEFAULT 1,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    login_id BIGINT NOT NULL,
    FOREIGN KEY (login_id) REFERENCES LOGIN(login_id)
);
