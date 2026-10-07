package br.gov.sp.etec.estacionamento.service;

import br.gov.sp.etec.estacionamento.entity.UsuarioEntity;
import br.gov.sp.etec.estacionamento.model.Usuario;
import br.gov.sp.etec.estacionamento.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository repository;

    @Override
    public String cadastroUsuario(Usuario usuario) {

        UsuarioEntity usuarioEntity = new UsuarioEntity();

        usuarioEntity.setNome(usuario.getNome());
        usuarioEntity.setCpf(usuario.getCpf());
        usuarioEntity.setEmail(usuario.getEmail());
        usuarioEntity.setNascimento(usuario.getNascimento());
        usuarioEntity.setTelefone(usuario.getTelefone());
        usuarioEntity.setSenha(usuario.getSenha());

        repository.save(usuarioEntity);

        return "Usuario cadastro com sucess";
    }

    @Override
    public List<Usuario> listarUsuario() {
        return List.of();
    }

    @Override
    public String atualizarUsuario(Usuario usuario) {
        return "";
    }

    @Override
    public String deletarUsuario(Long id) {
        return "";
    }

    @Override
    public Usuario buscaUsuarioPorEmail(String email) {
        UsuarioEntity entity = repository.findByEmail(email);
        Usuario user = toUsuario(entity);
        return user;
    }

    private Usuario toUsuario(UsuarioEntity entity){
        Usuario usuario = new Usuario();
        usuario.setEmail(entity.getEmail());
        usuario.setNome(entity.getNome());
        usuario.setSenha(entity.getSenha());
        usuario.setTelefone(entity.getTelefone());
        return usuario;
    }
}
