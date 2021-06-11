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
        double imc = peso / (altura * 2);

        Imc imcModel = new Imc();

        imcModel.altura = String.format("%.2f", altura).replace(".", ",");
        imcModel.peso = String.format("%.2f", peso).replace(".", ",");
        imcModel.classification = getImcClassification(imc);
        imcModel.risk = getImcRisk(imc);
        imcModel.imc = String.format("%.2f", imc);
        return imcModel;
    }

    private String getImcClassification(double imc) {
        if (imc < 18.5) {
            return "Abaixo do peso";
        }

        if (imc > 18.5 && imc < 24.9) {
            return "Peso ideal";
        }

        if (imc > 25.0 && imc < 29.9) {
            return "Excesso de peso";
        }

        if (imc > 30 && imc < 34.9) {
            return "Obesidade grau 1";
        }

        if (imc > 35.0 && imc < 39.9) {
            return "Obesidade grau 2";
        }

        return "Obesidade grau 3";
    }

    private String getImcRisk(double imc) {
        if (imc < 18.5) {
            return "Elevado";
        }

        if (imc > 18.5 && imc < 24.9) {
            return "Inexistente";
        }

        if (imc > 25.0 && imc < 29.9) {
            return "Elevado";
        }

        if (imc > 30 && imc < 34.9) {
            return "Muito elevado";
        }

        if (imc > 35.0 && imc < 39.9) {
            return "Muitíssimo elevado";
        }

        return "Obesidade mórbida";
    }
}
