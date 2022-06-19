FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} movie-catalog-service.jar
ENTRYPOINT ["java", "-jar", "/movie-catalog-service.jar"]
EXPOSE 8081