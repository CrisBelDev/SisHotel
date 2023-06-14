package bean;

import clases.fecha;
import clases.fechaFormat;
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

public class ocupaBean {

    private Connection connection;
    private PreparedStatement insertOcupa;
    private PreparedStatement updateOcupa;
    private ocupa ocupaModificar;
    private PreparedStatement eliminarOcupa;
    private VariablesConexion variable;
    fechaFormat fecha = new fechaFormat();

    public ocupaBean() throws SQLException {
        variable = new VariablesConexion();
        variable.inicioConexion();
        connection = variable.getConnection();
        System.out.println("Iniciando conexion ocupa");
    }

    @PreDestroy
    public void cerrarConexion() {
        variable.cerrarConexion();
    }

    public String insertarOcupa(int idReservacion, int idHabitacion, String fecSalida) {

        String salida = "";
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" insert into ocupa ");
                query.append(" values(nextval('sec_ocupa'), ?,?,?,?) ");
                if (insertOcupa == null) {
                    insertOcupa = connection.prepareStatement(query.toString());
                }
                //obteniendo los datos de mi formulario jsp
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                fecha fecha = new fecha();
                String actual = fecha.getFechaActual();
                Date f_inicio = fecha.deStringToDate(actual);
                
                String fechaSalida = fecSalida;
                Date fInicio = f_inicio;

                //casteando el stock para la BD
                int id_reservacion = idReservacion;
                int id_Habitacion = idHabitacion;
                //pasando los parametros a la consulta
                insertOcupa.setInt(1, id_reservacion);
                insertOcupa.setInt(2, id_Habitacion);

                fechaFormat fec = new fechaFormat();
                Date fs = fec.convertirFecha(fechaSalida);
                insertOcupa.setDate(3, new java.sql.Date(f_inicio.getTime()));
                insertOcupa.setDate(4, new java.sql.Date(fs.getTime()));

