package com.example.appquocgia.Entity;

public class Country {
    private String country;
    private long population;
    private float square;
    private String urlImg;

    @Override
    public String toString() {
        return this.country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public long getPopulation() {
        return population;
    }

    public void setPopulation(long population) {
        this.population = population;
    }

    public float getSquare() {
        return square;
    }

    public void setSquare(float square) {
        this.square = square;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public Country() {
    }

    public Country(String country, long population, float square, String urlImg) {
        this.country = country;
        this.population = population;
        this.square = square;
        this.urlImg = urlImg;
    }
}
