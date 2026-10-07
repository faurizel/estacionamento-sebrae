package br.gov.sp.etec.estacionamento.controller;

import br.gov.sp.etec.estacionamento.entity.VeiculoEntity;
import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.service.UsuarioService;
import br.gov.sp.etec.estacionamento.service.VeiculoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class LoginController {

    private static final Logger log = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    UsuarioService service;

    @Autowired
    VeiculoService veiculoSevice;

    @GetMapping("/")
    public String index(){
        return "login";
    }

    @GetMapping("/cadastro")
    public String cadastrar(){
        return "tela-cadastro";
    }

    @PostMapping("/efetuar-cadastro")
    public String efetuarCadastro(Usuario usuario){
        log.info(usuario.toString());
        service.cadastroUsuario(usuario);
        return "cadastro-sucess";
    }

    @PostMapping("autenticar")
    public String autenticar(String email, String senha, Model model){
        Usuario xpto = service.buscaUsuarioPorEmail(email);

        if(xpto != null && senha.equals(xpto.getSenha())){
           List<VeiculoEntity> veiculos =  veiculoSevice.listaVeiculo();
            model.addAttribute("veiculos",veiculos );
            return "painel";
        }else {
            return "erro";
        }

    }


}
