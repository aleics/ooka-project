# ooka-project

[![Build Status](https://api.travis-ci.org/aleics/ooka-project.svg?branch=develop)](https://travis-ci.org/aleics/chatter)

A product store application for academic purposes.

## Steps
 - ~~Database connection in product store (ALC).~~
 - Chat module in product store web (NIG).
 - ~~Chat backend as docker container (NIG).~~
 - ~~User management backend (ALC).~~
 - ~~Log in page module (NIG).~~
 - ~~Product view (ALC).~~
 - ~~Add CI : travis (ALC)~~.
 - ~~Authentication in all microservices connecting to usermngmt (ALC).~~
 
## Technologies
For this project, a number of different technologies has been used, to prove that in a microservice-working environment, each team can decide internally whatever technology will be used and should not depend on other teams. The technologies used for the implementation of an specific service should be responsible to the team. Thus, the chosen technology will be the one that fits the best for the new service. The following technologies have been used for the different services of this project:

 - product-store:
   - Java 8
   - Dropwizard
   - GraphQL
   - PostgreSQL
   - JDBI
 - product-store-web:
   - Typescript
   - Angular
 - user-managemnet:
   - Java 8
   - Spring
   - MongoDB
   - JWT
 - chat:
   - Java 8
   - Spring
   - Websockets

Of course, Docker has been used for the deployment of each microservice into containers.

 ## Branch topology
 It will be defined a single `develop` branch. The different feature branches will be divided by project topology as defined in the next section.

### project topologies
 - product-store-web: `psw`
 - product-store: `ps`
 - product-deploy: `d`
 - user-management: `um`
 - chat: `c`

### feature branch
 Create a new branch for features:
 - [ project name ] / [ feature name ]. For example: `psw/init-chat`.

 ## Meeting schedule
 - Tuesday
 - Friday
