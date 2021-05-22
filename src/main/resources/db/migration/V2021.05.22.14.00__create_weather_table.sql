CREATE TABLE weather
(
    id          INT PRIMARY KEY,
    city        VARCHAR(255) NOT NULL,
    country     VARCHAR(255) NOT NULL,
    temperature NUMERIC(5, 2)
);

CREATE SEQUENCE weather_id_sequence START WITH 1;
