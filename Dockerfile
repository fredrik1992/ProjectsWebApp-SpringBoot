FROM openjdk:14-alpine

ARG WAR_FILE=./target/*.jar

COPY ${WAR_FILE} webapp.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "webapp.jar"]