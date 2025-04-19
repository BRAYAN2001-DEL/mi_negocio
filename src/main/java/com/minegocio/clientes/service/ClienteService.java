package com.minegocio.clientes.service;

import com.minegocio.clientes.domain.Cliente;
import com.minegocio.clientes.domain.Direccion;
import com.minegocio.clientes.dto.ClienteDTO;
import com.minegocio.clientes.exception.ClienteYaExisteException;
import com.minegocio.clientes.exception.DireccionMatrizYaExisteException;
import com.minegocio.clientes.repository.ClienteRepository;
import com.minegocio.clientes.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepo;
    private final DireccionRepository direccionRepo;

    @Autowired
    public ClienteService(ClienteRepository clienteRepo, DireccionRepository direccionRepo) {
        this.clienteRepo = clienteRepo;
        this.direccionRepo = direccionRepo;
    }

    public Cliente crearCliente(ClienteDTO dto) {
        validarClienteExistente(dto.identificationNumber);
        Cliente cliente = mapearClienteDesdeDTO(dto);
        Direccion direccionMatriz = crearDireccionMatrizDesdeDTO(dto, cliente);
        cliente.getDirecciones().add(direccionMatriz);
        return clienteRepo.save(cliente);
    }

    public List<Cliente> buscarClientes(String valor) {
        return clienteRepo.findByNumeroIdentificacionContainingOrNombresContaining(valor, valor);
    }

    public Cliente editarCliente(Long id, ClienteDTO dto) {
        Cliente cliente = obtenerClientePorId(id);
        actualizarClienteDesdeDTO(cliente, dto);
        return clienteRepo.save(cliente);
    }

    public void eliminarCliente(Long id) {
        clienteRepo.deleteById(id);
    }

     public Direccion agregarDireccion(Long clienteId, Direccion direccion) {
    Cliente cliente = obtenerClientePorId(clienteId);

    // Validar que no exista ya una dirección matriz
    if (direccion.isEsMatriz()) {
        boolean yaTieneMatriz = cliente.getDirecciones().stream()
            .anyMatch(Direccion::isEsMatriz);

        if (yaTieneMatriz) {
            throw new DireccionMatrizYaExisteException("El cliente ya tiene una dirección matriz registrada");
        }
    }

    direccion.setCliente(cliente);
    return direccionRepo.save(direccion);
}
    

    public List<Direccion> listarDirecciones(Long clienteId) {
        return direccionRepo.findByClienteId(clienteId);
    }

    // --- Métodos privados de soporte ---

    private void validarClienteExistente(String numeroIdentificacion) {
        if (clienteRepo.existsByNumeroIdentificacion(numeroIdentificacion)) {
            throw new ClienteYaExisteException("El cliente ya existe");
        }
    }

    private Cliente mapearClienteDesdeDTO(ClienteDTO dto) {
        Cliente cliente = new Cliente();
        cliente.setTipoIdentificacion(dto.identificationType);
        cliente.setNumeroIdentificacion(dto.identificationNumber);
        cliente.setNombres(dto.names);
        cliente.setCorreo(dto.email);
        cliente.setCelular(dto.cellphone);
        return cliente;
    }

    private Direccion crearDireccionMatrizDesdeDTO(ClienteDTO dto, Cliente cliente) {
        Direccion direccion = new Direccion();
        direccion.setProvincia(dto.mainProvince);
        direccion.setCiudad(dto.mainCity);
        direccion.setDireccion(dto.mainAddress);
        direccion.setEsMatriz(true);
        direccion.setCliente(cliente);
        return direccion;
    }

    private Cliente obtenerClientePorId(Long id) {
        return clienteRepo.findById(id).orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    private void actualizarClienteDesdeDTO(Cliente cliente, ClienteDTO dto) {
        cliente.setTipoIdentificacion(dto.identificationType);
        cliente.setNumeroIdentificacion(dto.identificationNumber);
        cliente.setNombres(dto.names);
        cliente.setCorreo(dto.email);
        cliente.setCelular(dto.cellphone);
    }
}
