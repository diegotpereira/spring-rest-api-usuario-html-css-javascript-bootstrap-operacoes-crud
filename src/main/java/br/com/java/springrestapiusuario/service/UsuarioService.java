package br.com.java.springrestapiusuario.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.java.springrestapiusuario.model.Usuario;
import br.com.java.springrestapiusuario.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    public void addUsuario(Usuario usuario) {
        repository.save(usuario);
    }

    public List<Usuario> listaUsuarios() {

        return repository.findAll();
    }
}
