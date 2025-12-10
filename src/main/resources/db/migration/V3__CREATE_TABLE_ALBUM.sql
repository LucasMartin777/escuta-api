CREATE TABLE ALBUM (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    album_duration INT,
    album_cover VARCHAR(500),
    description VARCHAR(500),
    release_date DATE,

    user_id BIGINT NOT NULL,

    is_active TINYINT(1) NOT NULL DEFAULT 1,

    CONSTRAINT fk_album_user
        FOREIGN KEY (user_id) REFERENCES user(user_id)
        ON DELETE CASCADE
);
