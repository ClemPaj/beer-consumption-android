package com.clempaj.beerconsumption.data;

import java.util.Date;

public class Consumption {
    private Date date;
    private int volume;
    private Beer beer;

    public Consumption(Date date, int volume, Beer beer) {
        this.date = date;
        this.volume = volume;
        this.beer = beer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Beer getBeer() {
        return beer;
    }

    public void setBeer(Beer beer) {
        this.beer = beer;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }
}
