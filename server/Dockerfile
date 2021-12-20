

FROM adoptopenjdk:11-jre-hotspot

EXPOSE 8080

# 언어 설정
RUN locale-gen ko_KR.UTF-8
ENV LANG ko_KR.UTF-8
ENV LANGUAGE ko_KR.UTF-8
ENV LC_ALL ko_KR.UTF-8

# TimeZone 설정
ENV TZ Asia/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

RUN mkdir /opt/app
COPY module-api/build/libs/module-api-1.0.jar /opt/app

ARG PASSWORD
ENV PASSWORD ${PASSWORD}

CMD ["java","-jar", \
"-Dspring.profiles.active=dev", \
"-Djasypt.encryptor.password=${PASSWORD}", \
"/opt/app/module-api-1.0.jar"]