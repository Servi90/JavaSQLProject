package com.trifulcas.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "dogs_veterinarians")
public class DogVeterinarian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dogsVeterinariansid")
    private int dogsVeterinariansid;

    @ManyToOne
    @JoinColumn(name = "dogsid")
    private Dogs dogs;

    @ManyToOne
    @JoinColumn(name = "veterinariansid")
    private Veterinarians veterinarians;

    // Constructor vacío
    public DogVeterinarian() {}

    // Constructor con parámetros
    public DogVeterinarian(Dogs dogs, Veterinarians veterinarians) {
        this.dogs = dogs;
        this.veterinarians = veterinarians;
    }

    // Getters y setters
    
    public int getDogsVeterinariansID() {
        return dogsVeterinariansid;
    }

    public void setDogsVeterinariansID(int dogsVeterinariansid) {
        this.dogsVeterinariansid = dogsVeterinariansid;
    }

    public Dogs getDog() {
        return dogs;
    }

    public void setDog(Dogs dogs) {
        this.dogs = dogs;
    }

    public Veterinarians getVeterinarian() {
        return veterinarians;
    }

    public void setVeterinarian(Veterinarians veterinarians) {
        this.veterinarians = veterinarians;
    }

    @Override
    public String toString() {
        return "DogVeterinarian [dogsVeterinariansid=" + dogsVeterinariansid + ", dogs=" + dogs + ", veterinarians=" + veterinarians + "]";
    }
}