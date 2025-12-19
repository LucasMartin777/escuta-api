CREATE TABLE musics (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    audio_url VARCHAR(500) NOT NULL,
    release_date DATE,
    single_cover VARCHAR(500),
    user_id BIGINT NOT NULL,
    genre_id BIGINT,
    album_id BIGINT,
    is_active TINYINT(1) NOT NULL DEFAULT 1,

    CONSTRAINT fk_musics_users
        FOREIGN KEY (user_id) REFERENCES users(user_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_musics_albums
        FOREIGN KEY (album_id) REFERENCES albums(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_musics_genres
        FOREIGN KEY (genre_id) REFERENCES genres(id)
        ON DELETE SET NULL
);
