package com.kylanches.paineldecontrole.services;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import com.kylanches.paineldecontrole.model.Vendas;
import com.kylanches.paineldecontrole.repository.VendasRepository;

@Service
public class VendasService {

    @Autowired
    private VendasRepository vendasRepository;

    public Page<Vendas> listar(Pageable pageable) {
        return this.vendasRepository.findAll(pageable);
    }

    public List<Vendas> buscarTodos() {
        return vendasRepository.findAll();
    }

    public Vendas buscarPorId(Long id) {
        return vendasRepository.findById(id).orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    public Vendas cadastrar(Vendas vendas) {
        return vendasRepository.save(vendas);
    }

    public Vendas atualizar(Vendas vendas, Long id) {
        return vendasRepository.save(vendas);
    }

    public void excluirPorId(Long id) {
        vendasRepository.deleteById(id);
    }
}