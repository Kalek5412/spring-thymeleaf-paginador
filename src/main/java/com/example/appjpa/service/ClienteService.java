package com.example.appjpa.service;

import com.example.appjpa.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface ClienteService {
    List<Cliente> listarClientes();

    Page<Cliente> findAll(Pageable pageable);
    Optional<Cliente> buscarPorId(Integer id);
    Cliente guardar(Cliente cliente);
    void eliminar(Integer id);
}
