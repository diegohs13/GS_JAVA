package com.example.gs.controller;

import com.example.gs.domain.Usuario;
import com.example.gs.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;


import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    // Retorna todos os usuarios
    @GetMapping
    public ResponseEntity<List<EntityModel<Usuario>>> getAllUsuarios() {
        List<EntityModel<Usuario>> todosUsuarios = usuarioRepository.findAll().stream()
                .map(usuario -> EntityModel.of(usuario,
                        linkTo(methodOn(UsuarioController.class).getUsuarioById(usuario.getId_usuario())).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).atualizarSenha(usuario.getId_usuario(), usuario)).withRel("atualizarSenha"),
                        linkTo(methodOn(UsuarioController.class).deletarUsuario(usuario.getId_usuario())).withRel("deletarUsuario"),
                        linkTo(methodOn(UsuarioController.class).login(usuario.getId_usuario(), usuario)).withRel("login")
                )).collect(Collectors.toList());
        return ResponseEntity.ok(todosUsuarios);
    }

    // Retorna um usuario pelo id_usuario
    @GetMapping("/{id_usuario}")
    public ResponseEntity<EntityModel<Usuario>> getUsuarioById(@PathVariable String id_usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id_usuario);

        if (usuarioEncontrado.isPresent()) {
            Usuario usuario = usuarioEncontrado.get();
            EntityModel<Usuario> usuarioModel = EntityModel.of(usuario,
                    linkTo(methodOn(UsuarioController.class).getUsuarioById(id_usuario)).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("todosUsuarios"),
                    linkTo(methodOn(UsuarioController.class).atualizarSenha(id_usuario, usuario)).withRel("atualizarSenha"),
                    linkTo(methodOn(UsuarioController.class).deletarUsuario(id_usuario)).withRel("deletarUsuario"),
                    linkTo(methodOn(UsuarioController.class).login(id_usuario, usuario)).withRel("login")
            );
            return ResponseEntity.ok(usuarioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Cadastra um novo usuario
    @PostMapping
    public ResponseEntity<EntityModel<Usuario>> cadastrarUsuario(@RequestBody Usuario usuario) {
        Usuario novoUsuario = usuarioRepository.save(usuario);
        EntityModel<Usuario> usuarioModel = EntityModel.of(novoUsuario,
                linkTo(methodOn(UsuarioController.class).getUsuarioById(novoUsuario.getId_usuario())).withSelfRel(),
                linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("todosUsuarios"),
                linkTo(methodOn(UsuarioController.class).atualizarSenha(novoUsuario.getId_usuario(), novoUsuario)).withRel("atualizarSenha"),
                linkTo(methodOn(UsuarioController.class).deletarUsuario(novoUsuario.getId_usuario())).withRel("deletarUsuario"),
                linkTo(methodOn(UsuarioController.class).login(novoUsuario.getId_usuario(), novoUsuario)).withRel("login")
        );
        return ResponseEntity.ok(usuarioModel);
    }

    // Atualiza a senha do usuario pelo id_usuario
    @PutMapping("/{id_usuario}/atualizarSenha")
    public ResponseEntity<EntityModel<Usuario>> atualizarSenha(@PathVariable String id_usuario, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id_usuario);

        if (usuarioEncontrado.isPresent()) {
            Usuario usuarioAtualizado = usuarioEncontrado.get();
            usuarioAtualizado.setSenha(usuario.getSenha());
            usuarioRepository.save(usuarioAtualizado);
            EntityModel<Usuario> usuarioModel = EntityModel.of(usuarioAtualizado,
                    linkTo(methodOn(UsuarioController.class).getUsuarioById(id_usuario)).withSelfRel(),
                    linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("todosUsuarios"),
                    linkTo(methodOn(UsuarioController.class).deletarUsuario(id_usuario)).withRel("deletarUsuario"),
                    linkTo(methodOn(UsuarioController.class).login(id_usuario, usuarioAtualizado)).withRel("login")
            );
            return ResponseEntity.ok(usuarioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Deleta um usuario pelo id_usuario
    @DeleteMapping("/{id_usuario}")
    public ResponseEntity<?> deletarUsuario(@PathVariable String id_usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id_usuario);

        if (usuarioEncontrado.isPresent()) {
            usuarioRepository.deleteById(id_usuario);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Realiza o login do usuario pelo id_usuario
    @PostMapping("/{id_usuario}/login")
    public ResponseEntity<EntityModel<Usuario>> login(@PathVariable String id_usuario, @RequestBody Usuario usuario) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id_usuario);

        if (usuarioEncontrado.isPresent()) {
            Usuario usuarioLogado = usuarioEncontrado.get();
            if (usuario.getSenha().equals(usuarioLogado.getSenha())) {
                EntityModel<Usuario> usuarioModel = EntityModel.of(usuarioLogado,
                        linkTo(methodOn(UsuarioController.class).getUsuarioById(id_usuario)).withSelfRel(),
                        linkTo(methodOn(UsuarioController.class).getAllUsuarios()).withRel("todosUsuarios"),
                        linkTo(methodOn(UsuarioController.class).atualizarSenha(id_usuario, usuarioLogado)).withRel("atualizarSenha"),
                        linkTo(methodOn(UsuarioController.class).deletarUsuario(id_usuario)).withRel("deletarUsuario")
                );
                return ResponseEntity.ok(usuarioModel);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}