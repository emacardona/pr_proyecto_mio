/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.Venta;
import jakarta.servlet.RequestDispatcher;
/**
 *
 * @author DELL
 *//**
 *
 * @author DELL
 */
public class sr_controlador extends HttpServlet {
Empleado empleado = new Empleado();
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O errprotected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletExceptor occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            String accion=request.getParameter("accion");
            String menu=request.getParameter("menu");
            if(menu.equals("Principal")) {
                request.getRequestDispatcher("Principal.jsp").forward(request, response);
            }   
            else if (menu.equals("Empleado")) {
                request.getRequestDispatcher("Empleado.jsp").forward(request, response);
                // Obtener los parámetros del formulario
                int id = Integer.parseInt(request.getParameter("txt_id"));
                String nombres = request.getParameter("txt_nombres");
                String apellidos = request.getParameter("txt_apellidos");
                String direccion = request.getParameter("txt_direccion");
                String telefono = request.getParameter("txt_telefono");
                String dpi = request.getParameter("txt_dpi");
                boolean genero = Boolean.parseBoolean(request.getParameter("txt_genero"));
                String fechaNacimiento = request.getParameter("txt_fn");
                int puesto = Integer.parseInt(request.getParameter("drop_puesto"));
                String fechaInicioLabores = request.getParameter("txt_fecha_inicio");
                String fechaIngreso = request.getParameter("txt_fecha_ingreso");

                // Crear instancia de Empleado con los datos del formulario
                empleado = new Empleado(id, nombres, apellidos, direccion, telefono, dpi, genero, fechaNacimiento, puesto, fechaInicioLabores, fechaIngreso);
                
                String action = request.getParameter("action");

                switch (action) {
                    case "agregar":
                        if (empleado.agregar() > 0) {
                            response.sendRedirect("Empleado.jsp");
                        } else {
                            response.getWriter().println("<h1>No se agregó</h1>");
                            response.getWriter().println("<a href='Empleado.jsp'>Regresar</a>");
                        }
                        break;

                    case "actualizar":
                        if (empleado.actualizar() > 0) {
                            response.sendRedirect("Empleado.jsp");
                        } else {
                            response.getWriter().println("<h1>No se actualizó</h1>");
                            response.getWriter().println("<a href='Empleado.jsp'>Regresar</a>");
                        }
                        break;

                    case "eliminar":
                        if (empleado.eliminar() > 0) {
                            response.sendRedirect("Empleado.jsp");
                        } else {
                            response.getWriter().println("<h1>No se eliminó</h1>");
                            response.getWriter().println("<a href='Empleado.jsp'>Regresar</a>");
                        }
                        break;

                    default:
                        response.getWriter().println("<h1>Acción no válida</h1>");
                        response.getWriter().println("<a href='Empleado.jsp'>Regresar</a>");
                        break;
                }
            }
                
                        
            else if(menu.equals("Cliente")) {
                request.getRequestDispatcher("Cliente.jsp").forward(request, response);
            }  
            else if(menu.equals("Producto")) {
                request.getRequestDispatcher("Producto.jsp").forward(request, response);
            }   
            else if(menu.equals("Nueva_compra")) {
                RequestDispatcher dispatcher = request.getRequestDispatcher("/sr_cCompras?menu=Nueva_compra");
    dispatcher.forward(request, response);
} else if(menu.equals("Proveedores")) {
    // Redirigir al nuevo servlet para manejar proveedores
    RequestDispatcher dispatcher = request.getRequestDispatcher("/sr_cCompras?menu=Proveedores");
    dispatcher.forward(request, response);
            }
            else if(menu.equals("Nueva_venta")) {
                request.getRequestDispatcher("Registro_venta.jsp").forward(request, response);
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
