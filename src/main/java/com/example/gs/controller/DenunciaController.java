package com.example.gs.controller;

import com.example.gs.domain.Denuncia;
import com.example.gs.repository.DenunciaRepository;
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
@RequestMapping("/denuncias")
public class DenunciaController {
    @Autowired
    private DenunciaRepository denunciaRepository;

    //Retorna todas as denuncias
    @GetMapping
    public ResponseEntity<List<EntityModel<Denuncia>>> getAllDenuncias(){
        List<EntityModel<Denuncia>> todasDenuncias = denunciaRepository.findAll().stream()
                .map(denuncia -> EntityModel.of(denuncia,
                        linkTo(methodOn(DenunciaController.class).getDenunciaById(denuncia.getId_denuncia())).withSelfRel(),
                        linkTo(methodOn(DenunciaController.class).atualizarDescricao(denuncia.getId_denuncia(), denuncia)).withRel("atualizarDescricao"),
                        linkTo(methodOn(DenunciaController.class).deletarDenuncia(denuncia.getId_denuncia())).withRel("deletarDenuncia")
                )).collect(Collectors.toList());
        return ResponseEntity.ok(todasDenuncias);
    }

    //Retorna uma denuncia pelo id_denuncia
    @GetMapping("/{id_denuncia}")
    public ResponseEntity<EntityModel<Denuncia>> getDenunciaById(@PathVariable String id_denuncia) {
        Optional<Denuncia> denunciaEncontrada = denunciaRepository.findById(id_denuncia);

        if (denunciaEncontrada.isPresent()) {
            Denuncia denuncia = denunciaEncontrada.get();
            EntityModel<Denuncia> denunciaModel = EntityModel.of(denuncia,
                    linkTo(methodOn(DenunciaController.class).getDenunciaById(id_denuncia)).withSelfRel(),
                    linkTo(methodOn(DenunciaController.class).getAllDenuncias()).withRel("todasDenuncias"),
                    linkTo(methodOn(DenunciaController.class).atualizarDescricao(id_denuncia, denuncia)).withRel("atualizarDescricao"),
                    linkTo(methodOn(DenunciaController.class).deletarDenuncia(id_denuncia)).withRel("deletarDenuncia")
            );
            return ResponseEntity.ok(denunciaModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra uma nova denúncia
    @PostMapping
    public ResponseEntity<EntityModel<Denuncia>> cadastrarDenuncia(@RequestBody Denuncia denuncia) {
        Denuncia novaDenuncia = denunciaRepository.save(denuncia);
        EntityModel<Denuncia> denunciaModel = EntityModel.of(novaDenuncia,
                linkTo(methodOn(DenunciaController.class).getDenunciaById(novaDenuncia.getId_denuncia())).withSelfRel(),
                linkTo(methodOn(DenunciaController.class).getAllDenuncias()).withRel("todasDenuncias"),
                linkTo(methodOn(DenunciaController.class).atualizarDescricao(novaDenuncia.getId_denuncia(), novaDenuncia)).withRel("atualizarDescricao"),
                linkTo(methodOn(DenunciaController.class).deletarDenuncia(novaDenuncia.getId_denuncia())).withRel("deletarDenuncia")
        );
        return ResponseEntity.ok(denunciaModel);
    }

    //Atualiza a descrição de uma denúncia pelo id_denuncia
    @PutMapping("/{id_denuncia}/atualizarDescricao")
    public ResponseEntity<EntityModel<Denuncia>> atualizarDescricao(@PathVariable String id_denuncia, @RequestBody Denuncia denuncia) {
        Optional<Denuncia> denunciaEncontrada = denunciaRepository.findById(id_denuncia);
        if (denunciaEncontrada.isPresent()) {
            Denuncia denunciaAtualizada = denunciaEncontrada.get();
            denunciaAtualizada.setDescricao(denuncia.getDescricao());
            denunciaRepository.save(denunciaAtualizada);
            EntityModel<Denuncia> denunciaModel = EntityModel.of(denunciaAtualizada,
                    linkTo(methodOn(DenunciaController.class).getDenunciaById(id_denuncia)).withSelfRel(),
                    linkTo(methodOn(DenunciaController.class).getAllDenuncias()).withRel("todasDenuncias"),
                    linkTo(methodOn(DenunciaController.class).atualizarDescricao(id_denuncia, denuncia)).withRel("atualizarDescricao"),
                    linkTo(methodOn(DenunciaController.class).deletarDenuncia(id_denuncia)).withRel("deletarDenuncia")
            );
            return ResponseEntity.ok(denunciaModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta uma denúncia pelo id_denuncia
    @DeleteMapping("/{id_denuncia}/deletarDenuncia")
    public ResponseEntity deletarDenuncia(@PathVariable String id_denuncia){
        Optional<Denuncia> denunciaEncontrada = denunciaRepository.findById(id_denuncia);
        if (denunciaEncontrada.isPresent()) {
            denunciaRepository.deleteById(id_denuncia);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}