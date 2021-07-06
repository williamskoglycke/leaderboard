CREATE TABLE IF NOT EXISTS `leaderboard_player` (
    `id` UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    `leaderboard_id` UUID NOT NULL,
    `player_id` UUID NOT NULL,
    `score` INT,
    FOREIGN KEY (leaderboard_id) REFERENCES leaderboards(leaderboard_id),
    FOREIGN KEY (player_id) REFERENCES players(player_id)
);