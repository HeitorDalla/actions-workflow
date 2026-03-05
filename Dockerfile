# Imagem para gerar o .JAR
FROM maven:3.9.4-eclipse-temurin-21-alpine as build
WORKDIR /app

COPY pom.xml .
RUN mvn -B -DskipTests dependency:go-offline
COPY src ./src

RUN mvn clean package -DskipTests

# Stage para usar o .JAR
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080
CMD ["java", "-jar", "app.jar"]