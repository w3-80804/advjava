package com.sunbeam.dmc;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/addcart")
      
    public class AddCartServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	
	
	
  HttpSession session = req.getSession();
  ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");
System.out.println("cart"+cart);
String[] bookIds = req.getParameterValues("book");
for (String bookId : bookIds) {
int id = Integer.parseInt(bookId);



if(!cart.contains(id))
	cart.add(id);
}
System.out.println("Current User: " + session.getAttribute("uname"));
System.out.println("Modified Cart: " + cart);

RequestDispatcher rd = req.getRequestDispatcher("subjects");
rd.forward(req, resp);
}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
doGet(req, resp);
}
}


