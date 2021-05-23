CREATE TABLE weather_data_holder
(
    id              INT PRIMARY KEY,
    base            VARCHAR(255),
    name            VARCHAR(255) NOT NULL,
    dt              INT,
    external_id     INT,
    visibility      INT,
    cod             INT,
    coord_lon       NUMERIC(5, 2),
    coord_lat       NUMERIC(5, 2),
    main_temp       NUMERIC(5, 2),
    main_pressure   INT,
    main_humidity   INT,
    main_temp_min   NUMERIC(5, 2),
    main_temp_max   NUMERIC(5, 2),
    wind_speed      NUMERIC(5, 2),
    wind_deg        INT,
    clouds_all      INT,
    sys_type        INT,
    sys_external_id INT,
    sys_message     NUMERIC(5, 2),
    sys_country     VARCHAR(255),
    sys_sunrise     INT,
    sys_sunset      INT
);

CREATE SEQUENCE weather_data_holder_id_sequence START WITH 1;
