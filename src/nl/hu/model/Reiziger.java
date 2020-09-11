package nl.hu.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reiziger {

    private int ID;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private LocalDate geboorteDatum;
    private Adres adres;

    private List<OVChipkaart> ovchip;

    public Reiziger(int ID, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboorteDatum) {
        this.ID = ID;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public LocalDate getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(LocalDate geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }

    public Adres getAdres() {
        return adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String stringMethod(){
       // return String.format("Reiziger {#%s %s %s, geb. %s, Adres {%s %s}}", "3", getVoorletters(), getAchternaam(), getGeboorteDatum().toString(), getAdres().getPostcode());

        return getID() + ". " + voorletters + "." + achternaam;
    }

    @Override
    public String toString() {
        return "Reiziger{" +
                "ID=" + ID +
                ", voorletters='" + voorletters + '\'' +
                ", tussenvoegsel='" + tussenvoegsel + '\'' +
                ", achternaam='" + achternaam + '\'' +
                ", geboorteDatum=" + geboorteDatum +
                ", adres=" + adres +
                '}';
    }
}