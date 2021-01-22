CREATE TABLE IF NOT EXISTS `leaderboard` (
    `uuid` UUID DEFAULT RANDOM_UUID() NOT NULL PRIMARY KEY,
    `nick` VARCHAR(20),
    `score` INT
);
