FROM docker.io/library/golang:1.17 AS build
WORKDIR /src
ENV CGO_ENABLED=0
COPY server.go /src
RUN go build -ldflags "-linkmode external -extldflags -static" -o /src/server /src/server.go

FROM scratch
WORKDIR /app
COPY --from=build /src/server /app/server
CMD /app/server
EXPOSE 8080
