<%-- 
    Document   : index
    Created on : 7 sep 2024, 9:23:48 a.m.
    Author     : emanu
--%>
<%@page import="modelo.Idcliente" %>
<%@page import="modelo.Idempleado" %>
<%@page import="modelo.Idproducto" %>
<%@page import="modelo.Idventa" %>
<%@page import="modelo.Venta" %>
<%@page import="modelo.Ventadetalle" %>
<%@page import="java.util.HashMap" %>
<%@page import="javax.swing.table.DefaultTableModel" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ventas</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
        </head>
        <body>
            
        <h1 style="text-align: center;">Formulario Ventas</h1>
        <button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_ventas" onclick="limpiar()">Nueva Venta</button>
        
        <div class="container">
        <div class="modal fade" id="modal_ventas" role="dialog">
        <div class="modal-dialog">
            
        <!-- Modal body -->
        <div class="modal-content">
        <div class="modal-body">
        <form action="sr_venta" method="post" class="form-group">
              
        
            <!-- ID -->
            <div class="form-group mb-3">
            <label for="lbl_id"><b>ID</b></label>
            <input type="text" name="txt_id" id="txt_id" class="form-control" value="0" readonly>
            </div>

            <!-- No. Factura -->
            <div class="form-group mb-3">
            <label for="lbl_nofactura"><b>No. Factura</b></label>
            <input type="text" name="txt_nofactura" id="txt_nofactura" class="form-control" placeholder="Ejemplo: 75486" required>
            </div>

            <!-- Serie -->
            <div class="form-group mb-3">
            <label for="lbl_serie"><b>Serie</b></label>
            <input type="text" name="txt_serie" id="txt_serie" class="form-control" placeholder="Ejemplo: 1475549493" required>
            </div>

            <!-- Fecha de Factura -->
            <div class="form-group mb-3">
            <label for="lbl_fechafactura"><b>Fecha de Factura</b></label>
            <input type="date" name="txt_fechafactura" id="txt_fechafactura" class="form-control" required>
            </div>

            <!-- Id Cliente -->
            <div class="form-group mb-3">
            <label for="lbl_idcliente"><b>Id Cliente</b></label>
            <select name="drop_idcliente" id="drop_idcliente" class="form-control">

                <%
                    Idcliente idcliente = new Idcliente();
                    HashMap<String, String> drop = idcliente.drop_sangre();
                    for (String i: drop.keySet()) {
                    out.println("<option value='" + i + "' >" + drop.get(i) + "</option>");
                    }
                %>
                
            </select>
            <a href="mantenimientoClientes.jsp">Ir a mantenimiento de Clientes</a>
            </div>

            <!-- Id Empleado -->
            <div class="form-group mb-3">
            <label for="lbl_idempleado"><b>Id Empleado</b></label>
            <select name="drop_idempleado" id="drop_idempleado" class="form-control">
                
                <%
                    Idempleado idempleado = new Idempleado();
                    HashMap<String, String> dropEmpleados = idempleado.drop_sangre1();
                    for (String i: dropEmpleados.keySet()) {
                    out.println("<option value='" + i + "' >" + dropEmpleados.get(i) + "</option>");
                    }
                %>
                
            </select>
            <a href="mantenimientoEmpleados.jsp">Ir a mantenimiento de Empleados</a>
            </div>

            <!-- Fecha de Ingreso -->
            <div class="form-group mb-3">
            <label for="lbl_fechaingreso"><b>Fecha de Ingreso</b></label>
            <input type="date" name="txt_fechaingreso" id="txt_fechaingreso" class="form-control" required>
            </div>
               
            <br>
            <button name="btn_agregar" id="btn_agregar" value="agregar" class="btn btn-primary">Agregar</button>
            <button name="btn_modificar" id="btn_modificar" value="modificar" class="btn btn-success">Modificar</button>
            <button name="btn_eliminar" id="btn_eliminar" value="eliminar" class="btn btn-danger"  onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar</button>        
            <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button>  
          </form>
        </div>
        
      </div>
    </div>
  </div> 
            
                
            <h1 style="text-align: center;">Formulario Ventas Detalle</h1>
