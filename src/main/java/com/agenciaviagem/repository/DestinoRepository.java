package com.agenciaviagem.repository;

import com.agenciaviagem.model.Destino;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Repository

public interface DestinoRepository extends JpaRepository<Destino, Long> {
    // Método que busca destinos por nome e localização
    @Query("SELECT d FROM Destino d WHERE " +
            "(LOWER(d.nome) LIKE LOWER(CONCAT('%', :nome, '%')) OR :nome IS NULL) AND " +
            "(LOWER(d.localizacao) LIKE LOWER(CONCAT('%', :localizacao, '%')) OR :localizacao IS NULL)")
    List<Destino> pesquisarDestinos(@Param("nome") String nome, @Param("localizacao") String localizacao);
}