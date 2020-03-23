#!/usr/bin/env bash

sudo docker build --no-cache -t macro .
sudo docker run -p 4444:4444 --shm-size=2g -v /tmp/logs:/tmp/logs --name macro_app macro