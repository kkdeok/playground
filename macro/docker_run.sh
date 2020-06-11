#!/usr/bin/env bash

if [ $# -ne 1 ]; then
  echo '[ERROR] please add your gpg password as an args e.g) ./docker_run.sh {gpg_password}'
  exit 1 
fi

gpg_password=$1
echo '[INFO] your gpg password is' ${gpg_password}

# build docker
../gradlew clean :macro:dockerBuild -Ppw=${gpg_password}

# run docker
docker run -d -p 4444:4444 -p 11619:11619 --shm-size=128m -v /tmp/logs:/tmp/logs --name macro_app macro

