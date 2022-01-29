Демо-проект состоит из 3х микросервисов: Auth, Admin и Courses.

### Auth:
Микросервис, отвечающий за аутентификацию и авторизацию пользователя.

Является единой точкой входа в систему: доступ к двум другим микросервисам закрыт физически. Поэтому все запросы сначала проходят через Auth, и при успехе перенаправляются в остальные микросервисы (посредством Zuul).

### Admin
Микросервис, к которому имеют доступы только пользователи с ролью ADMIN.

Демо-функционал: управление пользователями (добавление, блокировка), а также метод, возвращающий список всех пользователей и всех существующих курсов (для демонстрации работы с FeiignClient).

### Courses
Микросервис, к которому имеют доступы пользователи с ролями USER и ADMIN. (обычные пользователи могут использовать только GET-методы, админы могут создавать и удалять курсы).

Демо-функционал: стандартные CRUD-методы для модели курсов.

## Запуск проекта:

Скрипт start.sh собирает и разворачивает в докер контейнерах все 3 микросервиса, а также 2 БД - одну с юзерами и одну основную (в демо-проекте в ней находятся только курсы).

После успешного запуска проекта можно открыть сваггер и переключаться в нем между вкладками с микросервисами:
http://localhost:8080/swagger-ui.html

Доступы:

Пользователь с правами админа:

Логин: admin@mail.ru

Пароль: admin

Обычный пользователь:

Логин: user@mail.ru 
  
Пароль: user