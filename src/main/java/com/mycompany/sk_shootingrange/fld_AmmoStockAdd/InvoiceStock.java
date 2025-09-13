/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_AmmoStockAdd;

import com.mycompany.sk_shootingrange.fld_Ammo.Ammo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 *
 * @author semih
 */
@Entity
@Table(name = "tbl_BuyAmmoInfo")
public class InvoiceStock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id = 0;
    
    @ManyToOne
    @JoinColumn(name = "invoice")
    private Invoice invoice = null;
    
    @ManyToOne
    @JoinColumn(name = "ammo")
    private Ammo ammo = null;
    
    @Column(name = "count")
    private int stock = 0;
    
    @Column(name = "brand", nullable = true)
    private String brand = "";

    public InvoiceStock() {
    }

    public InvoiceStock(Invoice invoice, Ammo ammo, int stock, String brand) {
        this.invoice = invoice;
        this.ammo = ammo;
        this.stock = stock;
        this.brand = brand;
    }
    
    public InvoiceStock(Ammo ammo, int stock, String brand) {
        this.ammo = ammo;
        this.stock = stock;
        this.brand = brand;
    }
    
    public int getId() {
        return id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public int getStock() {
        return stock;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
    
    public boolean chkSetBrand(String brand){
        if(brand == null || brand.trim().matches("^[0-9a-zA-ZüğışçöÜĞİŞÇÖ ]{0,50}$")){
            this.brand = brand;
            return true;
        }
        return false;
    }
}
