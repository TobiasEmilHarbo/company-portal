FROM openjdk:17-alpine
COPY build/libs/company-portal-0.0.1-SNAPSHOT.jar company-portal-0.0.1.jar
ENTRYPOINT ["java","-jar","/company-portal-0.0.1.jar"]
