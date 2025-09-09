FROM openjdk:17
WORKDIR /app
COPY /target/bankcards.jar /app
ENTRYPOINT ["java", "jar", "bankcards.jar"]