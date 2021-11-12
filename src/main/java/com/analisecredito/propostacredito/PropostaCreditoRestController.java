package com.analisecredito.propostacredito;

import com.analisecredito.usuario.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;

@Validated
@RequiredArgsConstructor
@RequestMapping("propostas-creditos")
@RestController
@PreAuthorize("hasAnyRole('ANALISTA')")
public class PropostaCreditoRestController {

    private final PropostaCreditoService service;

    @GetMapping("/{id:\\d+}")
    public ResponseEntity<PropostaCreditoDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(PropostaCreditoConverter.toDto(this.service.findById(id)));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('SOLICITANTE')")
    public ResponseEntity<PropostaCreditoDTO> insert(@RequestBody @Valid PropostaCreditoCadastroDTO cadastroDTO) {
        return ResponseEntity.ok().body(this.service.insert(cadastroDTO));
    }

    @PutMapping("{id:\\d+}")
    public ResponseEntity<PropostaCreditoDTO> update(@PathVariable Long id, @RequestBody @Valid PropostaCreditoCadastroDTO dto) {
        return ResponseEntity.ok().body(this.service.update(id, dto));
    }

    @GetMapping
    public ResponseEntity<List<PropostaCreditoDTO>> filter(@RequestParam(required = false) String statusProposta,
                                                          @RequestParam(required = false) LocalDate dataInicioProposta,
                                                          Pageable pageable) {

        List<PropostaCreditoDTO> propostasCreditos = this.service.filter(statusProposta, dataInicioProposta, pageable);
        return ResponseEntity.ok().body(propostasCreditos);
    }

    @DeleteMapping("{id:\\d+}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.service.delete(id);
        return ResponseEntity.ok().body(null);
    }
}
