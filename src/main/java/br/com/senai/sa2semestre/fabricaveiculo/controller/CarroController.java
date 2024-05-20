package br.com.senai.sa2semestre.fabricaveiculo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/carros")
public class CarroController {

    @GetMapping
    public String getCarros(){
        return "Hello world!";
    }



}
