package br.com.java.springrestapiusuario.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.java.springrestapiusuario.model.Usuario;
import br.com.java.springrestapiusuario.repository.UsuarioRepository;

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@RequestMapping("/api")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;

    @GetMapping("/usuarios")
    public List<Usuario> todos() {
        return repository.findAll();
    }

    // Salvar cliente
    @PostMapping("/add")
    public Usuario salvar(@RequestBody Usuario usuario) {
        return repository.save(usuario);
    }

    @GetMapping("/usuarios/{id}")
    public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable(value = "id") long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if(usuario.isPresent()) {
            return ResponseEntity.ok().body(usuario.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    


    @PutMapping("/usuario/{id}")
    public ResponseEntity <Usuario> atualizarUsuario(@RequestBody Usuario usuario, @PathVariable Long id) {

        return repository.findById(id)
           .map(record -> {
               record.setNome(usuario.getNome());
               record.setSobrenome(usuario.getSobrenome());
               record.setUsuarionome(usuario.getUsuarionome());
               record.setEmail(usuario.getEmail());
               record.setImageURL(usuario.getImageURL());

               Usuario atualizarUsuario = repository.save(record);

               return ResponseEntity.ok().body(atualizarUsuario);
           }).orElse(ResponseEntity.notFound().build());
    }
}
