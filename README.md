# POC APIs

## Table of Contents

### API Specification

#### API Standard
Follow the RESTful API standard.

#### Version Control
By mixing the version number into the URL.

#### Documentation
Swagger via OpenAPI 3

#### API Management
Follow the global standard and integrate into the global uniform API management platform.

### APM

#### Logging
ELK

#### Monitoring
Prometheus

#### Service Chain Tracing

### Coding Specification

#### Project Structure
root
 |--- xxx-api           API of the business service.
        |--- src/main
                |--- java
 |--- xxx-provider      Implementation of the business service.
        |--- src/main
                |--- java
                |--- resources
 |--- .gitignore        Git management configuration.
 |--- deployment.yml    K8s deployment yaml.
 |--- Dockerfile        Docker related configuration.
 |--- pom.xml           Maven related configuration

#### Parameter Validation
Follow the Spring Boot Validation Guideline.

#### Exception Handling
GlobalExceptionHandler via controller advice.

#### i18n
Follow the Spring Boot i18n Guideline.

### Build
Maven

### Deployment

#### Docker

#### k8s