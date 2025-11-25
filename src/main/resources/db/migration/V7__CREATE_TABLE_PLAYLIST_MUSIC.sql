CREATE TABLE PLAYLIST_MUSIC (
    playlist_id BIGINT NOT NULL,
    music_id BIGINT NOT NULL,

    PRIMARY KEY (playlist_id, music_id),

    CONSTRAINT fk_pm_playlist
        FOREIGN KEY (playlist_id) REFERENCES playlist(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_pm_music
        FOREIGN KEY (music_id) REFERENCES music(id)
        ON DELETE CASCADE
);
