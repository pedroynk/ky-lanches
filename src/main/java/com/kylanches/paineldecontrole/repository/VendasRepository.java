package com.kylanches.paineldecontrole.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kylanches.paineldecontrole.model.Vendas;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface VendasRepository extends JpaRepository<Vendas, Long> {

    boolean existsByNumero(Integer numero);

    Optional<Vendas> findById(Long id);

    List<Vendas> findByDataVenda(LocalDate dataVenda);

    List<Vendas> findByLanche(String lanche, Pageable pageable);

    List<Vendas> findByFormaPagamento(Integer formaPagamento);

    List<Vendas> findByQuantidade(Integer quantidade);

    List<Vendas> findByValorTotal(Double valorTotal);

	Page<Vendas> findAll(Specification<Vendas> spec, Pageable pageable);

}
