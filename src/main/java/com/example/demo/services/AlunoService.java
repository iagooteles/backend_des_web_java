package com.example.demo.services;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoDTO> listarTodos() {
        List<Aluno> alunos = alunoRepository.findAll();
        return alunos.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AlunoDTO salvar(AlunoDTO dto) {
        Aluno aluno = toEntity(dto);
        aluno = alunoRepository.save(aluno);
        return toDTO(aluno);
    }

    // Aqui pode-se retornar Optional também, se não quiser tratar o erro.
    public AlunoDTO buscarPorId(Long id) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
        return toDTO(aluno);
    }

    public AlunoDTO update(Long id, AlunoDTO updateAluno) {
        return alunoRepository.findById(id)
                .map( aluno -> {
                    aluno.setNome(updateAluno.getNome());
                    aluno.setEmail(updateAluno.getEmail());
                    aluno.setIdade(updateAluno.getIdade());
                    return toDTO(alunoRepository.save(aluno));
                }).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        AlunoDTO dto = new AlunoDTO();
        dto.setId(aluno.getId());
        dto.setNome(aluno.getNome());
        dto.setEmail(aluno.getEmail());
        dto.setIdade(aluno.getIdade());
        return dto;
    }

    private Aluno toEntity(AlunoDTO dto) {
        Aluno aluno = new Aluno();
        aluno.setId(dto.getId());
        aluno.setNome(dto.getNome());
        aluno.setEmail(dto.getEmail());
        aluno.setIdade(dto.getIdade());
        return aluno;
    }
}
