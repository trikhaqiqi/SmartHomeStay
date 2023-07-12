FROM maven:3-jdk-11 AS MAVEN_BUILD

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:11

COPY --from=MAVEN_BUILD /app/target/smarthome.jar /smarthome.jar

RUN cp /usr/share/zoneinfo/Asia/Jakarta /etc/localtime && echo "Asia/Jakarta" > /etc/timezone

ENTRYPOINT ["java", "-jar", "smarthome.jar"]