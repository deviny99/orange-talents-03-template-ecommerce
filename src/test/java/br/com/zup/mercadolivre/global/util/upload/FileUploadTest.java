package br.com.zup.mercadolivre.global.util.upload;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;

public class FileUploadTest {

    private FileUpload fileUpload;

    @BeforeEach
    public void setup(){
        this.fileUpload = new FileUploadService();
    }

    @Test
    void deveFazerUpload() throws IOException {

        InputStream inputStream = new FileInputStream(new File("src\\test\\java\\br\\com\\zup\\mercadolivre\\global\\util\\upload\\FileUploadTest.java"));
        String retorno = this.fileUpload.uploadDirTemp("teste",inputStream);
        Assertions.assertNotNull(retorno);
        Assertions.assertNotNull(inputStream);
    }

    @Test
    void naoDeveFazerUpload() throws IOException {

        try {
            InputStream inputStream = new FileInputStream(new File("arquivo não existente"));
            String retorno = this.fileUpload.uploadDirTemp("teste",inputStream);
            Assertions.fail();
        }catch(Exception e){
            Assertions.assertEquals("arquivo não existente (O sistema não pode encontrar o arquivo especificado)",e.getLocalizedMessage().trim());

        }

    }

}
