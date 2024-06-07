package com.example.gs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.hateoas.RepresentationModel;

@Table(name = "TB_USUARIO")
@Entity(name = "TB_USUARIO")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario extends RepresentationModel<Usuario> {

    @Id
    @NotNull()
    private String id_usuario;

    private String nome;

    private String email;

    private String senha;

    private String tipo_usuario;
}
