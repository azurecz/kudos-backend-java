FROM openjdk:11-slim

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8


RUN { \
		echo '#!/bin/sh'; \
		echo 'set -e'; \
		echo; \
		echo 'dirname "$(dirname "$(readlink -f "$(which javac || which java)")")"'; \
	} > /usr/local/bin/docker-java-home \
	&& chmod +x /usr/local/bin/docker-java-home
ENV JAVA_HOME /usr/lib/jvm/java-11-openjdk/jre
ENV PATH $PATH:/usr/lib/jvm/java-11-openjdk/jre/bin:/usr/lib/jvm/java-11-openjdk/bin


EXPOSE 8080
ADD /target/@application.version@ kudos-be-java.jar
ENTRYPOINT ["java","-jar","Kudos-service.jar"]