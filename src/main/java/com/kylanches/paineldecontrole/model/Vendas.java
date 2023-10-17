package com.kylanches.paineldecontrole.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "vendas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Vendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = true)
    private Integer numero;

    @Column(name = "data_venda", nullable = true)
    private LocalDate dataVenda;

    @Enumerated(EnumType.STRING)
    @Column(name = "forma_pagamento", nullable = true)
    private FormaPagamentoEnum formaPagamento;

    @Enumerated(EnumType.STRING)
    @Column(name = "lanche", nullable = true)
    private LancheEnum lanche;

    @Column(name = "quantidade", nullable = true)
    private Integer quantidade;

    @Column(name = "valor_total", nullable = true)
    private Double valorTotal;
}