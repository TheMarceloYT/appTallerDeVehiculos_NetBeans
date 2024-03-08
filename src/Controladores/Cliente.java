/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

//librerias
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.lang.Math;

/**
 *
 * @author atude
 */

public class Cliente {
    //atributos del cliente
    private String rut_cliente;
    private String nombre;
    private String fono;
    private boolean borrado;
    private boolean validado = false;
    
    //constructor
    public Cliente(String rut_cliente, String nombre, String fono) {
        this.rut_cliente = rut_cliente;
        this.nombre = nombre;
        this.fono = fono;
    }
    
    //gets y sets
    public boolean isValidado() {
        return validado;
    }

    public void setValidado(boolean validado) {
        this.validado = validado;
    }
    
    public String getRut_cliente() {
        return rut_cliente;
    }

    public void setRut_cliente(String rut_cliente) {
        this.rut_cliente = rut_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFono() {
        return fono;
    }

    public void setFono(String fono) {
        this.fono = fono;
    }
    
    public void setBorrado(boolean borrado){
        this.borrado =  borrado;
    }
    
    public boolean getBorrado(){
        return borrado;
    }
    
    //validar el rut de cliente
    public void validarRut(){
        String rutInvertido = this.rut_cliente.substring(0,this.rut_cliente.length()-1);
        StringBuilder sb = new StringBuilder(rutInvertido);
        String invertida = sb.reverse().toString();
        String [] cadena = invertida.split("");
        String verificar;
        //////////////////////
        double suma=0;
        int incremento=2;
        for (int i = 0; i < invertida.length(); i++) {
            suma += Integer.parseInt(cadena[i]) * incremento;
            if(incremento == 7){
                incremento = 2;
            }else{
                incremento++;
            }
        }
        
        double valor1 = Math.floor(suma / 11);
        double valor2 = suma - (valor1 * 11);
        double v = 11 - valor2;
        
        if(v == 10){
            verificar = "k";
        }else if(v >= 11){
            verificar = "0";
        }
        
        verificar = String.valueOf(v);
        char ultimo = this.rut_cliente.charAt(this.rut_cliente.length()-1);
        
        if(verificar.equals(ultimo)){
            this.validado = true;
        }
        
    }
    //insertado de un nuevo cliente
    public void insertarCliente(){
        try {
            //intento insertado del cliente
            String sql = "insert into clientes values('"+rut_cliente+"','"+nombre+"','"+fono+"', "+false+")";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Datos guardados correctamente", "Exito en agregar cliente", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en guardar cliente", "Error en agregar cliente", 2);
        }
    }
    
    //actualizar registro de un cliente
    public void actualizarCliente(){
        try {
            //intento actualizar registro
            String sql = "update clientes set nombre='"+nombre+"',fono='"+fono+"' where rut = '"+rut_cliente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Cliente actualizado correctamente", "Actualizar registro de un cliente", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en actualizar cliente", "Error en actualizar registro de un cliente", 2);
        }
    }
    
    //borrar un cliente (usando soft delete)
    public void borrarCliente(){
        try {
            //intento borrar cliente
            String sql = "update clientes set is_deleted = "+true+" where rut = '"+rut_cliente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Cliente borrado correctamente", "Borrar un cliente", 1);
            Conexion.desconectar();
        } 
        //hay un error
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error en borrar cliente", "Error en borrar registro de un cliente", 2);
        }
    }
    
    //buscar un cliente
    public void existCliente(){
        try {
            //intento buscar el cliente
            Conexion.buscarCliente=false;
            String sql = "select * from clientes where rut = '"+rut_cliente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            ResultSet rs = Conexion.stm.executeQuery(sql);
            //existe el cliente?
            if(rs.next()){
                Conexion.buscarCliente=true;
                rut_cliente = rs.getString(1);
                nombre= rs.getString(2);
                fono = rs.getString(3);
                borrado = rs.getBoolean(4);
            }
            Conexion.desconectar();
        } 
        //no existe
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error, no existe el cliente", "Buscar un cliente", 2);
        }
    }
    
    //restaurar un cliente
    public void restaurarCliente(){
        try{
            //intento restaurar al cliente
            String sql = "update clientes set is_deleted = "+false+" where rut = '"+rut_cliente+"' ";
            Conexion.conectar();
            Conexion.stm = Conexion.con.prepareStatement(sql);
            Conexion.stm.execute(sql);
            JOptionPane.showMessageDialog(null, "Cliente restaurado correctamente", "Restaurar un cliente", 1);
            Conexion.desconectar();
        }
        //hubo un erro
        catch(Exception ex){
            JOptionPane.showMessageDialog(null, "Error, no se restauró el cliente", "Restaurar un cliente", 2);
        }
    }
}
