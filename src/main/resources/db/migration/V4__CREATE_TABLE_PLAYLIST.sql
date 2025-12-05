CREATE TABLE playlists (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    cover VARCHAR(500),
    is_public BOOLEAN DEFAULT TRUE,
    registration_date DATE,

    user_id BIGINT NOT NULL,

    is_active TINYINT(1) NOT NULL DEFAULT 1,

    CONSTRAINT fk_playlists_users
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE
);
