package br.com.zup.mercadolivre.global.util.storage;

import br.com.zup.mercadolivre.global.web.exception.ControllerException;
import java.io.File;

public class GoogleStorage implements Storage{

    private static final String URL = "https://www.google.com/storage";

    @Override
    public String upload(Bucket bucket, File file) {

        if(file.isFile() && file != null && file.exists()){
            return URL+bucket.getPath();
        }
        throw ControllerException.badRequest("Não foi possivel fazer o upload da " +
                "do arquivo no GCP Storage");
    }

    @Override
    public void remove(String url) {
        System.out.println("Arquivo: "+url+" removido com sucesso");
    }
}
