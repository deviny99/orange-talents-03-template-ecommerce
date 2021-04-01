package br.com.zup.mercadolivre.global.util.storage;

import java.io.File;

public interface Storage {

    /**
     * Responsavel por fazer o upload do arquivo na nuvem
     *
     * @param bucket Objeto que indica o caminho do arquivo no provedor de armazenamento
     * @param file Arquivo a ser salvo no provedor de armazenamento
     * @return url do arquivo salvo
     */
    String upload(Bucket bucket,File file);
    //responsavel por fazer a remoção do arquivo na nuvem
    /**
     * Responsável por remover arquivo na nuvem e no banco de dados
     *
     * @param url link do arquivo a ser removido no banco de dados da aplicação e no
     * provedor de armazenamento
     *
     **/
    void remove(String url);

}
