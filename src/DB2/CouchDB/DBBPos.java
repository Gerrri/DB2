package DB2.CouchDB;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class DBBPos {
    private int bestnr;
    private int menge;

    public DBBPos() {
    }

    public DBBPos(int bestnr, int menge) {
        this.bestnr = bestnr;
        this.menge = menge;
    }

    public int getBestnr() {
        return bestnr;
    }

    public void setBestnr(int bestnr) {
        this.bestnr = bestnr;
    }

    public int getMenge() {
        return menge;
    }

    public void setMenge(int menge) {
        this.menge = menge;
    }

}