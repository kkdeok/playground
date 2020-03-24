FROM selenium/standalone-chrome:3.141.59-zirconium
MAINTAINER doubleknd26
MAINTAINER doubleknd26@gmail.com

ARG passphrase

USER root
RUN apt-get update
RUN apt-get install -y git
RUN apt-get install -y openjdk-8-jdk
RUN echo "deb https://dl.bintray.com/sobolevn/deb git-secret main" | sudo tee -a /etc/apt/sources.list
RUN wget -qO - https://api.bintray.com/users/sobolevn/keys/gpg/public.key | sudo apt-key add -

RUN mkdir -p /app/exercise
RUN git clone https://github.com/doubleknd26/exercise.git /app/exercise

WORKDIR /app/exercise/macro
# https://unix.stackexchange.com/questions/60213/gpg-asks-for-password-even-with-passphrase
RUN echo ${passphrase} | gpg --batch --yes --passphrase-fd 0 config/prod.yml.gpg

ENTRYPOINT ["./run.sh", "-b"]