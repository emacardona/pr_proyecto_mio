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
import modelo.Idproducto;
import modelo.Idventa;
import modelo.Ventadetalle; 

/**
 *
 * @author emanu
 */
@WebServlet("/sr_venta_detalle")
public class sr_ventadetalle extends HttpServlet {

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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_venta</title>");
            out.println("</head>");
            out.println("<body>");
            
        Ventadetalle ventadetalle = new Ventadetalle();
        Idventa idventa = new Idventa();
        Idproducto idproducto = new Idproducto();
        
        
        String id = request.getParameter("txt_id");
        String id_venta = request.getParameter("drop_idventa");
        String id_producto = request.getParameter("drop_idproducto");
        String cantidad = request.getParameter("txt_cantidad");
        String precio_unitario = request.getParameter("txt_precio_unitario");

        
        // Instanciar solo aquí
        idventa.setId_venta(Integer.parseInt(id_venta));
        idproducto.setId_producto(Integer.parseInt(id_producto));
        ventadetalle.setCantidad(cantidad);
        ventadetalle.setPrecio_unitario(precio_unitario);
        ventadetalle.setId(Integer.parseInt(id));
        
            //boton agregar
            if ("agregar".equals(request.getParameter("btn_agregar"))) {            
            if (ventadetalle.agregar() > 0) {
                response.sendRedirect("index.jsp");
            } else {
                out.println("<h1>Error al agregar...</h1>");
                out.println("<a href = 'index.jsp'>Regresar</a>");
            }   
            }

            // Botón Modificar
            if ("modificar".equals(request.getParameter("btn_modificar"))) {            
            if (ventadetalle.modificar() > 0) {
                response.sendRedirect("index.jsp");
            } else {
                out.println("<h1>Error al modificar...</h1>");
                out.println("<a href = 'index.jsp'>Regresar</a>");
            }   
            }

            // Botón Eliminar
            if ("Eliminar".equals(request.getParameter("btn_eliminar"))) {            
            if (ventadetalle.eliminar() > 0) {
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