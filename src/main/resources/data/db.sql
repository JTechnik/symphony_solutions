CREATE USER weather_application_user WITH PASSWORD 'qwerty_';
CREATE DATABASE weather_application;
GRANT ALL ON DATABASE weather_application TO weather_application_user;
\connect weather_application;
CREATE SCHEMA IF NOT EXISTS production;
GRANT ALL ON SCHEMA production TO weather_application_user;