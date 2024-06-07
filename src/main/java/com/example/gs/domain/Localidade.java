package com.example.gs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Table(name = "TB_LOCALIDADE")
@Entity(name = "TB_LOCALIDADE")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Localidade extends RepresentationModel<Localidade>{

    @Id
    @GeneratedValue(strategy = jakarta.persistence.GenerationType.IDENTITY)
    private String id_local;

    private String nome_local;

    private String latitude;

    private String longitude;

    private String Relatorio_id_relatorio;
}