<button type="button" class="btn btn-info btn-lg" data-toggle="modal" data-target="#modal_ventas_detalle" onclick="limpiar()">Nuevo Detalle de Venta</button>

<div class="container">
  <div class="modal fade" id="modal_ventas_detalle" role="dialog">
    <div class="modal-dialog">
      
      <!-- Modal body -->
      <div class="modal-content">
        <div class="modal-body">
          <form action="sr_venta_detalle" method="post" class="form-group">
            
            <!-- ID -->
            <div class="form-group mb-3">
              <label for="lbl_id_detalle"><b>ID</b></label>
              <input type="text" name="txt_id_detalle" id="txt_id_detalle" class="form-control" value="0" readonly>
            </div>

            <!-- ID Venta -->
            <div class="form-group mb-3">
            <label for="lbl_idventa"><b>Id Venta</b></label>
            <select name="drop_idventa" id="drop_idventa" class="form-control">
                
                <%
                    Idventa idventa = new Idventa();
                    HashMap<String, String> dropVentas = idventa.drop_sangre3();
                    for (String i: dropVentas.keySet()) {
                    out.println("<option value='" + i + "' >" + dropVentas.get(i) + "</option>");
                    }
                %>
                
            </select>
            </div>

            <!-- ID Producto -->
            <div class="form-group mb-3">
            <label for="lbl_idproducto"><b>Id Producto</b></label>
            <select name="drop_idproducto" id="drop_idproducto" class="form-control">
                
                <%
                    Idproducto idproducto = new Idproducto();
                    HashMap<String, String> dropProductos = idproducto.drop_sangre2();
                    for (String i: dropProductos.keySet()) {
                    out.println("<option value='" + i + "' >" + dropProductos.get(i) + "</option>");
                    }
                %>
                
            </select>
            <a href="mantenimientoEmpleados.jsp">Ir a mantenimiento de Empleados</a>
            </div>

            <!-- Cantidad -->
            <div class="form-group mb-3">
              <label for="lbl_cantidad"><b>Cantidad</b></label>
              <input type="number" name="txt_cantidad" id="txt_cantidad" class="form-control" required>
            </div>

            <!-- Precio Unitario -->
            <div class="form-group mb-3">
              <label for="lbl_precio_unitario"><b>Precio Unitario</b></label>
              <input type="number" name="txt_precio_unitario" id="txt_precio_unitario" class="form-control" required>
            </div>

            <!-- Subtotal -->
            <div class="form-group mb-3">
              <label for="lbl_subtotal"><b>Subtotal</b></label>
              <input type="number" name="txt_subtotal" id="txt_subtotal" class="form-control" readonly>
            </div>

            <br>
            <button name="btn_agregar_detalle" id="btn_agregar_detalle" value="agregar" class="btn btn-primary">Agregar Detalle</button>
            <button name="btn_modificar_detalle" id="btn_modificar_detalle" value="modificar" class="btn btn-success">Modificar Detalle</button>
            <button name="btn_eliminar_detalle" id="btn_eliminar_detalle" value="eliminar" class="btn btn-danger" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">Eliminar Detalle</button>
            <button type="button" class="btn btn-dark" data-dismiss="modal">Cerrar</button>
          </form>
        </div>
      </div>
    </div>
  </div>
