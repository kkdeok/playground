#!/usr/bin/env bash

DEBUG="-Xms256m -Xmx256m -Dcom.sun.management.jmxremote=true -Dcom.sun.management.jmxremote.port=11619 -Dcom.sun.management.jmxremote.ssl=false -Dcom.sun.management.jmxremote.authenticate=false"

java $DEBUG \
  -cp /program/exercise/macro/macro-service-1.0-SNAPSHOT-all.jar \
  com.doubleknd26.exercise.macro.mask.MaskMacro --config-path config/prod.yml