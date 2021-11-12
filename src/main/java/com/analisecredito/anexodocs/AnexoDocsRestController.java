package com.analisecredito.anexodocs;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@RequiredArgsConstructor
@RequestMapping("clientes/anexos")
@RestController
@PreAuthorize("hasAnyRole('SOLICITANTE')")
public class AnexoDocsRestController {

    private final AnexoDocsService service;

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<AnexoDocsDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok().body(AnexoDocsConverter.toDto(this.service.findById(id)));
    }

    @GetMapping
    public ResponseEntity<List<AnexoDocsDTO>> filter(
            @RequestParam(required = false) Long idCliente,
            @RequestParam(required = false) Pageable pageable) {

        var anexos = this.service.filter(idCliente, pageable);

        return ResponseEntity.ok().body(anexos.getContent().stream()
                .map( AnexoDocsConverter::toDto)
                .collect(Collectors.toList()));
    }

    @PostMapping(consumes = { "multipart/form-data" })
    public ResponseEntity<AnexoDocsDTO> insert(@RequestPart MultipartFile arquivo, @RequestParam String nomeDoc,
                                               @RequestParam Long idCpt) {
        var cadastroDTO = new  AnexoDocsCadastroDTO(idCpt,nomeDoc, arquivo);

        return ResponseEntity.ok().body(this.service.insert(cadastroDTO));
    }

    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
