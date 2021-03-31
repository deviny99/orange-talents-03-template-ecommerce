package br.com.zup.produto.data.service;

import br.com.zup.global.util.storage.Storage;
import java.io.IOException;
import java.io.InputStream;

public interface ProdutoImagemService {


    /**
     * Responsável por salvar imagem na nuvem e os links no banco de dados
     *
     * @param idProduto id do produto
     * @param inputStream  arquivo da imagem para fazer o upload
     * @param storage  interface do provedor de serviço de armazenamento
     *
     * @return url imagem salvo
     **/
    String salvarImagemCloud(Long idProduto, InputStream inputStream, Storage storage) throws IOException;


    /**
     * Responsável por remover imagem na nuvem e no banco de dados
     *
     * @param url link da imagem a ser removida no banco de dados da aplicação e no
     * provedor de armazenamento
     *
     **/
    void removerImagemCloud(String url);

}
