package br.fiap.calculadora_spring.controller;

import br.fiap.calculadora_spring.service.CalculadoraService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CalculadoraController {
    private CalculadoraService service;

    public CalculadoraController(CalculadoraService service) {this.service = service; }

    @PostMapping("/calcular") //Mapeia este método para a URL ".../calcular"
    public String calcular(@RequestParam String valor1, //pede valor1
                           @RequestParam String valor2, //pede valor2
                           @RequestParam String operacao, //pede qual operação
                           Model model)
    {
        BigDecimal resultado = null; //criada para guardar o resultado
        String erro = ""; //variavel para guardar uma mensagem de erro

        try{
            resultado = service.calcular(valor1, valor2, operacao);
            //manda o calculo para service
        } catch (Exception e){
            //pega um erro e guarda mensagem
            erro = e.getMessage();
        }
        // No HTML, você usará th:text="${resultado}"
        model.addAttribute("resultado", resultado);
        // Envia a mensagem de erro (se houver) para o HTML (th:text="${erro}")
        model.addAttribute("erro",erro);
        // Envia os valores de volta, para que o HTML possa manter os campos preenchidos
        model.addAttribute("valor1",valor1);
        model.addAttribute("valor2",valor2);
        // (Útil para o th:checked que mantem o que o usuario escolheu)
        model.addAttribute("operacao",operacao);
        return "index";
    }
}
