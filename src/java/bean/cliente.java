/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

/**
 *
 * @author HP
 */
public class cliente {
    private int id_cliente;
    private int id_admin;
    private String nom_clie;
    private String pat_clie;
    private String mat_clie;
    private int telf_clie;
    private String email_clie;
    private String dni_clie;

    public cliente() {
    }
    
    

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public String getNom_clie() {
        return nom_clie;
    }

    public void setNom_clie(String nom_clie) {
        this.nom_clie = nom_clie;
    }

    public String getPat_clie() {
        return pat_clie;
    }

    public void setPat_clie(String pat_clie) {
        this.pat_clie = pat_clie;
    }

    public String getMat_clie() {
        return mat_clie;
    }

    public void setMat_clie(String mat_clie) {
        this.mat_clie = mat_clie;
    }

    public int getTelf_clie() {
        return telf_clie;
    }

    public void setTelf_clie(int telf_clie) {
        this.telf_clie = telf_clie;
    }

    public String getEmail_clie() {
        return email_clie;
    }

    public void setEmail_clie(String email_clie) {
        this.email_clie = email_clie;
    }

    public String getDni_clie() {
        return dni_clie;
    }

    public void setDni_clie(String dni_clie) {
        this.dni_clie = dni_clie;
    }
    
}
