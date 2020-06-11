FROM selenium/standalone-chrome:3.141.59-zirconium
MAINTAINER doubleknd26
MAINTAINER doubleknd26@gmail.com

ARG passphrase
ENV MACRO_PATH=/program/exercise/macro

USER root 
RUN apt-get update
##RUN apt-get install -y openjdk-8-jdk
RUN echo "deb https://dl.bintray.com/sobolevn/deb git-secret main" | sudo tee -a /etc/apt/sources.list
RUN wget -qO - https://api.bintray.com/users/sobolevn/keys/gpg/public.key | sudo apt-key add -

# Make directories
RUN mkdir -p $MACRO_PATH
RUN mkdir -p $MACRO_PATH/config

# Decrypt configuration file
# https://unix.stackexchange.com/questions/60213/gpg-asks-for-password-even-with-passphrase
RUN echo "passphrase: ${passphrase}"
RUN echo ${passphrase} | gpg --batch --yes --passphrase-fd 0 config/prod.yml.gpg

# Copy binary and others we need 
COPY build/libs/macro-application-1.0-SNAPSHOT-all.jar $MACRO_PATH
COPY docker/run.sh $MACRO_PATH/run.sh
RUN chmod 755 $MACRO_PATH/run.sh
COPY config/prod.yml $MACRO_PATH/config/prod.yml
RUN chmod 755 $MACRO_PATH/config/prod.yml

# Expose entrypoint script file
ENTRYPOINT ["/program/exercise/macro/run.sh"]