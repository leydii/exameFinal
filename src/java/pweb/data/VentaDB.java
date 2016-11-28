package pweb.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import pweb.business.Libro;

import pweb.business.Venta;

public class VentaDB {
    
    public static int insert(Venta venta) 
    {
        Connection connection = null;
        PreparedStatement ps = null;        

        try 
        {
            // cargar el controlador
            Class.forName("org.sqlite.JDBC");

            // crear una conexion             
            String dbURL = "jdbc:sqlite:C:/sqlite3/LibrosDB.db";
            connection = DriverManager.getConnection(dbURL);      

            String query = "INSERT INTO " 
                         + "VENTAS (CODLIBRO, TITULAR, NUMTARJETA, TOTAL) "
                         + "VALUES (?, ?, ?, ?)";

            ps = connection.prepareStatement(query);                        
            ps.setString(1, venta.getCodigoLibro());
            ps.setString(2, venta.getTitularTarjeta());
            ps.setString(3, venta.getNumeroTarjeta());
            ps.setDouble(4, venta.getTotalVenta());

            return ps.executeUpdate();            
        } 
        catch (ClassNotFoundException e) {
            System.out.println(e);
            return 0;
        }         
        catch (SQLException e) {
            System.out.println(e);
            return 0;
        } 
        finally {
            DBUtil.closePreparedStatement(ps);
            DBUtil.closeConnection(connection);            
        }
    }    
    
}
    
    

