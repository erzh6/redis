# redis-postgres-demo

Учебный проект Spring Boot: Redis (кэш + хранение сессий) + PostgreSQL.

## Запуск

1. Поднять Redis и PostgreSQL:
   ```
   docker-compose up -d
   ```

2. Запустить приложение:
   ```
   ./mvnw spring-boot:run
   ```

3. Swagger UI: http://localhost:8080/swagger-ui.html

## Проверка Redis из консоли

```
docker exec -it redis redis-cli
SET mykey "Hello Redis"
GET mykey
DEL mykey
```

## Эндпоинты

**Пользователи (PostgreSQL, с кэшем в Redis)**
- `GET    /api/users` — все пользователи
- `POST   /api/users` — создать
- `GET    /api/users/{id}` — по id
- `PUT    /api/users/{id}` — обновить
- `DELETE /api/users/{id}` — удалить

**Сессии (Redis)**
- `POST   /api/sessions?userId=1` — создать сессию
- `GET    /api/sessions` — все сессии
- `GET    /api/sessions/{sessionId}` — по id
- `PUT    /api/sessions/{sessionId}/active` — обновить время активности
- `DELETE /api/sessions/{sessionId}` — удалить сессию

## Кэширование пользователей

- `getAllUsers()` — `@Cacheable` (ключ `allUsers`)
- `getUserById(id)` — `@Cacheable` (ключ = id)
- `createUser()` — `@CacheEvict` (сбрасывает `allUsers`)
- `updateUser()` — `@CachePut` (обновляет кэш по id)
- `deleteUser()` — `@CacheEvict` (по id)

TTL записи кэша — 10 минут (см. `RedisConfig`).
