package DB2.SQL;

import DB2.Objects.Kunde;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
}
