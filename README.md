# Company Portal

This application allows for creating, listing and manipulation of companies.

This application contains to APIs:
- One for creating, listing and manipulation companies and the relationship with owners
- One for validating a social security number

## Get started
To run the application with docker run the following commands in the project directory:

> ./gradlew build
>
> docker build -t company-portal . 
> 
> docker run -p 8080:8080 company-portal .



## API Requests

### List all companies:
> curl --location 'http://localhost:8080/companies/'

### Create new company
> curl --location 'http://localhost:8080/companies/' \
--header 'Content-Type: application/json' \
--header 'role: ADMIN' \
--data '{
"name": "Christensen ApS",
"country": "DK",
"phoneNumber": "20 22 07 93",
"ownerIds": []
}'

### View one company
> curl --location 'http://localhost:8080/companies/{:company-id}'

### Update one company
> curl --location --request PUT 'http://localhost:8080/companies/{:company-id}' \
--header 'Content-Type: application/json' \
--header 'role: OWNER' \
--data '{
"name": "ACME",
"country": "DK",
"phoneNumber": "23 48 67 28",
"ownerIds": [
"61317f27-3cdf-4758-a834-f692878f8c06"
]
}'

### List owner detail of company
> curl --location 'http://localhost:8080/companies/{:company-id}/owners' \
--header 'role: OWNER'

### Add owner to company
> curl --location 'http://localhost:8080/companies/ed78a68b-ca51-47c9-a925-c94c2dbb73f8/owners' \
--header 'Content-Type: application/json' \
--header 'role: OWNER' \
--data '{
"socialSecurityNumber": "12345678"
}'

### Create social security checker
> curl --location 'http://localhost:8080/social-security-number-checkers/' \
--header 'Content-Type: application/json' \
--data '{
"socialSecurityNumber": "12345678"
}'

## Authenticating
I would propose to use some kind of OAuth 2 token based authentication.