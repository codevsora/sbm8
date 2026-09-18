<<<<<<< HEAD
# KursusePunkt

Backend for a course-and-grade management system. REST API; the user interface is a separate module.

## Getting started

Requires **Docker**. Nothing else — no JDK, no Maven, no PostgreSQL.

```bash
make dev-docker
```

- API — http://localhost:8080/api/courses
- Health — http://localhost:8080/actuator/health
- API docs — http://localhost:8080/swagger-ui.html

`make help` lists every command.

## Running the tests

```bash
make test
```

Tests run against a real PostgreSQL started by Testcontainers, so Docker must be running.

## What is already here

A single `Course` resource, implemented through the whole stack as a worked example:

```
src/main/java/ee/tak24/kursusepunkt/
├── KursusePunktApplication.java
├── common/GlobalExceptionHandler.java   RFC 9457 problem details
└── course/
    ├── Course.java                      entity
    ├── CourseRepository.java            Spring Data
    ├── CourseService.java               business logic, transactions
    ├── CourseController.java            REST endpoints
    └── dto/                             request and response records
```

Plus a Flyway migration, a web slice test, a Testcontainers repository test, and ArchUnit rules that
fail the build if a controller reaches into a repository.

Build on this pattern for the rest of the domain: students, enrolments, assignments, results.

## Study guides

The guides for this project live in their own repository:

**[SB-M8-guide](https://github.com/kuressaareametikool/SB-M8-guide)**

Start with `00-getting-started.md`.

## Project status

Template. Replace this README as the project takes shape — guide 09 explains what it should become.
=======
# Spring Boot Developer Study Guide (TAK-24)

Backend development with **Spring Boot 4.1, Java 26 and JPA/Hibernate 7**. Every learning outcome (ÕV)
is practised on one running project rather than on disconnected exercises.

The guides are numbered in **build order**, not in ÕV order — the application has to exist before it
can be tested, optimised or documented. Work through them in sequence, starting with
[guide 00](00-getting-started.md).

**Everything runs in Docker.** The build, the application, the tests and the database are all
containerised — you need Docker and Git, and no local JDK, Maven or PostgreSQL.

Start from the template repository, which has a working skeleton and the container setup already
committed:

**https://github.com/kuressaareametikool/SB-M8-template** → *Use this template*

```bash
git clone https://github.com/<you>/kursusepunkt.git
cd kursusepunkt && make dev-docker
```

[Guide 00](00-getting-started.md) walks through it.

| # | Guide | Outcome |
|---|---|---|
| 00 | [Getting started](00-getting-started.md) | — |
| 01 | [Setup and code standard](01-setup-and-code-standard.md) | ÕV7 |
| 02 | [REST and MVC architecture](02-rest-and-mvc-architecture.md) | ÕV3 |
| 03 | [ORM with JPA and Hibernate](03-orm-with-jpa.md) | ÕV4 |
| 04 | [Programming patterns](04-programming-patterns.md) | ÕV1 |
| 05 | [Math and logic in applications](05-math-and-logic.md) | ÕV2 |
| 06 | [Unit testing](06-unit-testing.md) | ÕV5 |
| 07 | [Mocks and test doubles](07-mocks-and-test-doubles.md) | ÕV6 |
| 08 | [Complex algorithms and components](08-complex-algorithms.md) | ÕV8 |
| 09 | [Documentation in English](09-documentation.md) | ÕV9 |
| 10 | [Capstone brief and rubric](10-capstone-and-rubric.md) | all |
| 11 | [Containerised setup](11-docker-setup.md) | supports ÕV4, ÕV7, ÕV9 |

## The learning outcomes

| Code | Outcome (ET) | Guide |
|---|---|---|
| ÕV1 | tunneb enamlevinud programmeerimismustreid | 04 |
| ÕV2 | kasutab rakenduste koostamisel matemaatika- ja loogikafunktsioone | 05 |
| ÕV3 | realiseerib rakenduse MVC arhitektuuriga rakendusena | 02 |
| ÕV4 | kasutab parimate praktikate kohaselt ORM vahendeid | 03 |
| ÕV5 | mõistab ühiktestide olemust ning nende kasutamisvõimalusi | 06 |
| ÕV6 | kasutab testides mock-klasse | 07 |
| ÕV7 | kasutab korrektselt kokkulepitud koodistandardit | 01 |
| ÕV8 | loob suurema keerukusastmega rakendusi | 08 |
| ÕV9 | dokumenteerib loodud rakendused inglise keeles | 09 |

## The running project: KursusePunkt

The backend of a course-and-grade management system — courses, students, enrolments, assignments,
weighted grade calculation and reporting endpoints — exposed as a REST API.

**The user interface is built by a separate module**, so this project is backend only: no server-rendered
views. That does not weaken ÕV3; a consumer you cannot see is a stricter test of layering than a
template you control yourself.

Any domain of similar complexity is acceptable as a substitute: a gym booking system, a library, a
bike-rental service.

## Stack

| Tool | Version | Notes |
|---|---|---|
| Java | 26 | JDK 25 is the current LTS; nothing here needs a 26-only feature |
| Spring Boot | 4.1.x | Spring Framework 7, modular starters and modular test slices |
| Hibernate | 7 | via Spring Data JPA |
| PostgreSQL | 16 | also in tests, via Testcontainers — no H2 |
| Flyway | 12 | versioned migrations, `ddl-auto=validate`; needs `spring-boot-flyway` on Boot 4 |
| JUnit 5 + Mockito 5 + AssertJ | current | `@MockitoBean`, `MockMvcTester` |
| springdoc-openapi | current | API documentation |
| Spotless + Google Java Format | current | enforced in the build |

> Most Spring Boot material online still targets Boot 3. `spring-boot-starter-web`, `@MockBean` and
> `javax.*` imports are the tells. Check which version a snippet was written for before using it.
> Boot 4 also moved the test slices (`@WebMvcTest`, `@DataJpaTest`) into their own modules and their
> own packages — see guide 01.

## How each guide is structured

1. **Why it matters** — the point of the outcome
2. **Theory** — the concepts and the rules
3. **Code** — working examples from the running project
4. **Exercises** — tasks, marked basic / intermediate / advanced
5. **Solutions** — worked answers, collapsed so you can try first
6. **Checklist** — what "achieved" looks like for this outcome
>>>>>>> 4b8900f3a522e6edfb0a5990359a0eb4d0f3eda0
