# front-to-do-test

[![E2E Tests](https://github.com/kevinaryeldev/front-to-do-test/actions/workflows/tests.yml/badge.svg)](https://github.com/kevinaryeldev/front-to-do-test/actions/workflows/tests.yml)
![Java](https://img.shields.io/badge/Java-21-007396?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-4.27-43B02A?logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber-7.20-23D96C?logo=cucumber&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?logo=apachemaven&logoColor=white)

Suíte de testes E2E para o projeto [front-to-do](https://github.com/kevinaryeldev/front-to-do), uma aplicação de gerenciamento de tarefas construída em React.

---

## Sobre o projeto

Este repositório contém os testes end-to-end da aplicação front-to-do, escritos com Selenium WebDriver e Cucumber em BDD. Os cenários cobrem os fluxos principais da aplicação — landing page, login e dashboard — e são executados automaticamente via GitHub Actions após cada deploy do projeto de front.

---

## Stack

| Tecnologia | Versão |
|---|---|
| Java | 21 |
| Selenium | 4.27.0 |
| Cucumber | 7.20.1 |
| JUnit | 5.11.4 |
| AssertJ | 3.27.2 |
| ExtentReports | 5.1.2 |
| Maven Surefire | 3.5.2 |

---

## Estrutura dos testes

```
src/
├── main/java/com/whatsnext/
│   ├── config/          # DriverManager, DriverFactory e CucumberHooks
│   └── util/            # ConfigReader e ByTestId (locator customizado)
└── test/
    ├── java/com/whatsnext/
    │   ├── pages/       # Page Objects (Landing, Login, Dashboard)
    │   ├── steps/       # Step definitions por contexto
    │   └── runners/     # Runners por suite e por browser
    └── resources/
        └── features/
            ├── e2e/     # Cenários por funcionalidade
            └── journey/ # Fluxo completo do usuário
```

### Cobertura

| Feature | Tags | Cenários |
|---|---|---|
| Landing Page | `@landing` `@e2e` `@smoke` | Navbar autenticado/não autenticado, botões hero e CTA |
| Login | `@login` `@e2e` `@smoke` | Credenciais válidas/inválidas, campos vazios, visibilidade de senha |
| Dashboard | `@dashboard` `@e2e` `@smoke` | CRUD de tarefas, filtros, estatísticas, logout, localStorage |
| Golden Path | `@goldenPath` | Jornada completa: landing → login → tarefas → logout |

---

## Pipeline de CI

O pipeline é acionado por push/PR na branch `main` e pelo projeto de front via `workflow_dispatch` após o deploy. Os jobs rodam em sequência:

```
build → smoke → e2e (chrome | firefox | edge) → golden-path
```

| Job | Descrição |
|---|---|
| **build** | Compila o código e as classes de teste |
| **smoke** | Executa os cenários `@smoke` para validação rápida |
| **e2e** | Executa os cenários `@e2e` em paralelo nos três browsers |
| **golden-path** | Executa o fluxo completo `@goldenPath` |

Ao final de cada job um relatório HTML gerado pelo ExtentReports é publicado como artefato no GitHub Actions.

---

## Executando localmente

### Pré-requisitos
- Java 21
- Maven

### Configuração

Crie `src/main/resources/config-dev.properties` e ajuste as credenciais base.url (pode apontar diretamente para o [Github Pages](https://kevinaryeldev.github.io/front-to-do)), test.username, e test.password. Configurar também headless e timeout.seconds conforme necessário.

### Comandos

```bash
# Todos os testes (Chrome, headless desligado por padrão no dev)
mvn test

# Filtrar por tag
mvn test -Dcucumber.filter.tags="@smoke"
mvn test -Dcucumber.filter.tags="@goldenPath"

# Escolher browser
mvn test -Dbrowser=firefox
mvn test -Dbrowser=edge

# Todos os browsers em sequência (perfil all-browsers)
mvn test -Pall-browsers
```

O relatório é gerado em `target/reports/extent-report.html`.