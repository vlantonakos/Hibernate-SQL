package com.vlanto.DemoHibe;

import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Map;
import java.util.Random;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class App 
{
    public static void main( String[] args )
    {
    	
    	Configuration con = new Configuration().configure().addAnnotatedClass(Student.class);
         
        ServiceRegistry reg = new StandardServiceRegistryBuilder().applySettings(con.getProperties()).build();
         
        SessionFactory sf = con.buildSessionFactory(reg);
         
        Session session = sf.openSession();
         
        session.beginTransaction();
        
        //Native Queries
        SQLQuery query = session.createSQLQuery("select name,marks from student where marks>60");
        query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
        List students = query.list();
        
        for(Object o : students) {
        	Map m =(Map)o;
        	System.out.println(m.get("name") + " : " + m.get("marks"));
        }
        
        session.getTransaction().commit();
        
        
    }
}