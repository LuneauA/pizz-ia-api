FROM shyndard/graalvm-native-build:java11-maven363 AS build

WORKDIR /build/
COPY . .
RUN ./mvnw package -Dquarkus.package.type=uber-jar

FROM openjdk:11.0-jdk-buster

WORKDIR /work/
COPY --from=build --chown=1001:root /build/target/*-SNAPSHOT-runner.jar /work/application.jar

CMD ["java", "-jar", "application.jar"]