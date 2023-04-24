FROM openjdk:8-jre

#设置时区
RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone


ADD ./target/  /opt/App/


WORKDIR /opt/App/

ENTRYPOINT ["sh","-c","java -jar backend.jar --spring.profiles.active=prod"]