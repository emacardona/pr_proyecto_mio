/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import modelo.Idcliente;
import modelo.Idempleado;
import modelo.Idproducto;
import modelo.Venta; 
import modelo.Ventadetalle;

/**
 *
 * @author emanu
 */
@WebServlet(name = "sr_venta", urlPatterns = {"/sr_venta"})
public class sr_venta extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
        Venta venta = new Venta();
        Idcliente idcliente = new Idcliente();
        Idempleado idempleado = new Idempleado();
        
        
        String id = request.getParameter("txt_id");
        String nofactura = request.getParameter("txt_nofactura");
        String serie = request.getParameter("txt_serie");
        String fechafactura = request.getParameter("txt_fechafactura");
        String id_cliente = request.getParameter("drop_idcliente");
        String id_empleado = request.getParameter("drop_idempleado");
        String fechaingreso = request.getParameter("txt_fechaingreso");

        
        // Instanciar solo aquí
        venta.setNofactura(nofactura);
        venta.setSerie(serie);
        venta.setFechafactura(fechafactura);
        idcliente.setId_cliente(Integer.parseInt(id_cliente));
        idempleado.setId_empleado(Integer.parseInt(id_empleado));
        venta.setFechaingreso(fechaingreso);
        venta.setId(Integer.parseInt(id));
        
        
        // Crear la lista de detalles
        List<Ventadetalle> detalles = new ArrayList<>();

        // Capturar los detalles de la venta
        String[] id_productos = request.getParameterValues("drop_idproducto");
        String[] cantidades = request.getParameterValues("txt_cantidad");
        String[] precios_unitarios = request.getParameterValues("txt_precio_unitario");

        // Llenar la lista de detalles
        for (int i = 0; i < id_productos.length; i++) {
            Ventadetalle detalle = new Ventadetalle();
            Idproducto idProducto = new Idproducto(); // Crear el objeto Idproducto
            idProducto.setId_producto(Integer.parseInt(id_productos[i]));

            // Asignar los valores a Ventadetalle
            detalle.setIdproducto(idProducto); 
            detalle.setCantidad(cantidades[i]);
            detalle.setPrecio_unitario(precios_unitarios[i]);

            // Añadir el detalle a la lista
            detalles.add(detalle);
        }
        
            //boton agregar
            if ("agregar".equals(request.getParameter("btn_agregar"))) {            
            if (venta.agregarVentaConDetalle(detalles) > 0) {
                response.sendRedirect("index.jsp");
            } else {
                out.println("<h1>Error al agregar...</h1>");
                out.println("<a href = 'index.jsp'>Regresar</a>");
            }   
            }

            // Botón Modificar
            if ("modificar".equals(request.getParameter("btn_modificar"))) {            
            if (venta.modificar() > 0) {
                response.sendRedirect("index.jsp");
            } else {
                out.println("<h1>Error al modificar...</h1>");
                out.println("<a href = 'index.jsp'>Regresar</a>");
            }   
            }

            // Botón Eliminar
            if ("Eliminar".equals(request.getParameter("btn_eliminar"))) {            
            if (venta.eliminar() > 0) {
                response.sendRedirect("index.jsp");
            } else {
                out.println("<h1>Error al eliminar...</h1>");
                out.println("<a href = 'index.jsp'>Regresar</a>");
            }   
            }
            
            
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
