#베이스 이미지 설정
FROM adoptopenjdk/openjdk11

ARG JAR_FILE_PATH=target/*.jar

#JAR_FILE_PATH를 복사하여 app.jar라는 명으로 바꿀것이다.
COPY ${JAR_FILE_PATH} app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]