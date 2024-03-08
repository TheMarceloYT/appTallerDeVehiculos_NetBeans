/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controladores;

//librerias
import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author atude
 */

public class Usuario {
    //atributos del usuario
    private String rut;
    private String usuario;
    private String contrasena;

    //constructor
    public Usuario(String usuario, String contrasena) {
        this.usuario = usuario;
        this.contrasena = contrasena;
    }
    
    //gets y sets
    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
    
    //validar usuario
    public byte validarUsuario(){
        //indicador
        byte index = 0;
        try {
            //intento validar el user
            String sql= "select * from usuarios where usuario='"+usuario+"' and contrasena='"+contrasena+"' ";
            Conexion.conectar();
            Conexion.stm =Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //el user existe
            if(rs.next()){
                rut = rs.getString(1);
                usuario = rs.getString(2);
                contrasena = rs.getString(3);
                Conexion.rut_user = rut;
                index = 1;
                if(index==1){
                   return index;
                }
            }
            //el user no existe
            else{
                //JOptionPane.showMessageDialog(null,"CREDENCIALES INCORRECTAS", "Error de acceso", 2);
                return index;
            }
            Conexion.desconectar();
        } 
        //hay un error de proceso
        catch (Exception e) {
            JOptionPane.showMessageDialog(null,"ERROR: " + e.getMessage(), "Error de proceso", 2);
            return index;
        }
        return index;
    }
}