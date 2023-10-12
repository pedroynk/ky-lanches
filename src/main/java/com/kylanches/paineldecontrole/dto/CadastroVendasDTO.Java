package com.kylanches.paineldecontrole.dto;

import java.util.List;

import com.kylanches.paineldecontrole.model.Servico;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CadastroVendasDTO {

      private List<Vendas> vendas;

}