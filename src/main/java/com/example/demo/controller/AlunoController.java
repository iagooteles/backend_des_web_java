package com.example.demo.controller;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.services.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
        return alunoService.findAllALunos();
    }

    @PostMapping
    public AlunoDTO criarAluno(@RequestBody Aluno aluno) {
        return alunoService.saveAluno(aluno);
    }

    @PutMapping
    public AlunoDTO atualizarAluno(@RequestBody Aluno aluno) {
        return alunoService.update(aluno.getId(), aluno);
    }
    
    // Recebendo o id por parametro e não pelo body 
    @PutMapping("/{id}")
    public AlunoDTO atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoService.update(id, aluno);
    }

    @GetMapping("/{id}")
    public Optional<AlunoDTO> buscarPorId(@PathVariable Long id) {
        return alunoService.findAlunoById(id);
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

