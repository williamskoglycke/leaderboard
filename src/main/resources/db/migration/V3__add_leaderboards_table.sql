CREATE TABLE IF NOT EXISTS `leaderboards` (
    `uuid` UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    `game_name` VARCHAR(50)
);