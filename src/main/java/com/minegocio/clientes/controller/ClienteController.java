package com.minegocio.clientes.controller;

import com.minegocio.clientes.domain.Cliente;
import com.minegocio.clientes.domain.Direccion;
import com.minegocio.clientes.dto.ClienteDTO;
import com.minegocio.clientes.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/crear")
    public Cliente crear(@RequestBody ClienteDTO dto) {
        return clienteService.crearCliente(dto);
    }

    @GetMapping("/buscar")
    public List<Cliente> buscar(@RequestParam String valor) {
        return clienteService.buscarClientes(valor);
    }

    @PutMapping("/{id}")
    public Cliente editar(@PathVariable Long id, @RequestBody ClienteDTO dto) {
        return clienteService.editarCliente(id, dto);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
    }

    @PostMapping("/{id}/direccion")
    public Direccion agregarDireccion(@PathVariable Long id, @RequestBody Direccion direccion) {
        return clienteService.agregarDireccion(id, direccion);
    }

    @GetMapping("/{id}/direcciones")
    public List<Direccion> listarDirecciones(@PathVariable Long id) {
        return clienteService.listarDirecciones(id);
    }
}
