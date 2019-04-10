package DB2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import oracle.jdbc.pool.*;

public class SQLConnection {

    public static Connection connect() throws SQLException {

        String treiber;
        OracleDataSource odc = new OracleDataSource();
        treiber ="oracle.jdbc.driver.OracleDriver";
        Connection dbConnect= null;
        //Treiber laden
        try{
            Class.forName(treiber);
        }catch(Exception e){
            System.out.println("fehler beim Laden des Treibers!");
        }
        try{
            odc.setURL("jdbc:oracle:thin:dbprak16/dbprak16@schelling.nt.fh-koeln.de:1521:xe");
            dbConnect = odc.getConnection();
        }catch(Exception e){
            System.out.println("Fehler beim Verbindungsaufbau!");
            System.out.println(e.getMessage());
        }
        return dbConnect;
    }
}
