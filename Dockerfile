# Stage 1: Build jar
FROM alpine:latest as builder
RUN apk update && apk upgrade
RUN apk add openjdk8
WORKDIR ~/applicationRepo
COPY . .
RUN ./gradlew build

# Stage 2: Run the built jar in alpine
FROM openjdk:8-jre-alpine
COPY --from=builder ~/applicationRepo/build/libs/application.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
