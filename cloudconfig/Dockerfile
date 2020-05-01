FROM adoptopenjdk/openjdk11:ubi

ARG JAR_FILE=target/*.jar

COPY ${JAR_FILE} app.jar

# private virtual EC2
ENV RABBITMQ_HOST="172.31.37.225" \
    RABBITMQ_USER_PORT=5672 \
    EURIKA_SERVER_HOST="172.31.37.225"

EXPOSE 7070

ENTRYPOINT ["java", "-jar", "/app.jar"]
