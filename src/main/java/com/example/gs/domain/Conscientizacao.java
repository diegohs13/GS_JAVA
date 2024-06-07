package com.example.gs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Table(name = "TB_CONSCIENTIZACAO")
@Entity(name = "TB_CONSCIENTIZACAO")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Conscientizacao extends RepresentationModel<Conscientizacao>{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private String id_campanha;

    private String titulo_campanha;

    private String descricao;

    private String data_inicio;

    private String data_fim;
}
