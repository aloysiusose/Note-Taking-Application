# Note-Taking-Application
This is an application for taking notes developed in java using Sringbootframe work

## Project Description
This application is a secured application where a user must be registered and logged in to add and retrieve notes the user have saved.
A user can only retrieve the note he/she saves. He/she cannot see notes by other users. This was made possible by using the the details of the logged in user to persist and retrieve notes.
The user and password service is used for user authenitication
Partial testing was carried out on the service layers of the project


## Not included
1. For this version, Roles and authorities were not inplemented in the security configurations
2. Registration validation was not also implemented( however, email verification was implemented to avoid multiple registation with a single email)
3. Security test 

## Motivation
The motivation of this project is to consolidate my learnings and journey in software development using Spring frame work and Spring boot.

## How to use this project
1. Clone or Fork this repo and edit the application.properties file to reflect your environment (Data base implementation)
2. use an API testing tool like postman to register by hitting the url: localhost:{portnumber}/register and hittiing the various url on the controllers , you can access the project.
3. kindly raise an issue if there is any issue that is related to what i have implemented. if I have not implemented them, include them in yours.
4. if you are a front-end developer, you can implement a front end for this project and notify me.
