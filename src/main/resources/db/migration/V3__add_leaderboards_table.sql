CREATE TABLE IF NOT EXISTS `leaderboards` (
    `leaderboard_id` UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    `game_name` VARCHAR(50)
);