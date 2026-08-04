FROM maven:3.8-eclipse-temurin-17-alpine AS BUILD
WORKDIR /app
COPY . .

RUN mvn clean install -DskipTests
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

COPY --from=build /app/target/*.jar app.jar

EXPOSE 1750

CMD ["java", "-jar", "/app/bff-agendador-tarefas.jar"]