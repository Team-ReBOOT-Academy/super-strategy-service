# Super-Strategy Service (SSS)

Сервис торговой стратегии для платформы виртуального трейдинга.

* **Автор:** Плоцкий Роман
* **Подробное архитектурное описание:** [docs/project-description.md](docs/project-description.md)

---

## Требования к окружению
* Docker 20+
* (Опционально для локального запуска без контейнера) JDK 25

---

## Сборка и запуск

### Запуск через Docker
1. Собрать Docker-образ:
```bash
docker build -t super-strategy-service .