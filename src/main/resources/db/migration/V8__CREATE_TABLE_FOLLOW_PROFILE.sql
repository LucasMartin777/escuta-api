CREATE TABLE FOLLOW_PROFILE (
    follower_id BIGINT NOT NULL,
    following_id BIGINT NOT NULL,
    follow_date DATE,

    PRIMARY KEY (follower_id, following_id),

    CONSTRAINT fk_follow_follower
        FOREIGN KEY (follower_id) REFERENCES user_login(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_follow_following
        FOREIGN KEY (following_id) REFERENCES user_login(id)
        ON DELETE CASCADE
);
