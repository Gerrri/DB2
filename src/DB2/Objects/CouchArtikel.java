package DB2.Objects;

import DB2.Objects.BPos;

import java.util.ArrayList;
import java.util.List;

public class CouchArtikel {
    private String id;
    private String revision;
    private int artnr;
    private String artbez;
    private String mge;
    private double preis;

    private List<BPos> bPos;

    public CouchArtikel() {
        this.artbez = "";
        this.mge = "";
        bPos = new ArrayList<BPos>();
    }

    public CouchArtikel(int artnr, String artbez, String mge, double preis) {
        this.artnr = artnr;
        this.artbez = artbez;
        this.mge = mge;
        this.preis = preis;
    }


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

    public List<BPos> getbPos() {
        return bPos;
    }

    public void setbPos(List<BPos> bPos) {
        this.bPos = bPos;
    }
}
