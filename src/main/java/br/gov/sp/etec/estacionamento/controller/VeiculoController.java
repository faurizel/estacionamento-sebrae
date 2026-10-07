package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Veiculo;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("veiculo")
public class VeiculoController {

    @Autowired
    VeiculoService service;

    @PostMapping("cadastrar")
    public String cadastrar(Veiculo x){
        service.cadastrarVeiculo(x);
        return "registrar-entrada";
    }

    @GetMapping("registrar-entrada")
    public String registrarEntrada(){
        return "registrar-entrada";
    }

    @GetMapping("registrar-saida")
    public String registrarSaida(Model model){
       List<VeiculoEntity>  veiculos = service.listaVeiculo();
       model.addAttribute("veiculos", veiculos);
        return "registrar-saida";
    }

    @GetMapping("saida/{id}")
    public String getVeiculo(Model model, @PathVariable Long id){
        List<VeiculoEntity>  veiculos = service.listaVeiculo();
        model.addAttribute("veiculos", veiculos);
        VeiculoEntity veiculo = service.buscaVeiculoPorId(id);
        model.addAttribute("veiculo",veiculo);
        return "registrar-saida";
    }

}
