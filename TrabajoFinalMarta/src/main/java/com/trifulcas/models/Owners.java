package com.trifulcas.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "owners")
public class Owners {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ownersid")
    private int ownersid;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "address", length = 255)
    private String address;

    // Constructor vacío
    public Owners() {}

    // Constructor con parámetros
    public Owners(String name, String address) {
        this.name = name;
        this.address = address;
    }

    // Getters y setters
    
    public int getOwnersID() {
        return ownersid;
    }

    public void setOwnersID(int ownersid) {
        this.ownersid = ownersid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    @Override
    public String toString() {
        return "Owners [ownersid=" + ownersid + ", name=" + name + ", address=" + address + "]";
    }
}