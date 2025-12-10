CREATE TABLE MUSIC (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    audio_url VARCHAR(500) NOT NULL,
    release_date DATE,
    single_cover VARCHAR(500),

    user_id BIGINT NOT NULL,
    genre_id BIGINT,
    album_id BIGINT,

    is_active TINYINT(1) NOT NULL DEFAULT 1,

    CONSTRAINT fk_music_user
        FOREIGN KEY (user_id) REFERENCES USER(user_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_music_album
        FOREIGN KEY (album_id) REFERENCES ALBUM(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_music_genre
        FOREIGN KEY (genre_id) REFERENCES GENRE(id)
        ON DELETE SET NULL
);
