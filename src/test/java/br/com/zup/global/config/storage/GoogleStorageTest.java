package br.com.zup.global.config.storage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.File;

public class GoogleStorageTest {

    private Storage storage;

    @BeforeEach
    public void setup(){
        this.storage = new GoogleStorage();
    }

    @Test
    void deveFazerUpload(){

        Bucket bucket = new Bucket("testes","unitarios");
        String retorno = this.storage.upload(bucket,new File("src\\test\\java\\br\\com\\zup\\global\\config\\storage\\GoogleStorageTest.java"));
        Assertions.assertNotNull(retorno);
        Assertions.assertFalse(retorno.isEmpty());
    }

    @Test
    void naoDeveFazerUpload(){
        try {
            Bucket bucket = new Bucket("testes", "unitarios");
            this.storage.upload(bucket, new File("arquivo inexistente"));
            Assertions.fail();
        }catch (Exception e){
            Assertions.assertEquals("Não foi possivel fazer o upload da do arquivo no GCP Storage",e.getLocalizedMessage().trim());
        }

    }
}