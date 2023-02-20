FROM openjdk:17
WORKDIR /app
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
VOLUME /root/.m2/repository
RUN ./mvnw dependency:go-offline
COPY src ./src
CMD ["./mvnw", "spring-boot:run"]
#
#FROM maven:3.8.7-amazoncorretto-17 as maven
#WORKDIR /app
#COPY ./pom.xml ./pom.xml
#VOLUME /root/.m2/repository
#RUN mvn dependency:go-offline
#COPY ./src ./src
#RUN mvn package && cp target/brahma-gateway-0.0.1-SNAPSHOT.jar app.jar
#
#FROM openjdk:17
#COPY --from=maven /root/.m2 /root/.m2
#WORKDIR /app
#COPY --from=maven /app/app.jar ./app.jar
#CMD ["java", "-jar", "/app/app.jar"]
