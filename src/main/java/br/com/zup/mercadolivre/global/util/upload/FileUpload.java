package br.com.zup.mercadolivre.global.util.upload;

import java.io.IOException;
import java.io.InputStream;

public interface FileUpload {
    String uploadDirTemp(String fileName, InputStream inputStream) throws IOException;
}
