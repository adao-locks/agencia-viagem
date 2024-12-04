package com.agenciaviagem.service;

import com.agenciaviagem.model.Destino;

import com.agenciaviagem.repository.DestinoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinoService {

    //private final List<Destino> destinos = new ArrayList<>();
    @Autowired
    private DestinoRepository destinoRepository;


    public Destino cadastrarDestino(Destino destino) {
        destinoRepository.save(destino);
        return destino;
    }

    public List<Destino> listarDestinos() {
        return destinoRepository.findAll();
    }

    public Optional<Destino> buscarPorId(Long id) {
        return destinoRepository.findById(id);
    }

    public void excluirDestino(Long id) {
        destinoRepository.deleteById(id);
    }

    public void avaliarDestino(Long id, int nota) {
        // Buscar o destino pelo ID
        Destino destino = buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        // Calcular a nova média de avaliação
        double novaMedia = (destino.getMediaAvaliacao() * destino.getTotalAvaliacoes() + nota)
                / (destino.getTotalAvaliacoes() + 1);

        // Atualizar a média de avaliação e o total de avaliações
        destino.setMediaAvaliacao(novaMedia);
        destino.setTotalAvaliacoes(destino.getTotalAvaliacoes() + 1);

        // Salvar as mudanças no banco de dados
        destinoRepository.save(destino);
    }


    public Destino atualizarDestino(Destino destinoAtualizado) {
        destinoRepository.save(destinoAtualizado);
        return destinoAtualizado;
    }

    // Método para pesquisar destinos por nome ou localização
    public List<Destino> pesquisarDestinos(String nome, String localizacao) {
        return destinoRepository.pesquisarDestinos(nome, localizacao);
    }
}
