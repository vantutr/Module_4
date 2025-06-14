//package com.codegym.thuchanh2;
//
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.List;
//
//@WebServlet(name = "CustomerServlet", urlPatterns = "/customers")
//public class CustomerServlet extends HttpServlet {
//    private final CustomerService customerService = new CustomerServiceImpl();
//
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        String action = request.getParameter("action");
//        if (action == null) {
//            listCustomers(request, response);
//        } else if (action.equals("view")) {
//            viewCustomer(request, response);
//        }
//    }
//
//    public void listCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Customer> customers = this.customerService.findAll();
//        request.setAttribute("customers", customers);
//
//        RequestDispatcher dispatcher = request.getRequestDispatcher("customer/list.jsp");
//
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void viewCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        int id = Integer.parseInt(request.getParameter("id"));
//        Customer customer = this.customerService.findById(id);
//        RequestDispatcher dispatcher;
//        if (customer == null) {
//            dispatcher = request.getRequestDispatcher("error.jsp");
//        } else {
//            request.setAttribute("customer", customer);
//            dispatcher = request.getRequestDispatcher("customer/info.jsp");
//        }
//        try {
//            dispatcher.forward(request, response);
//        } catch (ServletException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.doPost(req, resp);
//    }
//}
