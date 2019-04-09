package DB2;

import java.sql.Connection;
import oracle.jdbc.pool.*;
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
}
