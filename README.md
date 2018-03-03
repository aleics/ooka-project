# ooka-project

[![Build Status](https://api.travis-ci.org/aleics/ooka-project.svg?branch=develop)](https://travis-ci.org/aleics/chatter)

A product store application for academic purposes.

## Steps
 - Database connection in product store (ALC).
 - Chat module in product store web (NIG).
 - Chat backend as docker container (NIG).
 - User management backend (ALC).
 - Log in page module (NIG).
 - Product view (ALC).
 - ~~Add CI : travis (ALC)~~.

 ## Branch topology
 It will be defined a single `develop` branch. The different feature branches will be divided by project topology as defined in the next section.

### project topologies
 - product-store-web: `psw-dev`
 - product-store: `ps-dev`
 - product-deploy: `d-dev`
 - user-management: `um-dev`
 - chat: `c-dev`

### feature branch
 Create a new branch for features:
 - [ project name ] / [ feature name ]. For example: `psw/init-chat`.

 ## Meeting schedule
 - Tuesday
 - Friday
