/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Costumer;

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
public class SqlCostumerProcess {

    private Transaction tx = null;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Costumer.class)
            .buildSessionFactory();

    public void exit() {
        if (session != null) {
            session.close();
        }
        if (factory != null) {
            factory.close();
        }
    }

    public Costumer getCostumer(String id) {
        Costumer costumer = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            costumer = session.find(Costumer.class, id);

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

        return costumer;
    }

    public boolean isThereCostumer(String id) {
        boolean isThere = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            if (session.find(Costumer.class, id) != null) {
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

    protected void list(JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            List<Object[]> costumers = session.createQuery("SELECT id, name, surname FROM Costumer", Object[].class).getResultList();

            for (Object[] costumer : costumers) {
                model.addRow(new Object[]{(String) costumer[0], (String) costumer[1] + " " + (String) costumer[2]});
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

    protected boolean add(Costumer costumer) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.persist(costumer);

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

    protected boolean update(Costumer costumer) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Costumer cost = session.find(Costumer.class, costumer.getId());

            if (cost != null) {
                cost.setId(costumer.getId());
                cost.setName(costumer.getName());
                cost.setSurname(costumer.getSurname());
                cost.setDate(costumer.getDate());
                cost.setPhone(costumer.getPhone());
                cost.setMail(costumer.getMail());
                cost.setAddress(costumer.getAddress());

                session.merge(cost);
                
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

    protected boolean delete(Costumer costumer) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.remove(costumer);

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
