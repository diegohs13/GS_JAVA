package com.example.gs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.hateoas.RepresentationModel;

@Table(name = "TB_DENUNCIA")
@Entity(name = "TB_DENUNCIA")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Denuncia extends RepresentationModel<Denuncia>{

    @Id
    @NotNull()
    private String id_denuncia;

    private String data_denuncia;

    private String descricao;

    private String status;

    private String Usuario_id_usuario;
}
