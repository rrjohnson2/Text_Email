FROM openjdk

COPY ./ home/app
WORKDIR /home/app


ADD target/sms-0.0.1-SNAPSHOT.jar sms-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","sms-0.0.1-SNAPSHOT.jar"]
