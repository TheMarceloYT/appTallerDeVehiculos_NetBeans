/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

//librerias

import java.sql.ResultSet;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Marce
 */
public class Boleta {
    //atributos
    private int id;
    private int id_bitacora;
    private double monto_total;
    private String rut_usuario;
    
    //gets y sets
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public int getIdBitacora(){
        return id_bitacora;
    }
    
    public void setIdBitacora(int IdBitacora){
        this.id_bitacora = IdBitacora;
    }
    
    public double getMontoTotal(){
        return monto_total;
    }
    
    public void setMontoTotal(double total){
        this.monto_total = total;
    }
    
    public String getRutUser(){
        return rut_usuario;
    }
    
    public void setRutUser(String RutUser){
        this.rut_usuario = RutUser;
    }
    
    //total 
    public int calcularTotal(int producto, int servicio){
        int precio_producto = 0;
        double precio_servicio = 0;
        //precio del producto
        try{
            Producto prod = new Producto(producto);
            prod.existeProducto();
            precio_producto = prod.getCostoUnitario();
        }
        catch(Exception ex){
            //error
        }
        //precio del servicio
        try{
            Servicio ser = new Servicio(servicio);
            ser.existeServicio();
            precio_servicio = ser.getValor();
        }
        catch(Exception ex){
            //error
        }
        int total = (int) (precio_producto+precio_servicio);
        return total;
    }
    
    //buscar la boleta
    public void buscarIdBoleta(){
        try {
            String sql = "select * from bitacora where id = (select max(id) from bitacora) ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el producto?
            if(rs.next()){
                id_bitacora = rs.getInt(1);
                int id_producto = rs.getInt(3);
                int id_servicio = rs.getInt(4);
                monto_total = calcularTotal(id_producto, id_servicio);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe la bitacora", "Buscar una bitacora", 2);
        }
    }
    
    //insertar boleta
    public void insertBoleta(){
        try {
            Date fecha = new Date();
            java.sql.Date sqlDate = new java.sql.Date(fecha.getTime());
            String rut_user = Conexion.rut_user;
            String sql = "insert into boletas values (id, "+id_bitacora+", "+monto_total+", '"+rut_user+"', "
                    + " '"+sqlDate+"' )";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "BOLETA AGREGADA \nId de la bitacora: "+id_bitacora+"\nMonto total: "+(int)monto_total+
                    "\nRut del usuario: "+rut_user+"\nFecha de la venta: "+sqlDate, 
                "Exito en agregar boleta", 1);
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe el registro", "Buscar un registro", 2);
        }
    }
}
