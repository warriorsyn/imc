package com.unit.imc.imc.controller;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.unit.imc.imc.model.Imc;

@RestController
@RequestMapping("/imc")
public class ImcController {

    @GetMapping
    public Imc getAll(@RequestParam double altura, @RequestParam double peso) {
        var imc = peso / (altura * 2);

        var imcModel = new Imc();

        imcModel.altura = String.format("%.2f", altura).replace(".", ",");
        imcModel.peso = String.format("%.2f", peso).replace(".", ",");
        imcModel.message = getImcMessage(imc);
        imcModel.imc = Double.toString(imc);
        return imcModel;
    }

    private String getImcMessage(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso ideal, vamos melhorar!";
        }

        if (imc > 18.5 && imc < 24.9) {
            return "Voce está no peso ideal, parabens!";
        }

        if (imc > 25.0 && imc < 29.9) {
            return "Voce está com excesso de peso, vamos melhorar!";
        }

        if (imc > 30 && imc < 34.9) {
            return "Obesidade Classe I, cuidado!";
        }

        if (imc > 35.0 && imc < 39.9) {
            return "Obesidade Classe II, ainda temos como mudar isso!";
        }

        return "Obesidade Classe III, procure ajuda, nada está perdido!";
    }
}
