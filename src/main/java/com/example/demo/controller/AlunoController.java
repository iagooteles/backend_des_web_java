package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.services.AlunoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoDTO> listar() {
        return alunoService.listarTodos();
    }

    @PostMapping
    public AlunoDTO criar(@RequestBody AlunoDTO dto) {
        return alunoService.salvar(dto);
    }

    @GetMapping("/{id}")
    public AlunoDTO buscar(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

