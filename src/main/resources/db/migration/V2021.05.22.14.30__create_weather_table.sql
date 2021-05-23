CREATE TABLE weather
(
    id                     INT PRIMARY KEY,
    external_id            INT          NOT NULL,
    main                   VARCHAR(255) NOT NULL,
    description            VARCHAR(255) NOT NULL,
    icon                   VARCHAR(255) NOT NULL,
    weather_data_holder_id INT          NOT NULL
);

CREATE SEQUENCE weather_id_sequence START WITH 1;
