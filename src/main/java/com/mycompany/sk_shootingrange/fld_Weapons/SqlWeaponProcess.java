/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Weapons;

import com.mycompany.sk_shootingrange.fld_Poligon.Poligon;
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
public class SqlWeaponProcess {

    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClasses(Weapon.class, Poligon.class)
            .buildSessionFactory();

    protected void list(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Object[]> weapons = session.createQuery("SELECT id, brand, model FROM Weapon", Object[].class).getResultList();

            for (Object[] weapon : weapons) {
                model.addRow(new Object[]{weapon[0], weapon[1], weapon[2]});
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

    public Weapon getWeapon(String id){
        Weapon weapon = null;
        
        try{
            session = factory.openSession();
            tx = session.beginTransaction();
            
            weapon = session.find(Weapon.class, id);
            
            tx.commit();
        }catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();
            }

            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        
        return weapon;
    }
    
    protected long getFireAmmo(Weapon id){
        long ammo = 0;
        
        try{
            session = factory.openSession();
            tx = session.beginTransaction();
            
            ammo = session.createQuery("SELECT COALESCE(SUM(count),0) FROM Poligon WHERE weapon = :id",Long.class)
                    .setParameter("id", id)
                    .getSingleResult();
            
            tx.commit();
        }catch (Exception e) {
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
    
    public boolean isThereID(String id){
        boolean isThere = false;
        
        try{
            session = factory.openSession();
            tx = session.beginTransaction();
            
            Weapon weapon = session.find(Weapon.class, id);
            
            if(weapon != null){
                isThere = true;
            }
            
            tx.commit();
        }catch (Exception e) {
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
    
    protected boolean add(Weapon weapon) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.persist(weapon);

            tx.commit();
            
            return true;
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

    protected boolean update(Weapon weapon) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Weapon weap = session.find(Weapon.class, weapon);
            if (weap != null) {
                weap.setId(weapon.getId());
                weap.setBrand(weapon.getBrand());
                weap.setModel(weapon.getModel());
                weap.setCalibre(weapon.getCalibre());
                weap.setActive(weapon.isActive());
                weap.setComment(weapon.getComment());
                
                session.merge(weap);

                tx.commit();
                
                return true;
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

    protected boolean delete(Weapon weapon) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.remove(weapon);

            tx.commit();
            
            return true;
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
