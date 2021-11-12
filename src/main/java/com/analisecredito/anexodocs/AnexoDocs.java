package com.analisecredito.anexodocs;

import com.analisecredito.cliente.Cliente;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Data
@EqualsAndHashCode
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "tb_anexos_docs")
public class AnexoDocs {

    @Id
    @Column(name = "id", updatable = false, unique = true)
    @SequenceGenerator(name = "anexos_docs_seq_generator", sequenceName = "seq_anexos_docs", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "anexos_docs_seq_generator")
    private Long id;

    @Column(name = "path_arquivo")
    private String pathArquivo;

    @Column(name = "nome_doc")
    private String nomeDoc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_cliente")
    private Cliente cliente;


}
