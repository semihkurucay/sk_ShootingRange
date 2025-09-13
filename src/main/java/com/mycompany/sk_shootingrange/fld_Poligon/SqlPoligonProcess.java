/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Poligon;

import com.mycompany.sk_shootingrange.fld_Ammo.Ammo;
import com.mycompany.sk_shootingrange.fld_Costumer.Costumer;
import com.mycompany.sk_shootingrange.fld_Employee.Employee;
import com.mycompany.sk_shootingrange.fld_Weapons.Weapon;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author semih
 */
public class SqlPoligonProcess {
    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClasses(Process.class, Ammo.class, Employee.class, Costumer.class, Weapon.class)
            .buildSessionFactory();
    
    protected boolean addPoligon(Poligon poligon){
        boolean isComplate = false;
        
        try{
            session = factory.openSession();
            tx = session.beginTransaction();
            
            session.persist(poligon);
            
            Ammo ammo = session.find(Ammo.class, poligon.getAmmo().getId());
            if(ammo != null){
                ammo.setStock(ammo.getStock() - poligon.getCount());
                session.merge(ammo);
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
        
        return isComplate;
    }
}
