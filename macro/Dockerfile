FROM selenium/standalone-chrome:3.141.59-zirconium
MAINTAINER doubleknd26
MAINTAINER doubleknd26@gmail.com

USER root
RUN apt-get update
RUN apt-get install -y git

RUN mkdir -p /app/exercise
RUN git clone https://github.com/doubleknd26/exercise.git /app/exercise

WORKDIR /app/exercise/macro
CMD