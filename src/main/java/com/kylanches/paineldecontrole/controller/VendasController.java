package com.kylanches.paineldecontrole.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.kylanches.paineldecontrole.model.Vendas;
import com.kylanches.paineldecontrole.services.VendasService;

@Controller
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;

    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("vendas/formulario");

        modelAndView.addObject("vendas", new Vendas());
        return modelAndView;
    }

    @GetMapping("/{id}/editar")
    public ModelAndView editar(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("vendas/formulario");
        modelAndView.addObject("vendas", this.vendasService.buscarPorId(id));

        return modelAndView;
    }

    @GetMapping("/listar")
    public ModelAndView listar(Pageable pageable) {
        ModelAndView modelAndView = new ModelAndView("vendas/lista");

        Page<Vendas> vendasPage = this.vendasService.listar(pageable);

        modelAndView.addObject("vendas", vendasPage);

        return modelAndView;
    }

    @GetMapping("/{id}/excluir")
    public String excluir(@PathVariable Long id) {
        vendasService.excluirPorId(id);

        return "redirect:/vendas/listar";
    }

    @GetMapping("/{id}")
    public ModelAndView detalhes(@PathVariable Long id) {
        ModelAndView modelAndView = new ModelAndView("vendas/detalhes");

        modelAndView.addObject("vendas", vendasService.buscarPorId(id));
        return modelAndView;
    }
}