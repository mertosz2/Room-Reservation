#stage-1 build artifact
FROM amazoncorretto:17.0.9-alpine3.18 as builder
WORKDIR /app
ADD . .
RUN chmod +x ./gradlew
RUN ["./gradlew", "bootJar"]

#
FROM gcr.io/distroless/java17-debian12:latest
WORKDIR /app
EXPOSE 8888
COPY --from=builder /app/build/libs/UTCC-room-reservation.jar UTCC-room-reservation-0.0.1-SNAPSHOT.jar

ADD https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.26/mysql-connector-java-8.0.26.jar /app/mysql-connector-java.jar

ENTRYPOINT ["java", "-jar", "UTCC-room-reservation-0.0.1-SNAPSHOT.jar"]