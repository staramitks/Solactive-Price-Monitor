FROM openjdk:11.0.11-jdk
VOLUME /tmp
ARG JAR_FILE=target/solactive-price-tracker.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]