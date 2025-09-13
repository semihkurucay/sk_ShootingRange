/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Costumer;

import com.mycompany.sk_shootingrange.fld_Poligon.Poligon;
import com.mycompany.sk_shootingrange.tc_check.Check;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_Costumer")
public class Costumer {

    @Id
    @Column(name = "id")
    private String id = "";
    
    @Column(name = "name")
    private String name = "";
    
    @Column(name = "surname")
    private String surname = "";
    
    @Column(name = "birthdate")
    private LocalDate date;
    
    @Column(name = "phone")
    private String phone = "";
    
    @Column(name = "mail")
    private String mail = "";
    
    @Column(name = "address")
    private String address = "";

    @OneToMany(mappedBy = "costumer", cascade = CascadeType.ALL)
    private List<Poligon> poligon = new ArrayList<>();
    
    @Transient
    private Check check = new Check();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getPhone() {
        return phone;
    }

    public String getMail() {
        return mail;
    }

    public String getAddress() {
        return address;
    }

    public List<Poligon> getPoligon() {
        return poligon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPoligon(List<Poligon> poligon) {
        this.poligon = poligon;
    }

    public boolean chkSetId(String id) {
        if (id.matches("[0-9]{11}")) {
            check.setTC(id.toCharArray());

            if (check.getCheck()) {
                this.id = id;
                return true;
            }
        }
        return false;
    }

    public boolean chkSetName(String name) {
        if (name.toUpperCase().matches("^[A-ZÜĞŞÇÖİ ]{1,50}$")) {
            this.name = name.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetSurname(String surname) {
        if (surname.toUpperCase().matches("^[A-ZÜĞŞÇÖİ]{1,50}$")) {
            this.surname = surname.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetDate(String date) {
        try {
            this.date = LocalDate.parse(date);

            if (Period.between(this.date, LocalDate.now()).getYears() >= 18) {
                return true;
            }
        } catch (Exception e) {
        }

        return false;
    }

    public boolean chkSetPhone(String phone) {
        if (phone.toUpperCase().matches("^[0-9]{0,15}$")) {
            this.phone = phone.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetMail(String mail) {
        if (mail.toLowerCase().matches("^$|^(?=.{1,100}$)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
            this.mail = mail.toLowerCase();
            return true;
        }
        return false;
    }

    public boolean chkSetAddress(String address) {
        if (address.matches("^[0-9a-zA-ZüğşçöıÜĞŞÇÖİ .*-_]{1,1000}$")) {
            this.address = address;
            return true;
        }
        return false;
    }
}
