package br.com.zup.produto.data.service;

import br.com.zup.global.config.storage.Bucket;
import br.com.zup.global.config.upload.FileUpload;
import br.com.zup.global.config.storage.Storage;
import br.com.zup.global.web.exception.ControllerException;
import br.com.zup.imagem.data.domain.Imagem;
import br.com.zup.imagem.data.repository.ImagemRepository;
import br.com.zup.produto.data.domain.Produto;
import br.com.zup.produto.data.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProdutoImagemServiceImpl implements ProdutoImagemService {

    private final String BUCKET_NAME = "/imagens/produtos";

    private final ImagemRepository imagemRepository;
    private final ProdutoRepository produtoRepository;
    private final FileUpload fileUpload;

    @Autowired
    public ProdutoImagemServiceImpl(ImagemRepository imagemRepository, ProdutoRepository produtoRepository,
                                    FileUpload fileUpload){
        this.imagemRepository = imagemRepository;
        this.produtoRepository = produtoRepository;
        this.fileUpload = fileUpload;
    }

    @Transactional
    @Override
    public String salvarImagemCloud(Long idProduto, InputStream inputStream, Storage storage) throws IOException {
        UUID id = UUID.randomUUID();
        Bucket bucket = new Bucket(BUCKET_NAME,new StringBuilder()
                .append("/")
                .append(idProduto)
                .append("/")
                .append(id)
                .toString());
        String path = this.fileUpload.uploadDirTemp(id.toString(), inputStream);
        String linkUrl = storage.upload(bucket,new File(path));
        Imagem imagem = this.imagemRepository.save(new Imagem(null, linkUrl));
        Optional<Produto> optionalProduto = this.produtoRepository.findById(idProduto);
        if(optionalProduto.isPresent() )
        {
            Produto produto = optionalProduto.get();
            produto.getImagens().add(imagem);
            this.produtoRepository.save(produto);

        }else {
            throw  ControllerException.notFound("Não existe produto com o ID informado.");
        }
        return linkUrl;
    }

    @Override
    public void removerImagemCloud(String url) {

    }

}
