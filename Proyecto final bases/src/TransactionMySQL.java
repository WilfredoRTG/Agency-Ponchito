import javax.swing.*;
import java.sql.*;
import java.io.*;

public class TransactionMySQL extends JFrame{
    static Connection conn = null;
    static Statement stmt = null;
    static BufferedReader in = null;
    static final String URL = "jdbc:mysql://localhost/";
    static final String BD = "ProyectoF";		// especificar: el nombre de la BD,
    static final String USER = "root";		// el nombre de usuario
    static final String PASSWD = "password";// el password del usuario


    public TransactionMySQL() throws Exception {

        conn = DriverManager.getConnection( URL+BD, USER, PASSWD );
        conn.setAutoCommit( false );
        stmt = conn.createStatement();
        in = new BufferedReader( new InputStreamReader(System.in) );

    }

    private void dumpResultSet( ResultSet rset ) throws SQLException {

        ResultSetMetaData rsetmd = rset.getMetaData();
        int i = rsetmd.getColumnCount();

        while( rset.next() ) {

            for( int j = 1; j <= i; j++ ) {
                System.out.print( rset.getString(j) + "\t" );
            }
        }
    }

    public void query(String statement) throws SQLException {

        ResultSet rset = TransactionMySQL.stmt.executeQuery( statement );
        dumpResultSet( rset );
        rset.close();
    }

    private void close() throws SQLException {

        stmt.close();
        conn.close();

    }

    public static void main( String arg[] ) throws SQLException, Exception {

        if( arg.length != 0 ) {
            System.err.println( "Use: java TransactionMySQL" );
            System.exit( 1 );
        }

        TransactionMySQL transaction = new TransactionMySQL();
        transaction.close();
    }

}
