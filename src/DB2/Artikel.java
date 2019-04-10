package DB2;

public class Artikel {
    private int artnr;
    private String artbez;
    private String mge;
    private double preis;
    private String kuehl;
    private String edat;



    public int getArtnr() {
        return artnr;
    }

    public void setArtnr(int artnr) {
        this.artnr = artnr;
    }

    public String getArtbez() {
        return artbez;
    }

    public void setArtbez(String artbez) {
        this.artbez = artbez;
    }

    public String getMge() {
        return mge;
    }

    public void setMge(String mge) {
        this.mge = mge;
    }

    public double getPreis() {
        return preis;
    }

    public void setPreis(double preis) {
        this.preis = preis;
    }

    public String getKuehl() {
        return kuehl;
    }

    public void setKuehl(String kuehl) {
        this.kuehl = kuehl;
    }

    public String getEdat() {
        return edat;
    }

    public void setEdat(String edat) {
        this.edat = edat;
    }
}
