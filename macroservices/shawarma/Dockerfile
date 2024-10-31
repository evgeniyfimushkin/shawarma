FROM gradle:8.10.2-jdk21-alpine AS build
WORKDIR /app
COPY . ./

RUN gradle clean bootJar --info

FROM openjdk:21-slim
COPY --from=build /app/build/libs/*.jar application.jar

ENV SPRING_PROFILES_ACTIVE=prod

ENTRYPOINT ["java", "-jar", "application.jar"]