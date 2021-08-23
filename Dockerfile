FROM openjdk:11 AS build
WORKDIR /source
COPY . /source
RUN ./gradlew --no-daemon distZip -x test

FROM openjdk:11
WORKDIR /kosl
COPY --from=build /source/build/distributions/kosl.zip /kosl/kosl.zip
RUN unzip kosl.zip && rm kosl.zip && mv kosl/* . && rm -rf kosl
WORKDIR /source
ENTRYPOINT ["/kosl/bin/kosl"]
