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
import java.util.LinkedList;
import java.util.List;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author HP
 */
public class habitacionBean {

    private Connection connection;
    private PreparedStatement insertHabitacion;
    private PreparedStatement updateHabitacion;
    private PreparedStatement updateHabitacionReserva;
    private PreparedStatement updateHabitacionLibre;
    private habitacion habitacionModificar;
    private habitacion habitacion;
    private PreparedStatement eliminarHabitacion;
    private VariablesConexion variable;

    public habitacionBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion de habitacion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    public String insertarHabitacion(HttpServletRequest request) {
        String salida = "";
        if (connection == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into habitacion ");
                query.append(" values(nextval('sec_habitacion'), ?,?,?,?,?) ");
                if (insertHabitacion == null) {
                    insertHabitacion = connection.prepareStatement(query.toString());
                }
                //obteniendo los datos de mi formulario jsp
                String nombreHabitacion = request.getParameter("nomHabitacion");
                String tipo = request.getParameter("tipo");
                String costo = request.getParameter("costoHabitacion");
                String estado = request.getParameter("estado");
                String descripcion = request.getParameter("descripcion");
                //casteando el stock para la BD
                int cost = Integer.parseInt(costo);
                //pasando los parametros a la consulta
                insertHabitacion.setString(1, nombreHabitacion);
                insertHabitacion.setString(2, tipo);
                insertHabitacion.setInt(3, cost);
                insertHabitacion.setString(4, estado);
                insertHabitacion.setString(5, descripcion);

                //ejecutando consulta
                int registros = insertHabitacion.executeUpdate();
                System.out.println("Insercion correcta de habitacion");
                salida = "Registro realizado correctamente";

            } catch (SQLException e) {
                System.out.println("Error al ejecutar el insert");
                e.printStackTrace();
                salida = "Error al ejecutar la consulta" + e.getMessage();
            }
        } else {
            salida = "error al ejcucar insercion habitacion ";
        }
        return salida;
    }

    public String listarHabitacion() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from habitacion where estado='libre' ");
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
                salidaTabla.append(resultado.getInt(4));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(6));
                salidaTabla.append(" </td> ");

                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarHabitacion.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarHabitacion.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    
    public String listarHabitacionBuscada(String tipoHabitacion) {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from habitacion where tipo=? and estado='libre'  ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setString(1,tipoHabitacion);
            ResultSet resultado = st.executeQuery();
            
            
            System.out.println(resultado);
            if (resultado.next()) {

                
                salidaTabla.append("<form method=\"post\">\n" +
"                        <div class=\"row\">\n" +
"                            <div class=\"col-12\">\n" +
"                                <div class=\"form-group row\">\n" +
"                                    <div class=\"col-4 mb-3\">\n" +
"                                        <input type=\"hidden\" class=\"form-control\" value='"+resultado.getInt(1)+"' name=\"habitacion\" >\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"nombre..\" name=\"nomclie\" id=\"nomclie\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-4 mb-3\">\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"paterno..\" name=\"patclie\" id=\"patclie\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-4  mb-3\">\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"materno..\" name=\"matclie\" id=\"matclie\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-12  mb-3\">\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"telefono..\" name=\"telfclie\" id=\"telfclie\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-12  mb-3\">\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"email..\" name=\"emailclie\" id=\"emailclie\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-12  mb-3\">\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"dni..\" name=\"dniclie\" id=\"dniclie\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-12 col-md-auto mb-3\">\n" +
"                                        <label for=\"costo\">costo</label>\n" +
"                                        <input type=\"text\" class=\"form-control\" placeholder=\"costo..\" name=\"costo\" id=\"costo\">\n" +
"                                    </div>\n" +
"                                    <div class=\"col-12 col-md-auto mb-3\">\n" +
"                                        <label for=\"costo\">fecha de Finalizacion</label>\n" +
"                                        <input type=\"date\" class=\"form-control\" name=\"fechaSalidaHabitacion\">\n" +
"                                    </div>\n" +
"                                    <input class=\"btn btn-primary btn-block\" type=\"submit\" value=\"Guardar\" name=\"guardar\">\n" +
"                                </div> \n" +
"                            </div>\n" +
"                            \n" +"</div>\n" +
"                    </form>  ");

            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public String listarHabitacionSelect() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select id_habitacion,nom_habitacion,costo_habitacion from habitacion where estado='libre' ");
        try {
            Statement st = connection.createStatement();
            ResultSet resultado = st.executeQuery(query.toString());
            while (resultado.next()) {
                salidaTabla.append("<option value=");
                salidaTabla.append(resultado.getInt(1));
                salidaTabla.append(">");
                salidaTabla.append(resultado.getString(2)+" ");
                salidaTabla.append(".    *"+resultado.getString(3)+"bs");
                salidaTabla.append("</option>");
            }
        } catch (SQLException e) {
            System.out.println("--ERROR EN listarHabitacionSelect--" + e.getMessage());
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }
    public String listarHabitacionSelectOcupado() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select id_habitacion,nom_habitacion,costo_habitacion from habitacion where estado='ocupado' ");
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

    public habitacion buscarHabitacion(String codHab) {
        habitacionModificar = new habitacion();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * ");
        query.append(" from habitacion ");
        query.append(" where id_habitacion=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codHab));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                habitacionModificar.setId_habitacion(resultado.getInt(1));
                habitacionModificar.setNom_habitacion(resultado.getString(2));
                habitacionModificar.setTipo(resultado.getString(3));
                habitacionModificar.setCosto(resultado.getInt(4));
                habitacionModificar.setEstado(resultado.getString(5));
                habitacionModificar.setDescripcion(resultado.getString(6));

            }
        } catch (SQLException e) {
            System.out.println("Error de coneccio en habitacionn");
            e.printStackTrace();
        }
        return habitacionModificar;
    }

    public String modificarHabitacion(HttpServletRequest request, String codHab) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update habitacion ");
                query.append(" set nom_habitacion=?, tipo=?, costo_habitacion=?,estado=?,descripcion=? ");
                query.append(" where id_habitacion=? ");
                if (updateHabitacion == null) {
                    updateHabitacion = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String nombre = request.getParameter("nomHab");
                String tipo = request.getParameter("tipoHab");
                String costo = request.getParameter("costoHab");
                String estado = request.getParameter("estadoHab");
                String descripcion = request.getParameter("descripcionHab");

                float cost = Float.parseFloat(costo);
                //actualizando el objeto piezaModificar
                habitacionModificar.setNom_habitacion(nombre);
                habitacionModificar.setTipo(tipo);
                habitacionModificar.setCosto(cost);
                habitacionModificar.setEstado(estado);
                habitacionModificar.setDescripcion(descripcion);

                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateHabitacion.setString(1, nombre);
                updateHabitacion.setString(2, tipo);
                updateHabitacion.setFloat(3, cost);
                updateHabitacion.setString(4, estado);
                updateHabitacion.setString(5, descripcion);

                updateHabitacion.setInt(6, Integer.parseInt(codHab == null ? "0" : codHab));
                //executando la consulta
                int registros = updateHabitacion.executeUpdate();
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
    
    public String modificarHabitacionLibre(HttpServletRequest request, String codHab) {
        String salida = "";
        String nombreCompara=request.getParameter("nom");
        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                StringBuilder query1 = new StringBuilder();
                StringBuilder query2 = new StringBuilder();
                query.append(" update habitacion ");
                query.append(" set estado='libre' ");
                query.append(" where nom_habitacion=? ");
                
                query1.append(" update reservacion set estado='libre' where id_reservacion=(select id_reservacion from reservacion join cliente on (reservacion.id_cliente=cliente.id_cliente) where (cliente.nom_cliente ||' '||cliente.pat_cliente ||' '||cliente.mat_cliente)=?) ");
                
                query2.append(" delete from requiere where id_habitacion=(select id_habitacion from habitacion where nom_habitacion=?) ");
                if (updateHabitacionLibre == null) {
                    updateHabitacionLibre = connection.prepareStatement(query.toString());
                }
                if(updateHabitacionReserva==null){
                    updateHabitacionReserva=connection.prepareStatement(query1.toString());
                }
                PreparedStatement st=connection.prepareStatement(query2.toString());
                st.setString(1, codHab);
                int ok=st.executeUpdate();
                //rescatando datos del formulario jsp
                String nombre = codHab;

                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateHabitacionLibre.setString(1, nombre);
                
                updateHabitacionReserva.setString(1, nombreCompara);
              
                //executando la consulta
                int registros = updateHabitacionLibre.executeUpdate();
                int registros2=updateHabitacionReserva.executeUpdate();
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
    
    public String eliminarHabitacion(HttpServletRequest request, String codHab) {
        String salida = "";
        if (salida == null) {
            return "";
        }
        if (connection != null && codHab != null && codHab.length() > 0) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" delete from habitacion ");
                query.append(" where id_habitacion=? ");
                eliminarHabitacion = connection.prepareStatement(query.toString());
                eliminarHabitacion.setInt(1, Integer.parseInt(codHab));
                int nroRegistros = eliminarHabitacion.executeUpdate();
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

    public habitacion getHabitacionModificar() {
        return habitacionModificar;
    }

    public void setHabitacionModificar(habitacion habitacionModificar) {
        this.habitacionModificar = habitacionModificar;
    }

}
