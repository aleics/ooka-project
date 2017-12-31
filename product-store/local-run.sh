#!/bin/bash -x

exec java ${JAVA_OPTS} -jar ./target/productstore-0.0.1-SNAPSHOT.jar server config.yml