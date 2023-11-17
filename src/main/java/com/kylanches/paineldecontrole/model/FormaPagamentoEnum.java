package com.kylanches.paineldecontrole.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum FormaPagamentoEnum {
    CARTAODECREDITO(1, "Cartão de Crédito"),
    DINHEIRO(2, "Dinheiro"),
    PIX(3, "Pix");

    private int chave;
    private String descricao;
}