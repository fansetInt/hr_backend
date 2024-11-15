FROM openjdk:17-jdk

WORKDIR /app

COPY target/DMS-0.0.1-SNAPSHOT.jar /app/DMS.jar

CMD [ "java", "-jar", "DMS.jar"]