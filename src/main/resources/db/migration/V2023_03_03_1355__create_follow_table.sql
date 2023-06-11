CREATE TABLE IF NOT EXISTS "follow"
(
    id           BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    follower_id  BIGINT NOT NULL,
    following_id BIGINT NOT NULL
);

CREATE UNIQUE INDEX ON "follow" (follower_id, following_id);
CREATE INDEX ON "follow" (follower_id);
CREATE INDEX ON "follow" (following_id);