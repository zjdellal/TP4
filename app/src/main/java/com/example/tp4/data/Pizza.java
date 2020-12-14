package com.example.tp4.data;

public class Pizza {

    private long id;
    private float prix;
    private Sorte sorte;
    private Type type;

    public Pizza() {
    }

    public Pizza(float prix, Sorte sorte, Type type) {
        this.prix = prix;
        this.sorte = sorte;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Sorte getSorte() {
        return sorte;
    }

    public void setSorte(Sorte sorte) {
        this.sorte = sorte;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public enum Sorte {
        FROMAGE,
        PEPPERONI,
        BACON,
        GARNIE,
        VEGE,
    }

    public enum Type {
        LARGE,
        MOYEN,
        PETIT
    }
}
