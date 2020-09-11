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
    private List<OVChipkaart> ovchip = new ArrayList<OVChipkaart>();

    public Reiziger(int ID, String voorletters, String tussenvoegsel, String achternaam, LocalDate geboorteDatum, OVChipkaart ovChipkaart) {
        this.ID = ID;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        ovchip.add(ovChipkaart);
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

    public List<OVChipkaart> getOvchip() {
        return ovchip;
    }

    public void setOvchip(List<OVChipkaart> ovchip) {
        this.ovchip = ovchip;
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
                ", heeft " + ovchip.size() + " ov_chipkaarten" +
                '}';
    }
}