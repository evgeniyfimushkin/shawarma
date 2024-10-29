FROM gradle:8.10.2-jdk21-alpine AS build
WORKDIR /app
COPY . ./
RUN cd shawarma
RUN gradle clean bootJar --info

FROM openjdk:21-slim
COPY --from=build /app/shawarma/build/libs/*.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]