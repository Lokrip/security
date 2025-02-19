#!/bin/bash

log() {
    echo "[INFO] $(date +'%Y-%m-%d %H:%M:%S') - $1"
}


log "Переход в корневую директорию проекта"
cd "$(dirname "$0")/.." || exit 1

log "Начало сборки проекта"
mvn clean package -DskipTests

log "Запуск докера"
docker-compose up --build
