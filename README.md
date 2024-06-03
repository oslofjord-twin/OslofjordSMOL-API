# OslofjordSMOL-API

This is a Kotlin-based API for the OslofjordSMOL project. It uses Spring Boot for the backend and Gradle for build automation.

## Getting Started

To get the project up and running on your local machine you need to follow the instructions below.

### Prerequisites for development

- Java 17 (required for the execution)
- Kotlin 1.9.23
- Gradle
- IntelliJ IDEA 2024.1.1 or any other IDE that supports Kotlin and Spring Boot

### Installing

Clone the repository

```bash
git clone https://github.com/sievericcardo/OslofjordSMOL-API.git
```

#### Build the project

Navigate into the project directory

```bash
cd OslofjordSMOL-API
```

Build the project to get the proper build file with

```bash
./gradlew bootJar
```

#### Run the project

Run the project after building using `Java`

```bash
java -jar build/libs/smol-api-${version}.jar
```

### Work with docker

You can also run the project using Docker. To do so, you need to build the Docker image and run it. Note that you need to build the `jar` file with `./gradlew bootJar` before building the Docker image.

#### Building the Docker image

```bash
docker build -t smol-api .
```

#### Running the Docker image

```bash
docker run -p 8888:8888 smol-api
```
