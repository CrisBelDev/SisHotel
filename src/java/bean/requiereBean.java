/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.fecha;
import conexion.VariablesConexion;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
public class requiereBean {
    private Connection connection;
    private PreparedStatement insertRequiere;
    private PreparedStatement UpdateRequiere;
    private requiere requiereModificar;
    private PreparedStatement eliminarRequiere;
    private VariablesConexion variable;

    public requiereBean()  throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion");
    }
    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }
    public String insertarRequiere(HttpServletRequest request) {
        
        String salida = "";
        if (connection == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into requiere ");
                query.append(" values(nextval('sec_requiere'), ?,?,?,?) ");
                if (insertRequiere == null) {
                    insertRequiere = connection.prepareStatement(query.toString());
                }
                //obteniendo los datos de mi formulario jsp
                String idHabitacion = request.getParameter("idHabitacion");
                String idServicio = request.getParameter("idServicio");
                String cantidad = request.getParameter("cantidad");
                //fecha actual del sistema
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                fecha fecha = new fecha();
                String actual = fecha.getFechaActual();
                Date f_inicio = fecha.deStringToDate(actual);
                
                
                //casteando el stock para la BD
                int id_habitacion = Integer.parseInt(idHabitacion);
                int id_servcio=Integer.parseInt(idServicio);
                int integerCantidad=Integer.parseInt(cantidad);
                //pasando los parametros a la consulta
                //realzando la codifcacion para las fcha

                insertRequiere.setInt(1, id_habitacion);
                insertRequiere.setInt(2, id_servcio);
                insertRequiere.setDate(3, new java.sql.Date(f_inicio.getTime()));
                insertRequiere.setInt(4, integerCantidad);
                //ejecutando consulta
                int registros = insertRequiere.executeUpdate();
                System.out.println("Insercion correcta");
                salida = "Registro realizado correctamente";

            } catch (SQLException e) {
                System.out.println("Error al ejecutar el insert");
                e.printStackTrace();
                salida = "Error al ejecutar la consulta" + e.getMessage();
            }
        } else {
            salida = "error al ejcucar insercion requiere ";
        }
        return salida;
    }
    

    public String listarRequiere() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from requiere ");
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
                salidaTabla.append(resultado.getInt(2));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(3));
                salidaTabla.append(" </td> ");



                
                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarRequiere.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarRequiere.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public requiere buscarRequiere(String codRequiere) {
        requiereModificar = new requiere();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * ");
        query.append(" from requiere ");
        query.append(" where id_requiere=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codRequiere));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                requiereModificar.setId_requiere(resultado.getInt(1));
                requiereModificar.setId_habitacion(resultado.getInt(2));
                requiereModificar.setId_servicio(resultado.getInt(3));
                
            }
        } catch (SQLException e) {
            System.out.println("Error de coneccion");
            e.printStackTrace();
        }
        return requiereModificar;
    }

    public String modificarRequiere(HttpServletRequest request, String codRequiere) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update requiere ");
                query.append(" set id_habitacion=?, id_servicio=? ");
                query.append(" where id_requiere=? ");
                if (UpdateRequiere == null) {
                    UpdateRequiere = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String idHab = request.getParameter("idHabitacion");
                String idServ = request.getParameter("idServicio");


                int idHabitacion = Integer.parseInt(idHab);
                int idServicio = Integer.parseInt(idServ);
                //actualizando el objeto piezaModificar
                requiereModificar.setId_requiere(Integer.parseInt(codRequiere));
                requiereModificar.setId_habitacion(idHabitacion);
                requiereModificar.setId_servicio(idServicio);

                
                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                UpdateRequiere.setInt(1, idHabitacion);
                UpdateRequiere.setInt(2, idServicio);
                UpdateRequiere.setInt(5, Integer.parseInt(codRequiere == null ? "0" : codRequiere));
                //executando la consulta
                int registros = UpdateRequiere.executeUpdate();
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
    public String eliminarRequiere(HttpServletRequest request,String codRequiere){
        String salida="";
        if(salida==null ){
            return "";
        }
        if(connection!=null && codRequiere!=null && codRequiere.length()>0){
            try {
                StringBuilder query=new StringBuilder();
                query.append(" delete from requiere ");
                query.append(" where id_requiere=? ");
                eliminarRequiere=connection.prepareStatement(query.toString());
                eliminarRequiere.setInt(1, Integer.parseInt(codRequiere));
                int nroRegistros=eliminarRequiere.executeUpdate();
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
    public String listarHabitacionesOcupadas() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select ocupa.id_habitacion from ocupa ");
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
                salidaTabla.append(resultado.getInt(2));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(3));
                salidaTabla.append(" </td> ");



                
                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarRequiere.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarRequiere.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    
}
