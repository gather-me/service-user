FROM gradle:jdk17 AS build

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build --no-daemon

FROM openjdk:17-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/user.jar /app/user.jar

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/user.jar"]