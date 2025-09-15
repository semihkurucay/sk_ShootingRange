/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Ammo;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author semih
 */
public class SqlAmmoProcess {

    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Ammo.class)
            .buildSessionFactory();

    protected void exit(){
        if(session != null){
            session.close();
        }
        if(factory != null){
            factory.close();
        }
    }
    
    protected void list(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Ammo> ammos = session.createQuery("FROM Ammo", Ammo.class).getResultList();

            for (Ammo ammo : ammos) {
                model.addRow(new Object[]{ammo.getId(), ammo.getName(), ammo.getStock()});
            }

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public Ammo getAmmo(int id) {
        Ammo ammo = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            ammo = session.find(Ammo.class, id);

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return ammo;
    }

    public String getName(int id){
        String name = "";
        
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            name = session.createQuery("SELECT name FROM Ammo WHERE id = :id", String.class)
                    .setParameter("id", id)
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return name;
    }
    
    public int getStock(int id) {
        int stock = 0;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            stock = session.createQuery("SELECT stock FROM Ammo WHERE id = :id", Integer.class)
                    .setParameter("id", id)
                    .getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return stock;
    }
    
    public boolean isThereID(int id){
        boolean isThere = false;
        
        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Ammo ammo = session.find(Ammo.class, id);

            if(ammo != null){
                isThere = true;
            }
            
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return isThere;
    }
    
    protected boolean add(Ammo ammo) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.persist(ammo);

            tx.commit();

            isComplate = true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }

    protected boolean update(Ammo ammo) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Ammo am = session.find(Ammo.class, ammo.getId());

            if (am != null) {
                am.setId(ammo.getId());
                am.setName(ammo.getName());
                am.setStock(ammo.getStock());

                session.merge(ammo);

                tx.commit();

                isComplate = true;
            }
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }

    protected boolean delete(Ammo ammo) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.remove(ammo);

            tx.commit();

            isComplate = true;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return isComplate;
    }
}
