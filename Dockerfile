# Stage 1: Build jar
FROM alpine:latest as builder
RUN apk update && apk upgrade && apk add openjdk8
WORKDIR ~/applicationRepo
COPY . .
RUN ./gradlew build

# Stage 2: Run the built jar in alpine
FROM openjdk:latest
RUN apk update && apk upgrade && apk add openjdk8
COPY --from=builder ~/applicationRepo/build/libs/application.jar application.jar
ENTRYPOINT ["java", "-jar", "application.jar"]
