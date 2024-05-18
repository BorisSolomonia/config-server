FROM maven:latest
WORKDIR /api-gateway

COPY . /api-gateway
COPY . /src/main/resources/application.yaml
EXPOSE 8765
ENTRYPOINT ["java", "-jar", "/api-gateway/target/api-gateway-0.0.1-SNAPSHOT.jar"]