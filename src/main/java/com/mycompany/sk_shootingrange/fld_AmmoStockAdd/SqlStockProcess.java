/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_AmmoStockAdd;

import com.mycompany.sk_shootingrange.fld_Ammo.Ammo;
import com.mycompany.sk_shootingrange.fld_Business.Business;
import java.util.List;
import javax.swing.DefaultListModel;
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
public class SqlStockProcess {

    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClasses(Invoice.class, InvoiceStock.class, Business.class, Ammo.class)
            .buildSessionFactory();

    public void exit() {
        if (session != null) {
            session.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    public void getHistoryList(JTable table, String id) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Object[]> items = session.createQuery("SELECT i.id, i.dateTime, b.id, b.name, COUNT(s.id), i.kdv, i.price FROM Invoice i JOIN i.business b JOIN i.stocks s WHERE cast(b.id as string) LIKE :id OR cast(i.id as string) LIKE :id GROUP BY i.id, i.dateTime, b.id, b.name, i.kdv, i.price", Object[].class)
                    .setParameter("id", "%" + id + "%")
                    .getResultList();

            for (Object[] item : items) {
                model.addRow(new Object[]{item[0], item[1], item[2], item[3], item[4], item[5], item[6]});
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

    protected Object[] getInvoiceInfo(String id) {
        Object[] invoice = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            invoice = session.createQuery("SELECT i.id,b.id, b.name,i.kdv, i.price FROM Invoice i JOIN i.business b WHERE i.id = :id", Object[].class)
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

        return invoice;
    }

    protected DefaultListModel getItems(String id) {
        DefaultListModel model = new DefaultListModel();

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Object[]> items = session.createQuery("SELECT a.id, a.name, s.brand, s.stock FROM InvoiceStock s JOIN s.ammo a JOIN s.invoice i WHERE i.id = :id", Object[].class)
                    .setParameter("id", id)
                    .getResultList();

            for (Object[] item : items) {
                model.addElement(item[0] + " - " + item[1] + " - " + item[2] + " - " + item[3]);
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

        return model;
    }

    public boolean isThereID(String id) {
        boolean isThere = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            if (session.find(Invoice.class, id) != null) {
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

    protected boolean stockAdd(Invoice invoice, List<InvoiceStock> stocks) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.persist(invoice);

            for (InvoiceStock stock : stocks) {
                stock.setInvoice(invoice);

                session.persist(stock);

                Ammo ammo = session.find(Ammo.class, stock.getAmmo().getId());
                if (ammo != null) {
                    ammo.setStock(ammo.getStock() + stock.getStock());
                    session.merge(ammo);
                }
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
