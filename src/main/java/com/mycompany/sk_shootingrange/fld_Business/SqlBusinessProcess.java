/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Business;

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
public class SqlBusinessProcess {

    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Business.class)
            .buildSessionFactory();

    protected void exit() {
        if (session != null) {
            session.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    protected void list(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Object[]> businesses = session.createQuery("SELECT id, name FROM Business", Object[].class).getResultList();

            for (Object[] business : businesses) {
                model.addRow(business);
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

    public Business getBusiness(String id) {
        Business business = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            business = session.find(Business.class, id);

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

        return business;
    }

    public String getName(String id) {
        String name = "";

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            name = session.find(Business.class, id).getName();

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

    public boolean isThereBusiness(String id) {
        boolean isThere = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            if (session.find(Business.class, id) != null) {
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

    protected boolean add(Business business) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.persist(business);

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

    protected boolean update(Business business) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Business busi = session.find(Business.class, business);

            if (busi != null) {
                busi.setId(business.getId());
                busi.setTaxOffice(business.getTaxOffice());
                busi.setName(business.getName());
                busi.setPhone(business.getPhone());
                busi.setMail(business.getMail());
                busi.setCity(business.getCity());
                busi.setDistrict(business.getDistrict());
                busi.setAddress(business.getAddress());

                session.merge(busi);

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

    protected boolean delete(Business business) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.remove(business);

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
