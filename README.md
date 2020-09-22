# POC APIs

## API Specification

### API Standard

Follow the RESTful API standard. Eg:
1. GET /wms/api/v1/inbound/asn/{asnId}
--> 200 OK.
2. GET /wms/api/v1/inbound/asn?wh_id={whId}&page_no={pageNo}&page_size={pageSize}
--> 200 OK.
3. POST /wms/api/v1/inbound/asn
--> 201 Created.
4. PUT /wms/api/v1/inbound/asn/{asnId}
--> 200 OK.
5. DELETE /wms/api/v1/inbound/asn/{asnId}
--> 204 No Content.

### Version Control

By mixing the version number into the URL. Eg:
1. GET /wms/api/v1/inbound/asn/{asnId}
2. GET /wms/api/v2/inbound/asn/{asnId}

### Domain Name Management

1. dev.xxx.com.cn
2. test.xxx.com.cn
3. uat.xxx.com.cn
4. prod.xxx.com.cn

### Documentation

Swagger via OpenAPI 3

### API Management

Follow the global standard and integrate into the global uniform API management platform.

## APM

### Logging

1. ELK

### Monitoring

1. Prometheus 
2. Grafana

### Service Chain Tracing

1. Jaeger

## Coding Guideline

Style Guide
1. [Google Java Style Guide](https://google.github.io/styleguide/javaguide.html)

Java Coding Guideline
1. Alibaba Java Coding Guideline

### Project Structure

    root
        |--- xxx-api                            Interface module.
            |--- src/main
                |--- java
                    |--- api                    Interface.
                    |--- dto                    Data transfer objects.
        |--- xxx-provider                       Implementation module.
            |--- src/main
                |--- java
                    |--- component              Java components.
                    |--- config                 Spring boot configuration.
                    |--- controller
                        |--- v1                 Controllers for V1.
                        |--- v2                 Controllers for V2.
                    |--- mapper                 DAOs.(if use Mybatis)
                    |--- repository             DAOs.(if use JPA)
                    |--- model                  Data entities.
                    |--- service                Business Services.
                |--- resources
                    |--- mapper                 Mybatis configuration.(if use Mybatis)
                    |--- static
                        |--- i18n               Message properties for i18n.
                    |--- application.yml        Spring boot config yaml.
                    |--- bootstrap.yml          Spring boot bootstrap config yml.
                    |--- logback-spring.xml     Logback config xml.
        |--- .gitignore                         Git management configuration.
        |--- deployment.yml                     K8s deployment configuration.
        |--- Dockerfile                         Docker related configuration.
        |--- pom.xml                            Maven related configuration.
        |--- README.md                          Description of the project.

### Parameter Validation

Follow the Spring Boot Validation Guideline.

### Exception Handling

GlobalExceptionHandler via controller advice.

### i18n

Follow the Spring Boot i18n Guideline.

## CICD

1. Maven
2. Sonar
3. Nexus
4. Jenkins

## Containerization

### Docker

1. Dockerfile

### K8s

1. ConfigMap
2. Deployment
3. Service
4. Ingress related