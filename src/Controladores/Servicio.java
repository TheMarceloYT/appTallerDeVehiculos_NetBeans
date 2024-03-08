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
public class Servicio {
    //atributos del Servicio
    private int id;
    private String nombre;
    private String descripcion;
    private double valor;
    private int tiempo_ejecucion;
    
    //constructor
    public Servicio(int id, String nombre, String descripcion, double valor, int tiempoEjecucion){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.valor = valor;
        this.tiempo_ejecucion =  tiempoEjecucion;
    }
    
    public Servicio(int id){
        this.id = id;
    }
    
    //gets y sets
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombre(){
        return nombre;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    }
    
    public double getValor(){
        return valor;
    }
    
    public void setValor(double valor){
        this.valor = valor;
    }
    
    public int getTiempoEjecucion(){
        return tiempo_ejecucion;
    }
    
    public void setTiempoEjecucion(int TiempoEjecucion){
        this.tiempo_ejecucion = TiempoEjecucion;
    }
    
    //insertar servicio
    public void insertarServicio(){
        try {
            //intento insertado del servicio
            String sql = "insert into servicios values ("+id+",'"+nombre+"', '"+descripcion+"', "+valor+", "+tiempo_ejecucion+") ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito en agregar servicio", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en guardar servicio", "Error en agregar servicio", 2);
        }
    }
    
    //actualizar servicio
    public void actualizarServicio(){
        try {
            //intento actualizar servicio
            String sql = "update servicios set nombre='"+nombre+"', descripcion='"+descripcion+"', valor="+valor+", "
                + " tiempo_ejecucion="+tiempo_ejecucion+" where id="+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Servicio actualizado correctamente", "Actualizar registro de un servicio", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en actualizar servicio", "Error en actualizar registro de un servicio", 2);
        }
    }
    
    //borrar servicio
    public void borrarServicio(){
        try {
            //intento borrar servicio
            String sql = "delete from servicios where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Servicio borrado correctamente", "Borrar un servicio", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en borrar servicio", "Error en borrar registro de un servicio", 2);
        }
    }
    
    //buscar servicio
    public void existeServicio(){
        try {
            //intento buscar el servicio
            Conexion.buscarServicio = false;
            String sql = "select * from servicios where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el servicio?
            if(rs.next()){
                Conexion.buscarServicio = true;
                id = rs.getInt(1);
                nombre = rs.getString(2);
                descripcion = rs.getString(3);
                valor = rs.getDouble(4);
                tiempo_ejecucion = rs.getInt(5);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe el servicio", "Buscar un servicio", 2);
        }
    }
}
