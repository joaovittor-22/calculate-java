# Projeto Integrador IV-A — Calculadora

**Aluno:** Thalles Bruno Goncalves Nery dos Santos
**Curso:** Análise e Desenvolvimento de Sistemas (EaD) — PUC Goiás
**Disciplina:** Projeto Integrador IV-A
**Ano:** 2026

---

## 1. Objetivo

Desenvolvimento de um projeto Java focado em **testes automatizados (unitários e funcionais)** utilizando **JUnit 5**, aplicando conceitos de verificação e validação de software. O projeto consiste em uma calculadora simples com as quatro operações básicas: adição, subtração, multiplicação e divisão.

## 2. Estrutura do Projeto

```
calculadora/
├── pom.xml
├── README.md
└── src/
    ├── main/
    │   └── java/
    │       └── com/puc/calculadora/
    │           └── Calculadora.java
    └── test/
        └── java/
            └── com/puc/calculadora/
                └── CalculadoraTest.java
```

Foi adotado o padrão **Maven** para gerenciamento do projeto e das dependências (JUnit 5).

## 3. Classe Calculadora

A classe `Calculadora` implementa quatro métodos públicos:

| Método | Descrição | Observação |
|---|---|---|
| `somar(double a, double b)` | Retorna `a + b` | — |
| `subtrair(double a, double b)` | Retorna `a - b` | — |
| `multiplicar(double a, double b)` | Retorna `a * b` | — |
| `dividir(double a, double b)` | Retorna `a / b` | Lança `ArithmeticException` quando `b == 0` |

Todos os métodos trabalham com `double`, o que permite representar números inteiros, negativos e fracionários.

## 4. Classe de Testes (JUnit 5)

A classe `CalculadoraTest` cobre os seguintes cenários, conforme solicitado no enunciado:

- **Somar:** positivos, negativos, positivo com negativo, e zero.
- **Subtrair:** positivos, negativos, resultado negativo, e zero.
- **Multiplicar:** positivos, negativos, positivo com negativo, e zero (elemento absorvente).
- **Dividir:** positivos, negativos, positivo com negativo, fracionário, zero dividido por número, **divisão por zero (exceção esperada)** e **0/0 (exceção esperada)**.

### Recursos do JUnit 5 utilizados

- `@Test` — marca cada método de teste.
- `@BeforeEach` — instancia a `Calculadora` antes de cada teste, garantindo isolamento.
- `@DisplayName` — descrições legíveis dos testes em português.
- `@Nested` — agrupa os testes por operação (somar, subtrair, multiplicar, dividir).
- `assertEquals(esperado, atual, delta)` — comparação de valores `double` com tolerância.
- `assertThrows(...)` — verifica que a exceção esperada é lançada na divisão por zero.

## 5. Como executar

### Pré-requisitos

- **JDK 17** (ou superior)
- **Maven 3.8+**

### Executar todos os testes

A partir da raiz do projeto:

```bash
mvn test
```

### Compilar e gerar o JAR

```bash
mvn clean package
```

### Executar uma classe específica de teste

```bash
mvn -Dtest=CalculadoraTest test
```

### Saída esperada (resumida)

```
[INFO] Tests run: 20, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

## 6. Resumo dos testes implementados

Total de **20 testes**, organizados em 4 classes aninhadas:

- **Somar** — 4 testes
- **Subtrair** — 4 testes
- **Multiplicar** — 4 testes
- **Dividir** — 8 testes (inclui dois cenários de exceção)

Todos os métodos da classe `Calculadora` estão **100% cobertos** por testes, com cenários de **sucesso e de falha** (conforme exigido no enunciado).
