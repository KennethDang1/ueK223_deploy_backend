#!/bin/bash

# Pull the latest image from Docker Hub
docker pull dockerknet/backend-app:latest

# Stop existing container (falls einer läuft)
docker stop backend-container || true
docker rm backend-container || true

# Start a new container
docker run -d -p 8909:8080 --name backend-container dockerknet/backend-app:latest
