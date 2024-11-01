<%-- 
    Document   : index
    Created on : 7 sep 2024, 9:23:48 a.m.
    Author     : emanu
--%>

<%@page import="modelo.Venta" %>
<%@page import="modelo.Cliente" %>
<%@page import="modelo.Empleado" %>
<%-- <%@page import="modelo.Productos" %> "Descomentar cuando ya este el crud Productos--%>  
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mantenimiento Ventas</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        </head>
        <body>
            
        <h1 style="text-align: center;">Formulario Ventas</h1>
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_ventas" onclick="limpiar()">Venta Nueva</button>
        
        <div class="modal fade" id="modal_ventas" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Venta Nueva</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="sr_ventas?menu=ventas" method="post">      
                 
                    
             <!-- ID Venta -->
            <div class="form-group mb-3">
            <label for="txt_id_venta"><b>ID de Venta</b></label>
            <input type="text" name="txt_id_venta" id="txt_id_venta" class="form-control" value="" readonly>
            </div>        
              
             
            <!-- No Factura -->
            <div class="form-group mb-3">
            <label for="txt_no_factura"><b>No de Factura</b></label>
            <input type="text" name="txt_no_factura" id="txt_no_factura" class="form-control" value="" readonly>
            </div>
             
            
            <!-- Serie -->
            <div class="form-group mb-3">
            <label for="txt_serie"><b>Serie</b></label>
            <input type="text" name="txt_serie" id="txt_serie" class="form-control" placeholder="Ejemplo: ABC" required>
            </div>

  
            <!-- Fecha de Factura -->
            <div class="form-group mb-3">
            <label for="txt_fecha_factura"><b>Fecha de Factura</b></label>
            <input type="date" name="txt_fecha_factura" id="txt_fecha_factura" class="form-control" required>
            </div>
            

            <!-- Id Cliente -->
            <div class="form-group mb-3">
            <label for="txt_id_cliente"><b>ID de Cliente</b></label> 
                            <select name="txt_id_cliente" id="txt_id_cliente" class="form-control" required>
                                <% 
                                    Cliente cliente = new Cliente();
                                    DefaultTableModel tablaCliente = cliente.leer();
                                    for (int t = 0; t < tablaCliente.getRowCount(); t++) {
                                        out.println("<option value='" + tablaCliente.getValueAt(t, 0) + "'>" + tablaCliente.getValueAt(t, 1) + "</option>");
                                    }
                                %>
                            </select>
            <a href="Cliente.jsp">Ir a mantenimiento de Clientes</a>
            </div>

            <!-- Id Empleado -->
            <div class="form-group mb-3">
            <label for="txt_id_empleado"><b>ID de Empleado</b></label> 
                            <select name="txt_id_empleado" id="txt_id_empleado" class="form-control" required>
                                <% 
                                    Empleado empleado = new Empleado();
                                    DefaultTableModel tablaEmpleado = empleado.leer();
                                    for (int t = 0; t < tablaEmpleado.getRowCount(); t++) {
                                        out.println("<option value='" + tablaEmpleado.getValueAt(t, 0) + "'>" + tablaEmpleado.getValueAt(t, 1) + "</option>");
                                    }
                                %>
                            </select>
            <a href="Cliente.jsp">Ir a mantenimiento de Clientes</a>
            </div>

            <!-- Fecha de Ingreso -->
            <div class="form-group mb-3">
            <label for="txt_fecha_ingreso"><b>Fecha de Ingreso:</b></label>
            <input type="datetime-local" name="txt_fecha_ingreso" id="txt_fecha_ingreso" class="form-control" required>          
            </div>
               
            
            <!-- Detalles de Productos -->
            <h5>Detalles de la Venta</h5>
            <div id="detalles_productos">
     
            <div class="row mb-3 producto-row">
            <div class='col-md-4'>
            <label for='id_producto'><b>Producto:</b></label>
            <select name='id_producto[]' class='form-control' id="selectProductos" required>
                <% 
                    Producto producto = new Producto();
                    DefaultTableModel tablaProducto = producto.leer();
                    for (int t = 0; t < tablaProducto.getRowCount(); t++) {
                    out.println("<option value='" + tablaProducto.getValueAt(t, 0) + "'>" + tablaProducto.getValueAt(t, 1) + "</option>");
                }
                %>
            </select>
            </div> 
            
            <div class="form-group mb-3">
            <label for='cantidad'><b>Cantidad:</b></label>
            <input type='number' name='cantidad[]' min='1' class='form-control' id="txt_cantidad" required/>
            </div>
            
            <div class="form-group mb-3">
            <label for='precio_unitario'><b>Precio Unitario:</b></label>
            <input type='number' step='.01' name='precio_costo_unitario[]' class='form-control' id="txt_precio_unitario" required/>
            </div>
            </div>
            </div> 
                
            <br>
            <div class="form-group mb-3">
            <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
            </div>
            <div class="form-group mb-3">
            <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
            </div>
            <div class="form-group mb-3">
            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger"  onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>        
            </div>
            </div>
            </form>
            </div>
            
            <div class="form-group mb-3">
            <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button>  
            </div>
            </div>
            </div>
            </div>

            <table class="table table-striped">
            <thead>
            <tr>
            <th>ID Venta</th>
            <th>No de Factura</th>
            <th>Serie</th>
            <th>ID de Cliente</th>
            <th>ID de Empleado</th>
            <th>Fecha de Ingreso</th>
            <th>Fecha de Ingreso</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio Unitario</th>
            </tr>
            </thead>
            
      <tbody id="tbl_venta">  
      <%
              Venta venta = new Venta();
              DefaultTableModel tablaVentas = venta.leer();
              for (int t=0;t<tablaVentas.getRowCount();t++) {
              out.println("<tr data-id=" + tablaVentas.getValueAt(t, 0) + "' data-id_p='" + tablaVentas.getValueAt(t, 5) + "' data-id_producto='" + tablaVentas.getValueAt(t, 6) + "'>");
              out.println("<td>" + tablaVentas.getValueAt(t,1) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,2) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,3) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,4) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,5) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,6) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,7) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,8) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,9) + "</td>");
              out.println("<td>" + tablaVentas.getValueAt(t,10) + "</td>");
              out.println("</tr>");
          }
          %>
          
    </tbody>
    </table>
    </div>
                
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
        <script type="text/javascript">
            function limpiar(){
            $("#txt_id_venta").val(0);
            $("#txt_no_factura").val('');
            $("#txt_serie").val(''); 
            $("#txt_fecha_factura").val(''); 
            $("#txt_id_cliente").val(1)
            $("#txt_id_empleado").val(1);
            $("#txt_fecha_ingreso").val('');
            $("#txt_id_producto").val('');
            $("#txt_cantidad").val('');
            $("#txt_precio_unitario").val('');
            }
            
            $('#tbl_venta').on('click', 'tr', function(evt) {
            var target, id_venta,no_factura,serie, fecha_factura,id_cliente,id_empleado, fecha_ingreso,producto, cantidad, precio_unitario;
            id_venta = target.data('id');
            no_factura = target.find("td").eq(1).html();
            serie = target.find("td").eq(2).html(); 
            fecha_factura = target.find("td").eq(3).html(); 
            id_cliente = target.data(id_cliente);
            id_empleado = target.data(id_empleado);
            fecha_ingreso = target.find("td").eq(6).html();
            producto = target.data('id_producto');
            cantidad = target.find("td").eq(8).html();
            precio_unitario = target.find("td").eq(9).html();
            

            $("#txt_id_venta").val(id_venta);
            $("#txt_no_factura").val(no_factura);
            $("#txt_serie").val(serie); 
            $("#txt_fecha_factura").val(fecha_factura); 
            $("#txt_id_cliente").val(id_cliente);
            $("#txt_id_empleado").val(id_empleado);
            $("#txt_fecha_ingreso").val(fecha_ingreso);
            $("#selectProductos").val(producto); 
            $("#txt_cantidad").val(cantidad);
            $("#txt_precio_unitario").val(precio_unitario);
            
            $("#modal_ventas").modal('show');
            });

            </script>
        
    </body>
</html>