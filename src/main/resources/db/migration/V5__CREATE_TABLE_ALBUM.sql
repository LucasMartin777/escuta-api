CREATE TABLE albums (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    album_cover VARCHAR(500),
    description VARCHAR(500),
    release_date DATE,

    user_id BIGINT NOT NULL,

    is_active TINYINT(1) NOT NULL DEFAULT 1,

    CONSTRAINT fk_albums_users
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
);
