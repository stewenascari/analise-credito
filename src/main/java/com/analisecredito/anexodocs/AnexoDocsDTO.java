package com.analisecredito.anexodocs;

import lombok.*;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AnexoDocsDTO {
    private Long id;
    private Long idCliente;
    private String pathFilesystem;
    private String nomeDoc;
}
