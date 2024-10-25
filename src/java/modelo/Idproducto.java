/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

/**
 *
 * @author emanu
 */
public class Idproducto {
    private int id_producto;
    private String producto;
    private Conexion cn;

    public Idproducto(){}
    public Idproducto(int id_producto, String producto) {
        this.id_producto = id_producto;
        this.producto = producto;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }
    
    public HashMap drop_sangre2(){
        HashMap<String, String> drop = new HashMap();
        try {   
            cn = new Conexion();
            String query= "SELECT id_Producto as id,producto FROM productos;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("producto"));
            }
            cn.cerrar_conexion();
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return drop;
        
        }
    
}
