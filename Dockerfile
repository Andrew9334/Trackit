FROM openjdk:17-jdk-slim
WORKDIR /app
COPY pom.xml /app
RUN ./mvnw dependency:go-offline
COPY . /app
RUN ./mvnw clean package -DskipTests
EXPOSE 8080
CMD ["java", "-jar", "target/trackit-0.0.1-SNAPSHOT.jar"]
