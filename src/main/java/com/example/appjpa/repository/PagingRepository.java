package com.example.appjpa.repository;

import com.example.appjpa.entity.Cliente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PagingRepository extends PagingAndSortingRepository<Cliente,Integer> {
    Page<Cliente> findAll(Pageable page);
}
