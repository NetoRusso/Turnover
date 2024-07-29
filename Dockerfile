FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-17-jdk
COPY . .

RUN apt-get install maven -y
RUN mvn clean install

FROM openjdk-17-jdk-slim

EXPOSE 8080

COPY --from=build /target/turnover-0.0.1.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]