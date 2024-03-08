/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import java.sql.ResultSet;
import javax.swing.JOptionPane;

/**
 *
 * @author Marcelo 
 */
public class Bitacora {
    private int idBitacora;
    private String patente;
    private int idProducto;
    private int idServicio;
    private String comentario;
    private String rutUsuario;

    public Bitacora(String patente, int idProducto, int idServicio, String comentario, String rutUsuario) {
        this.patente = patente;
        this.idProducto = idProducto;
        this.idServicio = idServicio;
        this.comentario = comentario;
        this.rutUsuario = rutUsuario;
    }

    public Bitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }
    
    
    
    public int getIdBitacora() {
        return idBitacora;
    }

    public void setIdBitacora(int idBitacora) {
        this.idBitacora = idBitacora;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public String getRutUsuario() {
        return rutUsuario;
    }

    public void setRutUsuario(String rutUsuario) {
        this.rutUsuario = rutUsuario;
    }
    
    //agregar bitacora
    public void insertarBitacora(){
        try {
            String sql = "insert into bitacora values (id, '"+patente+"', "+idProducto+", "+idServicio+",'"+comentario+"','"+rutUsuario+"')";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            //JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito en agregar bitacora", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en guardar registro", "Error en agregar registro", 2);
        }
    }
      
    //existe bitacora?
    public void existeBitacora(){
        try {
            Conexion.buscarBitacora = false;
            String sql = "select * from bitacora where id = "+idBitacora+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el producto?
            if(rs.next()){
                Conexion.buscarBitacora = true;
                idBitacora = rs.getInt(1);
                patente= rs.getString(2);
                idProducto = rs.getInt(3);
                idServicio = rs.getInt(4);
                comentario = rs.getString(5);
                rutUsuario = rs.getString(6);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe el registro", "Buscar un registro", 2);
        }
    }
}
