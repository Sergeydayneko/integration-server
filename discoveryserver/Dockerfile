FROM adoptopenjdk/openjdk11:ubi

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

ENV EURIKA_SERVER_HOST="18.219.140.164"

EXPOSE 5050

ENTRYPOINT ["java", "-jar", "/app.jar"]
