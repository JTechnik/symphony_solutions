CREATE TABLE weather_data_holder
(
    id              INT PRIMARY KEY,
    base            VARCHAR(255)  NOT NULL,
    name            VARCHAR(255)  NOT NULL,
    dt              INT           NOT NULL,
    external_id     INT           NOT NULL,
    visibility      INT           NOT NULL,
    cod             INT           NOT NULL,
    coord_lon       NUMERIC(5, 2) NOT NULL,
    coord_lat       NUMERIC(5, 2) NOT NULL,
    main_temp       NUMERIC(5, 2) NOT NULL,
    main_pressure   INT           NOT NULL,
    main_humidity   INT           NOT NULL,
    main_temp_min   NUMERIC(5, 2) NOT NULL,
    main_temp_max   NUMERIC(5, 2) NOT NULL,
    wind_speed      NUMERIC(5, 2) NOT NULL,
    wind_deg        INT           NOT NULL,
    clouds_all      INT           NOT NULL,
    sys_type        INT           NOT NULL,
    sys_external_id INT           NOT NULL,
    sys_message     NUMERIC(5, 2),
    sys_country     VARCHAR(255)  NOT NULL,
    sys_sunrise     INT           NOT NULL,
    sys_sunset      INT           NOT NULL
);

CREATE SEQUENCE weather_data_holder_id_sequence START WITH 1;
