package com.clempaj.beerconsumption.data;

public class Beer {
    private long id;
    private String name;
    private double alcohol;

    public Beer(long id, String name, double alcohol) {
        this.id = id;
        this.name = name;
        this.alcohol = alcohol;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }
}

