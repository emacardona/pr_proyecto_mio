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
public class Idventa {
    private int id_venta;
    private int no_factura;
    private Conexion cn;

    public Idventa(){}
    public Idventa(int id_venta, int no_factura) {
        this.id_venta = id_venta;
        this.no_factura = no_factura;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }
    
    public HashMap drop_sangre3(){
        HashMap<String, String> drop = new HashMap();
        try {   
            cn = new Conexion();
            String query= "SELECT id_Venta as id,no_factura FROM ventas;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("no_factura"));
            }
            cn.cerrar_conexion();
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return drop;
        
        }
    
}
