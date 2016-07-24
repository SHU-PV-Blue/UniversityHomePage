package com.wolfogre.uhp.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

/**
 * Created by wolfogre on 16-7-24.
 */
public class ImageAction  extends ActionSupport {
    // 封装文件标题请求参数的属性
    private String title;
    // 封装上传文件域的属性
    private File upload;
    // 封装上传文件类型的属性
    private String uploadContentType;
    // 封装上传文件名的属性
    private String uploadFileName;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public File getUpload() {
        return upload;
    }

    public void setUpload(File upload) {
        this.upload = upload;
    }

    public String getUploadContentType() {
        return uploadContentType;
    }

    public void setUploadContentType(String uploadContentType) {
        this.uploadContentType = uploadContentType;
    }

    public String getUploadFileName() {
        return uploadFileName;
    }

    public void setUploadFileName(String uploadFileName) {
        this.uploadFileName = uploadFileName;
    }


    public void upload() throws Exception
    {
        if(getUpload() != null){
            FileInputStream fis = new FileInputStream(getUpload());
            byte[] buffer = new byte[1024];
            int len = 0;
            while ((len = fis.read(buffer)) > 0)
            {
                //TODO
            }
            fis.close();
        }
        String jsonString="{\"result\":\"success\"}";
        ServletActionContext.getResponse().getWriter().println(jsonString);
        return;
    }
}