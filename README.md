## Sumário

1. [Objetivo](#1-objetivo)
2. [Estrutura do Projeto](#2-estrutura-do-projeto)
3. [Implementação da Classe Calculadora](#3-implementação-da-classe-calculadora)
4. [Implementação da Classe de Testes (JUnit 5)](#4-implementação-da-classe-de-testes-junit-5)
5. [Criação do Repositório no GitHub](#5-criação-do-repositório-no-github)
6. [Criação de Branch e Merge](#6-criação-de-branch-e-merge)
7. [Workflow CI — Testes automáticos no Pull Request](#7-workflow-ci--testes-automáticos-no-pull-request)
8. [Como executar localmente](#8-como-executar-localmente)

---

## 1. Objetivo

Desenvolvimento de um módulo de software em Java — uma **calculadora** com as quatro operações aritméticas básicas (adição, subtração, multiplicação e divisão) —, aplicando **testes automatizados unitários e funcionais** com o framework **JUnit 5** e gerenciando o código-fonte com **Git e GitHub** por meio de práticas de branching e merge.

Os objetivos específicos são:

- Aplicar testes unitários e funcionais automatizados com JUnit 5, cobrindo cenários de sucesso e de falha para cada operação;
- Configurar e gerenciar um repositório Git, implementando práticas de branching e merge;
- Simular o desenvolvimento colaborativo com boas práticas de controle de versão e qualidade de software.

---

## 2. Estrutura do Projeto

```
calculadora/
├── .github/
│   └── workflows/
│       └── ci.yml                  ← Workflow GitHub Actions (CI)
├── assets/                         ← Prints de tela para documentação
│   ├── 01-classe-calculadora.png
│   ├── 02-classe-testes.png
│   ├── 03-testes-passando.png
│   ├── 04-repositorio-github.png
│   ├── 05-branch-criada.png
│   ├── 06-pull-request.png
│   └── 07-ci-workflow.png
├── src/
│   ├── main/java/com/puc/calculadora/
│   │   └── Calculadora.java
│   └── test/java/com/puc/calculadora/
│       └── CalculadoraTest.java
├── pom.xml
└── README.md
```

O projeto utiliza **Maven** como gerenciador de build e dependências. A dependência do JUnit 5 está declarada no `pom.xml`:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

---

## 3. Implementação da Classe Calculadora

A classe `Calculadora` (pacote `com.puc.calculadora`) implementa os quatro métodos aritméticos com retorno em `double`:

| Método | Descrição | Observação |
|---|---|---|
| `somar(double a, double b)` | Retorna `a + b` | — |
| `subtrair(double a, double b)` | Retorna `a - b` | — |
| `multiplicar(double a, double b)` | Retorna `a * b` | — |
| `dividir(double a, double b)` | Retorna `a / b` | Lança `ArithmeticException` se `b == 0` |

O método `dividir` valida o divisor antes de operar, lançando uma exceção com mensagem descritiva quando `b == 0`, evitando comportamentos indefinidos.

**Print — Implementação da classe Calculadora:**

![Implementação da classe Calculadora](assets/01-classe-calculadora.png)

---

## 4. Implementação da Classe de Testes (JUnit 5)

A classe `CalculadoraTest` contém **45 casos de teste** organizados com classes internas (`@Nested`) por operação. Cada teste recebe um nome legível via `@DisplayName`.

### Recursos do JUnit 5 utilizados

| Recurso | Finalidade |
|---|---|
| `@Test` | Marca cada método de teste individual |
| `@BeforeEach` | Instancia `new Calculadora()` antes de cada teste, garantindo isolamento |
| `@DisplayName` | Descreve o teste em português para facilitar leitura dos relatórios |
| `@Nested` | Agrupa os testes por operação (Somar, Subtrair, Multiplicar, Dividir) |
| `@ParameterizedTest` + `@CsvSource` | Executa o mesmo teste com múltiplos conjuntos de dados |
| `assertEquals(esperado, atual, delta)` | Compara valores `double` com tolerância (DELTA = 1e-9) |
| `assertAll(...)` | Agrupa múltiplas asserções em um único teste |
| `assertThrows(...)` | Verifica que a exceção correta é lançada na divisão por zero |

### Cenários cobertos por operação

| Operação | Cenários | Testes |
|---|---|---|
| **Somar** | Positivos, negativos, positivo+negativo, zero, comutatividade, decimais, MAX_VALUE, parametrizados | 11 |
| **Subtrair** | Positivos, negativos, resultado negativo, zero, si mesmo, decimais, parametrizados | 10 |
| **Multiplicar** | Positivos, negativos, positivo×negativo, zero (absorvente), um (neutro), comutatividade, decimais, parametrizados | 11 |
| **Dividir** | Positivos, negativos, positivo÷negativo, fracionário, 0÷número, si mesmo, 3 exceções (÷0), parametrizados | 13 |
| **Total** | | **45** |

**Print — Implementação da classe de testes:**

![Implementação da classe de testes](assets/02-classe-testes.png)

**Print — Todos os testes passando:**

![Todos os testes passando](assets/03-testes-passando.png)

---

## 5. Criação do Repositório no GitHub

O repositório foi criado na conta do GitHub e recebe o código-fonte do projeto, incluindo a classe `Calculadora`, a classe `CalculadoraTest` e o workflow de CI.

O primeiro commit foi realizado na branch `master` com todos os arquivos base do projeto:

```bash
git init
git add pom.xml src/ README.md
git commit -m "feat: implementa calculadora Java com testes JUnit 5 — Projeto Integrador IV-A"
git remote add origin git@github.com:joaovittor-22/calculadora-java.git
git push -u origin master
```

**Print — Repositório criado no GitHub:**

![Repositório no GitHub](assets/04-repositorio-github.png)

---

## 6. Criação de Branch e Merge

Para adicionar os testes parametrizados e o workflow de CI, foi criada a branch `feature/github-actions-ci` a partir da `master`:

```bash
git checkout -b feature/github-actions-ci
```

Após o desenvolvimento e os commits nessa branch, ela foi enviada ao GitHub e um **Pull Request** foi aberto para merge na `master`. O merge consolida o histórico de ambas as branches.

**Print — Branch criada no GitHub:**

![Branch criada](assets/05-branch-criada.png)

**Print — Pull Request aberto:**

![Pull Request](assets/06-pull-request.png)

---

## 7. Workflow CI — Testes automáticos no Pull Request

O arquivo `.github/workflows/ci.yml` configura o **GitHub Actions** para executar os testes automaticamente em todo push e pull request direcionado às branches `master` ou `main`.

```yaml
on:
  pull_request:
    branches: [ master, main ]
  push:
    branches: [ master, main ]
```

O workflow roda `mvn verify` em **Java 17 e Java 21** em paralelo (matrix strategy) e publica o relatório Surefire como artefato de 7 dias — permitindo inspecionar o resultado de cada teste direto no GitHub.

**Print — Workflow CI executando no Pull Request:**

![Workflow CI](assets/07-ci-workflow.png)

---

## 8. Como executar localmente

**Pré-requisitos:** JDK 17+ e Maven 3.8+

```bash
# Executar todos os testes
mvn test

# Compilar e gerar o JAR
mvn clean package

# Saída esperada
# Tests run: 45, Failures: 0, Errors: 0, Skipped: 0
# BUILD SUCCESS
```

---

## Referências

- GITHUB, Inc. **GitHub**. Disponível em: https://github.com. Acesso em: jun. 2026.
- JUNIT TEAM. **JUnit 5 User Guide**. Disponível em: https://junit.org/junit5/docs/current/user-guide/. Acesso em: jun. 2026.
- APACHE MAVEN PROJECT. **Maven**. Disponível em: https://maven.apache.org. Acesso em: jun. 2026.
- ORACLE CORPORATION. **Java SE 17**. Disponível em: https://www.oracle.com/java/. Acesso em: jun. 2026.
