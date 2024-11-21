FROM lscr.io/linuxserver/webtop:arch-xfce

# Setzen der Umgebungsvariablen
ENV PUID=1000
ENV PGID=1000
ENV TZ=Europe/Berlin
ENV AUTO_LOGIN=false

# Installation von XFCE4 und zusätzlichen Tools
RUN \
    pacman -Syu --noconfirm && \
    pacman -S --noconfirm \
    xfce4 \
    openssh \
    xfce4-goodies \
    firefox \
    thunar \
    mousepad \
    pulseaudio \
    sddm \
    pavucontrol && \
    systemctl enable sshd

RUN \
    echo "exec startxfce4" > /defaults/.xinitrc
