# zaykflux-api


## About this project

The application's main objective is to empower service providers in its Customer Service and Support operations.
The application serves the Customer Support domain, and can generally accommodate customer support operations of various segments - whatever products or services.

In general, its functionalities address the following possibilities:

Customers call the ticket opening channel;

Monitoring and control of these tickets by service providers;

Registration of appointments of all actions/works related to any open tickets;

Communication channel - through application - between customers and service providers.

## Sobre o projeto

Esta aplicaçao tem como principal objetivo empoderar prestadores de serviços em suas operações de atendimento e Suporte a Cliente.

A aplicação atende ao domínio de Suporte ao Cliente, podendo acomodar genericamente operações de suporte a cliente de diversos seguimentos - sejam quaisquer produtos ou serviços.

Em linhas gerais, suas funcionalidades abordam as seguintes possibilidades:

- Canal de abertura de tickets de chamado por parte de clientes;

- Monitoramento e controle destes tickets por parte de prestadores de serviço;

- Registro de apontamentos de todas as atuações/atendimentos referentes a eventuais tickets abertos;

- Canal de comunicação - por meio aplicação - entre clientes e prestadores de serviço.



## Technologies----------------------------------

This project is centered on concepts of - mainly - DDD, SOLID and also some concepts of clean architecture.



Below is a sketch of the model in class diagram

![UML](https://github.com/FelipeC91/Zaykflux-api/blob/master/uml-z-flux.jpeg)
This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Pnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Pnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/zaykflux-api-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- Flyway ([guide](https://quarkus.io/guides/flyway)): Handle your database schema migrations
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Provided Code

### Hibernate ORM

Create your first JPA entity

[Related guide section...](https://quarkus.io/guides/hibernate-orm)

[Related Hibernate with Panache section...](https://quarkus.io/guides/hibernate-orm-panache)


### RESTEasy JAX-RS

Easily start your RESTful Web Services

[Related guide section...](https://quarkus.io/guides/getting-started#the-jax-rs-resources)
