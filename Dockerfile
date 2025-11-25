
FROM amazoncorretto:21.0.3-alpine3.19

WORKDIR /app


COPY target/*.jar app.jar


ENTRYPOINT ["java", "-jar", "app.jar"]