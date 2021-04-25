# gitHub
## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Installation](#installation)
* [Usage](#usage)

## General info
This project is a REST API using GitHub API to display user's public repositories - repositories' name and number of stars - in JSON format. It also provides total number of stars for all user's repositories\
Users and parking spaces data is kept in MySQL Database.\
\
Swagger UI was built to the project, so that it is easier to understand what endpoints are available.
  
## Technologies
Project is created with:
* Java 8
* Spring Boot
* Maven
* GitHub API
* Swagger UI
	
## Installation
To run this project, install it locally:

```
$ git clone git@github.com:hofman-iga/gitHub.git

Then run the following command in a terminal window (in the complete) directory:

$ mvnw spring-boot:run

```

## Usage

Endpoints available in application: 

GET
/repos/{userLogin} \
Endpoint enabling displaying list of user's repositories' name and number of stars in JSON format.\
  - userLogin - user's GitHub login

GET
/repos/stars/{userLogin} \
Endpoint enabling displaying total number of stars in all user's repositories.\
  - userLogin - user's GitHub login