                //ejecutando consulta
                int registros = insertOcupa.executeUpdate();
                System.out.println("Insercion correcta en ocupa++++");
                salida = "Registro realizado correctamente";
                try {
                    StringBuilder query1 = new StringBuilder();
                    query1.append(" update habitacion set estado='ocupado' where id_habitacion=? ");
                    PreparedStatement preparedStatement = connection.prepareStatement(query1.toString());
                    preparedStatement.setInt(1, id_Habitacion);
                    int re = preparedStatement.executeUpdate();
                    System.out.println("se actualizo a ocupado ");
                } catch (SQLException e) {
                    System.out.println("error de actualizar habitacion " + e.getMessage());
                }
                

            } catch (SQLException e) {
                System.out.println("Error al ejecutar el insert");
                e.printStackTrace();
                salida = "Error al ejecutar la consulta" + e.getMessage();
            }
        } else {
            salida = "error al ejcucar insercion ocupa ";
        }
        return salida;
    }

    public String listarOcupa() {
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * from ocupa ");
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
                salidaTabla.append("<a href=modificarOcupa.jsp?cd=").append(resultado.getInt(1)).append(" class='btn btn-success btn-sm'>Modificar</a>");
                salidaTabla.append("<a href='listarOcupa.jsp?cod=").append(resultado.getInt(1)).append(" 'onclick='return confirmarEliminacion();' class='btn btn-danger btn-sm'>Eliminar</a> ");
                salidaTabla.append("</td>");

                salidaTabla.append("</tr>");
            }
        } catch (SQLException e) {
            System.out.println("error de conexion en listado de ocupa++++ ");
            e.printStackTrace();
        }
        return salidaTabla.toString();
    }

    public ocupa buscarOcupa(String codOcupa) {
        ocupaModificar = new ocupa();
        StringBuilder salidaTabla = new StringBuilder();
        StringBuilder query = new StringBuilder();
        query.append(" select * ");
        query.append(" from ocupa ");
        query.append(" where id_ocupa=? ");
        try {
            PreparedStatement st = connection.prepareStatement(query.toString());
            st.setInt(1, Integer.parseInt(codOcupa));
            ResultSet resultado = st.executeQuery();
            //si existe un resultado
            if (resultado.next()) {
                //cargando los datos al objeto piezaModificar
                ocupaModificar.setId_ocupa(resultado.getInt(1));
                ocupaModificar.setId_reservacion(resultado.getInt(2));
                ocupaModificar.setId_habitacion(resultado.getInt(3));
                ocupaModificar.setFecha_ingreso(resultado.getDate(4));
                ocupaModificar.setFecha_salida(resultado.getDate(4));

            }
        } catch (SQLException e) {
            System.out.println("Error de coneccion");
            e.printStackTrace();
        }
        return ocupaModificar;
    }

    public String modificarOcupa(HttpServletRequest request, String codOcupa) {
        String salida = "";

        if (request == null) {
            return "";
        }
        if (connection != null) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" update ocupa ");
                query.append(" set id_reservacion=?, id_habitacion=?, fecha_ingreso=?,fecha_salida=? ");
                query.append(" where id_ocupa=? ");
                if (updateOcupa == null) {
                    updateOcupa = connection.prepareStatement(query.toString());
                }
                //rescatando datos del formulario jsp
                String idReserv = request.getParameter("idReservacion");
                String idHab = request.getParameter("idHabitacion");
                String fechaIngreso = request.getParameter("fechaIngreso");
                String fechaSalida = request.getParameter("fechaSalida");
                //convierto las fechas
                Date fInicio = convertirFecha(fechaIngreso);
                Date fSalida = convertirFecha(fechaSalida);
                //casteo de datos
                int idReservacion = Integer.parseInt(idReserv);
                int idHabitacion = Integer.parseInt(idHab);
                //actualizando el objeto piezaModificar
                ocupaModificar.setId_reservacion(idReservacion);
                ocupaModificar.setId_habitacion(idHabitacion);

                Date fe = fecha.convertirFecha(fechaIngreso);
                Date fs = fecha.convertirFecha(fechaSalida);
                ocupaModificar.setFecha_ingreso(new java.sql.Date(fe.getTime()));
                ocupaModificar.setFecha_salida(new java.sql.Date(fs.getTime()));
                //para cambiar en la base dedatos utilizamos el objeto insertPieza
                updateOcupa.setInt(1, idReservacion);
                updateOcupa.setInt(2, idReservacion);
                updateOcupa.setDate(3, new java.sql.Date(fe.getTime()));
                updateOcupa.setDate(4, new java.sql.Date(fs.getTime()));
                updateOcupa.setInt(5, Integer.parseInt(codOcupa == null ? "0" : codOcupa));
                //executando la consulta
                int registros = updateOcupa.executeUpdate();
                System.out.println("modificacion realizado en ocupa");
                salida = "modificacion correcta";

            } catch (SQLException e) {
                System.out.println("eeror al ejecutar el udpdate...");
                e.printStackTrace();
                salida = "eeror al ejecutar el update: " + e.getMessage();
            }
        }
        return salida;
    }

    public String eliminarOcupa(HttpServletRequest request, String codOcupa) {
        String salida = "";
        if (salida == null) {
            return "";
        }
        if (connection != null && codOcupa != null && codOcupa.length() > 0) {
            try {
                StringBuilder query = new StringBuilder();
                query.append(" delete from ocupa ");
                query.append(" where id_ocupa=? ");
                eliminarOcupa = connection.prepareStatement(query.toString());
                eliminarOcupa.setInt(1, Integer.parseInt(codOcupa));
                int nroRegistros = eliminarOcupa.executeUpdate();
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
        Date fecha = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            //revisar la fecha
            fecha = (Date) sdf.parse(fechaString);
        } catch (ParseException e) {
            System.out.println("error em las fecha resvisar");
        }
        return fecha;
    }

    public ocupa getOcupaModificar() {
        return ocupaModificar;
    }

    public void setOcupaModificar(ocupa ocupaModificar) {
        this.ocupaModificar = ocupaModificar;
    }

}
