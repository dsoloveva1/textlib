Как запустить серверное приложение
Запустите ./mvnw Spring-boot:run внутри внутренней папки. Или просто используйте свою любимую IDE.

По умолчанию приложение работает по адресу http://localhost:8080.

Как запустить фронтенд-приложение
Запустите ngserve или npm start в папке внешнего интерфейса. Обратите внимание, что вам необходимо установить node, npm и, при необходимости, @angular/cli.

По умолчанию приложение работает по адресу http://localhost:4200.

## Как запустить серверное приложение
Выполните команду ```./mvnw spring-boot:run``` внутри папки ```backend```. Или импортируйте папку ```backend``` в Eclipse или аналогичной IDE следующим образом: нажмите File -> Import ... -> Maven -> Existing Maven Projects -> Next.
Создайте Maven проект, чтобы установить все необходимые зависимости.<br/>
Чтобы настроить базы данных, установите PostgreSQL и Mongodb.<br/>
Затем обновите поля конфигурации в файле application.properties внутри папки /resources:
```
spring.datasource.postgres.url=jdbc:postgresql://localhost:5432/dsolo
spring.datasource.postgres.username=dsolo
spring.datasource.postgres.password=1234
spring.datasource.mongodb.url = mongodb://localhost:27017/records
spring.datasource.mongodb.db = records
```
## Как запустить фронтенд-приложение
Выполните команду ```ng serve``` или ```npm start``` в папке ```frontend```. Обратите внимание, что вам необходимо установить node, npm и, при необходимости, @angular/cli.
По умолчанию приложение работает по адресу http://localhost:8080.
