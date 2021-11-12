package com.analisecredito.anexodocs;

import com.analisecredito.cliente.ClienteService;
import com.analisecredito.exceptions.RecursoNaoEncontradoException;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AnexoDocsService {

    // private final StorageF storage;
    private final AnexoDocsRepository repository;
    private ClienteService clienteService;


    @Value("${filepath.anexos}")
    private String diretorioArquivos;

    @Autowired
    public void setClienteService(ClienteService clienteService){this.clienteService = clienteService;}

    public AnexoDocsDTO insert(@NotNull AnexoDocsCadastroDTO cadastroDTO){
        return Optional.ofNullable(cadastroDTO)
                .map(this::prepareAnexoDocs)
                .map(repository::save)
                .map(AnexoDocsConverter::toDto).orElse(null);
    }

    @NotNull
    private AnexoDocs prepareAnexoDocs(AnexoDocsCadastroDTO cadastroDTO) {
        var entity = AnexoDocsConverter.fromDto(cadastroDTO);
        var ClienteEntity = clienteService.findById(cadastroDTO.getIdCpt());
        final var arquivo = cadastroDTO.getArquivo();
        var formatoArquivo = arquivo.getOriginalFilename().substring(arquivo.getOriginalFilename().lastIndexOf(".") + 1)
                .toUpperCase();

        entity.setNomeDoc(arquivo.getOriginalFilename());
        entity.setCliente(ClienteEntity);

        // final String pathArquivo = this.saveFile(arquivo, cadastroDTO.getIdCpt());
        //    entity.setPathArquivo(pathArquivo);

        return entity;
    }

    // private String saveFile(MultipartFile arquivo, Long idCliente) {
    //   return this.storage.salvar(arquivo, this.diretorioArquivos, idCliente.toString());
    // }

    public AnexoDocs findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("[AnexoDocs] id: " + id));
    }

    @Transactional
    public void delete(Long id) {
        var anexoDocs = Optional.ofNullable(id).map(this::findById).orElse(null);
        deleteStorageFile(anexoDocs);
        Optional.ofNullable(anexoDocs.getId()).ifPresent(repository::deleteById);
    }

    @Transactional
    public void deleteStorageFile(AnexoDocs anexo) {
        // Optional.ofNullable(anexo.getPathArquivo()).ifPresent(this.storage::excluir);
    }

    public Page<AnexoDocs> filter(Long idCliente, @Nullable Pageable pageable) {
        return this.repository.findAllByClienteId(idCliente, this.preparePageable(pageable));
    }

    private Pageable preparePageable(@Nullable Pageable pageable) {
        return Optional.ofNullable(pageable).orElse(Pageable.unpaged());
    }
}
