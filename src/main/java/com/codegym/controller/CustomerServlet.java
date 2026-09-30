package com.codegym.controller;

import com.codegym.model.Customer;
import com.codegym.service.CustomerService;
import com.codegym.service.CustomerServiceImpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
public class CustomerServlet extends HttpServlet {
    private final CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "create": showCreateForm(request,response); break;
            case "edit": showEditForm(request,response); break;
            case "delete": showDeleteForm(request,response); break;
            case "view": viewCustomer(request,response); break;
            default: listCustomers(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        if ("create".equals(action)) createCustomer(request,response);
        else if ("edit".equals(action)) updateCustomer(request,response);
        else if ("delete".equals(action)) deleteCustomer(request,response);
    }

    private void listCustomers(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        List<Customer> customers=customerService.findAll();
        request.setAttribute("customers",customers);
        request.getRequestDispatcher("/customer/list.jsp").forward(request,response);
    }

    private void showCreateForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException { request.getRequestDispatcher("/customer/create.jsp").forward(request,response); }

    private void showEditForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=customerService.findById(id);
        if (customer==null) { request.getRequestDispatcher("/error-404.jsp").forward(request,response); return; }
        request.setAttribute("customer",customer);
        request.getRequestDispatcher("/customer/edit.jsp").forward(request,response);
    }

    private void showDeleteForm(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=customerService.findById(id);
        if (customer==null) { request.getRequestDispatcher("/error-404.jsp").forward(request,response); return; }
        request.setAttribute("customer",customer);
        request.getRequestDispatcher("/customer/delete.jsp").forward(request,response);
    }

    private void viewCustomer(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=customerService.findById(id);
        if (customer==null) { request.getRequestDispatcher("/error-404.jsp").forward(request,response); return; }
        request.setAttribute("customer",customer);
        request.getRequestDispatcher("/customer/view.jsp").forward(request,response);
    }

    private void createCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id=(int)(Math.random()*10000);
        customerService.save(new Customer(id,request.getParameter("name"),request.getParameter("email"),request.getParameter("address")));
        response.sendRedirect(request.getContextPath() + "/customers");
    }

    private void updateCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        Customer customer=new Customer(id,request.getParameter("name"),request.getParameter("email"),request.getParameter("address"));
        customerService.update(id,customer);
        response.sendRedirect(request.getContextPath() + "/customers");
    }

    private void deleteCustomer(HttpServletRequest request,HttpServletResponse response) throws IOException {
        int id=Integer.parseInt(request.getParameter("id"));
        customerService.remove(id);
        response.sendRedirect(request.getContextPath() + "/customers");
    }
}
