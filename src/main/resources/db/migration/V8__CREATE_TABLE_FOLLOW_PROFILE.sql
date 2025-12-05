CREATE TABLE follows_profiles (
    follower_id BIGINT NOT NULL,
    following_id BIGINT NOT NULL,
    follow_date DATE,

    PRIMARY KEY (follower_id, following_id),

    CONSTRAINT fk_follows_followers
        FOREIGN KEY (follower_id) REFERENCES users(user_id)
        ON DELETE CASCADE,

    CONSTRAINT fk_follows_followings
        FOREIGN KEY (following_id) REFERENCES users(user_id)
        ON DELETE CASCADE
);
