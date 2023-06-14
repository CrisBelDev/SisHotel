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
import java.util.Date;
import javax.annotation.PreDestroy;
import javax.servlet.http.HttpServletRequest;


public class clienteBean {

    private Connection connection;
    private PreparedStatement insertCliente;
    private PreparedStatement updateCliente;
    private cliente clienteModificar;
    private PreparedStatement eliminarCliente;
    private VariablesConexion variable;

    public clienteBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion CLIENTE");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    public String insertarCliente(HttpServletRequest request) throws SQLException {
       
        String salida = "";
        if (connection == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into cliente ");
                query.append(" values(nextval('sec_cliente'), ?,?,?,?,?,?,?) ");
                if (insertCliente == null) {
                    insertCliente = connection.prepareStatement(query.toString());
                }
                //obteniendo los datos de mi formulario jsp
                String idAdmin = "8";
                String nomClie = request.getParameter("nomclie");
                String patClie = request.getParameter("patclie");
                String matClie = request.getParameter("matclie");
                String telfClie = request.getParameter("telfclie");
                String emailClie = request.getParameter("emailclie");
                String dniClie = request.getParameter("dniclie");
                String costos = request.getParameter("costo");
                String idHabitacion = request.getParameter("habitacion");
                String fechaSalida = request.getParameter("fechaSalidaHabitacion");
                //casteando el stock para la BD
                int telf = Integer.parseInt(telfClie);
                int codAdmin = Integer.parseInt(idAdmin);
                int costo = Integer.parseInt(costos);
                int id_habitacion = Integer.parseInt(idHabitacion);

                //pasando los parametros a la consulta
                insertCliente.setInt(1, codAdmin);
                insertCliente.setString(2, nomClie);
                insertCliente.setString(3, patClie);
                insertCliente.setString(4, matClie);
                insertCliente.setInt(5, telf);
                insertCliente.setString(6, emailClie);
                insertCliente.setString(7, dniClie);

                //ejecutando consulta
                int registros = insertCliente.executeUpdate();
                System.out.println("Insercion correcta");
                try {
                    StringBuilder query1 = new StringBuilder();
                    query1.append(" select max(id_cliente) from cliente ");
                    Statement st = connection.createStatement();
                    ResultSet respuesta = st.executeQuery(query1.toString());
                    if (respuesta.next()) {
                        int cod_cliente = respuesta.getInt(1);
//                        reservacion reservacion=new reservacion();
//                        reservacion.setId_cliente(cod_cliente);
                        reservacionBean obj = new reservacionBean();
                        obj.insertarReservacion(cod_cliente, costo, id_habitacion, fechaSalida);
                    }

                } catch (SQLException e) {
                    System.out.println("error en cliente " + e.getMessage());
                }
               
                salida="<div class='alert alert-info mt-5' id='alerta'>"
                            +"<p>Tu reservacion se registro corectamente.!</p>"
                            + "</div>";

            } catch (SQLException e) {
                System.out.println("++Error al ejecutar el insert en cliente++");
                e.printStackTrace();
                salida = "Error al ejecutar la consulta" + e.getMessage();
            }
        } else {
            salida = "error al ejecutar el insertado del cliente ";
        }
        return salida;
    }

    public String costoNoche(HttpServletRequest request) {
        int idReservacion=0;
        String salidaTabla = "";
        StringBuilder query = new StringBuilder();
        StringBuilder query1=new StringBuilder();
        query1.append(" select Max(id_reservacion) from reservacion ");
        try {
            Statement st=connection.createStatement();
            ResultSet resultado=st.executeQuery(query1.toString());
            if(resultado.next()){
                idReservacion=resultado.getInt(1);
                System.out.println("idreservacion "+idReservacion);
            }
        } catch (SQLException e) {
            System.out.println("error "+e.getMessage());
        }
        query.append(" select fecha_ingreso,fecha_salida, habitacion.costo_habitacion ");
        query.append(" from ocupa join habitacion on (ocupa.id_habitacion=habitacion.id_habitacion) ");
        query.append(" where id_reservacion=? ");
        
        ocupa ocupa=new ocupa();
        int costoHabitacion=0;
        try {
            PreparedStatement consulta=connection.prepareStatement(query.toString());
            consulta.setInt(1, idReservacion);
            ResultSet respuesta=consulta.executeQuery();
            if(respuesta.next()){
                ocupa.setFecha_ingreso(respuesta.getDate(1));
                ocupa.setFecha_salida(respuesta.getDate(2));
                costoHabitacion=respuesta.getInt(3);
                
            }
            fecha operacionFecha=new fecha();
            int x=operacionFecha.diferenciasDeFechas(ocupa.getFecha_ingreso(), ocupa.getFecha_salida());
            
            int total=x*costoHabitacion-Integer.parseInt(request.getParameter("costo"));
            salidaTabla="Total cancelar: "+total+"<br>"+"Dias de instancia: "+x;
        } catch (SQLException e) {
            System.out.println("error de calculos de pago "+e.getMessage());
        }
        
        
        return salidaTabla;
    }

    public String listarCliente() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from cliente ORDER BY id_cliente desc ");
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
                salidaTabla.append(resultado.getString(3));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(4));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getInt(6));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(7));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(8));
                salidaTabla.append(" </td> ");
                //opciones
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=modificarCliente1.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarCliente1.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion en cliente ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public String listarClienteHabitacion() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select nom_cliente, pat_cliente,mat_cliente,habitacion.nom_habitacion,habitacion.tipo,ocupa.fecha_ingreso,ocupa.fecha_salida");
        query.append(" from cliente join reservacion ");
        query.append(" on (cliente.id_cliente=reservacion.id_cliente) join ocupa ");
        query.append(" on (reservacion.id_reservacion=ocupa.id_reservacion) join habitacion ");
        query.append(" on (ocupa.id_habitacion=habitacion.id_habitacion) where habitacion.estado='ocupado' order by id_ocupa desc");
        try {
            Statement st = connection.createStatement();
            //la consulta retorna en una lista de tipo Resulset
            ResultSet resultado = st.executeQuery(query.toString());
            System.out.println(resultado);
            while (resultado.next()) {
                salidaTabla.append(" <tr> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getString(1));
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
                salidaTabla.append(resultado.getString(5));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDate(6));
                salidaTabla.append(" </td> ");

                salidaTabla.append("<td>");
                salidaTabla.append(resultado.getDate(7));
                salidaTabla.append(" </td> ");
                String nombre=resultado.getString(1)+"+"+resultado.getString(2)+"+"+resultado.getString(3);
                System.out.println("nombre "+nombre);
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=reporteConsumo.jsp?cd=").append(nombre).append("&cod=").append(resultado.getString(4)).append(">ver consumo</a>");
                salidaTabla.append(" </td> ");
                
                salidaTabla.append("<td>");
                salidaTabla.append("<a href=listarOcupa.jsp?op=").append(resultado.getString(4)).append("&nom=").append(nombre).append(">Desocupar</a>");
                salidaTabla.append(" </td> ");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion en cliente ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public cliente buscarCliente(String codClie) {
        clienteModificar = new cliente();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select p.id_cliente,p.id_admin,p.nom_cliente, p.pat_cliente,p.mat_cliente, p.telf_cliente, p.email_cliente, p.dni_cliente ");
        query.append(" from cliente p ");
        query.append(" where p.id_cliente=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codClie));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                clienteModificar.setId_cliente(resultado.getInt(1));
                clienteModificar.setId_admin(resultado.getInt(2));
                clienteModificar.setNom_clie(resultado.getString(3));
                clienteModificar.setPat_clie(resultado.getString(4));
                clienteModificar.setMat_clie(resultado.getString(5));
                clienteModificar.setTelf_clie(resultado.getInt(6));
                clienteModificar.setEmail_clie(resultado.getString(7));
                clienteModificar.setDni_clie(resultado.getString(8));

            }
        } catch (SQLException e) {
            System.out.println("Error de coneccion");
            e.printStackTrace();
        }
        return clienteModificar;
    }

    public String modificarCliente(HttpServletRequest request, String codClie) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update cliente ");
                query.append(" set id_admin=?, nom_cliente=?, pat_cliente=?, mat_cliente=?, telf_cliente=?, email_cliente=?,dni_cliente=?  ");
                query.append(" where id_cliente=? ");
                if (updateCliente == null) {
                    updateCliente = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String idAdmin = request.getParameter("idAdmin");
                String nombre = request.getParameter("nombre");
                String paterno = request.getParameter("paterno");
                String materno = request.getParameter("materno");
                String telefono = request.getParameter("telefono");
                String email = request.getParameter("email");
                String dni = request.getParameter("dni");

                int codigoAdmin = Integer.parseInt(idAdmin);
                int tel = Integer.parseInt(telefono);
                //actualizando el objeto piezaModificar
                clienteModificar.setId_admin(codigoAdmin);
                clienteModificar.setNom_clie(nombre);
                clienteModificar.setPat_clie(paterno);
                clienteModificar.setMat_clie(materno);
                clienteModificar.setTelf_clie(tel);
                clienteModificar.setEmail_clie(email);
                clienteModificar.setDni_clie(dni);
                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateCliente.setInt(1, codigoAdmin);
                updateCliente.setString(2, nombre);
                updateCliente.setString(3, paterno);
                updateCliente.setString(4, materno);
                updateCliente.setInt(5, tel);
                updateCliente.setString(6, email);
                updateCliente.setString(7, dni);
                updateCliente.setInt(8, Integer.parseInt(codClie == null ? "0" : codClie));
                //executando la consulta
                int registros = updateCliente.executeUpdate();
                System.out.println("modificacion  de cliente realizado");
                salida = "modificacion correcta";

            } catch (SQLException e) {
                System.out.println("eeror al ejecutar el udpdate.. cliente.");
                e.printStackTrace();
                salida = "eeror al ejecutar el update: " + e.getMessage();
            }
        }
        return salida;
    }

    public String eliminarCliente(HttpServletRequest request, String codClie) {
        String salida = "";
        if (salida == null) {
            return "";
        }
        if (connection != null && codClie != null && codClie.length() > 0) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" delete from cliente ");
                query.append(" where id_cliente=? ");
                eliminarCliente = connection.prepareStatement(query.toString());
                eliminarCliente.setInt(1, Integer.parseInt(codClie));
                int nroRegistros = eliminarCliente.executeUpdate();
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

    public cliente getClienteModificar() {
        return clienteModificar;
    }

    public void setClienteModificar(cliente clienteModificar) {
        this.clienteModificar = clienteModificar;
    }

}
