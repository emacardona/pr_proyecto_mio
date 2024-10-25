/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.util.List;              // Para utilizar List (para manejar colecciones de Ventadetalle)
import java.sql.Connection;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.HashMap;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author emanu
 */
    
    public class Venta {
    private String nofactura, serie, fechafactura, fechaingreso;
    private Idcliente idcliente;
    private Idempleado idempleado;
    private int id;
    private Conexion cn;

    public Venta(){}

    public Venta(String nofactura, String serie, String fechafactura, String fechaingreso, Idcliente idcliente, Idempleado idempleado, int id) {
        this.nofactura = nofactura;
        this.serie = serie;
        this.fechafactura = fechafactura;
        this.fechaingreso = fechaingreso;
        this.idcliente = idcliente;
        this.idempleado = idempleado;
        this.id = id;
    }
    
    
     public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Idcliente getIdcliente() {
        return idcliente;
    }

    public void setIdcliente(Idcliente idcliente) {
        this.idcliente = idcliente;
    }

    public Idempleado getIdempleado() {
        return idempleado;
    }

    public void setIdempleado(Idempleado idempleado) {
        this.idempleado = idempleado;
    }

    public String getNofactura() {
        return nofactura;
    }

    public void setNofactura(String nofactura) {
        this.nofactura = nofactura;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFechafactura() {
        return fechafactura;
    }

    public void setFechafactura(String fechafactura) {
        this.fechafactura = fechafactura;
    }

    public String getFechaingreso() {
        return fechaingreso;
    }

    public void setFechaingreso(String fechaingreso) {
        this.fechaingreso = fechaingreso;
    }
    
    
   public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
    try {
        cn = new Conexion();
        cn.abrir_conexion();

        String query = "SELECT id_Venta as id, no_factura, serie, fecha_factura, id_Cliente, id_Empleado, fecha_ingreso FROM ventas;";
        ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);

        String encabezado[] = {"Codigo de Venta", "No. Factura", "No. Serie", "Fecha de Factura", "Codigo del Cliente", "Codigo del Empleado", "Fecha de Ingreso"};
        tabla.setColumnIdentifiers(encabezado);
        String datos[] = new String[7];
        while (consulta.next()) {
            datos[0] = consulta.getString("id");
            datos[1] = consulta.getString("no_factura");
            datos[2] = consulta.getString("serie");
            datos[3] = consulta.getString("fecha_factura");
            datos[4] = consulta.getString("id_Cliente");      
            datos[5] = consulta.getString("id_Empleado");     
            datos[6] = consulta.getString("fecha_ingreso");
            tabla.addRow(datos);
        }

        cn.cerrar_conexion();
    } catch (SQLException ex) {
        System.out.println(ex.getMessage());
    }
    return tabla;
}
   
   
   public int agregarVentaConDetalle(List<Ventadetalle> detalles) {
    int retorno = 0;
    PreparedStatement parametroVenta = null;
    PreparedStatement parametroObtenerId = null;
    ResultSet rs = null;
    try {
        cn = new Conexion();
        cn.abrir_conexion(); // Abre la conexión

        // Iniciar transacción
        cn.conexionBD.setAutoCommit(false); // Inicia la transacción

        // Insertar en la tabla 'ventas'
        String queryVenta = "INSERT INTO ventas(no_factura, serie, fecha_factura, id_Cliente, id_Empleado, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?);";
        parametroVenta = cn.conexionBD.prepareStatement(queryVenta);
        parametroVenta.setString(1, this.getNofactura());
        parametroVenta.setString(2, this.getSerie());
        parametroVenta.setString(3, this.getFechafactura());
        parametroVenta.setInt(4, this.idcliente.getId_cliente());
        parametroVenta.setInt(5, this.idempleado.getId_empleado());
        parametroVenta.setString(6, this.getFechaingreso());
        retorno = parametroVenta.executeUpdate();
        System.out.println("Venta insertada: " + retorno);

        // Obtener el ID de la venta recién insertada
        String queryObtenerIdVenta = "SELECT LAST_INSERT_ID()";
        parametroObtenerId = cn.conexionBD.prepareStatement(queryObtenerIdVenta);
        rs = parametroObtenerId.executeQuery();
        int idVenta = 0;
        if (rs.next()) {
            idVenta = rs.getInt(1);
            System.out.println("ID de la venta insertada: " + idVenta);
        }

        // Insertar en la tabla 'ventas_detalle'
        String queryDetalle = "INSERT INTO ventas_detalle(id_Venta, id_Producto, cantidad, precio_unitario) VALUES (?, ?, ?, ?);";
        PreparedStatement parametroDetalle = cn.conexionBD.prepareStatement(queryDetalle);

        for (Ventadetalle detalle : detalles) {
            parametroDetalle.setInt(1, idVenta);
            parametroDetalle.setInt(2, detalle.getIdproducto().getId_producto());
            parametroDetalle.setString(3, detalle.getCantidad());
            parametroDetalle.setString(4, detalle.getPrecio_unitario());
            retorno += parametroDetalle.executeUpdate();
            System.out.println("Detalle insertado: " + retorno);
        }

        // Confirmar la transacción
        cn.conexionBD.commit();
        System.out.println("Transacción completada con éxito.");

    } catch (SQLException ex) {
        try {
            // Si ocurre un error, se revierte la transacción
            System.out.println("Error en la transacción, realizando rollback: " + ex.getMessage());
            if (cn.conexionBD != null) {
                cn.conexionBD.rollback();
            }
        } catch (SQLException e) {
            System.out.println("Error al realizar rollback: " + e.getMessage());
        }
        retorno = 0;
    } finally {
        try {
            if (rs != null) rs.close();
            if (parametroVenta != null) parametroVenta.close();
            if (parametroObtenerId != null) parametroObtenerId.close();
            cn.cerrar_conexion();
        } catch (SQLException e) {
            System.out.println("Error al cerrar conexión: " + e.getMessage());
        }
    }

    return retorno;
} 


     /* public int agregar() {
     int retorno = 0;
     try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "INSERT INTO ventas(no_factura, serie, fecha_factura, id_Cliente, id_Empleado, fecha_ingreso) VALUES (?, ?, ?, ?, ?, ?);";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, this.getNofactura());
        parametro.setString(2, this.getSerie());
        parametro.setString(3, this.getFechafactura());
        parametro.setInt(4, this.idcliente.getId_cliente());
        parametro.setInt(5, this.idempleado.getId_empleado());
        parametro.setString(6, this.getFechaingreso());
        

        
         retorno = parametro.executeUpdate();
         cn.cerrar_conexion();
        
        } catch (SQLException ex) {
        System.out.println(ex.getMessage());
       retorno = 0;
        }
    
        return retorno;
 } */

    public int modificar() {
    int retorno = 0;
    try {
        PreparedStatement parametro;
        cn = new Conexion();
        String query = "UPDATE ventas SET no_factura=?,serie=?,fecha_factura=?,id_Cliente=?,id_Empleado=?,fecha_ingreso=? WHERE id_Venta=?;";
        cn.abrir_conexion();
        parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
        parametro.setString(1, getNofactura());
        parametro.setString(2, getSerie());
        parametro.setString(3, getFechafactura());
        parametro.setInt(4, this.idcliente.getId_cliente());
        parametro.setInt(5, this.idempleado.getId_empleado());
        parametro.setString(6, getFechaingreso());
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
        String query = "DELETE FROM ventas WHERE id_Venta=?;";
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
