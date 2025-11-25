CREATE TABLE PLAYLIST (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cover VARCHAR(500),
    is_public BOOLEAN DEFAULT TRUE,
    registration_date DATE DEFAULT CURRENT_DATE,
    login_id BIGINT NOT NULL,
    CONSTRAINT fk_playlist_login
        FOREIGN KEY (login_id) REFERENCES user_login(id)
        ON DELETE CASCADE
);
