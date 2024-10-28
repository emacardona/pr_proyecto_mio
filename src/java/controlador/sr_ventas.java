/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;
//import modelo.Producto;// "Descomentar, cuando este hecho el crud de productos"
import modelo.Venta;
import modelo.VentasDAO;
import modelo.Ventadetalle;
import modelo.Cliente;
import modelo.Empleado;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author emanu
 */
@WebServlet(name = "sr_ventas", urlPatterns = {"/sr_ventas"})
public class sr_ventas extends HttpServlet {

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
        String menu = request.getParameter("menu"); //aquí almacena la variable del menu
                                                    //depende de la opción que seleccione del menú
                                                    //corre mi metodo "handleNuevaVenta"

        if ("Nueva_venta".equals(menu)) {
        handleNuevaVenta(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
}   
        
        private void handleNuevaVenta(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action = request.getParameter("action");
	VentasDAO ventasDAO = new VentasDAO(); // Crear instancia de ComprasDAO
	// Obtener los parámetros de venta

        String serie = request.getParameter("txt_serie");
        String fechaFactura = request.getParameter("txt_fecha_factura");
	String idClienteStr = request.getParameter("txt_id_cliente"); // ID del cliente
        String idEmpleadoStr = request.getParameter("txt_id_empleado"); // ID del empleado
        String fechaIngreso = request.getParameter("txt_fecha_ingreso");
        String idVentaStr = request.getParameter("txt_id_venta"); // ID de la venta, solo en caso de actualización

	// Validar que los campos requeridos no estén vacíos
	if(serie == null || serie.isEmpty() || fechaFactura == null || fechaFactura.isEmpty() || idClienteStr == null || idClienteStr.isEmpty() || idEmpleadoStr == null || idEmpleadoStr.isEmpty() || fechaIngreso == null || fechaIngreso.isEmpty() || (action.equals("actualizar") && (idVentaStr == null || idVentaStr.isEmpty()))) { // Validar idCompraStr solo si se actualiza
		request.getRequestDispatcher("index.jsp").forward(request, response);
		return;
	}

	int idCliente;
        int idEmpleado;
	int idVenta = -1; // Valor por defecto
	try {
		idCliente = Integer.parseInt(idClienteStr);
		idEmpleado = Integer.parseInt(idEmpleadoStr);

		Venta nuevaVenta;
		switch(action) {
			case "agregar":
				Venta ventaInstance = new Venta();
				int nuevoNumeroFactura = ventaInstance.obtenerUltimoNum() + 1;
				// Crear una nueva instancia de Compra
				nuevaVenta = new Venta(nuevoNumeroFactura, nuevoNumeroFactura, serie, fechaFactura, idCliente,idEmpleado,fechaIngreso);
				// Aquí debes recoger los detalles (productos) que se van a agregar
				List <Ventadetalle> detalles = obtenerDetallesDesdeFormulario(request); // Método para obtener detalles
				// Agregar la compra y sus detalles
				ventasDAO.agregarVentaYDetalles(nuevaVenta, detalles); // Llama al nuevo método
				response.sendRedirect("index.jsp");
				break;

			case "actualizar":
				idVenta = Integer.parseInt(idVentaStr); // Obtener el ID de compra para actualizar
				
                                Venta ventaExistente = ventasDAO.obtenerVentaPorId(idVenta);
				if(ventaExistente != null) {
					// Crear una nueva instancia de Compra con los datos actualizados
					nuevaVenta = new Venta(idVenta, ventaExistente.getNo_factura(), serie, fechaFactura, idCliente,idEmpleado,fechaIngreso);
					// Aquí debes recoger los detalles actualizados desde el formulario
					List <Ventadetalle> detallesActualizados = obtenerDetallesDesdeFormulario(request); // Método para obtener detalles actualizados
					// Actualizar la compra y sus detalles
					ventasDAO.actualizarVentaYDetalles(nuevaVenta, detallesActualizados); // Llama al nuevo método
					response.sendRedirect("index.jsp");
				} else {
					response.getWriter().println("<h1>No se encontró la Venta</h1>");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				break;
			case "eliminar":
				idVenta = Integer.parseInt(idVentaStr); // Obtener el ID de compra para eliminar
				if(ventasDAO.eliminarVentaYDetalles(idVenta)) { // Llama al nuevo método
					response.sendRedirect("index.jsp");
				} else {
					response.getWriter().println("<h1>No se pudo eliminar la compra</h1>");
					request.getRequestDispatcher("index.jsp").forward(request, response);
				}
				break;
			default:
				response.sendRedirect("index.jsp");
				break;
		}
	} catch (NumberFormatException e) {
		response.sendRedirect("index.jsp");
	}
}
     
         @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }


    
     private List<Ventadetalle> obtenerDetallesDesdeFormulario(HttpServletRequest request) {
    List<Ventadetalle> detalles = new ArrayList<>();
    
    String[] idsProductos = request.getParameterValues("id_producto[]");
    String[] cantidades = request.getParameterValues("cantidad[]"); 
    String[] preciosUnitarios = request.getParameterValues("precio_unitario[]");

    if (idsProductos != null && cantidades != null && preciosUnitarios != null) {
        for (int i = 0; i < idsProductos.length; i++) {
            Ventadetalle detalle = new Ventadetalle();
            detalle.setId_producto(Integer.parseInt(idsProductos[i])); // Establecer ID del producto
            detalle.setCantidad(Integer.parseInt(cantidades[i])); // Establecer cantidad
            detalle.setPrecio_unitario(Double.parseDouble(preciosUnitarios[i])); // Establecer precio unitario
            
            detalles.add(detalle); // Agregar a la lista
        }
    }
    return detalles; // Devolver la lista de detalles
    }             
}
