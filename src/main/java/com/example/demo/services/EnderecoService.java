package com.example.demo.services;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Endereco;
import com.example.demo.repository.AlunoRepository;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    
    private final EnderecoRepository enderecoRepository;
    private final AlunoRepository alunoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository, AlunoRepository alunoRepository) {
        this.enderecoRepository = enderecoRepository;
        this.alunoRepository = alunoRepository;
    }

    public List<Endereco> findAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco saveEndereco(Long alunoId, Endereco endereco) {
        Aluno aluno = alunoRepository.findById(alunoId)
                .orElseThrow(() -> new RuntimeException("Esse ID Aluno não existe."));

        endereco.setAluno(aluno);
        return enderecoRepository.save(endereco);
    }

    public Endereco updateEndereco(Long id, Endereco enderecoUpdated) {
        return enderecoRepository.findById(id)
                .map( endereco -> {
                    endereco.setCep(enderecoUpdated.getCep());
                    endereco.setComplemento(enderecoUpdated.getComplemento());
                    endereco.setLogradouro(enderecoUpdated.getLogradouro());
                    endereco.setNumero(enderecoUpdated.getNumero());

                    return enderecoRepository.save(endereco);

                }).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
    }

    @Transacitional
    public Endereco updateEnderecoByAlunoId(Long alunoId, Endereco novoEndereco) {
        Aluno aluno = alunoRepository.findById(alunoId);

        Endereco enderecoAtual = enderecoRepository.findAlunoById(alunoId);

        enderecoAtual.setLogradouro(novoEndereco.getLogradouro);
        enderecoAtual.setCep(novoEndereco.getCep);
        enderecoAtual.setComplemento(novoEndereco.getComplemento);
        enderecoAtual.setNumero(novoEndereco.getNumero);

        enderecoAtual.setAluno(aluno);
        
        return enderecoRepository.save(enderecoAtual);
    }

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

}
