/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_AmmoStockAdd;

import com.mycompany.sk_shootingrange.fld_Business.Business;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_BuyAmmo")
public class Invoice {
    @Id
    @Column(name = "id")
    private String id = "";
    
    @ManyToOne
    @JoinColumn(name = "business")
    private Business business = null;
    
    @Column(name = "kdv")
    private double kdv = 0.0;
    
    @Column(name = "price")
    private double price = 0.0;
    
    @Column(name = "date")
    private LocalDateTime dateTime = LocalDateTime.now();
    
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL)
    List<InvoiceStock> stocks = new ArrayList();

    public String getId() {
        return id;
    }

    public Business getBusiness() {
        return business;
    }

    public double getKdv() {
        return kdv;
    }

    public double getPrice() {
        return price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public List<InvoiceStock> getStocks() {
        return stocks;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public void setKdv(double kdv) {
        this.kdv = kdv;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setStocks(List<InvoiceStock> stocks) {
        this.stocks = stocks;
    }
    
    public boolean chkSetId(String id) {
        if(id.trim().matches("^[0-9a-zA-ZüğışçöÜĞİŞÇÖ -._/]{1,50}$")){
            this.id = id.trim();
            return true;
        }
        return false;
    }

    public boolean chkSetKdv(String kdv) {
        try{
            double _kdv = Double.parseDouble(kdv);
            if(_kdv >= 0){
                this.kdv = _kdv;
                return true;
            }
        }catch(Exception e){
            
        }
        return false;
    }

    public boolean chkSetPrice(String price) {
        try{
            double _price = Double.parseDouble(price);
            if(_price >= 0){
                this.price = _price;
                return true;
            }
        }catch(Exception e){
            
        }
        return false;
    }
}
