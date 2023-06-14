/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;



/**
 *
 * @author HP
 */
public class fechaFormat {

    public Date convertirFecha(String fechaString){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date fecha=null;
        try {
            
            //revisar la fecha
            fecha=sdf.parse(fechaString);
            System.out.println("fecha formateada: "+fecha);
        } catch (ParseException e) {
            System.out.println("error em las fecha resvisar");
        }
        return fecha;
    }
    public static void main(String[] args) {
        fechaFormat fecha=new fechaFormat();
        Date f=fecha.convertirFecha("2018-10-19");
        System.out.println("f"+f);
    }
}
