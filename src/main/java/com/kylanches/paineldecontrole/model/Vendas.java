package com.kylanches.paineldecontrole.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Column(name = "numero", nullable = false)
    private Integer numero;

    @Column(name = "data_venda", nullable = false)
    private LocalDate dataVenda;

    @Column(name = "lanche", nullable = false)
    private String lanche;

    @Column(name = "forma_pagamento", nullable = false)
    private Integer formaPagamento;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;

    @Column(name = "valor_total", nullable = false)
    private Double valorTotal;
}