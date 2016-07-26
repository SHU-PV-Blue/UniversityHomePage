package com.wolfogre.common;

import org.hibernate.*;
import org.hibernate.annotations.Table;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.List;

/**
 * Created by Jason Song(wolfogre.com) on 2016/5/7.
 */
public class HibernateTool {

    /**
     * 对话工厂
     */
    private SessionFactory sessionFactory;

    static HibernateTool hibernateTool = null;

    public static HibernateTool getHibernateTool(){
        if(hibernateTool == null){
            hibernateTool = new HibernateTool();
            return hibernateTool;
        } else
            return hibernateTool;
    }

    /**
     * 构造函数
     */
    private HibernateTool() {
        Configuration configuration = new Configuration().configure();
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
    }

    /**
     * 查询数据库
     * @param type 需要查询的持久化类
     * @param queryString 查询语句
     * @return 查询结果
     */
    public List query(Class type, String queryString) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List result = session.createSQLQuery(queryString).addEntity(type).list();
        transaction.commit();
        return result;
    }

    /**
     * 根据主键获取指定的持久化对象
     * @param type 指定的持久化类
     * @param id 主键
     * @return 指定的持久化对象
     */
    public Object get(Class type, Serializable id) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Object result = session.get(type, id);
        transaction.commit();
        return result;
    }

    /**
     * 根据持久化类获取所有的的持久化对象
     * @param type 指定的持久化类
     * @return 指定的持久化对象
     */
    public List getAll(Class type) throws Exception {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List result = session.createQuery("select en from " + type.getSimpleName() + " en").list();
        transaction.commit();
        return result;
    }

    /**
     * 插入持久化对象
     * @param obj 需要插入数据库的持久化对象
     * @return 插入数据库的持久化对象的主键
     */
    public Serializable save(Object obj) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        Serializable result = session.save(obj);
        transaction.commit();
        return  result;
    }

    /**
     * 更新持久化对象
     * @param obj 需要更新的持久化对象
     */
    public void update(Object obj) {
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.update(obj);
        transaction.commit();
    }
    /**
     * 释放资源
     * @throws Throwable 释放资源失败
     */
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        sessionFactory.close();
    }
}