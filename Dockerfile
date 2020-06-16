FROM openjdk:11.0.6-slim
EXPOSE 8089
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} school-search.jar
#ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar /school-search.jar"]
ENTRYPOINT ["java","-jar","/school-search.jar"]