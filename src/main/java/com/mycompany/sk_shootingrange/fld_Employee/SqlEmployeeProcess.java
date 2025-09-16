/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Employee;

import jakarta.persistence.NoResultException;
import java.util.List;
import javax.swing.JOptionPane;
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
public class SqlEmployeeProcess {

    private Transaction tx;
    private Session session = null;
    private SessionFactory factory = new Configuration()
            .configure("hibernate.cfg.xml")
            .addAnnotatedClass(Employee.class)
            .buildSessionFactory();

    public void exit() {
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

            List<Object[]> employees = session.createQuery("SELECT id, name, surname FROM Employee", Object[].class)
                    .getResultList();

            for (Object[] employee : employees) {
                model.addRow(new Object[]{(String) employee[0], (String) employee[1] + " " + (String) employee[2]});
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

    public Employee getEmployee(String id) {
        Employee employee = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            employee = session.find(Employee.class, id);

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

        return employee;
    }

    protected Object[] getEmployee_User(String id) {
        Object[] employee = null;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            employee = session.createQuery("SELECT id, name, surname, phone, mail FROM Employee WHERE id = :id", Object[].class)
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

        return employee;
    }

    public String getEmployeeID(String username, String password) {
        String id = "";

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            id = session.createQuery("SELECT id FROM Employee WHERE username = :username AND password = :password", String.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();

            tx.commit();
        } catch (NoResultException e) {
            
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

        return id;
    }

    private boolean isThereUsername(String username) {
        boolean isThere = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Long count = session.createQuery("SELECT COUNT(id) FROM Employee WHERE username = :username", Long.class)
                    .setParameter("username", username)
                    .getSingleResult();

            if (count > 0) {
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

    public boolean isThereEmployee(String id) {
        boolean isThere = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Employee employee = session.find(Employee.class, id);

            if (employee != null) {
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

    public boolean isAdmin(String id) {
        boolean isAdmin = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            isAdmin = session.createQuery("SELECT admin FROM Employee WHERE id = :id", Boolean.class)
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

        return isAdmin;
    }

    protected boolean add(Employee employee) {
        boolean isComplate = false;

        try {
            if (!isThereUsername(employee.getUsername())) {
                session = factory.openSession();
                tx = session.beginTransaction();

                session.persist(employee);

                tx.commit();
                isComplate = true;
            } else {
                JOptionPane.showMessageDialog(null, "Girdiğiniz kullanıcı adı sistemde kullanılıyor.\nYeni bir kullanıcı adı girin.", "Tekrarlanan Kullanıcı Adı", JOptionPane.WARNING_MESSAGE);
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

    protected boolean update(Employee employee) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Employee emp = session.find(Employee.class, employee.getId());

            if (emp != null) {
                emp.setId(employee.getId());
                emp.setName(employee.getName());
                emp.setSurname(employee.getSurname());
                emp.setAdmin(employee.isAdmin());
                emp.setPassword(employee.getPassword());
                emp.setPhone(employee.getPhone());
                emp.setMail(employee.getMail());
                emp.setAddress(employee.getAddress());

                session.merge(emp);

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

    protected boolean update_user(Employee employee) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            Employee emp = session.find(Employee.class, employee.getId());

            if (emp != null) {
                emp.setId(employee.getId());
                emp.setPassword(employee.getPassword());

                session.merge(emp);

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

    protected boolean delete(Employee employee) {
        boolean isComplate = false;

        try {
            session = factory.openSession();
            tx = session.beginTransaction();

            session.remove(employee);

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
