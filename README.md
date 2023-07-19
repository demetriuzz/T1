# T1

- Java + Gradle + Spring Boot 2.x
- PostgreSQL + jOOQ
- Kafka
- Docker + TestContainers

## Where are the database objects?

But... where? Chicken __or__ egg: who comes first? :)

File `db/changelog/sql/0001-init.sql` contain first DB object. But this _migration_.

Before run application or test need __completed__ DB structure.

In current small test project used `gradle` plugin `org.liquibase.gradle`
