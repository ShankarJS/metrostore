# Metrostore SOAP Web Services Project

This project is built to simulate enterprise architecture similar to Atlas system.

## Tech Stack
- Java 11
- WildFly 30
- SOAP Web Services (JAX-WS)
- EJB (Stateless Beans)
- JPA (EclipseLink)
- PostgreSQL

## Modules
- persistence (JPA entities)
- service (EJB business logic)
- web (SOAP endpoints)

## Features
- Product creation via SOAP API
- JPA integration with PostgreSQL
- Multi-module Maven architecture

## Setup
1. Configure datasource in WildFly: java:/jdbc/MetrostoreDS
2. Deploy WAR
3. Access WSDL:
   http://localhost:8080/metrostore/ProductWebService?wsdl
