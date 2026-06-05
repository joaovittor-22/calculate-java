package com.puc.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Classe de testes unitários e funcionais para a classe {@link Calculadora}.
 *
 * Utiliza JUnit 5 (Jupiter) e cobre os cenários de:
 *  - números positivos;
 *  - números negativos;
 *  - operações com zero;
 *  - casos de falha (divisão por zero — exceção esperada).
 *
 * Conceitos aplicados: verificação e validação de software.
 */
@DisplayName("Testes da classe Calculadora")
class CalculadoraTest {

    private Calculadora calc;

    // Tolerância para comparação de valores em ponto flutuante (double).
    private static final double DELTA = 1e-9;

    @BeforeEach
    void setUp() {
        // Instancia uma nova Calculadora antes de cada teste,
        // garantindo isolamento entre os cenários.
        calc = new Calculadora();
    }

    // ----------------------------------------------------------------------
    // Testes do método SOMAR
    // ----------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: somar")
    class TestesSomar {

        @Test
        @DisplayName("Soma de dois números positivos")
        void somarPositivos() {
            assertEquals(10.0, calc.somar(4, 6), DELTA);
        }

        @Test
        @DisplayName("Soma de dois números negativos")
        void somarNegativos() {
            assertEquals(-10.0, calc.somar(-4, -6), DELTA);
        }

        @Test
        @DisplayName("Soma de um número positivo e um negativo")
        void somarPositivoComNegativo() {
            assertEquals(2.0, calc.somar(5, -3), DELTA);
        }

        @Test
        @DisplayName("Soma com zero (elemento neutro da adição)")
        void somarComZero() {
            assertEquals(7.0, calc.somar(7, 0), DELTA);
            assertEquals(7.0, calc.somar(0, 7), DELTA);
            assertEquals(0.0, calc.somar(0, 0), DELTA);
        }
    }

    // ----------------------------------------------------------------------
    // Testes do método SUBTRAIR
    // ----------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: subtrair")
    class TestesSubtrair {

        @Test
        @DisplayName("Subtração de dois números positivos")
        void subtrairPositivos() {
            assertEquals(4.0, calc.subtrair(10, 6), DELTA);
        }

        @Test
        @DisplayName("Subtração de dois números negativos")
        void subtrairNegativos() {
            // (-4) - (-6) = -4 + 6 = 2
            assertEquals(2.0, calc.subtrair(-4, -6), DELTA);
        }

        @Test
        @DisplayName("Subtração resultando em valor negativo")
        void subtrairResultadoNegativo() {
            assertEquals(-5.0, calc.subtrair(3, 8), DELTA);
        }

        @Test
        @DisplayName("Subtração com zero")
        void subtrairComZero() {
            assertEquals(9.0, calc.subtrair(9, 0), DELTA);
            assertEquals(-9.0, calc.subtrair(0, 9), DELTA);
            assertEquals(0.0, calc.subtrair(0, 0), DELTA);
        }
    }

    // ----------------------------------------------------------------------
    // Testes do método MULTIPLICAR
    // ----------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: multiplicar")
    class TestesMultiplicar {

        @Test
        @DisplayName("Multiplicação de dois números positivos")
        void multiplicarPositivos() {
            assertEquals(20.0, calc.multiplicar(4, 5), DELTA);
        }

        @Test
        @DisplayName("Multiplicação de dois números negativos (resultado positivo)")
        void multiplicarNegativos() {
            assertEquals(20.0, calc.multiplicar(-4, -5), DELTA);
        }

        @Test
        @DisplayName("Multiplicação de positivo por negativo (resultado negativo)")
        void multiplicarPositivoComNegativo() {
            assertEquals(-20.0, calc.multiplicar(4, -5), DELTA);
        }

        @Test
        @DisplayName("Multiplicação por zero (elemento absorvente)")
        void multiplicarPorZero() {
            assertEquals(0.0, calc.multiplicar(99, 0), DELTA);
            assertEquals(0.0, calc.multiplicar(0, 99), DELTA);
            assertEquals(0.0, calc.multiplicar(0, 0), DELTA);
        }
    }

    // ----------------------------------------------------------------------
    // Testes do método DIVIDIR
    // ----------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: dividir")
    class TestesDividir {

        @Test
        @DisplayName("Divisão de dois números positivos")
        void dividirPositivos() {
            assertEquals(5.0, calc.dividir(10, 2), DELTA);
        }

        @Test
        @DisplayName("Divisão de dois números negativos (resultado positivo)")
        void dividirNegativos() {
            assertEquals(5.0, calc.dividir(-10, -2), DELTA);
        }

        @Test
        @DisplayName("Divisão de positivo por negativo (resultado negativo)")
        void dividirPositivoComNegativo() {
            assertEquals(-5.0, calc.dividir(10, -2), DELTA);
        }

        @Test
        @DisplayName("Divisão com resultado fracionário")
        void dividirComResultadoFracionario() {
            assertEquals(2.5, calc.dividir(5, 2), DELTA);
        }

        @Test
        @DisplayName("Zero dividido por número diferente de zero deve resultar zero")
        void zeroDivididoPorNumero() {
            assertEquals(0.0, calc.dividir(0, 7), DELTA);
        }

        @Test
        @DisplayName("Divisão por zero deve lançar ArithmeticException")
        void dividirPorZeroDeveLancarExcecao() {
            ArithmeticException ex = assertThrows(
                    ArithmeticException.class,
                    () -> calc.dividir(10, 0)
            );
            assertEquals("Divisão por zero não é permitida.", ex.getMessage());
        }

        @Test
        @DisplayName("Divisão de zero por zero também deve lançar ArithmeticException")
        void zeroDivididoPorZeroDeveLancarExcecao() {
            assertThrows(ArithmeticException.class, () -> calc.dividir(0, 0));
        }
    }
}
