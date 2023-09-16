FROM openjdk:17-jdk-slim as builder

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle . 
COPY src src
RUN chmod +x ./gradlew
RUN ./gradlew bootJar

FROM openjdk:17-jdk-slim
COPY --from=builder build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=aws","/app.jar"]
EXPOSE 8080

# docker build -t soljigi . 

# docker run -p 8080:8080 
# -d 
# -e SPRING_DATASOURCE_URL=jdbc:mysql://이씨투주소:3306/디비스키마이름
# -e SPRING_DATASOURCE_PASSWORD=절대올려선안될비밀번호
# 도커이밎명


# docker run --detach
# --env MYSQL_ROOT_PASSWORD="절대올려선안될비밀번호"
# --env MYSQL_USER="soljigi"
# --env MYSQL_PASSWORD="절대올려선안될비밀번호"
# --name "mysql-db"
# --publish 3306:3306
# mysql;