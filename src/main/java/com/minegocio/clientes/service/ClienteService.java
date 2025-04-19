package com.minegocio.clientes.service;

import com.minegocio.clientes.domain.Cliente;
import com.minegocio.clientes.domain.Direccion;
import com.minegocio.clientes.dto.ClienteDTO;
import com.minegocio.clientes.repository.ClienteRepository;
import com.minegocio.clientes.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepo;

    @Autowired
    private DireccionRepository direccionRepo;

    public Cliente crearCliente(ClienteDTO dto) {
        if (clienteRepo.existsByNumeroIdentificacion(dto.identificationNumber)) {
            throw new RuntimeException("El cliente ya existe");
        }

        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(dto.identificationType);
        cliente.setNumeroIdentificacion(dto.identificationNumber);
        cliente.setNombres(dto.names);
        cliente.setCorreo(dto.email);
        cliente.setCelular(dto.cellphone);

        Direccion matriz = new Direccion();
        matriz.setProvincia(dto.mainProvince);
        matriz.setCiudad(dto.mainCity);
        matriz.setDireccion(dto.mainAddress);
        matriz.setEsMatriz(true);
        matriz.setCliente(cliente);

        cliente.getDirecciones().add(matriz);

        return clienteRepo.save(cliente);
    }

    public List<Cliente> buscarClientes(String valor) {
        return clienteRepo.findByNumeroIdentificacionContainingOrNombresContaining(valor, valor);
    }

    public Cliente editarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = clienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
        cliente.setTipoIdentificacion(dto.identificationType);
        cliente.setNumeroIdentificacion(dto.identificationNumber);
        cliente.setNombres(dto.names);
        cliente.setCorreo(dto.email);
        cliente.setCelular(dto.cellphone);
        return clienteRepo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepo.deleteById(id);
    }

    public Direccion agregarDireccion(Long clienteId, Direccion direccion) {
        Cliente cliente = clienteRepo.findById(clienteId).orElseThrow();
        direccion.setCliente(cliente);
        return direccionRepo.save(direccion);
    }

    public List<Direccion> listarDirecciones(Long clienteId) {
        return direccionRepo.findByClienteId(clienteId);
    }
}
