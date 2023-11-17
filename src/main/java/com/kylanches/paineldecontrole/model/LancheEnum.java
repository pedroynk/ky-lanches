package com.kylanches.paineldecontrole.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum LancheEnum {
    CAFÉ(1, "Café"),
    CACHORROQUENTE(2, "Cachorro - Quente"),
    PAOCOMCARNE(3, "Pão com Carne"),
    PAOCOMFRANGO(4, "Pão com Frango");

    private int chave;
    private String descricao;
}