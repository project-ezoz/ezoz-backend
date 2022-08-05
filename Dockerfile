FROM openjdk:11-jre

COPY build/libs/project-ezoz-api-0.0.1-SNAPSHOT.jar ezoz-api-sever.jar

ENTRYPOINT ["java", "-jar", "ezoz-api-sever.jar"]