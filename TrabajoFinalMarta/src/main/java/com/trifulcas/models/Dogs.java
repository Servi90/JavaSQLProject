package com.trifulcas.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dogs")
public class Dogs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dogsid")
    private int dogsid;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "breed", length = 100)
    private String breed;

    @ManyToOne
    @JoinColumn(name = "ownersid")
    private Owners owners;

    // Constructor vacío
    public Dogs() {}

    // Constructor con parámetros
    public Dogs(String name, String breed, Owners owners) {
        this.name = name;
        this.breed = breed;
        this.owners = owners;
    }

    // Getters y setters
    
    public int getDogsID() {
        return dogsid;
    }

    public void setDogsID(int dogsid) {
        this.dogsid = dogsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Owners getOwner() {
        return owners;
    }

    public void setOwner(Owners owners) {
        this.owners = owners;
    }

    @Override
    public String toString() {
        return "Dogs [dogsid=" + dogsid + ", name=" + name + ", breed=" + breed + ", owners=" + owners + "]";
    }
}