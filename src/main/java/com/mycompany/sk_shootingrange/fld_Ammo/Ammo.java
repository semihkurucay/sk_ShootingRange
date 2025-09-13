/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Ammo;

import com.mycompany.sk_shootingrange.fld_AmmoStockAdd.InvoiceStock;
import com.mycompany.sk_shootingrange.fld_Poligon.Poligon;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
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
@Table(name = "tbl_Ammo")
public class Ammo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id = 0;
    
    @Column(name = "name")
    private String name = "";
    
    @Column(name = "stock")
    private int stock = 0;

    @OneToMany(mappedBy = "ammo", cascade = CascadeType.ALL)
    private List<InvoiceStock> stocks = new ArrayList<>();
    
    @OneToMany(mappedBy = "ammo", cascade = CascadeType.ALL)
    private List<Poligon> poligon = new ArrayList<>();
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }

    public List<InvoiceStock> getStocks() {
        return stocks;
    }

    public List<Poligon> getPoligon() {
        return poligon;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setStocks(List<InvoiceStock> stocks) {
        this.stocks = stocks;
    }

    public void setPoligon(List<Poligon> poligon) {
        this.poligon = poligon;
    }
    
    public boolean chkSetId(String id) {
        if(id.matches("^[0-9]{1,}$")){
            this.id = Integer.parseInt(id);
            return true;
        }
        return false;
    }

    public boolean chkSetName(String name) {
        if(name.toUpperCase().matches("^[0-9A-ZÜĞŞÇÖİ .-]{1,50}$")){
            this.name = name.toUpperCase();
            return true;
        }
        return false;
    }
}
