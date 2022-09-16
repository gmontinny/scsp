FROM adoptopenjdk/openjdk11:latest 
ENV APP scsp.jar
RUN mkdir -p /opt
COPY ${APP} /opt
EXPOSE 8080
CMD java -Xms${JAVA_XMS:-512m} -Xmx${JAVA_XMX:-1024m} -jar /opt/${APP}