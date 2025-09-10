package com.example.demo.services;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AlunoService {

    @Autowired
    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public List<AlunoDTO> findAllALunos() {
        return alunoRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public AlunoDTO saveAluno(Aluno aluno) {
        Aluno alunoSaved = alunoRepository.save(aluno);
        return toDTO(alunoSaved);
    }

    public Optional<AlunoDTO> findAlunoById(Long id) {
        return alunoRepository.findById(id).map(this::toDTO);
    }

    public AlunoDTO update(Long id, Aluno updateAluno) {
        return alunoRepository.findById(id)
                .map( aluno -> {
                    aluno.setNome(updateAluno.getNome());
                    aluno.setEmail(updateAluno.getEmail());
                    aluno.setIdade(updateAluno.getIdade());
                    aluno.setSenha(updateAluno.getSenha());

                    Aluno alunoSaved = alunoRepository.save(aluno);
                    return toDTO(alunoSaved);

                }).orElseThrow(() -> new RuntimeException("Aluno não encontrado"));
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }

    private AlunoDTO toDTO(Aluno aluno) {
        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getIdade());
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
