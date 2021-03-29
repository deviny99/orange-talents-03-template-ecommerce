package br.com.zup.global.config.storage;

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
