package com.example.gs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Table(name = "TB_RELATORIO")
@Entity(name = "TB_RELATORIO")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Relatorio extends RepresentationModel<Relatorio>{

    @Id
    private String id_relatorio;

    private String data_relatorio;

    private String descricao;

    private String quantidade_residuo;

    private String Usuario_id_usuario;
}
