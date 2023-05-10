#!/bin/sh

docker create --name quote -p 8080:8080 -m 2g  quote:latest
docker start quote
docker logs -f quote
