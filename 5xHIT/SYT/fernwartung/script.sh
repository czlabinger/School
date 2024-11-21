#!/bin/env bash

case "$1" in
    start)
        docker-compose up -d
        ;;
    stop)
        docker-compose down
        ;;
    rebuild)
        docker-compose up -d --build
        ;;
    *)
        echo "Verwendung: $0 {start|stop|rebuild}"
        exit 1
esac
