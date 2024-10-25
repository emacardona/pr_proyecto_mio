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
public class Idempleado {
    private int id_empleado;
    private String nombre;
    private Conexion cn;
    int get;

    public Idempleado(){}
    public Idempleado(int id_empleado, String nombre) {
        this.id_empleado = id_empleado;
        this.nombre = nombre;
    }
    
    
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public HashMap drop_sangre1(){
        HashMap<String, String> drop = new HashMap();
        try {   
            cn = new Conexion();
            String query= "SELECT id_Empleado as id,nombres FROM empleados;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("nombres"));
            }
            cn.cerrar_conexion();
            
        } catch(SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return drop;
        
        }

    
    
}
