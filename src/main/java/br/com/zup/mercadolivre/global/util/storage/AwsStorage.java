package br.com.zup.mercadolivre.global.util.storage;

import br.com.zup.mercadolivre.global.web.exception.ControllerException;
import java.io.File;

public class AwsStorage implements Storage{

    private static final String URL = "https://www.amazon.com/aws/storage";

    @Override
    public String upload(Bucket bucket, File file) {

        if(file.isFile() && file != null){
            return URL+bucket.getPath();
        }
        throw ControllerException.badRequest("Não foi possivel fazer o upload do arquivo na AWS Storage");
    }

    @Override
    public void remove(String url) {
        System.out.println("Arquivo: "+url+" removido com sucesso");
    }
}
