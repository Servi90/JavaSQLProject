package com.trifulcas.models;

import jakarta.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "veterinarians")
public class Veterinarians {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veterinariansid")
    private int veterinariansid;

    @Column(name = "name")
    private String name;

    @Column(name = "specialty")
    private String specialty;

    @ManyToMany
    @JoinTable(
        name = "dogs_veterinarians", 
        joinColumns = @JoinColumn(name = "veterinariansid"),
        inverseJoinColumns = @JoinColumn(name = "dogsid") 
    )
    private Set<Dogs> dogs = new HashSet<>();

    // Constructor vacío
    public Veterinarians() {}

    // Constructor con parámetros
    public Veterinarians(String name, String specialty) {
        this.name = name;
        this.specialty = specialty;
    }

    // Getters y setters
    public int getVeterinarianID() {
        return veterinariansid;
    }

    public void setVeterinarianID(int veterinariansid) {
        this.veterinariansid = veterinariansid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public Set<Dogs> getDogs() {
        return dogs;
    }

    public void setDogs(Set<Dogs> dogs) {
        this.dogs = dogs;
    }

    @Override
    public String toString() {
        return "Veterinarians [veterinariansid=" + veterinariansid + ", name=" + name + ", specialty=" + specialty + "]";
    }
}