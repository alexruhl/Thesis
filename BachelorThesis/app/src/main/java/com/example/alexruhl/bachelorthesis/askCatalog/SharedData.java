package com.example.alexruhl.bachelorthesis.askCatalog;

import java.io.Serializable;

 class SharedData implements Serializable {

    private String anzahlSportarten;
    private String ratingRegelmaessig;
    private String festeZeiten;
    private String struktur;
    private String sportPerWeek;
    private String tageszeitraum;
    private String alter;

     SharedData() {}

     String getAnzahlSportarten() {
        return anzahlSportarten;
    }

     void setAnzahlSportarten(String anzahlSportarten) {
        this.anzahlSportarten = anzahlSportarten;
    }

      String getRatingRegelmaessig() {
        return ratingRegelmaessig;
    }

     void setRatingRegelmaessig(String ratingRegelmaessig) {
        this.ratingRegelmaessig = ratingRegelmaessig;
    }

      String getFesteZeiten() {
        return festeZeiten;
    }

      void setFesteZeiten(String festeZeiten) {
        this.festeZeiten = festeZeiten;
    }

     String getStruktur() {
        return struktur;
    }

     void setStruktur(String struktur) {
        this.struktur = struktur;
    }

      String getSportPerWeek() {
        return sportPerWeek;
    }

      void setSportPerWeek(String sportPerWeek) {
        this.sportPerWeek = sportPerWeek;
    }

       String getAlter() {
        return alter;
    }

      void setAlter(String alter) {
        this.alter = alter;
    }
}
