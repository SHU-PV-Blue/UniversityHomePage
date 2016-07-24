package com.wolfogre.uhp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.common.HibernateTool;
import com.wolfogre.uhp.domain.HomePageEntity;
import org.apache.struts2.ServletActionContext;

/**
 * Created by wolfogre on 16-7-24.
 */
public class ContentAction extends ActionSupport {
    private String id;
    private String value;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String set() throws Exception{
        ActionContext actionContext = ActionContext.getContext();
        if(actionContext.getSession().get("admin") == null || !(boolean)actionContext.getSession().get("admin")){
            String jsonString = "{\"success\":false, \"reason\":\"Not Admin\"}";
            ServletActionContext.getResponse().getWriter().println(jsonString);
            return null;
        }

        HibernateTool hDao = new HibernateTool();
        try{
            HomePageEntity aim = (HomePageEntity)hDao.get(HomePageEntity.class, Integer.parseInt(getId()));
            aim.setContent(getValue());
            hDao.update(aim);
        } catch (Exception e) {
            String jsonString = "{\"success\":false, \"reason\":\"" + e.getMessage() + "\"}";
            ServletActionContext.getResponse().getWriter().println(jsonString);
            return null;
        }
        String jsonString = "{\"success\":true}";
        ServletActionContext.getResponse().getWriter().println(jsonString);
        return null;
    }

    public String get() throws Exception{
        HibernateTool hDao = new HibernateTool();
        String result = "";
        try{
            result = ((HomePageEntity)hDao.get(HomePageEntity.class, Integer.parseInt(getId()))).getContent();
        } catch (Exception e) {
            String jsonString = "{\"success\":false, \"reason\":\"" + e.getMessage() + "\"}";
            ServletActionContext.getResponse().getWriter().println(jsonString);
            return null;
        }
        String jsonString = "{\"success\":true, \"data\":\"" + result + "\"}";
        ServletActionContext.getResponse().getWriter().println(jsonString);
        return null;
    }
}
