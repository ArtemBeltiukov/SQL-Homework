FROM java:8
COPY . /usr/src
WORKDIR /usr/src
CMD ["/bin/sh", "-c", "java -jar web-server-0.1.0.jar"]