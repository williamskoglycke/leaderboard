CREATE TABLE IF NOT EXISTS `leaderboard_player` (
    `uuid` UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    `leaderboard_id` UUID NOT NULL,
    `player_id` UUID NOT NULL,
    `score` INT,
    FOREIGN KEY (leaderboard_id) REFERENCES leaderboards(uuid),
    FOREIGN KEY (player_id) REFERENCES players(uuid)
);