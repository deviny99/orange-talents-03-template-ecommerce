package br.com.zup.global.config.upload;

import br.com.zup.global.web.exception.ControllerException;
import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Service;
import java.io.*;

@Service
public class FileUploadService implements FileUpload{
    @Override
    public String uploadDirTemp(String fileName, InputStream inputStream) throws IOException {
        if(fileName.isEmpty() && inputStream == null){
            //ADICIONAR UM LOG
            throw ControllerException.badRequest("");
        }
        File tempDir = new File(System.getProperty("java.io.tmpdir"),"imagens");
        if(!tempDir.exists()){
            tempDir.mkdir();
        }
        File file = new File(tempDir,fileName);
        FileOutputStream out = new FileOutputStream(file);
        IOUtils.copy(inputStream,out);
        IOUtils.closeQuietly(out);
        return file.getAbsolutePath();
    }
}
