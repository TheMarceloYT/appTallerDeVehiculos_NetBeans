/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

//librerias
import javax.swing.JOptionPane;
import java.sql.ResultSet;

/**
 *
 * @author Marce
 */

public class Vehiculo {
    //atributos del vehiculo
    private String patente;
    private String marca;
    private String color;
    private String modelo;
    private String comentario;
    private String rut_cliente;
    private boolean borrado;
    
    //constructores
    public Vehiculo(String patente, String marca, String color, String modelo, String comentario, String rut_cliente){
        this.patente = patente;
        this.marca = marca;
        this.color = color;
        this.modelo = modelo;
        this.comentario = comentario;
        this.rut_cliente = rut_cliente;
    }
    
    public Vehiculo(String patente){
        this.patente = patente;
    }
    
    //gets y sets
    public String getPatente(){
        return patente;
    }
    
    public void setPatente(String patente){
        this.patente = patente;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public String getColor(){
        return color;
    }
    
    public void setColor(String color){
        this.color = color;
    }
    
    public String getModelo(){
        return modelo;
    }
    
    public void setModelo(String modelo){
        this.modelo = modelo;
    }
    
    public String getComentario(){
        return comentario;
    }
    
    public void setComentario(String comentario){
        this.comentario = comentario;
    }
    
    public String getRutCliente(){
        return rut_cliente;
    }
    
    public void setRutCliente(String rutCliente){
        this.rut_cliente = rutCliente;
    }
    
    public void setBorrado(boolean borrado){
        this.borrado =  borrado;
    }
    
    public boolean getBorrado(){
        return borrado;
    }
    
    //insertar nuevo vehiculo
    public void insertarVehiculo(){
        try{
            //intento guardar vehiculo
            String sql = "insert into vehiculos values ('"+patente+"', '"+marca+"', '"+color+"', '"+modelo+"', "
                            + " '"+comentario+"', '"+rut_cliente+"', "+false+" )";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito en agregar vehiculo", 1);
            Conexion.desconectar();
        }
        //hay errores
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error en guardar vehiculo", "Error en agregar vehiculo", 2);
        }
    }
    
    //actulizar vehiculo
    public void actualizarVehiculo(){
        try {
            //intento actualizar registro
            String sql = "update vehiculos set marca='"+marca+"', color='"+color+"', modelo='"+modelo+"', "
                    + "comentario='"+comentario+"', rut_cliente='"+rut_cliente+"' where patente='"+patente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Vehiculo con patente ("+patente+") actualizado correctamente", "Actualizar registro de un Vehiculo", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en actualizar Vehiculo", "Error en actualizar registro de un Vehiculo", 2);
        }
    }
    
    //borrar vehiculo
    public void borrarVehiculo(){
        try {
            //intento borrar vehiculo
            String sql = "update vehiculos set is_deleted = "+true+" where patente = '"+patente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Vehiculo borrado correctamente", "Borrar un vehiculo", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en borrar vehiculo", "Error en borrar registro de un vehiculo", 2);
        }
    }
    
    //validar vehiculo
    public void existeVehiculo(){
        try {
            //intento buscar el vehiculo
            Conexion.buscarVehiculo = false;
            String sql = "select * from vehiculos where patente = '"+patente+"'";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el vehiculo?
            if(rs.next()){
                Conexion.buscarVehiculo = true;
                patente = rs.getString(1);
                marca = rs.getString(2);
                color = rs.getString(3);
                modelo = rs.getString(4);
                comentario = rs.getString(5);
                rut_cliente = rs.getString(6);
                borrado = rs.getBoolean(7);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe el vehiculo", "Buscar un vehiculo", 2);
        }
    }
    
    //restaurar un vehiculo
    public void restaurarVehiculo(){
        try{
            //intento restaurar al vehiculo
            String sql = "update vehiculos set is_deleted = "+false+" where patente = '"+patente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Vehiculo restaurado correctamente", "Restaurar un vehiculo", 1);
            Conexion.desconectar();
        }
        //hubo un erro
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error, no se restauró el vehiculo", "Restaurar un vehiculo", 2);
        }
    }
}
