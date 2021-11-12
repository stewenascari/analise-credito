package com.analisecredito.usuario;

import com.analisecredito.usuario.perfil.Perfil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.analisecredito.usuario.enums.PerfilEnumConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @Column(name = "id", updatable = false, unique = true)
    @SequenceGenerator(name = "usuario_seq_generator", sequenceName = "seq_usuario", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usuario_seq_generator")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    @JsonIgnore
    private String senha;

    @ManyToOne
    @JoinColumn(name = "fk_perfil")
    private Perfil perfil;
}
