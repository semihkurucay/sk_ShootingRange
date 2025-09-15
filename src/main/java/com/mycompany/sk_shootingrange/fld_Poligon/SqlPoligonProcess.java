/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Poligon;

import com.mycompany.sk_shootingrange.fld_Ammo.Ammo;
import com.mycompany.sk_shootingrange.fld_Costumer.Costumer;
import com.mycompany.sk_shootingrange.fld_Employee.Employee;
import com.mycompany.sk_shootingrange.fld_Weapons.Weapon;
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
public class SqlPoligonProcess {

    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClasses(Process.class, Ammo.class, Employee.class, Costumer.class, Weapon.class)
            .buildSessionFactory();

    public void exit(){
        if(session != null){
            session.close();
        }
        if(factory != null){
            factory.close();
        }
    }
    
    public void getHistoryList(JTable table, String id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Object[]> items = session.createQuery("SELECT p.id, p.date, e.id, concat(e.name, concat(' ', e.surname)), w.id, concat(w.brand, concat(' ', w.model)), a.id, a.name, p.count, c.id, concat(c.name, concat(' ', c.surname)), p.userInfo, p.userSignature, p.study, p.price FROM Poligon p JOIN p.employee e JOIN p.ammo a JOIN p.costumer c JOIN p.weapon w WHERE cast(w.id as string) LIKE :id OR cast(e.id as string) LIKE :id OR cast(c.id as string) LIKE :id", Object[].class)
                    .setParameter("id", "%" + id + "%")
                    .getResultList();

            for (Object[] item : items) {
                model.addRow(new Object[]{item[0], item[1], item[2], item[3], item[4], item[5], item[6], item[7], item[8], item[9], item[10], item[11], item[12], item[13], item[14]});
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

    protected boolean addPoligon(Poligon poligon) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.persist(poligon);

            Ammo ammo = session.find(Ammo.class, poligon.getAmmo().getId());
            if (ammo != null) {
                ammo.setStock(ammo.getStock() - poligon.getCount());
                session.merge(ammo);
            }

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
