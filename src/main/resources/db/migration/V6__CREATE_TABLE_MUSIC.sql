CREATE TABLE MUSIC (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    audio_url VARCHAR(500) NOT NULL,
    release_date DATE,
    single_cover VARCHAR(500),
    login_id BIGINT NOT NULL,
    genre_id BIGINT,
    album_id BIGINT,
    is_active tinyint(1) not null default 1,


    CONSTRAINT fk_music_login
     FOREIGN KEY (login_id) REFERENCES user_login(id)
     ON DELETE CASCADE,

    CONSTRAINT fk_music_album
     FOREIGN KEY (album_id) REFERENCES album(id)
     ON DELETE SET NULL,

    CONSTRAINT fk_music_genre
     FOREIGN KEY (genre_id) REFERENCES genre(id)
     ON DELETE SET NULL
    );