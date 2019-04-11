package DB2.Objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    public boolean validateArtikel(){

        // alle not null Prüfen
        if(artbez == null || mge == null || preis == 0 || kuehl == null || edat == null){
            return false;
        }

        // VALIDATE KUEHL = TK || KS || NK
            if(!    (kuehl.equalsIgnoreCase("TK") ||
                    kuehl.equalsIgnoreCase("KS") ||
                    kuehl.equalsIgnoreCase("NK"))){
                return false;
            }


        //VALIDATE PREIS decimal(7,2)
            double temp_d = preis*100;
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


        //VALIDATE DATE Format = dd-MM-yyyy
            if(!isValidDate(edat)){
                return false;
            }


        return true;
    }

    private boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

}
