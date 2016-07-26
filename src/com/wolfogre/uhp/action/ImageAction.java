package com.wolfogre.uhp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.common.HibernateTool;
import com.wolfogre.uhp.domain.HomePageEntity;
import com.wolfogre.uhp.domain.LayoutImageEntity;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Arrays;

/**
 * Created by wolfogre on 16-7-24.
 */
public class ImageAction  extends ActionSupport {

    // 封装上传文件域的属性
    private File uploadFile;

    private String universityId;

    private ByteArrayInputStream inputStream;

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUniversityId() {
        return universityId;
    }

    public void setUniversityId(String universityId) {
        this.universityId = universityId;
    }

    public ByteArrayInputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream) {
        this.inputStream = inputStream;
    }

    public String upload() throws Exception
    {
        ActionContext actionContext = ActionContext.getContext();
        if(actionContext.getSession().get("admin") == null || !(boolean)actionContext.getSession().get("admin")){
            actionContext.put("success", false);
            actionContext.put("reason", "权限不足");
            return SUCCESS;
        }

        if(getUploadFile() == null || getUniversityId() == null){
            actionContext.put("success", false);
            actionContext.put("reason", "文件或参数缺失");
            return SUCCESS;
        }

        FileInputStream fis = new FileInputStream(getUploadFile());
        byte[] buffer = new byte[1024 * 1024];
        int length = fis.read(buffer, 0, 1024 * 1024);
        if(length >= 1024 * 1024){
            actionContext.put("success", false);
            actionContext.put("reason", "文件大于 1 MB");
            return SUCCESS;
        }
        fis.close();

        HibernateTool hibernateTool = HibernateTool.getHibernateTool();
        byte[] image = Arrays.copyOfRange(buffer,0, length);
        LayoutImageEntity aimLayout = (LayoutImageEntity)hibernateTool.get(LayoutImageEntity.class, Integer.parseInt(getUniversityId()));
        if(aimLayout != null){
            aimLayout.setImage(image);
            hibernateTool.update(aimLayout);
        } else {
            aimLayout = new LayoutImageEntity();
            aimLayout.setId(Integer.parseInt(getUniversityId()));
            aimLayout.setImage(image);
            hibernateTool.save(aimLayout);
        }

        HomePageEntity aim = (HomePageEntity)hibernateTool.get(HomePageEntity.class, Integer.parseInt(getUniversityId()));
        aim.setIfLayoutImage(true);
        hibernateTool.update(aim);

        actionContext.put("success", true);
        actionContext.put("name", aim.getName());

        return SUCCESS;
    }

    public String download() throws Exception
    {
        HibernateTool hibernateTool = HibernateTool.getHibernateTool();;
        LayoutImageEntity aim = (LayoutImageEntity)hibernateTool.get(LayoutImageEntity.class, Integer.parseInt(getUniversityId()));
        this.inputStream = new ByteArrayInputStream(aim.getImage());
        return SUCCESS;
    }
}