package konekasi;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * 
 * @author herudi
 */

public class koneksi {

    private static Connection conn;
    private static final String url = "jdbc:jtds:sqlserver://HERUDI-PC:1433/baju";
    private static final String user = "sa";
    private static final String pass = "kabinet90";

    public static Connection connect() throws SQLException{
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException cnfe){
            System.err.println("Error: "+cnfe.getMessage());
        }

        conn = DriverManager.getConnection(url,user,pass);
        return conn;
    }
    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        if(conn !=null && !conn.isClosed())
            return conn;
        connect();
        return conn;

    }
}