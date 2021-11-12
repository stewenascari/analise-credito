package com.analisecredito.cliente;

import com.analisecredito.cliente.enums.EstadoCivil;
import com.analisecredito.cliente.enums.EstadoCivilEnumConverter;
import com.analisecredito.propostacredito.PropostaCredito;
import com.analisecredito.usuario.Usuario;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_cliente")
public class Cliente {

    @Id
    @Column(name = "id", updatable = false, unique = true)
    @SequenceGenerator(name = "cliente_seq_generator", sequenceName = "seq_cliente", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_seq_generator")
    private Long id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "mae")
    private String mae;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Convert(converter = EstadoCivilEnumConverter.class)
    @Column(name = "estado_civil")
    private EstadoCivil estadoCivil;

    @Column(name = "rg")
    private String rg;

    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Column(name = "salario")
    private Double salario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<PropostaCredito> propostas;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_usuario", referencedColumnName = "id")
    private Usuario usuario;


}
