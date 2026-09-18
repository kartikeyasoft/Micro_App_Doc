FROM maven:3.9-eclipse-temurin-17 AS build

WORKDIR /build

COPY pom.xml .
RUN mvn dependency:go-offline

COPY src ./src  
RUN mvn package -DskipTests


FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/auth-service.war /app/auth-service.war
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/auth-service.war"]


