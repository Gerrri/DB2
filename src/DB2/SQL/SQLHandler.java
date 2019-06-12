package DB2.SQL;

import DB2.Objects.Artikel;
import DB2.Objects.Bestellung;
import DB2.Objects.Kunde;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        String datum;
        String[] aendern;
        aendern = edat.split(" ");
        aendern = aendern[0].split("-");
        datum = aendern[2] + "-" + aendern[1] + "-" + aendern[0];

        sql = "INSERT INTO ARTIKEL (ARTBEZ, MGE, PREIS, KUEHL, EDAT) VALUES " +
        "('" + artbez + "', '" + mge + "', "+ preis + ",'" + kuehl + "','" +datum + "')" ;



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
            a.setPreis(Double.parseDouble(rs.getNString("PREIS")));
            a.setKuehl(rs.getNString("KUEHL"));
            a.setEdat(rs.getNString("EDAT"));

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return a;
    }

    public List<Artikel> select_alle_Artikel(){

        List<Artikel> al = new ArrayList<Artikel>();
        Artikel a = null;
        sql = "SELECT * FROM ARTIKEL";

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            rs.next();
            String s;
            double d;
            String[] arr = new String[2];


            while(rs.next()){
                a = new Artikel();
                a.setArtnr(Integer.parseInt(rs.getNString("ARTNR")));
                a.setArtbez(rs.getNString("ARTBEZ"));
                a.setMge(rs.getNString("MGE"));

                s = rs.getNString("PREIS");

                arr = s.split(",");


                d = Double.parseDouble(arr[0])+Double.parseDouble(arr[1])/100;


                a.setPreis(d);
                a.setKuehl(rs.getNString("KUEHL"));
                a.setEdat(rs.getNString("EDAT"));

                al.add(a);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return al;
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
        List<Artikel> ls_temp = new ArrayList<>();
        List<String> ls_lief = new ArrayList<>();


        Map<String, Integer> vergl = new HashMap<>();


        for(Artikel a : ls){

                if(!vergl.containsKey(a.getArtbez())) {
                    vergl.put(a.getArtbez(), 1);
                    ls_temp.add(a);
                }else{
                    int anzahl = vergl.get(a.getArtbez());
                    anzahl ++;
                    vergl.put(a.getArtbez(),anzahl);
                }


            //ls_lief.add("BestPos(" + a.getArtnr() + ",'" + a.getArtbez() + "','"+ a.getMge()+"',"+a.getPreis()+",'"+a.getKuehl()+"','"+a.getEdat()+"',"+ anz+")");
        }

        String datum;
        String[] aendern;
        for(Artikel a : ls_temp){
            aendern = a.getEdat().split(" ");
            aendern = aendern[0].split("-");
            datum = aendern[2] + "-" + aendern[1] + "-" + aendern[0];
            ls_lief.add("BestPos(" + a.getArtnr() + ",'" + a.getArtbez() + "','"+ a.getMge()+"',"+a.getPreis()+",'"+a.getKuehl()+"','"+datum+"',"+ vergl.get(a.getArtbez()) +")");
        }

        String BestPos = "";

        boolean anfang = true;
        for(String s:ls_lief){
            if(!anfang){
                BestPos += ",";
            }
            BestPos += s;
            anfang = false;
        }


        String positionen = "Positionen("+BestPos+")";


        sql = "INSERT INTO BESTELLUNG2 (KNR,STATUS,RSUM,Lieferposition) VALUES " +
                "(" + knr + ", '" + status + "', "+ rSUM + ", " + positionen + ")";



        try {
            stmt = con.createStatement();
            stmt.executeUpdate(sql);
            erg = true;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return erg;

    }

    public void selectBestellung(int start, int end){
        List<Bestellung> a = new ArrayList<>();
        sql = "SELECT * FROM BESTELLUNG2 WHERE BSTNR>=" + start + " AND BSTNR<=" + end;

        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while(rs.next()){
                Bestellung temp = new Bestellung();
                temp.setkNR(Integer.parseInt(rs.getNString("kNR")));
                temp.setStatus(Integer.parseInt(rs.getNString("Status")));
                temp.setBstNR(Integer.parseInt(rs.getNString("BSTNR")));
                Array array = rs.getArray("LIEFERPOSITION");
                Object[] address_list = (Object[]) array.getArray();
                for (int i = 0; i < address_list.length; i++) {
                    Struct address = (Struct) address_list[i];
                    Object[] attrib = address.getAttributes();
                    Artikel artikel = new Artikel();
                    BigDecimal cast = (BigDecimal) attrib[0];
                    artikel.setArtnr(cast.intValue());
                    artikel.setArtbez((String) attrib[1]);
                    artikel.setMge((String) attrib[2]);
                    cast = (BigDecimal) attrib[3];
                    artikel.setPreis(cast.doubleValue());
                    Timestamp time = (Timestamp) attrib[5];
                    artikel.setKuehl((String) attrib[4]);
                    artikel.setEdat(time.toString());
                    temp.addArt(artikel);
                }
                a.add(temp);
            }
            for(Bestellung bestellung : a){
                bestellung.print_Bestellung();
            }


        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}
