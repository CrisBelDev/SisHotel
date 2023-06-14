/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import clases.Encripted2561;
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
public class administradorBean {

    private Connection connection;
    private PreparedStatement insertCliente;
    private PreparedStatement updateCliente;
    private administrador administradorModificar;
    private PreparedStatement eliminarCliente;
    private VariablesConexion variable;

    public administradorBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    public String insertarCliente(HttpServletRequest request) {
        Encripted2561 obj = new Encripted2561();
        String salida = "";
        if (connection == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into administrador ");
                query.append(" values(nextval('sec_admin'), ?,?,?,?,?,?,?,?) ");
                if (insertCliente == null) {
                    insertCliente = connection.prepareStatement(query.toString());
                }
                //obteniendo los datos de mi formulario jsp
                String nombre = request.getParameter("nombre");
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String telefono = request.getParameter("telefono");
                String cargo = request.getParameter("cargo");
                String dni = request.getParameter("dni");
                String expedido = request.getParameter("expedido");
                //casteando el stock para la BD
                int telf = Integer.parseInt(telefono);
                //pasando los parametros a la consulta
                insertCliente.setString(1, nombre);
                insertCliente.setString(2, paterno);
                insertCliente.setString(3, materno);
                insertCliente.setInt(4, telf);
                insertCliente.setString(5, cargo);
                insertCliente.setString(6, dni);

                String codigo = nombre.charAt(0) + "" + paterno.charAt(0) + "" + materno.charAt(0) + "" + dni + "";
                System.out.println("codigo de usario: " + codigo);

                //llamando al metodo  de sha256 para incriptaar
                //String ced=String.valueOf(codigo);
                String pwd = codigo;
                insertCliente.setString(7, pwd);
                insertCliente.setString(8, expedido);
                //ejecutando consulta
                int registros = insertCliente.executeUpdate();
                System.out.println("Insercion correcta");
                //salida = "Registro realizado correctamente";

            } catch (SQLException e) {
                System.out.println("Error al ejecutar el insert");
                e.printStackTrace();
                salida = "Error al ejecutar la consulta" + e.getMessage();
            }
        } else {
            salida = "error al ";
        }
        return salida;
    }

    public String listarUsuario() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from administrador ORDER BY id_admin asc ");
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

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(5));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(6));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(7));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(9));
                salidaTabla.append(" </td> ");
                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(8));
                salidaTabla.append(" </td> ");
                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarCliente.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarUsuarios.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public administrador buscarPieza(String codPieza) {
        administradorModificar = new administrador();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select p.id_admin,p.nombre_admin,p.pat_admin, p.mat_admin, p.telf_admin, p.cargo, p.dni_admin, p.password, p.expedido  ");
        query.append(" from administrador p ");
        query.append(" where p.id_admin=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codPieza));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                administradorModificar.setCodAdmin(resultado.getInt(1));
                administradorModificar.setNombre(resultado.getString(2));
                administradorModificar.setPaterno(resultado.getString(3));
                administradorModificar.setMaterno(resultado.getString(4));
                administradorModificar.setTelefono(resultado.getInt(5));
                administradorModificar.setCargo(resultado.getString(6));
                administradorModificar.setDni(resultado.getString(7));
                administradorModificar.setPassword(resultado.getString(8));
                administradorModificar.setExpedido(resultado.getString(9));
            }
        } catch (SQLException e) {
            System.out.println("Error de coneccion");
            e.printStackTrace();
        }
        return administradorModificar;
    }

    public String modificarAdministrador(HttpServletRequest request, String codAdmin) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update administrador ");
                query.append(" set nombre_admin=?, pat_admin=?, mat_admin=?, telf_admin=?, cargo=?, dni_admin=?, password=?, expedido=?  ");
                query.append(" where id_admin=? ");
                if (updateCliente == null) {
                    updateCliente = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String nombre = request.getParameter("nombre");
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String telefono = request.getParameter("telefono");
                String cargo = request.getParameter("cargo");
                String dni = request.getParameter("dni");
                String expedido = request.getParameter("expedido");
                String password = request.getParameter("password");

                int tel = Integer.parseInt(telefono);
                //actualizando el objeto piezaModificar
                administradorModificar.setNombre(nombre);
                administradorModificar.setPaterno(paterno);
                administradorModificar.setMaterno(materno);
                administradorModificar.setTelefono(tel);
                administradorModificar.setCargo(cargo);
                administradorModificar.setDni(dni);
                administradorModificar.setPassword(password);
                administradorModificar.setExpedido(expedido);
                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateCliente.setString(1, nombre);
                updateCliente.setString(2, paterno);
                updateCliente.setString(3, materno);
                updateCliente.setInt(4, tel);
                updateCliente.setString(5, cargo);
                updateCliente.setString(6, dni);
                updateCliente.setString(7, password);
                updateCliente.setString(8, expedido);
                updateCliente.setInt(9, Integer.parseInt(codAdmin == null ? "0" : codAdmin));
                //executando la consulta
                int registros = updateCliente.executeUpdate();
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
    public String eliminarAdministrador(HttpServletRequest request,String codAdmin){
        String salida="";
        if(salida==null ){
            return "";
        }
        if(connection!=null && codAdmin!=null && codAdmin.length()>0){
            try {
                StringBuilder query=new StringBuilder();
                query.append(" delete from administrador ");
                query.append(" where id_admin=? ");
                eliminarCliente=connection.prepareStatement(query.toString());
                eliminarCliente.setInt(1, Integer.parseInt(codAdmin));
                int nroRegistros=eliminarCliente.executeUpdate();
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
    
    public administrador getAdministradorModificar() {
        return administradorModificar;
    }

    public void setAdministradorModificar(administrador administradorModificar) {
        this.administradorModificar = administradorModificar;
    }
    

}
