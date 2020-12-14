package com.example.tp4.data;

public class Commande {

    private long id;
    private String nomClient;
    private float montantPaye;

    public Commande(long id, String nomClient, float montantPaye) {
        this.id = id;
        this.nomClient = nomClient;
        this.montantPaye = montantPaye;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public float getMontantPaye() {
        return montantPaye;
    }

    public void setMontantPaye(float montantPaye) {
        this.montantPaye = montantPaye;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}
