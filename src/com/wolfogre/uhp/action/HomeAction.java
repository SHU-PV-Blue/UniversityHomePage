package com.wolfogre.uhp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.common.dao.HibernateDao;
import com.wolfogre.uhp.domain.HomePageEntity;

/**
 * Created by wolfogre on 16-7-18.
 */
public class HomeAction extends ActionSupport{
    public String index(){
        ActionContext actionContext = ActionContext.getContext();
        actionContext.put("test", new HibernateDao<HomePageEntity>().findCount(HomePageEntity.class));
        return SUCCESS;
    }
}
