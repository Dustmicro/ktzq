package com.fzt.ktzq.common.appmid.parser;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * 文件上传实体类
 * @author 黄弋峰 2022/12/21
 */
public class AppMidFile {
    private MultipartFile partFile;

    public AppMidFile(MultipartFile partFile){
        this.partFile = partFile;
    }

    public String getOriginalFilename(){
        return this.partFile.getOriginalFilename();
    }

    public String getContentType(){
        return this.partFile.getContentType();
    }

    public boolean getIsEmpty(){
        return this.partFile.isEmpty();
    }

    public long getSize(){
        return this.partFile.getSize();
    }

    public byte[] getBytes() throws IOException{
        return this.partFile.getBytes();
    }

    public InputStream getInputStream() throws IOException{
        return this.partFile.getInputStream();
    }

    public void transferTo (Path dest) throws IOException, IllegalStateException{
        this.partFile.transferTo(dest);
    }

    public void transferTo (File dest) throws IOException, IllegalStateException{
        this.partFile.transferTo(dest);
    }
}
