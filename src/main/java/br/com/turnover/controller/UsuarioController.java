package br.com.turnover.controller;

import br.com.turnover.dtos.UsuarioRecordDto;
import br.com.turnover.models.UsuarioModel;
import br.com.turnover.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    // ... GET, POST, DELETE, UPDATE methods
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    @PreAuthorize("hasRole('ROLE_CEO')")
    public List<UsuarioModel> getAllUsuarios() {
        return usuarioService.findAll();
    }

//    @GetMapping("/{id}")
//    public ResponseEntity<UsuarioModel> getUsuarioById(@PathVariable UUID id) {
//        Optional<UsuarioModel> usuario = usuarioService.findById(id);
//        return usuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
//    }

//    @PostMapping("/login")
//    public UsuarioModel login(@RequestBody UsuarioRecordDto usuario) {
//        String cpf = usuario.cpf();
//        String senha = usuario.senha();
//        System.out.println(cpf);
//        System.out.println(senha);
//        return usuarioService.login(cpf, senha);
//    }

    @PostMapping
    public UsuarioModel createUsuario(@RequestBody UsuarioRecordDto usuario) {
        return usuarioService.save(usuario);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> deleteUsuario(@PathVariable UUID id) {
//        usuarioService.deleteById(id);
//        return ResponseEntity.noContent().build();
//    }
}

