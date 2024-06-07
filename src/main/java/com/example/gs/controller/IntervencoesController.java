package com.example.gs.controller;

import com.example.gs.domain.Intervencoes;
import com.example.gs.repository.IntervencoesRepository;
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
@RequestMapping("/intervencoes")
public class IntervencoesController {
    @Autowired
    private IntervencoesRepository intervencoesRepository;

    //Retorna todas as intervencoes
    @GetMapping
    public ResponseEntity<List<EntityModel<Intervencoes>>> getAllIntervencoes(){
        List<EntityModel<Intervencoes>> todasIntervencoes = intervencoesRepository.findAll().stream()
                .map(intervencoes -> EntityModel.of(intervencoes,
                        linkTo(methodOn(IntervencoesController.class).getIntervencoesById(intervencoes.getId_intervencao())).withSelfRel(),
                        linkTo(methodOn(IntervencoesController.class).atualizarResiduos(intervencoes.getId_intervencao(), intervencoes)).withRel("atualizarResiduos"),
                        linkTo(methodOn(IntervencoesController.class).deletarIntervencao(intervencoes.getId_intervencao())).withRel("deletarIntervencao")
                )).collect(Collectors.toList());
        return ResponseEntity.ok(todasIntervencoes);
    }

    //Retorna uma intervencao pelo id_intervencao
    @GetMapping("/{id_intervencao}")
    public ResponseEntity<EntityModel<Intervencoes>> getIntervencoesById(@PathVariable String id_intervencao) {
        Optional<Intervencoes> intervencoesEncontrada = intervencoesRepository.findById(id_intervencao);

        if (intervencoesEncontrada.isPresent()) {
            Intervencoes intervencoes = intervencoesEncontrada.get();
            EntityModel<Intervencoes> intervencoesModel = EntityModel.of(intervencoes,
                    linkTo(methodOn(IntervencoesController.class).getIntervencoesById(id_intervencao)).withSelfRel(),
                    linkTo(methodOn(IntervencoesController.class).getAllIntervencoes()).withRel("todasIntervencoes"),
                    linkTo(methodOn(IntervencoesController.class).atualizarResiduos(id_intervencao, intervencoes)).withRel("atualizarResiduos"),
                    linkTo(methodOn(IntervencoesController.class).deletarIntervencao(id_intervencao)).withRel("deletarIntervencao")
            );
            return ResponseEntity.ok(intervencoesModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra uma nova intervencao
    @PostMapping

    public ResponseEntity<EntityModel<Intervencoes>> cadastrarIntervencao(@RequestBody Intervencoes intervencoes) {
        Intervencoes novaIntervencao = intervencoesRepository.save(intervencoes);
        EntityModel<Intervencoes> intervencoesModel = EntityModel.of(novaIntervencao,
                linkTo(methodOn(IntervencoesController.class).getIntervencoesById(novaIntervencao.getId_intervencao())).withSelfRel(),
                linkTo(methodOn(IntervencoesController.class).getAllIntervencoes()).withRel("todasIntervencoes"),
                linkTo(methodOn(IntervencoesController.class).atualizarResiduos(novaIntervencao.getId_intervencao(), novaIntervencao)).withRel("atualizarResiduos"),
                linkTo(methodOn(IntervencoesController.class).deletarIntervencao(novaIntervencao.getId_intervencao())).withRel("deletarIntervencao")
        );
        return ResponseEntity.ok(intervencoesModel);
    }

    //Atualiza a quantidade de residuos de uma intervencao pelo id_intervencao
    @PutMapping("/{id_intervencao}/atualizarResiduos")
    public ResponseEntity<EntityModel<Intervencoes>> atualizarResiduos(@PathVariable String id_intervencao, @RequestBody Intervencoes intervencoes) {
        Optional<Intervencoes> intervencoesEncontrada = intervencoesRepository.findById(id_intervencao);
        if (intervencoesEncontrada.isPresent()) {
            Intervencoes intervencoesAtualizada = intervencoesEncontrada.get();
            intervencoesAtualizada.setQuantidade_residuos(intervencoes.getQuantidade_residuos());
            intervencoesRepository.save(intervencoesAtualizada);
            EntityModel<Intervencoes> intervencoesModel = EntityModel.of(intervencoesAtualizada,
                    linkTo(methodOn(IntervencoesController.class).getIntervencoesById(id_intervencao)).withSelfRel(),
                    linkTo(methodOn(IntervencoesController.class).getAllIntervencoes()).withRel("todasIntervencoes"),
                    linkTo(methodOn(IntervencoesController.class).atualizarResiduos(id_intervencao, intervencoes)).withRel("atualizarResiduos"),
                    linkTo(methodOn(IntervencoesController.class).deletarIntervencao(id_intervencao)).withRel("deletarIntervencao")
            );
            return ResponseEntity.ok(intervencoesModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta uma intervencao do banco de dados
    @DeleteMapping("/{id_intervencao}/deletarIntervencao")
    public ResponseEntity deletarIntervencao(@PathVariable String id_intervencao){
        Optional<Intervencoes> intervencoesEncontrada = intervencoesRepository.findById(id_intervencao);
        if (intervencoesEncontrada.isPresent()) {
            intervencoesRepository.deleteById(id_intervencao);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}