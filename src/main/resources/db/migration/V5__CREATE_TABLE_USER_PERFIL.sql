CREATE TABLE USER_PERFIL (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    profile_photo VARCHAR(255),
    description VARCHAR(500),
    user_login_id BIGINT UNIQUE,
    is_active tinyint(1) not null default 1,
    CONSTRAINT fk_user_login FOREIGN KEY (user_login_id)
        REFERENCES USER_LOGIN(id)
        ON DELETE CASCADE
);
