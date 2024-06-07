package com.example.gs.controller;

import com.example.gs.domain.Relatorio;
import com.example.gs.repository.RelatorioRepository;
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
@RequestMapping("/relatorios")
public class RelatorioController {
    @Autowired
    private RelatorioRepository relatorioRepository;

    //Retorna todos os relatorios
    @GetMapping
    public ResponseEntity<List<EntityModel<Relatorio>>> getAllRelatorios(){
        List<EntityModel<Relatorio>> todosRelatorios = relatorioRepository.findAll().stream()
                .map(relatorio -> EntityModel.of(relatorio,
                        linkTo(methodOn(RelatorioController.class).getRelatorioById(relatorio.getId_relatorio())).withSelfRel(),
                        linkTo(methodOn(RelatorioController.class).atualizarDescricao(relatorio.getId_relatorio(), relatorio)).withRel("atualizarDescricao"),
                        linkTo(methodOn(RelatorioController.class).deletarRelatorio(relatorio.getId_relatorio())).withRel("deletarRelatorio")
                )).collect(Collectors.toList());
        return ResponseEntity.ok(todosRelatorios);
    }

    //Retorna um relatorio pelo id_relatorio
    @GetMapping("/{id_relatorio}")
    public ResponseEntity<EntityModel<Relatorio>> getRelatorioById(@PathVariable String id_relatorio) {
        Optional<Relatorio> relatorioEncontrado = relatorioRepository.findById(id_relatorio);

        if (relatorioEncontrado.isPresent()) {
            Relatorio relatorio = relatorioEncontrado.get();
            EntityModel<Relatorio> relatorioModel = EntityModel.of(relatorio,
                    linkTo(methodOn(RelatorioController.class).getRelatorioById(id_relatorio)).withSelfRel(),
                    linkTo(methodOn(RelatorioController.class).getAllRelatorios()).withRel("todosRelatorios"),
                    linkTo(methodOn(RelatorioController.class).atualizarDescricao(id_relatorio, relatorio)).withRel("atualizarDescricao"),
                    linkTo(methodOn(RelatorioController.class).deletarRelatorio(id_relatorio)).withRel("deletarRelatorio")
            );
            return ResponseEntity.ok(relatorioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra um novo relatorio
    @PostMapping
    public ResponseEntity<EntityModel<Relatorio>> cadastrarRelatorio(@RequestBody Relatorio relatorio) {
        Relatorio novoRelatorio = relatorioRepository.save(relatorio);
        EntityModel<Relatorio> relatorioModel = EntityModel.of(novoRelatorio,
                linkTo(methodOn(RelatorioController.class).getRelatorioById(novoRelatorio.getId_relatorio())).withSelfRel(),
                linkTo(methodOn(RelatorioController.class).getAllRelatorios()).withRel("todosRelatorios"),
                linkTo(methodOn(RelatorioController.class).atualizarDescricao(novoRelatorio.getId_relatorio(), novoRelatorio)).withRel("atualizarDescricao"),
                linkTo(methodOn(RelatorioController.class).deletarRelatorio(novoRelatorio.getId_relatorio())).withRel("deletarRelatorio")
        );
        return ResponseEntity.ok(relatorioModel);
    }

    //Atualiza a descrição de um relatorio pelo id_relatorio
    @PutMapping("/{id_relatorio}/atualizarDescricao")
    public ResponseEntity<EntityModel<Relatorio>> atualizarDescricao(@PathVariable String id_relatorio, @RequestBody Relatorio relatorio) {
        Optional<Relatorio> relatorioEncontrado = relatorioRepository.findById(id_relatorio);

        if (relatorioEncontrado.isPresent()) {
            Relatorio relatorioAtualizado = relatorioEncontrado.get();
            relatorioAtualizado.setDescricao(relatorio.getDescricao());
            relatorioRepository.save(relatorioAtualizado);
            EntityModel<Relatorio> relatorioModel = EntityModel.of(relatorioAtualizado,
                    linkTo(methodOn(RelatorioController.class).getRelatorioById(id_relatorio)).withSelfRel(),
                    linkTo(methodOn(RelatorioController.class).getAllRelatorios()).withRel("todosRelatorios"),
                    linkTo(methodOn(RelatorioController.class).atualizarDescricao(id_relatorio, relatorio)).withRel("atualizarDescricao"),
                    linkTo(methodOn(RelatorioController.class).deletarRelatorio(id_relatorio)).withRel("deletarRelatorio")
            );
            return ResponseEntity.ok(relatorioModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta um relatorio pelo id_relatorio
    @DeleteMapping("/{id_relatorio}/deletarRelatorio")
    public ResponseEntity deletarRelatorio(@PathVariable String id_relatorio){
        Optional<Relatorio> relatorioEncontrado = relatorioRepository.findById(id_relatorio);

        if (relatorioEncontrado.isPresent()) {
            relatorioRepository.deleteById(id_relatorio);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
