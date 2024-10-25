/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emanu
 */
public class Ventadetalle {
    private String cantidad, precio_unitario;
    private Idventa idventa;
    private Idproducto idproducto;
    private int id;
    private Conexion cn;
    
    public Ventadetalle(){}

    public Ventadetalle(String cantidad, String precio_unitario, Idventa idventa, Idproducto idproducto, int id) {
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
        this.idventa = idventa;
        this.idproducto = idproducto;
        this.id = id;
    }
    
    public Idventa getIdventa() {
        return idventa;
    }

    public void setIdventa(Idventa idventa) {
        this.idventa = idventa;
    }

    public Idproducto getIdproducto() {
        return idproducto;
    }

    public void setIdproducto(Idproducto idproducto) {
        this.idproducto = idproducto;
    }
    
    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(String precio_unitario) {
        this.precio_unitario = precio_unitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();

        String query = "SELECT id_Venta_detalle as id, id_Venta, id_Producto, cantidad, precio_unitario FROM ventas_detalle;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

        String encabezado[] = {"Codigo de Detalle de Venta", "Codigo de Venta", "Codigo de Producto", "cantidad", "precio_unitario"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String[5];
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("id_venta");
            datos[2] = consulta.getString("id_producto");
            datos[3] = consulta.getString("cantidad");
            datos[4] = consulta.getString("precio_unitario");
            tabla.addRow(datos);
        }

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return tabla;
}

   public int agregar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "INSERT INTO ventas_detalle(id_venta, id_producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, this.idventa.getId_venta());
        parametro.setInt(2, this.idproducto.getId_producto());
        parametro.setString(3, this.getCantidad());
        parametro.setString(4, this.getPrecio_unitario());

        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
        
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        retorno = 0;
        }
    
        return retorno;
}
    
   
       
   
   public int modificar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "UPDATE ventas_detalle SET id_Venta=?,id_Producto=?,cantidad=?,precio_unitario=? WHERE id_Venta_detalle=?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, this.idventa.getId_venta());
        parametro.setInt(2, this.idproducto.getId_producto());
        parametro.setString(3, this.getCantidad());
        parametro.setString(4, this.getPrecio_unitario());
        parametro.setInt(7, getId());
        
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
        
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        retorno = 0;
        }
    
        return retorno;
}
   
   
   public int eliminar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "DELETE FROM ventas_detalle WHERE id_Venta_detalle=?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setInt(1, getId());
        retorno = parametro.executeUpdate();
        cn.cerrar_conexion();
        
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
        retorno = 0;
        }
        return retorno;
    }

    

    

    

    
}
