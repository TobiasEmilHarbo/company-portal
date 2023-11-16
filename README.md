# Company Portal

This application allows for creating, listing and manipulation of companies.

This application contains to APIs:
- One for creating, listing and manipulation companies and the relationship with owners
- One for validating a social security number

## Get started
To run the application with docker run the following commands in the project directory:

> ./gradlew bootJar
>
> docker build -t company-portal . 
> 
> docker run -p 8080:8080 company-portal .

## Project structure
This project is structured with inspiration from Domain Driven Design and contains four main packages:

`application` package containing application specific needed for setup such as API registration

`api` package contains all APIs exposed to the consumer of this application. 

`domain` package contains all business logic through services handling different user scenarios.

`external` package contains all logic which is not directly described by the domain such as external API services and data storage

## API Requests

### List all companies
> curl --location 'http://localhost:8080/companies/'

### Create new company
> curl --location 'http://localhost:8080/companies/' \
> --header 'Content-Type: application/json' \
> --header 'role: ADMIN' \
> --data '{
"name": "Christensen ApS",
"country": "DK",
"phoneNumber": "20 22 07 93",
"ownerIds": []
> }'

### View one company
> curl --location 'http://localhost:8080/companies/{:company-id}'

### Update one company
> curl --location --request PUT 'http://localhost:8080/companies/{:company-id}' \
> --header 'Content-Type: application/json' \
> --header 'role: OWNER' \
> --data '{
"name": "ACME",
"country": "DK",
"phoneNumber": "23 48 67 28",
"ownerIds": [
"{:owner-id}"
]
> }'

### List owner detail of company
> curl --location 'http://localhost:8080/companies/{:company-id}/owners' \
> --header 'role: OWNER'

### Add owner to company
> curl --location 'http://localhost:8080/companies/{:company-id}/owners' \
> --header 'Content-Type: application/json' \
> --header 'role: OWNER' \
> --data '{
"socialSecurityNumber": "12345678"
> }'

### Create social security number checker
> curl --location 'http://localhost:8080/social-security-number-checkers/' \
> --header 'Content-Type: application/json' \
> --data '{
"socialSecurityNumber": "12345678"
> }'

## Authenticating
I would propose to use some kind of OAuth 2 token based authentication.

OAuth 2 token based authentication fairly well documented.

Depending on th# Company Portal

This application allows for creating, listing and manipulation of companies.

This application contains to APIs:
- One for creating, listing and manipulation companies and the relationship with owners
- One for validating a social security number

## Get started
To run the application with docker run the following commands in the project directory:

> ./gradlew bootJar
>
> docker build -t company-portal .
>
> docker run -p 8080:8080 company-portal .



## API Requests

### List all companies
> curl --location 'http://localhost:8080/companies/'

### Create new company
> curl --location 'http://localhost:8080/companies/' \
> --header 'Content-Type: application/json' \
> --header 'role: ADMIN' \
> --data '{
"name": "Christensen ApS",
"country": "DK",
"phoneNumber": "20 22 07 93",
"ownerIds": []
> }'

### View one company
> curl --location 'http://localhost:8080/companies/{:company-id}'

### Update one company
> curl --location --request PUT 'http://localhost:8080/companies/{:company-id}' \
> --header 'Content-Type: application/json' \
> --header 'role: OWNER' \
> --data '{
"name": "ACME",
"country": "DK",
"phoneNumber": "23 48 67 28",
"ownerIds": [
"{:owner-id}"
]
> }'

### List owner detail of company
> curl --location 'http://localhost:8080/companies/{:company-id}/owners' \
> --header 'role: OWNER'

### Add owner to company
> curl --location 'http://localhost:8080/companies/ed78a68b-ca51-47c9-a925-c94c2dbb73f8/owners' \
> --header 'Content-Type: application/json' \
> --header 'role: OWNER' \
> --data '{
"socialSecurityNumber": "12345678"
> }'

### Create social security number checker
> curl --location 'http://localhost:8080/social-security-number-checkers/' \
> --header 'Content-Type: application/json' \
> --data '{
"socialSecurityNumber": "12345678"
> }'

## Authenticating
I would propose to use some kind of OAuth 2 token based authentication.

OAuth 2 token based authentication fairly well documented.

Depending on what services should interact with this application different grand type could be implemented.

It could be Authorization Code grant if the service is interacted with by a frontend or Client Credentials Grant Type if the services purely exposes functionality to other micro services.