package DB2.SQL;

import DB2.Objects.Artikel;
import DB2.Objects.Bestellung;
import DB2.Objects.Kunde;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SQLHandler {

    private static SQLHandler instance = null;

    private Connection con;
    private Statement stmt = null;
    private ResultSet rs = null;
    private String sql;

    public static SQLHandler getSQLHandler(){
        if(instance != null){
            return instance;
        }
        return new SQLHandler();
    }

    private SQLHandler(){
        instance = this;
        try {
            con = SQLConnection.connect();
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
    }


    public boolean insert_artikel(String artbez, String mge, double preis, String kuehl, String edat){
        boolean erg = false;

        /* Beispiel insert: INSERT INTO ARTIKEL (ARTBEZ, MGE, PREIS, KUEHL, EDAT)
VALUES ('Toast', 'kg', '1', 'NK', '7-JAN-2001')*/


        sql = "INSERT INTO ARTIKEL (ARTBEZ, MGE, PREIS, KUEHL, EDAT) VALUES " +
        "('" + artbez + "', '" + mge + "', "+ preis + ",'" + kuehl + "','" +edat + "')" ;



        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            erg = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return erg;

    }


    public boolean insert(String table, String neuerWert, String values){
        boolean erg = false;
        sql = "INSERT INTO" + table + "(" + neuerWert + ")" + "VALUES" + values;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            erg = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return erg;

    }

    public Artikel select_Artikel_by_ARTNR(int ARTNR){
        Artikel a = null;
        sql = "SELECT * FROM ARTIKEL WHERE ARTNR="+ARTNR;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();

            a = new Artikel();
            a.setArtnr(Integer.parseInt(rs.getNString("ARTNR")));
            a.setArtbez(rs.getNString("ARTBEZ"));
            a.setMge(rs.getNString("MGE"));
            a.setPreis(Integer.parseInt(rs.getNString("PREIS")));
            a.setKuehl(rs.getNString("KUEHL"));
            a.setEdat(rs.getNString("EDAT"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        return a;
    }

    public Kunde select_Kunde_by_KNR(int KNR){
        Kunde k = null;
        sql = "SELECT * FROM KUNDE WHERE KNR="+KNR;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();

            k = new Kunde();
            k.setKnr(Integer.parseInt(rs.getNString("KNR")));
            k.setKname(rs.getNString("KNAME"));
            k.setPlz(Integer.parseInt(rs.getNString("PLZ")));
            k.setOrt(rs.getNString("ORT"));
            k.setStrasse(rs.getNString("STRASSE"));
            k.setKklimit(Double.parseDouble(rs.getNString("KKLIMIT")));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }



        return k;
    }

    public boolean update_kunde (Kunde k){
        boolean erg = false;
        sql = "UPDATE KUNDE SET KNAME = '" + k.getKname() +
                                "', PLZ = " + k.getPlz() +
                                ", ORT = '" + k.getOrt() +
                                "', STRASSE = '" + k.getStrasse() +
                                "', KKLIMIT = " + k.getKklimit() +
                                " WHERE KNR = " + k.getKnr();

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            erg = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return erg;

    }


    public boolean insert_bestellung(Bestellung b){
        boolean erg = false;

        int knr = b.getkNR();
        int status = b.getStatus();
        double rSUM = b.getrSUM();
        List<Artikel> ls = b.getArtList();

        //INSERT INTO BESTELLUNG2 (KNR,STATUS,RSUM,Lieferposition) VALUES (2,'3',4, Positionen(BestPos(1,'2','3',4,'5','17-05-2019',7)));
        //BestPos(artnr,'artbez','mge',preis,'kuehl','17-05-2019',anzahl)


        Artikel t;
        int anz=0;
        List<Artikel> ls_temp = ls;
        List<String> ls_lief = new ArrayList<>();

        for(Artikel a : ls_temp){

            for(Artikel as : ls){

                if(a.getArtnr() == as.getArtnr()){
                    anz ++;
                    ls_temp.remove(as);
                }
            }

            ls_lief.add("BestPos(" + a.getArtnr() + ",'" + a.getArtbez() + "','"+ a.getMge()+"',"+a.getPreis()+",'"+a.getKuehl()+"','"+a.getEdat()+"',"+ anz+")");

        }

        String BestPos = "";

        for(String s:ls_lief){
            BestPos += s +",";
        }


        String positionen = "Positionen("+BestPos+")";


        sql = "INSERT INTO BESTELLUNG2 (KNR,STATUS,RSUM,Lieferposition) VALUES " +
                "('" + knr + "', '" + status + "', "+ rSUM + ",'" + positionen;



        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            erg = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return erg;

    }


}
