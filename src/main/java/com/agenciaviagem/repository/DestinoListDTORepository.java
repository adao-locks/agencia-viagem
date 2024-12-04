package com.agenciaviagem.repository;

import com.agenciaviagem.model.Destino;
import com.agenciaviagem.model.DestinoListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface DestinoListDTORepository extends JpaRepository<DestinoListDTO, Long> {
}
