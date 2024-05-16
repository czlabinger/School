FROM ubuntu:22.04
RUN echo 'APT::Install-Suggests "0";' >> /etc/apt/apt.conf.d/00-docker
RUN echo 'APT::Install-Recommends "0";' >> /etc/apt/apt.conf.d/00-docker
COPY requirements.txt requirements.txt
COPY main.py main.py
COPY places_tf.csv places_tf.csv
RUN DEBIAN_FRONTEND=noninteractive \
  apt update \
  && apt install -y python3 \
  && apt install -y python3-pip \
  && apt install -y python3-pyqt5 \
  && apt install -y python3-tk \
  && apt install -y xvfb \
  && pip install -r requirements.txt \
  && rm -rf /var/lib/apt/lists/*
RUN useradd -ms /bin/bash apprunner
USER apprunner
ENV DISPLAY=:99
RUN Xvfb :99 & 
CMD bash
