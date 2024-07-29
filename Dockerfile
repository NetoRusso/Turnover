# FROM ubuntu:latest AS build
#
# RUN apt-get update
# RUN apt-get install -y openjdk-22-jdk maven
# ENV JAVA_HOME /usr/lib/jvm/java-22-openjdk-amd64
# RUN exportJAVA_HOME
# COPY . .
#
# RUN mvn clean install
#
# FROM openjdk:22-jdk-slim
#
# EXPOSE 8080
#
# COPY --from=build /target/turnover-0.0.1.jar app.jar
#
# ENTRYPOINT ["java","-jar","app.jar"]


# Etapa de build
FROM openjdk:22-slim AS build

# Atualizar pacotes e instalar Maven
RUN apt-get update && apt-get install -y maven

# Copiar todos os arquivos da aplicação para o contêiner
COPY . .

# Executar a construção do Maven sem testes
RUN mvn clean install -DskipTests

# Etapa de runtime
FROM openjdk:22-slim

# Expor a porta 8080
EXPOSE 8080

# Copiar o artefato construído da etapa de build para a etapa de runtime
COPY --from=build /target/turnover-0.0.1.jar app.jar

# Definir o ponto de entrada para executar o jar
ENTRYPOINT ["java", "-jar", "app.jar"]