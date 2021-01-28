##############
# BUILD STEP #
##############

# See https://github.com/Shyndard/graalvm-native-build
FROM shyndard/graalvm-native-build:java11-maven363 AS build

WORKDIR /build/
COPY . .
RUN ./mvnw package -Pnative

######################
# CONFIGURATION STEP #
######################

FROM registry.access.redhat.com/ubi8/ubi:8.3

WORKDIR /work/
RUN chown 1001 /work \
    && chmod "g+rwX" /work \
    && chown 1001:root /work

COPY --from=build --chown=1001:root /build/target/*-runner /work/application

USER 1001

CMD ["./application", "-Dquarkus.http.host=0.0.0.0"]
