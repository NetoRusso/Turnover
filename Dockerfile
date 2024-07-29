FROM ubuntu:latest AS build

RUN apt-get update
RUN apt-get install -y openjdk-22-jdk maven
ENV JAVA_HOME /usr/lib/jvm/java-22-openjdk-amd64
RUN exportJAVA_HOME
COPY . .

RUN mvn clean install

FROM openjdk:22-jdk-slim

EXPOSE 8080

COPY --from=build /target/turnover-0.0.1.jar app.jar

ENTRYPOINT ["java","-jar","app.jar"]