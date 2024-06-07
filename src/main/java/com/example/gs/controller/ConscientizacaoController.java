package com.example.gs.controller;

import com.example.gs.domain.Conscientizacao;
import com.example.gs.repository.ConscientizacaoRepository;
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
@RequestMapping("/conscientizacoes")
public class ConscientizacaoController {
    @Autowired
    private ConscientizacaoRepository conscientizacaoRepository;

    //Retorna todas as conscientizacoes
    @GetMapping
    public ResponseEntity<List<EntityModel<Conscientizacao>>> getAllConscientizacoes(){
        List<EntityModel<Conscientizacao>> todasConscientizacoes = conscientizacaoRepository.findAll().stream()
                .map(conscientizacao -> EntityModel.of(conscientizacao,
                        linkTo(methodOn(ConscientizacaoController.class).getConscientizacaoById(conscientizacao.getId_campanha())).withSelfRel(),
                        linkTo(methodOn(ConscientizacaoController.class).atualizarDescricao(conscientizacao.getId_campanha(), conscientizacao)).withRel("atualizarDescricao"),
                        linkTo(methodOn(ConscientizacaoController.class).deletarConscientizacao(conscientizacao.getId_campanha())).withRel("deletarConscientizacao")
                )).collect(Collectors.toList());
        return ResponseEntity.ok(todasConscientizacoes);
    }

    //Retorna uma conscientizacao pelo id_conscientizacao
    @GetMapping("/{id_conscientizacao}")
    public ResponseEntity<EntityModel<Conscientizacao>> getConscientizacaoById(@PathVariable String id_conscientizacao) {
        Optional<Conscientizacao> conscientizacaoEncontrada = conscientizacaoRepository.findById(id_conscientizacao);

        if (conscientizacaoEncontrada.isPresent()) {
            Conscientizacao conscientizacao = conscientizacaoEncontrada.get();
            EntityModel<Conscientizacao> conscientizacaoModel = EntityModel.of(conscientizacao,
                    linkTo(methodOn(ConscientizacaoController.class).getConscientizacaoById(id_conscientizacao)).withSelfRel(),
                    linkTo(methodOn(ConscientizacaoController.class).getAllConscientizacoes()).withRel("todasConscientizacoes"),
                    linkTo(methodOn(ConscientizacaoController.class).atualizarDescricao(id_conscientizacao, conscientizacao)).withRel("atualizarDescricao"),
                    linkTo(methodOn(ConscientizacaoController.class).deletarConscientizacao(id_conscientizacao)).withRel("deletarConscientizacao")
            );
            return ResponseEntity.ok(conscientizacaoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Cadastra uma nova conscientizacao
    @PostMapping
    public ResponseEntity<EntityModel<Conscientizacao>> cadastrarConscientizacao(@RequestBody Conscientizacao conscientizacao) {
        Conscientizacao novaConscientizacao = conscientizacaoRepository.save(conscientizacao);
        EntityModel<Conscientizacao> conscientizacaoModel = EntityModel.of(novaConscientizacao,
                linkTo(methodOn(ConscientizacaoController.class).getConscientizacaoById(novaConscientizacao.getId_campanha())).withSelfRel(),
                linkTo(methodOn(ConscientizacaoController.class).getAllConscientizacoes()).withRel("todasConscientizacoes"),
                linkTo(methodOn(ConscientizacaoController.class).atualizarDescricao(novaConscientizacao.getId_campanha(), novaConscientizacao)).withRel("atualizarDescricao"),
                linkTo(methodOn(ConscientizacaoController.class).deletarConscientizacao(novaConscientizacao.getId_campanha())).withRel("deletarConscientizacao")
        );
        return ResponseEntity.ok(conscientizacaoModel);
    }

    //Atualiza a descrição da conscientizacao pelo id_campanha
    @PutMapping("/{id_campanha}/atualizarDescricao")
    public ResponseEntity<EntityModel<Conscientizacao>> atualizarDescricao(@PathVariable String id_campanha, @RequestBody Conscientizacao conscientizacao) {
        Optional<Conscientizacao> conscientizacaoEncontrada = conscientizacaoRepository.findById(id_campanha);

        if (conscientizacaoEncontrada.isPresent()) {
            Conscientizacao conscientizacaoAtualizada = conscientizacaoEncontrada.get();
            conscientizacaoAtualizada.setDescricao(conscientizacao.getDescricao());
            conscientizacaoRepository.save(conscientizacaoAtualizada);
            EntityModel<Conscientizacao> conscientizacaoModel = EntityModel.of(conscientizacaoAtualizada,
                    linkTo(methodOn(ConscientizacaoController.class).getConscientizacaoById(id_campanha)).withSelfRel(),
                    linkTo(methodOn(ConscientizacaoController.class).getAllConscientizacoes()).withRel("todasConscientizacoes"),
                    linkTo(methodOn(ConscientizacaoController.class).atualizarDescricao(id_campanha, conscientizacao)).withRel("atualizarDescricao"),
                    linkTo(methodOn(ConscientizacaoController.class).deletarConscientizacao(id_campanha)).withRel("deletarConscientizacao")
            );
            return ResponseEntity.ok(conscientizacaoModel);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //Deleta uma conscientizacao pelo id_campanha
    @DeleteMapping("/{id_campanha}/deletarConscientizacao")
    public ResponseEntity deletarConscientizacao(@PathVariable String id_campanha){
        Optional<Conscientizacao> conscientizacaoEncontrada = conscientizacaoRepository.findById(id_campanha);

        if (conscientizacaoEncontrada.isPresent()) {
            conscientizacaoRepository.deleteById(id_campanha);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
