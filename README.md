Spring Boot Coding Dojo
---

Welcome to the Spring Boot Coding Dojo!

### Introduction:

This is a simple application that requests its data from [OpenWeather](https://openweathermap.org/) and stores the result in a database.

### It is use:
Java 11, <br />
Spring Boot, <br /> 
Swagger, <br /> 
MapStruct, <br /> 
Flyway, <br /> 
PostgreSQL, <br />
Third party API

### Deploy:
Install and set up your DB according to variables into `src/main/resources/application.yml` file <br />
Set up `APP_ID` variable in your environment <br />
After deploy you can find Swagger UI by URL `host:8701/api/swagger-ui/index.html?configUrl=/api/v3/api-docs/swagger-config` <br />

### For running all tests:
Yuo will need to set up `APP_ID` variable in your environment

### Footnote:
It's possible to generate the API key (`APP_ID`) going to the [OpenWeather Sign up](https://openweathermap.org/appid) page.
