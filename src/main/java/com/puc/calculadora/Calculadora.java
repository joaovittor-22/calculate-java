package com.puc.calculadora;

/**
 * Classe Calculadora.
 *
 * Implementa as quatro operações aritméticas básicas: adição, subtração,
 * multiplicação e divisão.
 */
public class Calculadora {

    /**
     * Realiza a adição de dois números.
     *
     * @param a primeiro operando
     * @param b segundo operando
     * @return o resultado da soma (a + b)
     */
    public double somar(double a, double b) {
        return a + b;
    }

    /**
     * Realiza a subtração de dois números.
     *
     * @param a minuendo
     * @param b subtraendo
     * @return o resultado da subtração (a - b)
     */
    public double subtrair(double a, double b) {
        return a - b;
    }

    /**
     * Realiza a multiplicação de dois números.
     *
     * @param a primeiro fator
     * @param b segundo fator
     * @return o resultado da multiplicação (a * b)
     */
    public double multiplicar(double a, double b) {
        return a * b;
    }

    /**
     * Realiza a divisão de dois números.
     *
     * @param a dividendo
     * @param b divisor
     * @return o resultado da divisão (a / b)
     * @throws ArithmeticException se o divisor for zero
     */
    public double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return a / b;
    }
}
