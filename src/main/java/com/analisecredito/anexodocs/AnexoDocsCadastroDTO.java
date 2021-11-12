package com.analisecredito.anexodocs;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Builder
@Data
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class AnexoDocsCadastroDTO {

    private Long idCpt;
    private String nomeDoc;
    private MultipartFile arquivo;
}
