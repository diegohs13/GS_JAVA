package com.example.gs.controller;

import com.example.gs.domain.Localidade;
import com.example.gs.repository.LocalidadeRepository;
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
@RequestMapping("/localidades")
public class LocalidadeController{
    @Autowired
    private LocalidadeRepository localidadeRepository;

    //Retorna todas as localidades
    @GetMapping
    public ResponseEntity<List<EntityModel<Localidade>>> getAllLocalidades(){
        List<EntityModel<Localidade>> todasLocalidades = localidadeRepository.findAll().stream()
                .map(localidade -> EntityModel.of(localidade,
                        linkTo(methodOn(LocalidadeController.class).getLocalidadeById(localidade.getId_local())).withSelfRel(),
                        linkTo(methodOn(LocalidadeController.class).atualizarNome(localidade.getId_local(), localidade)).withRel("atualizarNome"),
                        linkTo(methodOn(LocalidadeController.class).deletarLocalidade(localidade.getId_local())).withRel("deletarLocalidade")
                )).collect(Collectors.toList());
        return ResponseEntity.ok(todasLocalidades);
    }

    //Retorna uma localidade pelo id_localidade
    @GetMapping("/{id_localidade}")
    public ResponseEntity<EntityModel<Localidade>> getLocalidadeById(@PathVariable String id_localidade) {
        Optional<Localidade> localidadeEncontrada = localidadeRepository.findById(id_localidade);

        if (localidadeEncontrada.isPresent()) {
            Localidade localidade = localidadeEncontrada.get();
            EntityModel<Localidade> localidadeModel = EntityModel.of(localidade,
                    linkTo(methodOn(LocalidadeController.class).getLocalidadeById(id_localidade)).withSelfRel(),
                    linkTo(methodOn(LocalidadeController.class).atualizarNome(id_localidade, localidade)).withRel("atualizarNome"),
                    linkTo(methodOn(LocalidadeController.class).deletarLocalidade(id_localidade)).withRel("deletarLocalidade"),
                    linkTo(methodOn(LocalidadeController.class).getAllLocalidades()).withRel("todasLocalidades")
            );
            return ResponseEntity.ok(localidadeModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra uma nova localidade
    @PostMapping
    public ResponseEntity<EntityModel<Localidade>> cadastrarLocalidade(@RequestBody Localidade localidade) {
        Localidade novaLocalidade = localidadeRepository.save(localidade);
        EntityModel<Localidade> localidadeModel = EntityModel.of(novaLocalidade,
                linkTo(methodOn(LocalidadeController.class).getLocalidadeById(novaLocalidade.getId_local())).withSelfRel(),
                linkTo(methodOn(LocalidadeController.class).getAllLocalidades()).withRel("todasLocalidades"),
                linkTo(methodOn(LocalidadeController.class).atualizarNome(novaLocalidade.getId_local(), novaLocalidade)).withRel("atualizarNome"),
                linkTo(methodOn(LocalidadeController.class).deletarLocalidade(novaLocalidade.getId_local())).withRel("deletarLocalidade")
        );
        return ResponseEntity.ok(localidadeModel);
    }

    //Atualiza o nome de uma localidade pelo id_localidade
    @PutMapping("/{id_localidade}/atualizarNome")
    public ResponseEntity<EntityModel<Localidade>> atualizarNome(@PathVariable String id_localidade, @RequestBody Localidade localidade) {
        Optional<Localidade> localidadeEncontrada = localidadeRepository.findById(id_localidade);

        if (localidadeEncontrada.isPresent()) {
            Localidade localidadeAtualizada = localidadeEncontrada.get();
            localidadeAtualizada.setNome_local(localidade.getNome_local());
            localidadeRepository.save(localidadeAtualizada);
            EntityModel<Localidade> localidadeModel = EntityModel.of(localidadeAtualizada,
                    linkTo(methodOn(LocalidadeController.class).getLocalidadeById(id_localidade)).withSelfRel(),
                    linkTo(methodOn(LocalidadeController.class).getAllLocalidades()).withRel("todasLocalidades"),
                    linkTo(methodOn(LocalidadeController.class).atualizarNome(id_localidade, localidade)).withRel("atualizarNome"),
                    linkTo(methodOn(LocalidadeController.class).deletarLocalidade(id_localidade)).withRel("deletarLocalidade")
            );
            return ResponseEntity.ok(localidadeModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta uma localidade do banco de dados
    @DeleteMapping("/{id_localidade}/deletarLocalidade")
    public ResponseEntity deletarLocalidade(@PathVariable String id_localidade){
        Optional<Localidade> localidadeEncontrada = localidadeRepository.findById(id_localidade);

        if (localidadeEncontrada.isPresent()) {
            localidadeRepository.deleteById(id_localidade);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
