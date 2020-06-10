#!/usr/bin/env bash

PASSPHARSE=$1
sudo docker build --build-arg passphrase=${PASSPHARSE} -t macro .
sudo docker run -d -p 4444:4444 -p 11619:11619 --shm-size=128m -v /tmp/logs:/tmp/logs --name macro_app macro