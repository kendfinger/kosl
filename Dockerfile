FROM openjdk:11 AS build
WORKDIR /source
COPY . /source
RUN ./gradlew --no-daemon distZip -x test

FROM openjdk:11
WORKDIR /app
COPY --from=build /source/build/distributions/kosl.zip /app/kosl.zip
RUN unzip kosl.zip && rm kosl.zip && mv kosl/* && rm -rf kosl
ENTRYPOINT /app/bin/kosl
