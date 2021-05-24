CREATE TABLE weather
(
    id                     INT PRIMARY KEY,
    external_id            INT,
    main                   VARCHAR(255),
    description            VARCHAR(255),
    icon                   VARCHAR(255),
    weather_data_holder_id INT NOT NULL
);

CREATE SEQUENCE weather_id_sequence START WITH 1;
