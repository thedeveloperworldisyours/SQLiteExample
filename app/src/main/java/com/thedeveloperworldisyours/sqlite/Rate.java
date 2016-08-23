package com.thedeveloperworldisyours.sqlite;

/**
 * Created by javierg on 23/08/16.
 */
public class Rate {
    private long id;
    private String coin;
    private Double value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCoin() {
        return coin;
    }

    public void setCoin(String coin) {
        this.coin = coin;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return coin;
    }
}
