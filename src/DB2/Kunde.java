package DB2;

public class Kunde {
    private int knr;
    private String kname;
    private int plz;
    private String ort;
    private String strasse;
    private double kklimit;

    public int getKnr() {
        return knr;
    }

    public void setKnr(int knr) {
        this.knr = knr;
    }

    public String getKname() {
        return kname;
    }

    public void setKname(String kname) {
        this.kname = kname;
    }

    public int getPlz() {
        return plz;
    }

    public void setPlz(int plz) {
        this.plz = plz;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public void setStrasse(String strasse) {
        this.strasse = strasse;
    }

    public double getKklimit() {
        return kklimit;
    }

    public void setKklimit(double kklimit) {
        this.kklimit = kklimit;
    }
}
