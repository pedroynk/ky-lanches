package com.kylanches.paineldecontrole.controller;

import java.beans.PropertyEditorSupport;
import java.security.Principal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kylanches.paineldecontrole.model.FormaPagamentoEnum;
import com.kylanches.paineldecontrole.model.Vendas;
import com.kylanches.paineldecontrole.services.VendasService;

@Controller
@RequestMapping("vendas")
public class VendasController {

    @Autowired
    private VendasService vendasService;


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDate.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(LocalDate.parse(text, DateTimeFormatter.ISO_DATE));
            }
        });
    }
    
    @GetMapping("/cadastrar")
    public ModelAndView cadastrar() {
        ModelAndView modelAndView = new ModelAndView("vendas/formulario");

        modelAndView.addObject("vendas", new Vendas());
        modelAndView.addObject("formasPagamento", FormaPagamentoEnum.values());
        return modelAndView;
    }

    @PostMapping("/cadastrar")
    public ModelAndView cadastrar(@Valid @ModelAttribute Vendas vendas, BindingResult bindingResult, ModelMap modelMap,
            RedirectAttributes attrs, @RequestParam(value = "vendas", required = false) Principal principal) {
                Double valorTotal = 0.0;
            for(int i=0; i< vendas.getLanche().size();i++){
                System.out.println(vendas.getLanche().get(i));
                if(vendas.getLanche().get(i).equals("Café")){
                    valorTotal += 1.5;
                }
                else{
                    valorTotal += 3;
                }
            }
        if (bindingResult.hasErrors()) {
            modelMap.addAttribute("vendas", vendas);
            return new ModelAndView("redirect:/vendas/formulario");
        } else {
            vendas.setValorTotal(valorTotal);
            vendasService.atualizar(vendas, vendas.getId());
        }

        return new ModelAndView("redirect:/vendas/listar");
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