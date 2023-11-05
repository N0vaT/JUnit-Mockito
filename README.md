# JUnit/Mockito

## Exercise 00
**Реализация:**
- Java — 1.8;
- JUnit 5 framework;
- maven-surefire-plugin
  junit-jupiter-engine
  junit-jupiter-params
  junit-jupiter-api

**Структура проекта**:
- Tests
    - src
        - main
            - java
                - edu.school21.numbers
                    - NumberWorker
            - resources
        - test
            - java
                - edu.school21.numbers
                    - NumberWorkerTest
            - resources
                -	data.csv
    - pom.xml

Создан класс NumberWorkerTest, реализующий логику тестирования модуля. Методы класса NumberWorkerTest должны проверять корректность работы методов NumberWorker для различных входных данных:
- метод isPrimeForPrimes для проверки isPrime с использованием простых чисел (@ParameterizedTest и @ValueSource).
- метод isPrimeForNotPrimes для проверки isPrime с использованием составных чисел (@ParameterizedTest и @ValueSource).
- метод isPrimeForIncorrectNumbers для проверки isPrime с использованием неправильных чисел (@ParameterizedTest и @ValueSource).
- метод проверки суммы цифр с использованием набора из не менее 10 чисел (@ParameterizedTest и @CsvFileSource из файла data.csv).

### Exercise 01 – Embedded DataBase
**Реализация:**
- Java — 1.8;
- JUnit 5 framework;
- maven-surefire-plugin
  junit-jupiter-engine
  junit-jupiter-params
  junit-jupiter-api
  spring-jdbc
  hsqldb

**Структура проекта**:
- Tests
    - src
        - main
            - java
                - edu.school21.numbers
                    - NumberWorker
            - resources
        - test
            - java
                - edu.school21
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml

Реализован механизм создания DataSource для HSQL DBMS. Для этого к проекту подключены зависимости Spring-jdbc и hsqldb. Подготовлены файлы Schema.sql и data.sql, в которых описаны структура таблицы продуктов и тестовые данные.
Создание класса EmbeddedDataSourceTest. В этом классе реализован метод init(), отмеченный аннотацией @BeforeEach. В этом классе реализована функциональность для создания источника данных с помощью EmbeddedDataBaseBuilder (класс в библиотеке Spring-jdbc).
Реализован простой тестовый метод для проверки возвращаемого значения метода getConnection(), созданного DataSource (это значение не должно быть нулевым).

## Exercise 02 – Test for JDBC Repository
**Реализация:**
- Java — 1.8;
- JUnit 5 framework;
- maven-surefire-plugin
  junit-jupiter-engine
  junit-jupiter-params
  junit-jupiter-api
  spring-jdbc
  hsqldb

**Структура проекта**:
- Tests
    - src
        - main
            - java
                - edu.school21
                    - numbers
                        - NumberWorker
                    - models
                        - Product
                    - repositories
                        - ProductsRepository
                        - ProductsRepositoryJdbcImpl
            - resources
        - test
            - java
                - edu.school21
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
                        - ProductsRepositoryJdbcImplTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml

Реализован класс ProductsRepositoryJdbcImplTest, содержащий методы, проверяющие функциональность репозитория, с использованием базы данных в памяти (HSQL DBMS).
Для этого заранее подготовены объекты модели, которые будут использоваться для сравнения во всех тестах.
```java
final List<Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(new Product(1L, "Ches", 200.),
            new Product(2L, "buckwheat", 80.),
            new Product(3L, "Tea", 100.),
            new Product(4L, "Bread", 40.),
            new Product(5L, "Beer", 70.));
final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L,"Tea", 100.0);
final Product EXPECTED_UPDATED_PRODUCT = new Product(3L, "Lipton Tea", 230.);
final Product EXPECTED_SAVE_PRODUCT = new Product(6L, "Chocolate", 250.);
```

## Exercise 03 – Test for Service
**Реализация:**
- Java — 1.8;
- JUnit 5 framework;
- maven-surefire-plugin
  junit-jupiter-engine
  junit-jupiter-params
  junit-jupiter-api
  spring-jdbc
  hsqldb
  mockito-core

**Структура проекта**:
- Tests
    - src
        - main
            - java
                - edu.school21
                    - exceptions
                        - AlreadyAuthenticatedException
                    - numbers
                        - NumberWorker
                    - models
                        - Product
                        - User
                    - services
                        - UsersServiceImpl
                    - repositories
                        - ProductsRepository
                        - ProductsRepositoryJdbcImpl
                        - UsersRepository
            - resources
        - test
            - java
                - edu.school21
                    - services
                        - UsersServiceImplTest
                    - numbers
                        - NumberWorkerTest
                    - repositories
                        - EmbeddedDataSourceTest
                        - ProductsRepositoryJdbcImplTest
            - resources
                -	data.csv
                -	schema.sql
                -	data.sql
    - pom.xml

Тестирование отдельный компонент системы без вызова функциональности его зависимостей.
- Создан интерфейс UsersRepository
- Создан класс UsersServiceImpl и метод аутентификации.
- Создайн тест модуля для класса UsersServiceImpl.

Проведена проверка корректность работы метода аутентификации независимо от компонента UsersRepository, использован фиктивный объект и заглушки методов findByLogin и update (библиотека Mockito).
