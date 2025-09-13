/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_AmmoStockAdd;

import com.mycompany.sk_shootingrange.fld_Ammo.Ammo;
import com.mycompany.sk_shootingrange.fld_Business.Business;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author semih
 */
public class SqlStockProcess {
    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClasses(Invoice.class, InvoiceStock.class, Business.class, Ammo.class)
            .buildSessionFactory();
    
    protected boolean isThereID(String id){
        boolean isThere = false;
        
        try{
            session = factory.openSession();
            tx = session.beginTransaction();
            
            if(session.find(Invoice.class, id) != null){
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
    
    protected boolean stockAdd(Invoice invoice, List<InvoiceStock> stocks){
        boolean isComplate = false;
        
        try{
            session = factory.openSession();
            tx = session.beginTransaction();
            
            session.persist(invoice);
            
            for(InvoiceStock stock : stocks){
                stock.setInvoice(invoice);
                
                session.persist(stock);
                
                Ammo ammo = session.find(Ammo.class, stock.getAmmo().getId());
                if(ammo != null){
                    ammo.setStock(ammo.getStock() + stock.getStock());
                    session.merge(ammo);
                }
            }
            
            tx.commit();
            
            isComplate = true;
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
