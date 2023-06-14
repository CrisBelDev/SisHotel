/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.fecha;
import clases.fechaFormat;
import com.sun.istack.internal.logging.Logger;
import conexion.VariablesConexion;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
public class reservacionBean {

    private Connection connection;
    private PreparedStatement insertReservacion;
    private PreparedStatement updateReservacion;
    private reservacion reservacionModificar;
    private reservacion reservacion;
    private PreparedStatement eliminarReservacion;
    private VariablesConexion variable;

    public reservacionBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    public String insertarReservacion(int id,int costo,int idHabitacion,String fechaSal) {

        String salida = "";
        if (connection == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into reservacion ");
                query.append(" values(nextval('sec_reserv'),?,?,?,?) ");
                if (insertReservacion == null) {
                    insertReservacion = connection.prepareStatement(query.toString());
                }
                int idCliente = id;
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                fecha fecha = new fecha();
                String actual = fecha.getFechaActual();
                Date f_inicio = fecha.deStringToDate(actual);
                Date f_salida = fecha.deStringToDate(fechaSal);
                int cost = costo;
                
                
                fechaFormat fec = new fechaFormat();
                Date fs = fec.convertirFecha(fechaSal);

                insertReservacion.setInt(1, idCliente);
                insertReservacion.setInt(2, cost);
                insertReservacion.setDate(3, new java.sql.Date(f_inicio.getTime()));
                insertReservacion.setDate(4, new java.sql.Date(fs.getTime()));

                //ejecutando consulta
                int registros = insertReservacion.executeUpdate();
                System.out.println("Insercion correcta");
                salida = "Registro realizado correctamente";
                try {
                    StringBuilder query1=new StringBuilder();
                    query1.append(" select max(id_reservacion) from reservacion ");
                    Statement st=connection.createStatement();
                    ResultSet respuesta=st.executeQuery(query1.toString());
                    if(respuesta.next()){
                        int id_reservacion=respuesta.getInt(1);
                        ocupaBean ocupar=new ocupaBean();
                        ocupar.insertarOcupa(id_reservacion,idHabitacion,fechaSal);
                    }
                    
                } catch (SQLException e) {
                    System.out.println("error en reservacion "+e.getMessage());
                }

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

    public String listarReservacion() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from reservacion ");
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

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDate(4));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDate(5));
                salidaTabla.append(" </td> ");

                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarReservacion.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarReservacion.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public reservacion buscarReservacion(String codReserv) {
        reservacionModificar = new reservacion();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * ");
        query.append(" from reservacion ");
        query.append(" where id_reservacion=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codReserv));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                reservacionModificar.setId_reservacion(resultado.getInt(1));
                reservacionModificar.setId_cliente(resultado.getInt(2));
                reservacionModificar.setCosto(resultado.getInt(3));
                reservacionModificar.setFecha_inicio(resultado.getDate(4));
                reservacionModificar.setFecha_salida(resultado.getDate(5));

            }
        } catch (SQLException e) {
            System.out.println("Error de coneccion");
            e.printStackTrace();
        }
        return reservacionModificar;
    }

    public String modificarReservacion(HttpServletRequest request, String codReserv) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update reservacion ");
                query.append(" set id_cliente=?, costo=?, f_inicio=?,f_salida=? ");
                query.append(" where id_reservacion=? ");
                if (updateReservacion == null) {
                    updateReservacion = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String idCliente = request.getParameter("idCliente");
                String costo = request.getParameter("costo");
                String fechaInicio = request.getParameter("fechaInicio");
                String fechaSalida = request.getParameter("fechaSalida");
                //realizo la conversion de fecha
                Date fe = convertirFecha(fechaInicio);
                Date fs = convertirFecha(fechaSalida);

                int cost = Integer.parseInt(costo);
                //actualizando el objeto piezaModificar
                reservacionModificar.setId_cliente(Integer.parseInt(idCliente));
                reservacionModificar.setCosto(cost);
                reservacionModificar.setFecha_inicio(new java.sql.Date(fe.getTime()));
                reservacionModificar.setFecha_salida(new java.sql.Date(fs.getTime()));

                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateReservacion.setInt(1, Integer.parseInt(idCliente));
                updateReservacion.setInt(2, cost);
                updateReservacion.setDate(3, new java.sql.Date(fe.getTime()));
                updateReservacion.setDate(4, new java.sql.Date(fs.getTime()));
                updateReservacion.setInt(5, Integer.parseInt(codReserv == null ? "0" : codReserv));
                //executando la consulta
                int registros = updateReservacion.executeUpdate();
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

    public String eliminarReservacion(HttpServletRequest request, String codAdmin) {
        String salida = "";
        if (salida == null) {
            return "";
        }
        if (connection != null && codAdmin != null && codAdmin.length() > 0) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" delete from reservacion ");
                query.append(" where id_reservacion=? ");
                eliminarReservacion = connection.prepareStatement(query.toString());
                eliminarReservacion.setInt(1, Integer.parseInt(codAdmin));
                int nroRegistros = eliminarReservacion.executeUpdate();
                if (nroRegistros == 1) {
                    salida = "Registro eleminado correctamente";
                } else {
                    salida = "error al eliminar";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                salida = "error al eliminar: " + e.getMessage();
            }
        }
        return salida;
    }

    public Date convertirFecha(String fechaString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {

            //revisar la fecha
            fecha = sdf.parse(fechaString);
            System.out.println("fecha formateada: " + fecha);
        } catch (ParseException e) {
            System.out.println("error em las fecha resvisar");
        }
        return fecha;
    }

    public reservacion getReservacionModificar() {
        return reservacionModificar;
    }

    public void setReservacionModificar(reservacion reservacionModificar) {
        this.reservacionModificar = reservacionModificar;
    }

}
