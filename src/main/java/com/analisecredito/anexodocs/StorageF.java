package com.analisecredito.anexodocs;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public interface StorageF {

    /**
     * Upload de arquivo para o diretório definido no parâmetro.
     *
     * @param arquivo        Arquivo {@link MultipartFile} a ser salvo
     * @param diretorio      Diretório no qual o arquivo será armazenado
     * @param diretorioFilho Diretório(s) filho(s) no(s) qual(ais) o arquivo será
     *                       armazenado
     * @return A url para download do arquivo. Que, dependendo do storage provider
     *         configurado, pode ser o path do arquivo no filesystem ou o filekey
     *         com a uri de download no bucket aws.
     * @throws Exception Se houver algum problema no upload do arquivo.
     */
    public String salvar(MultipartFile arquivo, String diretorio, String... diretorioFilho);

    /**
     * Upload de arquivo para o diretório definido no parâmetro.
     *
     * @param arquivo        Arquivo {@link File} a ser salvo
     * @param diretorio      Diretório no qual o arquivo será armazenado
     * @param diretorioFilho Diretório(s) filho(s) no(s) qual(ais) o arquivo será
     *                       armazenado
     * @return A url para download do arquivo. Que, dependendo do storage provider
     *         configurado, pode ser o path do arquivo no filesystem ou o filekey
     *         com a uri de download no bucket aws.
     * @throws Exception Se houver algum problema no upload do arquivo.
     */
    public String salvar(File arquivo, String diretorio, String... diretorioFilho);

    /**
     * Download de arquivo persistido no storage.
     *
     * @param url
     * @return A url para download do arquivo. Que, dependendo do storage provider
     *         configurado, pode ser o path do arquivo no filesystem ou o filekey
     *         com a uri de download no bucket aws.
     */
    public byte[] download(String url);

    /**
     * Excluir arquivo persistido no storage.
     *
     * @param url
     * @return A url para download do arquivo. Que, dependendo do storage provider
     *         configurado, pode ser o path do arquivo no filesystem ou o filekey
     *         com a uri de download no bucket aws.
     * @return true Se o arquivo foi excluído com sucesso, false caso contrário.
     */
    public boolean excluir(String url);

    /**
     * Downloads de um arquivo a partir de uma url pública na internet.
     *
     * @param url      Url pública (link na internet) do arquivo a ser baixado.
     * @param prefixo  Prefixo para definir o nome do arquivo temporário que será
     *                 criado
     * @param extensao Extensão do arquivo (Ex: .pdf)
     * @return Uma referência para o arquivo temporário no qual foi salvo o
     *         download.
     */
    public File createTempDownloadFileFromPublicUrl(String url, String prefixo, String extensao);

    /**
     * Criar uma cópia temporária de um arquivo que será baixado a partir de uma url
     *
     * @param url      A url para download do arquivo. Que, dependendo do storage
     *                 provider configurado, pode ser o path do arquivo no
     *                 filesystem ou o filekey com a uri de download no bucket aws.
     * @param prefixo  Prefixo para definir o nome do arquivo temporário que será
     *                 criado
     * @param extensao Extensão do arquivo (Ex: .pdf)
     * @return Uma referência para o arquivo temporário no qual foi salvo o
     *         download.
     */
    public File createTempDownloadFile(String url, String prefixo, String extensao);
}
