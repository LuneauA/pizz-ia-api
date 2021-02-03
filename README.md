# pizzia api
![Version](https://img.shields.io/badge/version-0.0.1-blue.svg?cacheSeconds=2592000)

> This API manages the pizzas processing.

When creating a pizza :
- base64 image is uploaded to a S3 bucket
- fake an IA with message and success result
- save everything in a database
- return results

## Install

```sh
./mvnw mvn install
```

## Run application in dev mode

```sh
./mvnw compile quarkus:dev
```

## Run tests

```sh
./mvnw test
```

## Packaging and running the application

### Without compiled dependencies

```shell script
./mvnw package
```

It produces the `*-SNAPSHOT-runner.jar` file in the `/target` directory and dependencies are copied into the `target/lib` directory.

### With compiled dependencies (fat-jar or über-jar)

```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application is runnable using `java -jar target/*-SNAPSHOT-runner.jar`.

## Creating a native executable

### With graalvm installed

```shell script
./mvnw package -Pnative
```
### Without graalvm installed

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

## Using docker

### Create image

```shell script
docker build -t sample-quarkus-restapi .
```
### Run image

```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```