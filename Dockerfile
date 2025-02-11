#Dockerfile
FROM eclipse-temurin:17.0.7_7-jre-alpine
ADD target/policy-client.war policy-client.war
RUN sh -c 'touch /policy-client.war'
ENTRYPOINT [ "java","-war","/policy-client.war"]