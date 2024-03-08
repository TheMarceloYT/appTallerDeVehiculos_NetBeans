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

public class Producto {
    //atributos del producto
    private int id;
    private String nombre;
    private int cantidad;
    private int costo_unitario;
    private String tipo;
    private String marca;
    private boolean borrado;
    
    //constructores
    public Producto(int id,String nombre, int cantidad, int costoUnitatrio, String tipo, String marca){
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo_unitario = costoUnitatrio;
        this.tipo = tipo;
        this.marca = marca;
    }
    
    public Producto(String nombre, int cantidad, int costoUnitatrio, String tipo, String marca){
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.costo_unitario = costoUnitatrio;
        this.tipo = tipo;
        this.marca = marca;
    }
    
    public Producto(int id){
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
    
    public int getCantidad(){
        return cantidad;
    }
    
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    
    public int getCostoUnitario(){
        return costo_unitario;
    }
    
    public void setCostoUnitario(int costoUnitario){
        this.costo_unitario = costoUnitario;
    }
    
    public String getTipo(){
        return tipo;
    }
    
    public void setTipo(String tipo){
        this.tipo = tipo;
    }
    
    public String getMarca(){
        return marca;
    }
    
    public void setMarca(String marca){
        this.marca = marca;
    }
    
    public void setBorrado(boolean borrado){
        this.borrado = borrado;
    }
    
    public boolean getBorrado(){
        return borrado;
    }
    
    //insertado de nuevo producto
    public void insertarProducto(){
        try {
            //intento insertado del producto
            String sql = "insert into productos values (id, '"+nombre+"', "+cantidad+", "+costo_unitario+", "
                + " '"+tipo+"', '"+marca+"', "+false+" )";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito en agregar producto", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en guardar producto", "Error en agregar producto", 2);
        }
    }
    
    //actualizar producto
    public void actualizarProducto(){
        try {
            //intento actualizar registro
            String sql = "update productos set nombre='"+nombre+"', cantidad="+cantidad+", "
                + "costo_unitario= "+costo_unitario+", tipo='"+tipo+"', marca='"+marca+"' where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Producto actualizado correctamente", "Actualizar registro de un producto", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en actualizar producto", "Error en actualizar registro de un producto", 2);
        }
    }
    
    //eliminar producto
    public void borrarProducto(){
        try {
            //intento borrar producto
            String sql = "update productos set is_deleted = "+true+" where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Producto borrado correctamente", "Borrar un producto", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en borrar producto", "Error en borrar registro de un producto", 2);
        }
    }
    
    //buscar producto
    public void existeProducto(){
        try {
            //intento buscar el producto
            Conexion.buscarProducto = false;
            String sql = "select * from productos where id = "+id+" or nombre = '"+nombre+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el producto?
            if(rs.next()){
                Conexion.buscarProducto = true;
                id = rs.getInt(1);
                nombre = rs.getString(2);
                cantidad = rs.getInt(3);
                costo_unitario = rs.getInt(4);
                tipo = rs.getString(5);
                marca = rs.getString(6);
                borrado = rs.getBoolean(7);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe el producto", "Buscar un producto", 2);
        }
    }
    
    //restaurar producto
    public void restaurarProducto(){
        try{
            //intento restaurar al producto
            String sql = "update productos set is_deleted = "+false+" where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Producto restaurado correctamente", "Restaurar un producto", 1);
            Conexion.desconectar();
        }
        //hubo un error
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error, no se restauró el producto", "Restaurar un producto", 2);
        }
    }
    
    //restar cantidad de producto usado
    public void restarCantProducto(int resta){
        try{
            //intento restar al producto su cantidad
            String sql = "update productos set cantidad = cantidad - "+resta+" where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            //JOptionPane.showMessageDialog(null, "Producto restado correctamente", "Restar cantidad de un producto", 1);
            Conexion.desconectar();
        }
        //hubo un error
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error, no se restó el producto", "Restar cantidad de un producto", 2);
        }
    }
    
    public boolean verificarStock(){
        int stock = 0;
        try {
            //intento buscar el producto
            String sql = "select * from productos where id = "+id+" ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el producto?
            if(rs.next()){
                stock = rs.getInt(3);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, en verificar stock de producto", "Buscar stock de un producto", 2);
        }
        return stock == 0;
    }
}
