/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import conexion.VariablesConexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author kings
 */
public class servicioBean {
    private Connection connection;
    private PreparedStatement insertServicio;
    private PreparedStatement updateServicio;
    private servicio servicioModificar;
    private PreparedStatement eliminarServicio;
    private VariablesConexion variable;

    public servicioBean()  throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion");
    }
    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }
    public String insertarServicio(HttpServletRequest request) {
        
        String salida = "";
        if (connection == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into servicio ");
                query.append(" values(nextval('sec_serv'), ?,?,?) ");
                if (insertServicio == null) {
                    insertServicio = connection.prepareStatement(query.toString());
                }
                //obteniendo los datos de mi formulario jsp
                String nombre = request.getParameter("nom_servicio");
                String costo = request.getParameter("costo_servicio");
                String descripcion = request.getParameter("desc_servicio");
                //casteando el stock para la BD
                int cost = Integer.parseInt(costo);
                //pasando los parametros a la consulta
                insertServicio.setString(1, nombre);
                insertServicio.setInt(2, cost);
                insertServicio.setString(3, descripcion);
                
                //ejecutando consulta
                int registros = insertServicio.executeUpdate();
                System.out.println("Insercion correcta");
                salida = "Registro realizado correctamente";

            } catch (SQLException e) {
                System.out.println("Error al ejecutar el insert");
                e.printStackTrace();
                salida = "Error al ejecutar la consulta" + e.getMessage();
            }
        } else {
            salida = "error al ejcucar insercion servicio ";
        }
        return salida;
    }

    public String listarServicio() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from servicio ");
        try {
            Statement st = connection.createStatement();
            //la consulta retorna en una lista de tipo Resulset
            ResultSet resultado = st.executeQuery(query.toString());
            System.out.println(resultado);
            while (resultado.next()) {
                salidaTabla.append(" <tr> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(2));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(3));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append(" </td> ");


                
                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarServicio.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarServicio.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    public String listarServicioSelect() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select id_servicio,nom_servicio from servicio ");
        try {
            Statement st = connection.createStatement();
            ResultSet resultado = st.executeQuery(query.toString());
            while (resultado.next()) {
                salidaTabla.append("<option value=");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append(">");
                salidaTabla.append(resultado.getString(2)+" ");
                
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            System.out.println("--ERROR EN listarHabitacionSelect--" + e.getMessage());
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public servicio buscarServicio(String codPieza) {
        servicioModificar = new servicio();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * ");
        query.append(" from servicio ");
        query.append(" where id_servicio=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codPieza));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                servicioModificar.setId_servicio(resultado.getInt(1));
                servicioModificar.setNom_servicio(resultado.getString(2));
                servicioModificar.setCosto_servicio(resultado.getInt(3));
                servicioModificar.setDescripcion(resultado.getString(4));
                
            }
        } catch (SQLException e) {
            System.out.println("Error de coneccion");
            e.printStackTrace();
        }
        return servicioModificar;
    }

    public String modificarServicio(HttpServletRequest request, String codServ) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update servicio ");
                query.append(" set nom_servicio=?, costo_servicio=?, desc_servicio=? ");
                query.append(" where id_servicio=? ");
                if (updateServicio == null) {
                    updateServicio = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String nombre = request.getParameter("nom_servicio");
                String costo = request.getParameter("costo_servicio");
                String descripcion = request.getParameter("desc_servicio");

                int cost = Integer.parseInt(costo);
                //actualizando el objeto piezaModificar
                servicioModificar.setNom_servicio(nombre);
                servicioModificar.setCosto_servicio(cost);
                servicioModificar.setDescripcion(descripcion);
                
                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateServicio.setString(1, nombre);
                updateServicio.setInt(2, cost);
                updateServicio.setString(3, descripcion);
                updateServicio.setInt(4, Integer.parseInt(codServ == null ? "0" : codServ));
                //executando la consulta
                int registros = updateServicio.executeUpdate();
                System.out.println("modificacion realizado");
                salida = "modificacion correcta";

            } catch (SQLException e) {
                System.out.println("eeror al ejecutar el udpdate...");
                e.printStackTrace();
                salida = "eeror al ejecutar el update: " + e.getMessage();
            }
        }
        return salida;
    }
    public String eliminarServicio(HttpServletRequest request,String codAdmin){
        String salida="";
        if(salida==null ){
            return "";
        }
        if(connection!=null && codAdmin!=null && codAdmin.length()>0){
            try {
                StringBuilder query=new StringBuilder();
                query.append(" delete from servicio ");
                query.append(" where id_servicio=? ");
                eliminarServicio=connection.prepareStatement(query.toString());
                eliminarServicio.setInt(1, Integer.parseInt(codAdmin));
                int nroRegistros=eliminarServicio.executeUpdate();
                if(nroRegistros==1){
                    salida="Registro eleminado correctamente";
                }else{
                    salida="error al eliminar";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                salida="error al eliminar: "+e.getMessage();
            }
        }
        return  salida;
    }

    public servicio getServicioModificar() {
        return servicioModificar;
    }

    public void setServicioModificar(servicio servicioModificar) {
        this.servicioModificar = servicioModificar;
    }
    
    
}
