package nl.hu.model;

public class Reiziger {

    private int ID;
    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private String geboorteDatum;

    public Reiziger(int ID, String voorletters, String tussenvoegsel, String achternaam, String geboorteDatum) {
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

    public String getGeboorteDatum() {
        return geboorteDatum;
    }

    public void setGeboorteDatum(String geboorteDatum) {
        this.geboorteDatum = geboorteDatum;
    }
}