FROM adoptopenjdk/openjdk11:ubi

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

EXPOSE 5050

ENTRYPOINT ["java", "-jar", "/app.jar"]
