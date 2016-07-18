package com.wolfogre.uhp.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.wolfogre.common.dao.HibernateDao;
import com.wolfogre.uhp.domain.HomePageEntity;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * Created by wolfogre on 16-7-18.
 */
public class HomeAction extends ActionSupport{
    public String index(){
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

        ActionContext actionContext = ActionContext.getContext();
        actionContext.put("test", new HibernateDao<HomePageEntity>(sessionFactory).findCount(HomePageEntity.class));
        return SUCCESS;
    }
}
