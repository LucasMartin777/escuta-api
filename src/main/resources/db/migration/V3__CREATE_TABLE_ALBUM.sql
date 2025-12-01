CREATE TABLE ALBUM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    album_duration INT,
    album_cover VARCHAR(500),
    description VARCHAR(500),
    login_id BIGINT NOT NULL,
    is_active tinyint(1) not null default 1,
    CONSTRAINT fk_album_login
        FOREIGN KEY (login_id) REFERENCES user_login(id)
        ON DELETE CASCADE
);
