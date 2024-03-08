package Controladores;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

//librerias sql 
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author atude
 */

public class Conexion {
    //variables para la conexión
    public static String URL = "jdbc:mysql://localhost:3306/tsi";
    public static String USER = "root";
    public static String PASS = "";
    public static Connection con;
    public static Statement stm;
    public static Boolean buscarCliente;
    public static Boolean buscarVehiculo;
    public static Boolean buscarProducto;
    public static Boolean buscarServicio;
    public static Boolean buscarUser;
    public static Boolean buscarBitacora;
    public static String rut_user;
     
    //conectar a la DB
    public static void conectar(){
        //intento de conexión
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL, USER, PASS);
            //JOptionPane.showMessageDialog(null,"conectado con exito");
        }
        //error en conectar
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Error: " + e.getMessage(), "Error de conexión", 2);
        }
    }
    
    //descpnectar de la DB
    public static void desconectar() throws SQLException{
        con.close();
    }
}
