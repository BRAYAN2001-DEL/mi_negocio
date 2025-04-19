package com.minegocio.clientes.service;

import com.minegocio.clientes.domain.Cliente;
import com.minegocio.clientes.domain.Direccion;
import com.minegocio.clientes.dto.ClienteDTO;
import com.minegocio.clientes.exception.ClienteYaExisteException;
import com.minegocio.clientes.repository.ClienteRepository;
import com.minegocio.clientes.repository.DireccionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
 

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClienteServiceTest {

    @Mock
    private ClienteRepository clienteRepo;

    @Mock
    private DireccionRepository direccionRepo;

    @InjectMocks
    private ClienteService clienteService;

    private ClienteDTO clienteDTO;
    private Cliente cliente;
    private Direccion direccion;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        clienteDTO = new ClienteDTO();
        clienteDTO.setIdentificationType("Cédula");
        clienteDTO.setIdentificationNumber("1234567890");
        clienteDTO.setNames("Juan Pérez");
        clienteDTO.setEmail("juan@example.com");
        clienteDTO.setCellphone("0987654321");
        clienteDTO.setMainProvince("Pichincha");
        clienteDTO.setMainCity("Quito");
        clienteDTO.setMainAddress("Av. 10 de Agosto");

        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setTipoIdentificacion("Cédula");
        cliente.setNumeroIdentificacion("1234567890");
        cliente.setNombres("Juan Pérez");
        cliente.setCorreo("juan@example.com");
        cliente.setCelular("0987654321");

        direccion = new Direccion();
        direccion.setId(1L);
        direccion.setDireccion("Av. 10 de Agosto");
        direccion.setCiudad("Quito");
        direccion.setProvincia("Pichincha");
        direccion.setEsMatriz(true);
        direccion.setCliente(cliente);
    }

    @Test
    public void testCrearCliente() {
        when(clienteRepo.existsByNumeroIdentificacion(clienteDTO.getIdentificationNumber())).thenReturn(false);
        when(clienteRepo.save(any(Cliente.class))).thenReturn(cliente);

        Cliente creado = clienteService.crearCliente(clienteDTO);

        assertNotNull(creado);
        assertEquals("Juan Pérez", creado.getNombres());
        verify(clienteRepo, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testCrearClienteYaExistente() {
        when(clienteRepo.existsByNumeroIdentificacion(clienteDTO.getIdentificationNumber())).thenReturn(true);

        ClienteYaExisteException thrown = assertThrows(ClienteYaExisteException.class, () -> {
            clienteService.crearCliente(clienteDTO);
        });

        assertEquals("El cliente ya existe", thrown.getMessage());
    }

    @Test
    public void testBuscarClientes() {
        when(clienteRepo.findByNumeroIdentificacionContainingOrNombresContaining("Juan", "Juan")).thenReturn(Arrays.asList(cliente));

        List<Cliente> clientes = clienteService.buscarClientes("Juan");

        assertEquals(1, clientes.size());
        assertEquals("Juan Pérez", clientes.get(0).getNombres());
    }

    @Test
    public void testEditarCliente() {
        when(clienteRepo.findById(1L)).thenReturn(Optional.of(cliente));
        when(clienteRepo.save(any(Cliente.class))).thenReturn(cliente);

        clienteDTO.setNames("Juan Pérez Actualizado");
        Cliente editado = clienteService.editarCliente(1L, clienteDTO);

        assertEquals("Juan Pérez Actualizado", editado.getNombres());
        verify(clienteRepo, times(1)).save(any(Cliente.class));
    }

    @Test
    public void testEliminarCliente() {
        doNothing().when(clienteRepo).deleteById(1L);

        clienteService.eliminarCliente(1L);

        verify(clienteRepo, times(1)).deleteById(1L);
    }

    @Test
    public void testAgregarDireccion() {
        when(clienteRepo.findById(1L)).thenReturn(Optional.of(cliente));
        when(direccionRepo.save(any(Direccion.class))).thenReturn(direccion);

        Direccion nuevaDireccion = clienteService.agregarDireccion(1L, direccion);

        assertNotNull(nuevaDireccion);
        assertEquals("Av. 10 de Agosto", nuevaDireccion.getDireccion());
        verify(direccionRepo, times(1)).save(any(Direccion.class));
    }

    @Test
    public void testListarDirecciones() {
        when(direccionRepo.findByClienteId(1L)).thenReturn(Arrays.asList(direccion));

        List<Direccion> direcciones = clienteService.listarDirecciones(1L);

        assertEquals(1, direcciones.size());
        assertEquals("Av. 10 de Agosto", direcciones.get(0).getDireccion());
    }
}
