package DB2.Objects;

import java.util.ArrayList;
import java.util.List;

public class Bestellung {

    private int bstNR;
    private int kNR;
    private String status;
    private double rSUM = 0;
    private int mge;
    private List<Artikel> artList = new ArrayList<>();


    public int getBstNR() {
        return bstNR;
    }

    public void setBstNR(int bstNR) {
        this.bstNR = bstNR;
    }

    public int getkNR() {
        return kNR;
    }

    public void setkNR(int kNR) {
        this.kNR = kNR;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        status = status;
    }

    public double getrSUM() {
        return rSUM;
    }

    public int getMge() {
        return mge;
    }

    public void setMge(int mge) {
        this.mge = mge;
    }

    public List<Artikel> getArtList() {
        return artList;
    }

    public void addArt(Artikel artikel){
        artList.add(artikel);
        rSUM += artikel.getPreis();
    }

    public void addArt(List<Artikel> artikelLst){
        for(Artikel artikel:artikelLst){
            artList.add(artikel);
        }
    }

    public boolean delArt(Artikel artikel){
        for(Artikel temp:artList){
            if(temp.getArtnr()==artikel.getArtnr()){
                return artList.remove(temp);
            }
        }
        return false;
    }
}
