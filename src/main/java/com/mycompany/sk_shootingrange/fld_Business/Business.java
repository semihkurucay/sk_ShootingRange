/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Business;

import com.mycompany.sk_shootingrange.fld_AmmoStockAdd.Invoice;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_Business")
public class Business {
    @Id
    @Column(name = "id")
    private String id = "";
    
    @Column(name = "taxOffice")
    private String taxOffice = "";
    
    @Column(name = "name")
    private String name = "";
    
    @Column(name = "mail")
    private String mail = "";
    
    @Column(name = "phone")
    private String phone = "";
    
    @Column(name = "city")
    private String city = "";
    
    @Column(name = "district")
    private String district = "";
    
    @Column(name = "address")
    private String address = "";
    
    @OneToMany(mappedBy = "business", cascade = CascadeType.ALL)
    private List<Invoice> invoices = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getTaxOffice() {
        return taxOffice;
    }

    public String getName() {
        return name;
    }

    public String getMail() {
        return mail;
    }

    public String getPhone() {
        return phone;
    }

    public String getCity() {
        return city;
    }

    public String getDistrict() {
        return district;
    }

    public String getAddress() {
        return address;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setTaxOffice(String taxOffice) {
        this.taxOffice = taxOffice;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public boolean chkSetId(String id) {
        if(id.matches("^[0-9]{1,11}$")){
            this.id = id;
            return true;
        }
        return false;
    }

    public boolean chkSetTaxOffice(String taxOffice) {
        if(taxOffice.toUpperCase().matches("^[A-ZÜĞİŞÇÖ .]{1,50}$")){
            this.taxOffice = taxOffice.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetName(String name) {
        if(name.toUpperCase().matches("^[A-ZÜĞİŞÇÖ .-_]{1,50}$")){
            this.name = name.toUpperCase();
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

    public boolean chkSetPhone(String phone) {
        if(phone.matches("^[0-9]{1,15}$")){
            this.phone = phone;
            return true;
        }
        return false;
    }

    public boolean chkSetCity(String city) {
        if(city.toUpperCase().matches("^[A-ZÜĞİŞÇÖ .-_]{1,50}$")){
            this.city = city.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetDistrict(String district) {
        if(district.toUpperCase().matches("^[A-ZÜĞİŞÇÖ .-_]{1,50}$")){
            this.district = district.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetAddress(String address) {
        if(address.matches("^[0-9a-zA-ZüğışçöÜĞİŞÇÖ .-_/]{1,1000}$")){
            this.address = address;
            return true;
        }
        return false;
    }
}
