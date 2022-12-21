package com.fzt.ktzq.common.appmid.parser;

import java.util.List;
import java.util.Map;

public class AppMidFileRequest {
    private List<AppMidFile> uploadAppMidFileList;
    private Map<String, String[]> uploadRequestParams;
    private Map<String, String[]> downloadRequestParams;

    public AppMidFileRequest(List<AppMidFile> uploadAppMidFileList, Map<String, String[]> uploadRequestParams, Map<String, String[]> downloadRequestParams){
        this.uploadAppMidFileList = uploadAppMidFileList;
        this.uploadRequestParams = uploadRequestParams;
        this.downloadRequestParams = downloadRequestParams;
    }

    public List<AppMidFile> getUploadAppMidFileList() {
        return uploadAppMidFileList;
    }

    public void setUploadAppMidFileList(List<AppMidFile> uploadAppMidFileList) {
        this.uploadAppMidFileList = uploadAppMidFileList;
    }

    public Map<String, String[]> getUploadRequestParams() {
        return uploadRequestParams;
    }

    public void setUploadRequestParams(Map<String, String[]> uploadRequestParams) {
        this.uploadRequestParams = uploadRequestParams;
    }

    public Map<String, String[]> getDownloadRequestParams() {
        return downloadRequestParams;
    }

    public void setDownloadRequestParams(Map<String, String[]> downloadRequestParams) {
        this.downloadRequestParams = downloadRequestParams;
    }
}
