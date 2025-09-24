package com.example.demo.controller;

import com.example.demo.entity.Endereco;
import com.example.demo.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> getAllEnderecos(){
        return enderecoService.findAllEnderecos();
    }

    @PostMapping("/{id}")
    public Endereco criarEndereco(@PathVariable Long id, @RequestBody Endereco endereco){
        return enderecoService.saveEndereco(id, endereco);
    }



}
