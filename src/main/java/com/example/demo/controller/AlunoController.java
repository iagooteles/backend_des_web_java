package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    @Autowired
    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @GetMapping
    public List<AlunoDTO> listar() {
        return alunoService.listarTodos();
    }

    @PostMapping
    public AlunoDTO criarAluno(@RequestBody AlunoDTO dto) {
        return alunoService.salvar(dto);
    }

    @PutMapping
    public AlunoDTO atualizarAluno(@RequestBody AlunoDTO dto) { 
        return alunoService.update(dto.getId(), dto);
    }
    
    // Recebendo o id por parametro e não pelo body 
    @PutMapping("/{id}")
    public AlunoDTO atualizarAluno(@PathVariable Long id, @RequestBody AlunoDTO dto) { 
        return alunoService.update(id, dto);
    }

    @GetMapping("/{id}")
    public AlunoDTO buscarPorId(@PathVariable Long id) {
        return alunoService.buscarPorId(id);
    }

    // jeito q o prof fez =>
//    @GetMapping("/{id}")
//    private ResponseEntity<Aluno> buscarAlunoPorId(@PathVariable Long id) {
//        return alunoService.buscarPorId(id)
//                .map(aluno -> new ResponseEntity<>(aluno, HttpStatus.OK))
//                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
//    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAluno(@PathVariable Long id) {
        alunoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}

