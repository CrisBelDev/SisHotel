/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.jsp.tagext.TryCatchFinally;
/**
 *
 * @author Incos
 */
public class VariablesConexion {
   // variables para establecer conexion con la base de datos
    public static String URL_BBDD="jdbc:postgresql://localhost:5432/SISHOTEL";
    public static String DRIVER_BBDD="org.postgresql.Driver";
    public static String USUARIO_BBDD="postgres";
    public static String PASSWORD_BBDD="12345678";
    private Connection connection;
    //constructor
    //metodos
    public void inicioConexion () throws SQLException{
        try {
            Class.forName(DRIVER_BBDD);
            connection=DriverManager.getConnection(URL_BBDD, USUARIO_BBDD, PASSWORD_BBDD);
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public void cerrarConexion (){
        System.out.println("Cerrando conexion...!!! ");
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    public void gg(){
        
    }
    // get y set

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
}
