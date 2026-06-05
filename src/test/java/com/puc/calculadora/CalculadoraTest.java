package com.puc.calculadora;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testes da classe Calculadora")
class CalculadoraTest {

    private Calculadora calc;

    private static final double DELTA = 1e-9;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }

    // ------------------------------------------------------------------
    // SOMAR
    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: somar")
    class TestesSomar {

        @Test
        @DisplayName("Soma de dois positivos")
        void somarPositivos() {
            assertEquals(10.0, calc.somar(4, 6), DELTA);
        }

        @Test
        @DisplayName("Soma de dois negativos")
        void somarNegativos() {
            assertEquals(-10.0, calc.somar(-4, -6), DELTA);
        }

        @Test
        @DisplayName("Soma de positivo com negativo")
        void somarPositivoComNegativo() {
            assertEquals(2.0, calc.somar(5, -3), DELTA);
        }

        @Test
        @DisplayName("Soma com zero — elemento neutro")
        void somarComZero() {
            assertAll(
                () -> assertEquals(7.0,  calc.somar(7, 0),  DELTA),
                () -> assertEquals(7.0,  calc.somar(0, 7),  DELTA),
                () -> assertEquals(0.0,  calc.somar(0, 0),  DELTA)
            );
        }

        @Test
        @DisplayName("Soma comutativa: a + b == b + a")
        void somarComutatividade() {
            assertEquals(calc.somar(3, 7), calc.somar(7, 3), DELTA);
        }

        @Test
        @DisplayName("Soma com valores decimais")
        void somarDecimais() {
            assertEquals(0.3, calc.somar(0.1, 0.2), 1e-9);
        }

        @Test
        @DisplayName("Soma com Double.MAX_VALUE não ultrapassa Infinity")
        void somarMaxValue() {
            double resultado = calc.somar(Double.MAX_VALUE, Double.MAX_VALUE);
            assertTrue(Double.isInfinite(resultado));
        }

        @ParameterizedTest(name = "{0} + {1} = {2}")
        @DisplayName("Soma parametrizada — múltiplos cenários")
        @CsvSource({
            "1,    1,    2",
            "-5,   5,    0",
            "100, -50,  50",
            "0.5,  0.5,  1",
        })
        void somarParametrizado(double a, double b, double esperado) {
            assertEquals(esperado, calc.somar(a, b), DELTA);
        }
    }

    // ------------------------------------------------------------------
    // SUBTRAIR
    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: subtrair")
    class TestesSubtrair {

        @Test
        @DisplayName("Subtração de dois positivos")
        void subtrairPositivos() {
            assertEquals(4.0, calc.subtrair(10, 6), DELTA);
        }

        @Test
        @DisplayName("Subtração de dois negativos")
        void subtrairNegativos() {
            assertEquals(2.0, calc.subtrair(-4, -6), DELTA);
        }

        @Test
        @DisplayName("Subtração resultando em negativo")
        void subtrairResultadoNegativo() {
            assertEquals(-5.0, calc.subtrair(3, 8), DELTA);
        }

        @Test
        @DisplayName("Subtração com zero")
        void subtrairComZero() {
            assertAll(
                () -> assertEquals(9.0,  calc.subtrair(9, 0),  DELTA),
                () -> assertEquals(-9.0, calc.subtrair(0, 9),  DELTA),
                () -> assertEquals(0.0,  calc.subtrair(0, 0),  DELTA)
            );
        }

        @Test
        @DisplayName("Subtração de si mesmo resulta em zero")
        void subtrairDeSiMesmo() {
            assertEquals(0.0, calc.subtrair(42, 42), DELTA);
        }

        @Test
        @DisplayName("Subtração com decimais")
        void subtrairDecimais() {
            assertEquals(0.1, calc.subtrair(0.3, 0.2), 1e-9);
        }

        @ParameterizedTest(name = "{0} - {1} = {2}")
        @DisplayName("Subtração parametrizada — múltiplos cenários")
        @CsvSource({
            "10,  3,  7",
            "0,  10, -10",
            "-3, -5,  2",
            "5,   5,  0",
        })
        void subtrairParametrizado(double a, double b, double esperado) {
            assertEquals(esperado, calc.subtrair(a, b), DELTA);
        }
    }

    // ------------------------------------------------------------------
    // MULTIPLICAR
    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: multiplicar")
    class TestesMultiplicar {

        @Test
        @DisplayName("Multiplicação de dois positivos")
        void multiplicarPositivos() {
            assertEquals(20.0, calc.multiplicar(4, 5), DELTA);
        }

        @Test
        @DisplayName("Multiplicação de dois negativos resulta positivo")
        void multiplicarNegativos() {
            assertEquals(20.0, calc.multiplicar(-4, -5), DELTA);
        }

        @Test
        @DisplayName("Multiplicação de positivo por negativo resulta negativo")
        void multiplicarPositivoComNegativo() {
            assertEquals(-20.0, calc.multiplicar(4, -5), DELTA);
        }

        @Test
        @DisplayName("Multiplicação por zero — elemento absorvente")
        void multiplicarPorZero() {
            assertAll(
                () -> assertEquals(0.0, calc.multiplicar(99, 0),  DELTA),
                () -> assertEquals(0.0, calc.multiplicar(0, 99),  DELTA),
                () -> assertEquals(0.0, calc.multiplicar(0, 0),   DELTA)
            );
        }

        @Test
        @DisplayName("Multiplicação por um — elemento neutro")
        void multiplicarPorUm() {
            assertAll(
                () -> assertEquals(7.0,  calc.multiplicar(7, 1),   DELTA),
                () -> assertEquals(7.0,  calc.multiplicar(1, 7),   DELTA),
                () -> assertEquals(-7.0, calc.multiplicar(-7, 1),  DELTA)
            );
        }

        @Test
        @DisplayName("Multiplicação comutativa: a * b == b * a")
        void multiplicarComutatividade() {
            assertEquals(calc.multiplicar(3, 8), calc.multiplicar(8, 3), DELTA);
        }

        @Test
        @DisplayName("Multiplicação com decimais")
        void multiplicarDecimais() {
            assertEquals(0.06, calc.multiplicar(0.2, 0.3), 1e-9);
        }

        @ParameterizedTest(name = "{0} * {1} = {2}")
        @DisplayName("Multiplicação parametrizada — múltiplos cenários")
        @CsvSource({
            "3,    4,   12",
            "-2,   6,  -12",
            "-3,  -3,    9",
            "0.5,  4,    2",
        })
        void multiplicarParametrizado(double a, double b, double esperado) {
            assertEquals(esperado, calc.multiplicar(a, b), DELTA);
        }
    }

    // ------------------------------------------------------------------
    // DIVIDIR
    // ------------------------------------------------------------------
    @Nested
    @DisplayName("Operação: dividir")
    class TestesDividir {

        @Test
        @DisplayName("Divisão de dois positivos")
        void dividirPositivos() {
            assertEquals(5.0, calc.dividir(10, 2), DELTA);
        }

        @Test
        @DisplayName("Divisão de dois negativos resulta positivo")
        void dividirNegativos() {
            assertEquals(5.0, calc.dividir(-10, -2), DELTA);
        }

        @Test
        @DisplayName("Divisão de positivo por negativo resulta negativo")
        void dividirPositivoComNegativo() {
            assertEquals(-5.0, calc.dividir(10, -2), DELTA);
        }

        @Test
        @DisplayName("Divisão com resultado fracionário")
        void dividirFracionario() {
            assertEquals(2.5, calc.dividir(5, 2), DELTA);
        }

        @Test
        @DisplayName("Zero dividido por número resulta zero")
        void zeroDivididoPorNumero() {
            assertEquals(0.0, calc.dividir(0, 7), DELTA);
        }

        @Test
        @DisplayName("Divisão por si mesmo resulta um")
        void dividirPorSiMesmo() {
            assertEquals(1.0, calc.dividir(99, 99), DELTA);
        }

        @Test
        @DisplayName("Divisão por zero lança ArithmeticException com mensagem correta")
        void dividirPorZeroLancaExcecao() {
            ArithmeticException ex = assertThrows(
                ArithmeticException.class,
                () -> calc.dividir(10, 0)
            );
            assertEquals("Divisão por zero não é permitida.", ex.getMessage());
        }

        @Test
        @DisplayName("Zero dividido por zero também lança ArithmeticException")
        void zeroPorZeroLancaExcecao() {
            assertThrows(ArithmeticException.class, () -> calc.dividir(0, 0));
        }

        @Test
        @DisplayName("Negativo dividido por zero lança ArithmeticException")
        void negativoPorZeroLancaExcecao() {
            assertThrows(ArithmeticException.class, () -> calc.dividir(-5, 0));
        }

        @ParameterizedTest(name = "{0} / {1} = {2}")
        @DisplayName("Divisão parametrizada — múltiplos cenários")
        @CsvSource({
            "10,   2,   5",
            "-10, -2,   5",
            "7,    2,   3.5",
            "1,    3,   0.3333333333",
        })
        void dividirParametrizado(double a, double b, double esperado) {
            assertEquals(esperado, calc.dividir(a, b), 1e-9);
        }
    }
}
