### About
 - Solactive Coding Challenge

### Running The Project
 - Checkout Project - git clone https://github.com/staramitks/Solactive-Price-Monitor.git
 - Go To Project Directory and Execute ```maven clean install```
 - Execute ```java -Dserver.port=8090 -jar target/solactive-price-tracker.jar```
 - Check if project is running fine by checking http://localhost:8090/home - It should show Hello World

## Improvements
 - If I had more time, I would like to test it with load testing tools like JMeter and also check on performance benchmarks
 
## Challenge Experience
 - It was quite an interesting challenge especially with 60 seconds timeframe for real time data fething was pretty good.
 

## Information
 - API Information is provided by Swagger UI Integration.
 - http://localhost:8090/swagger-ui.html

## Assumptions
 - If no statistics is available for last 60 seconds then 404 status code will be returned with JSON object as 0 for all statistics keys.
 - Records older than 60 seconds are not required to be persisted.
 - If invalid request comes where Keys of tickr are null or invalid then Invalid Request code - 400 will be returned 

## Technical Specifications
 - SpringBoot Version - 2.5.0
 - Java Version - 11.0.8-win64
 - Build - Maven
 

 
 