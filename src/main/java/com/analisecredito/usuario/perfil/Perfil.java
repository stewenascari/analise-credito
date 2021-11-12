package com.analisecredito.usuario.perfil;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tb_perfil")
public class Perfil {
    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "perfil_seq_generator", sequenceName = "seq_perfil", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "perfil_seq_generator")
    private Long id;

    @Column(nullable = false, unique = true)
    private String descricao;
}
