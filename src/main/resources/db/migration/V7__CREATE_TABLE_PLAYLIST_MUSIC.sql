CREATE TABLE playlists_musics (
    playlist_id BIGINT NOT NULL,
    music_id BIGINT NOT NULL,

    PRIMARY KEY (playlist_id, music_id),

    CONSTRAINT fk_pm_playlists
        FOREIGN KEY (playlist_id) REFERENCES playlists(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_pm_musics
        FOREIGN KEY (music_id) REFERENCES musics(id)
        ON DELETE CASCADE
);
