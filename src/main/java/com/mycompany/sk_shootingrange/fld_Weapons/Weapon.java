/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Weapons;

import com.mycompany.sk_shootingrange.fld_Poligon.Poligon;
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
@Table(name = "tbl_Weapons")
public class Weapon {
    @Id
    @Column(name = "id")
    private String id = "";
    
    @Column(name = "brand")
    private String brand = "";
    
    @Column(name = "model")
    private String model = "";
    
    @Column(name = "calibre")
    private String calibre = "";
    
    @Column(name = "active")
    private boolean active = false;
    
    @Column(name = "comment")
    private String comment = "";

    @OneToMany(mappedBy = "weapon", cascade = CascadeType.ALL)
    private List<Poligon> poligon = new ArrayList<>();
    
    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public String getCalibre() {
        return calibre;
    }

    public boolean isActive() {
        return active;
    }

    public String getComment() {
        return comment;
    }

    public List<Poligon> getPoligon() {
        return poligon;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setCalibre(String calibre) {
        this.calibre = calibre;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setPoligon(List<Poligon> poligon) {
        this.poligon = poligon;
    }
    
    public boolean chkSetId(String id) {
        if(id.matches("^[0-9a-zA-ZüğşçöıÜĞŞÇÖİ.-]{1,50}$")){
            this.id = id;
            return true;
        }
        return false;
    }

    public boolean chkSetBrand(String brand) {
        if(brand.toUpperCase().matches("^[0-9A-ZÜĞŞÇÖİ .-]{1,50}$")){
            this.brand = brand.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetModel(String model) {
        if(model.toUpperCase().matches("^[0-9A-ZÜĞŞÇÖİ .-]{1,50}$")){
            this.model = model.toUpperCase();
            return true;
        }
        return false;
    }

    public boolean chkSetCalibre(String calibre) {
        if(calibre.toUpperCase().matches("^[0-9A-ZÜĞŞÇÖİ .-]{1,50}$")){
            this.calibre = calibre.toUpperCase();
            return true;
        }
        return false;
    }
    
    public boolean chkSetComment(String comment) {
        if(comment.matches("^[0-9a-zA-ZüğşçöıÜĞŞÇÖİ .-]{0,1000}$")){
            this.comment = comment;
            return true;
        }
        return false;
    }
}
