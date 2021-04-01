package br.com.zup.mercadolivre.global.util.storage;

public class Bucket {

    private String bucketName;
    private String fileName;

    public Bucket(String bucketName, String fileName){
        this.bucketName = bucketName;
        this.fileName = fileName;
    }

    public String getBucketName() {
        return bucketName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getPath(){
        return this.bucketName+fileName;
    }
}
