<%@page import="java.io.File"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="net.sf.jasperreports.engine.*" %>
<%@page import="java.util.*"%>
<%@page import="conexion.VariablesConexion" %>


<!DOCTYPE html>
<%
    VariablesConexion conexion=new VariablesConexion();
    conexion.inicioConexion();
    File reporFile=new File(application.getRealPath("jasper/reporteGananciaFecha.jasper"));
    Map parameters=new HashMap();
    //rescatando parametros
    String fecha=request.getParameter("fechaInicio");
    String fecha2=request.getParameter("fechaFinal");
    
   
    SimpleDateFormat formato=new SimpleDateFormat("d/M/y");
    Date fechaInicio=formato.parse(fecha);
    Date fechaFinal=formato.parse(fecha2);
    System.out.println("fecha:  "+fechaInicio);
    System.out.println("fecha final:  "+fechaFinal);
    parameters.put("fechaFinal", fechaFinal);
    parameters.put("fechaInicio", fechaInicio);
    
    
    byte[] bytes= JasperRunManager.runReportToPdf(reporFile.getPath(),parameters,conexion.getConnection());
    response.setContentType("application/pdf");
    response.setContentLength(bytes.length);
    ServletOutputStream outputStream=response.getOutputStream();
    outputStream.write(bytes, 0, bytes.length);
    outputStream.flush();
    outputStream.close();
    
%>