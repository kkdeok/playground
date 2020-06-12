FROM selenium/standalone-chrome:3.141.59-zirconium
MAINTAINER doubleknd26
MAINTAINER doubleknd26@gmail.com

ARG MACRO_TYPE 
ARG PASSPHRASE 
ENV MACRO_PATH=/program/exercise/macro
ENV MACRO_TYPE_VAL $MACRO_TYPE

RUN echo "passphrase: ${PASSPHRASE}"
RUN echo "macro type: ${MACRO_TYPE}"

USER root 
#RUN apt-get update
#RUN apt-get install -y openjdk-8-jdk
RUN echo "deb https://dl.bintray.com/sobolevn/deb git-secret main" | sudo tee -a /etc/apt/sources.list
RUN wget -qO - https://api.bintray.com/users/sobolevn/keys/gpg/public.key | sudo apt-key add -

# Make directories
RUN mkdir -p $MACRO_PATH
RUN mkdir -p $MACRO_PATH/config

# Copy binary and others we need 
COPY build/libs/macro-application-1.0-SNAPSHOT-all.jar $MACRO_PATH
COPY config/prod.yml.gpg $MACRO_PATH/config/prod.yml.gpg
RUN chmod 755 $MACRO_PATH/config/prod.yml.gpg

# Go to work dir
WORKDIR $MACRO_PATH

# Decrypt configuration file
# https://unix.stackexchange.com/questions/60213/gpg-asks-for-password-even-with-passphrase
RUN echo ${PASSPHRASE} | gpg --batch --yes --passphrase-fd 0 config/prod.yml.gpg

ENV DEBUG="-Xms256m -Xmx256m \
-Dcom.sun.management.jmxremote=true \
-Dcom.sun.management.jmxremote.port=11619 \
-Dcom.sun.management.jmxremote.ssl=false \
-Dcom.sun.management.jmxremote.authenticate=false"

CMD java $DEBUG \
  -cp /program/exercise/macro/macro-application-1.0-SNAPSHOT-all.jar \
  com.doubleknd26.exercise.macro.MacroApplication \
  --config-path config/prod.yml \
  --macro-type $MACRO_TYPE_VAL