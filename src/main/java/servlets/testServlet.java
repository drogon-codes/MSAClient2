/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import clients.myclient;
import entities.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Objects;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.eclipse.microprofile.rest.client.inject.RestClient;

/**
 *
 * @author c computer
 */
@WebServlet(name = "testServlet", urlPatterns = {"/testServlet"})
public class testServlet extends HttpServlet {
    @Inject @RestClient myclient mc;
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet testServlet</title>");            
            out.println("</head>");
            out.println("<body>");
//            out.println("<h1>Servlet testServlet at " + mc.getHello() + "</h1>");
            
            out.println("<form method='GET'>");
            out.println("<select name='txtSearch'>");
            Collection<Category> cats = null;
            cats = mc.getCategory();
            for (Category cat : cats) {
                out.println("<option value=" + cat.getCatid() + ">"+cat.getCategoryname()+"</option>");
            }
            out.println("</select>");
            out.println("<input type='submit' name='btnSubmit' value='Search'>");
            out.println("</form>");
            Integer catId = Integer.parseInt(request.getParameter("txtSearch"));
            Collection<Garments> garments = mc.getGarmentsByCatId(catId);
            Collection<TblOrder> orders = mc.getOrders();
            out.println("<table border='1'><tr><th>Garment Id</th><th>Category</th><th>Price</th><th>Stock</th></tr>");
            out.println("<tr>");
            for (Garments garment : garments) {
                out.println("<td>"+garment.getGarmentid()+"</td>");
                out.println("<td>"+garment.getCatid().getCategoryname()+"</td>");
                out.println("<td>"+garment.getPrice()+"</td>");
                out.println("<td>"+garment.getStock()+"</td>");
                out.println("</tr>");
                out.println("</table>");
                out.println("<table border='1'>");
                out.println("<th>Customer Name</th><th>Mobile Number</th><th>Order Date</th><th>City</th>");
                for (TblOrder order : orders) {
                    if(garment.getGarmentid()==order.getGarmentId().getGarmentid()  ){
                        out.println("<tr>");
                        out.println("<td>"+order.getCustomerId().getCustomerName()+"</td>");
                        out.println("<td>"+order.getCustomerId().getMobile()+"</td>");
                        out.println("<td>"+order.getDate()+"</td>");
                        out.println("<td>"+order.getCustomerId().getCity()+"</td>");
                        out.println("</tr>");
                    }
                }
            }
            out.println("</table>");
//            out.println("<h1>"+catId+"</h1>");
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
