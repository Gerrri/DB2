package DB2.Objects;

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

    public boolean validateKunde(){

        //VALIDATE PLZ (5 stellig)
            if(plz < 100000){
                return false;
            }

        //VALIDATE kklimit
            double temp_d = kklimit*100;
            int temp_i;

            // Nachkommastellen Check (max 2)
            if(temp_d % 100 != 0){
                return false;
            }else{
                //checke stellen <= 7 vor ","
                if(!((int)temp_d < 1000000000)){
                    return false;
                }
            }

        return true;
    }

    private int intLength (int i){
        String s = String.valueOf(i);
        return s.length();
    }

}
