FROM ubuntu:latest

RUN <<EOF
    apt-get update
    apt-get install -y openjdk-17-jdk
EOF

COPY build/libs/* /app/smol-api.jar
WORKDIR /app

ENTRYPOINT ["java", "-jar", "smol-api.jar"]
EXPOSE 8888
