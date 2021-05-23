### About
 - Solactive Coding Challenge

### Running The Project
 - Checkout Project - git clone https://github.com/staramitks/Solactive-Price-Monitor.git
 - Go To Project Directory and Execute ```maven clean install```
 - Execute ```java -Dserver.port=8090 -jar target/solactive-price-tracker.jar```
 - Check if project is running fine by checking http://localhost:8090/home - It should show Hello World


## Information
 - API Information is provided by Swagger UI Integration.
 - http://localhost:8090/swagger-ui.html

## Assumptions
 - If no statistics is available for last 60 seconds then 404 status code will be returned with JSON object as 0 for all statistics keys.
 - Records older than 60 seconds are not required to be persisted

## Technical Specifications
 - SpringBoot Version - 2.5.0
 - Java Version - 11.0.8-win64
 - Build - Maven
 

 
 