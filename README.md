# pizzia api
![Version](https://img.shields.io/badge/version-0.0.1-blue.svg?cacheSeconds=2592000)

> This API manages the pizzas processing.

When creating a pizza :
- base64 image is uploaded to a S3 bucket
- fake an IA with message and success result
- save everything in a database
- return results

## Explanation

Here is a deployment diagram with explanations of the process.

![alt text](https://zupimages.net/up/21/05/fdy0.png)

1. Take a picture of a pizza
2. Sending the image to an API hosted in the cloud.
3. The image is saved in an s3 bucket.
4. Informations are saved in a database. 
5. Raspberry pulls informations, turn on the red led if successful otherwise turn on the green led and show message on screen.
6. Call API on PI zero to inform that the next pizza is ready to be photographed.

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
docker build -t pizzia-api .
```
### Run image (don't forget env var)

```shell script
docker run -i --rm -p 8080:8080 pizzia-api
```

## Environment variables

| NAME           | DESCRIPTION          |
|----------------|----------------------|
| S3_CRED_ID     | S3 credential id     |
| S3_CRED_SECRET | S3 credential secret |
| S3_URL         | S3 url               |
| S3_REGION      | S3 region            |
| S3_BUCKET      | S3 bucket            |
| DB_USERNAME    | Database username    |
| DB_PASSWORD    | Database password    |
| DB_HOSTNAME    | Database hostname    |
| DB_PORT        | Database port        |
| DB_NAME        | Database name        |