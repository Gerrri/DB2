package DB2.Objects;

import java.util.ArrayList;
import java.util.List;

public class Bestellung {

    private int bstNR;
    private int kNR;
    private int status;
    private double rSUM = 0;
    private int mge;
    private List<ArtikelSQL> artList = new ArrayList<>();


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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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

    public List<ArtikelSQL> getArtList() {
        return artList;
    }

    public void addArt(ArtikelSQL artikel){
        artList.add(artikel);
        rSUM += artikel.getPreis();
    }

    public void addArt(List<ArtikelSQL> artikelLst){
        for(ArtikelSQL artikel:artikelLst){
            addArt(artikel);
        }
    }

    public boolean delArt(ArtikelSQL artikel){
        for(ArtikelSQL temp:artList){
            if(temp.getArtnr()==artikel.getArtnr()){
                return artList.remove(temp);
            }
        }
        return false;
    }

    public boolean validate(){

        // alle not null Prüfen
        if(bstNR <= 0|| kNR <= 0|| status == 0 || rSUM < 0 || mge == 0 || artList.size() < 1){
            return false;
        }

        // VALIDATE KUEHL = TK || KS || NK
        if (!(status > 0 && status <=5)){
            return false;
        }

        //VALIDATE PREIS decimal(7,2)
        double temp_d = rSUM*100;
        int temp_i;

        // Nachkommastellen Check (max 2)
        if(temp_d % 1 != 0){
            return false;
        }else{
            //checke stellen <= 7 vor ","
            if(!((int)temp_d < 1000000000)){
                return false;
            }
        }

        return true;
    }

    public void print_Bestellung(){
        System.out.println("Bestellnummer: "+ bstNR);
        System.out.println("Kundennummer : "+ kNR);
        System.out.println("Status       : "+ status);
        System.out.println("rSUM         : "+ rSUM);
        System.out.println("mge          : "+ mge);
        System.out.println("------------- Alle ArtikelSQL -------------");
        int nr=1;
        for(ArtikelSQL a: artList) {
            System.out.println("    ArtikelSQL Nr   : "+ nr);
            System.out.println("    Artikelnummer: "+ a.getArtnr());
            System.out.println("    Artikelbez   : "+ a.getArtbez());
            System.out.println("    menge        : "+ a.getMge());
            System.out.println("    preis        : "+ a.getPreis());
            System.out.println("    Kühl         : "+ a.getKuehl());
            System.out.println("    edat         : "+ a.getEdat());
        }
        System.out.println("------------- Alle ArtikelSQL -------------");
    }

}
