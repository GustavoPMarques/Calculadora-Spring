package br.fiap.calculadora_spring.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class CalculadoraService {

    public BigDecimal calcular(String valor1, String valor2, String operacao) {
        //Big decimal serve para evitar problemas no arredondamento e adiciona métodos que facilitam operações
        //Tais métodos como add, multiply
        BigDecimal v1 = toBigDecimal(valor1);
        BigDecimal v2 = toBigDecimal(valor2);

        return switch(operacao) {
            case "soma" -> v1.add(v2); //case no MESMO nome do value
            case "subtracao" -> v1.subtract(v2);
            case "multiplicacao" -> v1.multiply(v2);
            case "divisao" -> {
                if (v2.compareTo(BigDecimal.ZERO) == 0){ // "se o valor de 2 for igual a 0...
                    throw new IllegalArgumentException("pare imediatamente de fazer essa palhaçada");
                    //para tudo para lançar exceção
                }
                yield v1.divide(v2, 6, RoundingMode.HALF_UP);
                //yield é um RETURN, scale é o maximo de decimais da divisao
            }
            default -> throw new IllegalArgumentException("Xabu: " + operacao);
            //É executado se a variável operacao não for igual a nenhum dos outros case
            //("soma", "subtracao", "multiplicacao", "divisao").

        };

    }

    private BigDecimal toBigDecimal(String valor) {
        String aux = valor.trim().replace(",", ".");
        //trim retira espaço em branco
        try {
            return new BigDecimal(aux);
        } catch (Exception e){
            throw new IllegalArgumentException("Deu xabu ai seu merda");
        }
    }
}