</div>
    

        
      
            <table class="table table-striped">
            <thead>
            <tr>
            <th>No. Factura</th>
            <th>Serie</th>
            <th>Fecha de Factura</th>
            <th>Id Cliente</th>
            <th>Id Empledo</th>
            <th>Fecha de Ingreso</th>
            </tr>
            </thead>
            
      <tbody id="tbl_venta">  
      <%
              Venta venta = new Venta();
              DefaultTableModel tabla = venta.leer();
              for (int t=0;t<tabla.getRowCount();t++) {
              out.println("<tr data-id=" + tabla.getValueAt(t, 0) + ">");
              out.println("<td>" + tabla.getValueAt(t,1) + "</td>");
              out.println("<td>" + tabla.getValueAt(t,2) + "</td>");
              out.println("<td>" + tabla.getValueAt(t,3) + "</td>");
              out.println("<td>" + tabla.getValueAt(t,4) + "</td>");
              out.println("<td>" + tabla.getValueAt(t,5) + "</td>");
              out.println("<td>" + tabla.getValueAt(t,6) + "</td>");
              out.println("</tr>");
          }
          %>
          
    </tbody>
    </table>
          
    <h2 style="text-align: center;">Detalles de Venta</h2>
<table class="table table-striped">
    <thead>
        <tr>
            <th>ID</th>
            <th>ID Venta</th>
            <th>Producto</th>
            <th>Cantidad</th>
            <th>Precio</th>
        </tr>
    </thead>
    <tbody id="tbl_venta_detalle">
        <% 
            Ventadetalle ventaDetalle = new Ventadetalle();
            DefaultTableModel tablaDetalle = ventaDetalle.leer(); 
            for (int t = 0; t < tablaDetalle.getRowCount(); t++) {
                out.println("<tr data-id='" + tablaDetalle.getValueAt(t, 0) + "'>");
                out.println("<td>" + tablaDetalle.getValueAt(t, 1) + "</td>");
                out.println("<td>" + tablaDetalle.getValueAt(t, 2) + "</td>");
                out.println("<td>" + tablaDetalle.getValueAt(t, 3) + "</td>");
                out.println("<td>" + tablaDetalle.getValueAt(t, 4) + "</td>");
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
            $("#txt_id").val(0);
            $("#txt_nofactura").val('');
            $("#txt_serie").val(''); 
            $("#txt_fechafactura").val(''); 
            $("#drop_idcliente").val(1)
            $("#drop_idempleado").val(1);
            $("#txt_fechaingreso").val('');
                     
            }
            
            $('#tbl_venta').on('click', 'tr', function(evt) {
            var target = $(evt.target).closest('tr');
            var id = target.data('id');
            var nofactura = target.find("td").eq(0).html();
            var serie = target.find("td").eq(1).html(); 
            var fechafactura = target.find("td").eq(2).html(); 
            var idcliente = target.find("td").eq(3).html();
            var idempleado = target.find("td").eq(4).html();
            var fechaingreso = target.find("td").eq(5).html();
            

            $("#txt_id").val(id);
            $("#txt_nofactura").val(nofactura);
            $("#txt_serie").val(serie); 
            $("#txt_fechafactura").val(fechafactura); 
            $("#drop_idpuesto").val(idcliente);
            $("#drop_idempleado").val(idempleado);
            $("#txt_fechaingreso").val(fechaingreso);
            $("#modal_ventas").modal('show');
            });
            
            
            function limpiar1(){
            $("#txt_id_detalle").val(0);
            $("#drop_idventa").val('');
            $("#drop_idproducto").val(1); 
            $("#txt_cantidad").val(''); 
            $("#txt_precio_unitario").val('')      
            }
            
            $('#tbl_venta_detalle').on('click', 'tr', function(evt) {
            var target = $(evt.target).closest('tr');
            var id = target.data('id');
            var idVenta = target.find("td").eq(1).html();
            var idproducto = target.find("td").eq(2).html();
            var cantidad = target.find("td").eq(3).html();
            var precio_unitario = target.find("td").eq(4).html();

            $("#txt_id_detalle").val(id);
            $("#drop_idventa").val(idVenta);
            $("#drop_idproducto").val(idproducto);
            $("#txt_cantidad").val(cantidad);
            $("#txt_precio_unitario ").val(precio_unitario);
            $("#modal_venta_detalle").modal('show');
            });

            </script>
        
    </body>
</html>
