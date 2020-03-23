FROM selenium/standalone-chrome:3.141.59-zirconium
MAINTAINER doubleknd26
MAINTAINER doubleknd26@gmail.com

USER root
RUN apt-get update
RUN apt-get install -y git
# git-secret: https://git-secret.io/installation
RUN echo "deb https://dl.bintray.com/sobolevn/deb git-secret main" | sudo tee -a /etc/apt/sources.list
RUN wget -qO - https://api.bintray.com/users/sobolevn/keys/gpg/public.key | sudo apt-key add -
RUN sudo apt-get update && sudo apt-get install -y git-secret

RUN mkdir -p /app/exercise
RUN git clone https://github.com/doubleknd26/exercise.git /app/exercise


WORKDIR /app/exercise
RUN git pull
#RUN (echo "doubleknd26") | gpg --import .gitsecret/my-private-key.asc
#RUN (echo "doubleknd26") | git-secret reveal