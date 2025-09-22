FROM openjdk:21-jdk-slim
WORKDIR /app

# JAR file ko container me copy karo
COPY target/apna-bank-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

# Container start hone par jar run karo
ENTRYPOINT ["java", "-jar", "app.jar"]
