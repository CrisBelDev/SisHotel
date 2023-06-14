<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%@page import="conexion.VariablesConexion" %>


<!DOCTYPE html>
<%
    VariablesConexion conexion=new VariablesConexion();
    conexion.inicioConexion();
    File reporFile=new File(application.getRealPath("jasper/reporteConsumo.jasper"));
    Map parameters=new HashMap();
    String nombre=request.getParameter("cd");
    String habitacion=request.getParameter("cod");
    System.out.println(nombre);
    System.out.println(habitacion);
    parameters.put("nombre",nombre);
    parameters.put("habitacion", habitacion);
    
    byte[] bytes= JasperRunManager.runReportToPdf(reporFile.getPath(),parameters,conexion.getConnection());
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outputStream=response.getOutputStream();
    outputStream.write(bytes, 0, bytes.length);
    outputStream.flush();
    outputStream.close();
    
%>
