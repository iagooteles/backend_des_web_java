package com.example.demo.services;

import com.example.demo.dto.AlunoDTO;
import com.example.demo.entity.Aluno;
import com.example.demo.entity.Endereco;
import com.example.demo.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    
    private final EnderecoRepository enderecoRepository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public List<Endereco> findAllEnderecos() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findEnderecoById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco saveEndereco(Endereco endereco) {
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

    public void deletarEndereco(Long id) {
        enderecoRepository.deleteById(id);
    }

}
