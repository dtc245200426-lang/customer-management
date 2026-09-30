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

    private CustomerService customerService = new CustomerServiceImpl();

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                createCustomer(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {
            action = "";
        }

        switch (action) {
            case "create":
                showCreateForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                showDeleteForm(request, response);
                break;
            case "view":
                viewCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    private void listCustomers(HttpServletRequest request,
                               HttpServletResponse response)
            throws ServletException, IOException {

        List<Customer> customers = customerService.findAll();

        request.setAttribute("customers", customers);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/customer/list.jsp");

        dispatcher.forward(request, response);
    }

    private void showCreateForm(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("/customer/create.jsp");

        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = customerService.findById(id);

        if (customer == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("customer", customer);

        request.getRequestDispatcher("/customer/edit.jsp")
                .forward(request, response);
    }

    private void showDeleteForm(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = customerService.findById(id);

        if (customer == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("customer", customer);

        request.getRequestDispatcher("/customer/delete.jsp")
                .forward(request, response);
    }

    private void viewCustomer(HttpServletRequest request,
                              HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = customerService.findById(id);

        if (customer == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        request.setAttribute("customer", customer);

        request.getRequestDispatcher("/customer/view.jsp")
                .forward(request, response);
    }

    private void createCustomer(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        int id = (int) (Math.random() * 10000);

        Customer customer =
                new Customer(id, name, email, address);

        customerService.save(customer);

        request.setAttribute(
                "message",
                "New customer was created"
        );

        request.getRequestDispatcher("/customer/create.jsp")
                .forward(request, response);
    }

    private void updateCustomer(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        Customer customer = customerService.findById(id);

        if (customer == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        customer.setName(name);
        customer.setEmail(email);
        customer.setAddress(address);

        customerService.update(id, customer);

        request.setAttribute("customer", customer);

        request.setAttribute(
                "message",
                "Customer information was updated"
        );

        request.getRequestDispatcher("/customer/edit.jsp")
                .forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request,
                                HttpServletResponse response)
            throws ServletException, IOException {

        int id = Integer.parseInt(request.getParameter("id"));

        Customer customer = customerService.findById(id);

        if (customer == null) {
            request.getRequestDispatcher("/error-404.jsp")
                    .forward(request, response);
            return;
        }

        customerService.remove(id);

        response.sendRedirect(
                request.getContextPath() + "/customers"
        );
    }
}