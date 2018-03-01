FROM openjdk:8

ADD /target/productstore-0.0.1-SNAPSHOT.jar /productstore.jar

ADD config.yml /

EXPOSE 8080 80 443

RUN java -jar productstore.jar server config.yml

