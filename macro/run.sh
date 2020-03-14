#!/usr/bin/env bash

BUILD=0
DEBUG="-Xms256m -Xmx256m -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=11619 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"B

while [[ $# -gt 0 ]]; do
  key="$1"
  case $key in
    -b)
      BUILD=1
      ;;
    *)
      # unknown option
      ;;
  esac
  shift # past argument or value
done


if [ "$BUILD" == "1" ]; then
  ../gradlew clean :macro:shadowJar
  [ $? -eq 0 ] || exit 1
fi

java $DEBUG \
  -cp ./build/libs/macro-service-1.0-SNAPSHOT-all.jar \
  com.doubleknd26.exercise.macro.mask.MaskMacro \
  --config-path config/prod.yml
