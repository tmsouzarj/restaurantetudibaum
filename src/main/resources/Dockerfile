FROM amazoncorretto:21-al2023

WORKDIR /aplicacao

COPY pom.xml .

COPY src ./src

RUN mvn clean package

ARG arquivo = target/*.jar

COPY ${arquivo} restaurantetudibaum.jar

EXPOSE 8080

# Colocar as configurações sobre escalabilidade. 

ENTRYPOINT [ "java", "-jar", "/restaurantetudibaum.jar" ]
