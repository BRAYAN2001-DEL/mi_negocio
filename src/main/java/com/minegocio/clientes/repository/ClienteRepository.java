package com.minegocio.clientes.repository;

import com.minegocio.clientes.domain.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    List<Cliente> findByNumeroIdentificacionContainingOrNombresContaining(String id, String nombres);
    boolean existsByNumeroIdentificacion(String numeroIdentificacion);
}
