FROM maven:3.5.2-jdk-8 AS MAVEN_BUILD
COPY pom.xml /tmp/
COPY src /tmp/src/
WORKDIR /tmp
RUN mvn package

FROM openjdk:8
EXPOSE 8080
COPY --from=MAVEN_BUILD /tmp/target/romannumeral-1.0-SNAPSHOT-jar-with-dependencies.jar romannumeral.jar
ENTRYPOINT ["java","-jar","romannumeral.jar"]
