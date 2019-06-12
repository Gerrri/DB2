package DB2.Objects;

public class CouchArtikel {
    private String id;
    private String revision;
    private int artnr;
    private String artbez;
    private String mge;
    private double preis;
    private String kuehl;
    private String edat;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

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
