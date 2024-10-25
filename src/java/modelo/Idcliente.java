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
public class Idcliente {
    private int id_cliente;
    private String nombre;
    private Conexion cn;

    public Idcliente(){}
    public Idcliente(int id_cliente, String nombre) {
        this.id_cliente = id_cliente;
        this.nombre = nombre;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public HashMap drop_sangre(){
        HashMap<String, String> drop = new HashMap();
        try {   
            cn = new Conexion();
            String query= "SELECT id_Cliente as id,nombres, NIT FROM clientes;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                String idCliente = consulta.getString("id");
                String nombresConNIT = idCliente + " - " + consulta.getString("nombres") + " - NIT: " + consulta.getString("NIT");
                drop.put(idCliente, nombresConNIT);  // Guarda el id_cliente como clave y el texto concatenado como valor
            }
            cn.cerrar_conexion();
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return drop;
        
        }
    
}
