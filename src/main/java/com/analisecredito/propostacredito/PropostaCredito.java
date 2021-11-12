package com.analisecredito.propostacredito;

import com.analisecredito.cliente.Cliente;
import com.analisecredito.propostacredito.enums.StatusProposta;
import com.analisecredito.propostacredito.enums.StatusPropostaEnumConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_proposta_credito")
public class PropostaCredito {

    @Id
    @Column(name = "id", updatable = false, unique = true)
    @SequenceGenerator(name = "proposta_credito_seq_generator", sequenceName = "seq_proposta_credito", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proposta_credito_seq_generator")
    private Long id;

    @Convert(converter = StatusPropostaEnumConverter.class)
    @Column(name = "status")
    private StatusProposta status;

    @Column(name = "data_inicio_proposta")
    private LocalDate dataInicioProposta;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente", referencedColumnName = "id")
    private Cliente cliente;
}
