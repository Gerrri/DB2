package DB2.CouchDB;

import java.util.ArrayList;
import java.util.List;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({ "id", "revision" })
public class DBArtikel16 {
    @JsonProperty("_id")
    private String id;
    @JsonProperty("_rev")
    private String revision;
    private int artnr;
    private String artbez;
    private String mge;
    private double preis;

    private List<DBBPos> bPos;

    public DBArtikel16() {
        this.artbez = "";
        this.mge = "";
        bPos = new ArrayList<DBBPos>();
    }

    public DBArtikel16(int artnr, String artbez, String mge, double preis) {
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

    public List<DBBPos> getbPos() {
        return bPos;
    }

    public void setbPos(List<DBBPos> bPos) {
        this.bPos = bPos;
    }
}
