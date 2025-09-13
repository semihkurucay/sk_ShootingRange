/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sk_shootingrange.fld_Poligon;

import com.mycompany.sk_shootingrange.fld_Ammo.Ammo;
import com.mycompany.sk_shootingrange.fld_Costumer.Costumer;
import com.mycompany.sk_shootingrange.fld_Employee.Employee;
import com.mycompany.sk_shootingrange.fld_Weapons.Weapon;
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
@Table(name = "tbl_Shooting")
public class Poligon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id = -1;
    
    @ManyToOne
    @JoinColumn(name = "employee")
    private Employee employee = null;
    
    @ManyToOne
    @JoinColumn(name = "costumer")
    private Costumer costumer = null;
    
    @ManyToOne
    @JoinColumn(name = "weapon")
    private Weapon weapon = null;
    
    @ManyToOne
    @JoinColumn(name = "ammo")
    private Ammo ammo = null;
    
    @Column(name = "count")
    private int count = -1;
    
    @Column(name = "price")
    private float price = 0.0f;
    
    @Column(name = "userInfo")
    private boolean userInfo = false;
    
    @Column(name = "userSignature")
    private boolean userSignature = false;
    
    @Column(name = "study")
    private boolean study = false;

    public int getId() {
        return id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public Costumer getCostumer() {
        return costumer;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public Ammo getAmmo() {
        return ammo;
    }

    public int getCount() {
        return count;
    }

    public float getPrice() {
        return price;
    }

    public boolean isUserInfo() {
        return userInfo;
    }

    public boolean isUserSignature() {
        return userSignature;
    }

    public boolean isStudy() {
        return study;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setCostumer(Costumer costumer) {
        this.costumer = costumer;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setAmmo(Ammo ammo) {
        this.ammo = ammo;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setUserInfo(boolean userInfo) {
        this.userInfo = userInfo;
    }

    public void setUserSignature(boolean userSignature) {
        this.userSignature = userSignature;
    }

    public void setStudy(boolean study) {
        this.study = study;
    }
    
    public boolean chkSetPrice(String price) {
        try{
            this.price = Float.parseFloat(price);
            
            if(this.price >= 0){
                return true;
            }
        }catch(Exception e){
            
        }
        return false;
    }
}
