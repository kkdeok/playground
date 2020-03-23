#!/usr/bin/env bash

docker build --no-cache -t macro .
docker run -p 4444:4444 --shm-size=2g --name macro_app macro