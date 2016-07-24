package com.wolfogre.uhp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.common.HibernateTool;
import com.wolfogre.uhp.domain.HomePageEntity;
import org.apache.struts2.ServletActionContext;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by wolfogre on 16-7-18.
 */
public class HomeAction extends ActionSupport{
    private String admin;

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String index() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        actionContext.put("universityList", new HibernateTool().getAll(HomePageEntity.class));
        if(getAdmin() != null && "df8c608611d9139055f0ffb5cd79d0db".equals(getMD5(getAdmin())))
            actionContext.put("admin", true);
        else
            actionContext.put("admin", false);
        return SUCCESS;
    }

    public String getMD5() throws Exception{
        String jsonString = "false";
        if(getAdmin() != null){
            jsonString = "{\"admin\":\"" + getAdmin() +  "\", \"md5\":\"" + getMD5(getAdmin()) +  "\"}";
        }

        ServletActionContext.getResponse().getWriter().println(jsonString);
        return null;
    }

    private String getMD5(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] messageDigest = md.digest(input.getBytes());
        BigInteger number = new BigInteger(1, messageDigest);
        String hashtext = number.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        return hashtext;
    }
}
