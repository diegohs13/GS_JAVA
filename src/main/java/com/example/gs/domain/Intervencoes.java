package com.example.gs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.hateoas.RepresentationModel;

@Table(name = "TB_INTERVENCOES")
@Entity(name = "TB_INTERVENCOES")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Intervencoes extends RepresentationModel<Intervencoes>{

    @Id
    @NotNull()
    private String id_intervencao;

    private String data_intervencao;

    private String quantidade_residuos;

    private int numero_pessoas;

    private String Localidade_id_localidade;
}
