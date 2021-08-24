FROM docker.io/google/dart:latest AS build
WORKDIR /build
COPY server.dart /build/server.dart
RUN dart compile exe /build/server.dart -o /build/server

FROM docker.io/google/dart:latest
WORKDIR /app
COPY --from=build /build/server /app/server
CMD /app/server
EXPOSE 8080
