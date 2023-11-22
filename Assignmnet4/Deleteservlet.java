package com.sunbeam.dmc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/deletebooks")
public class Deleteservlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	    String bookId = req.getParameter("id");
	    
	    try(BookDao dao = new BookDao()) {
	        int count = dao.deleteById(Integer.parseInt(bookId));
	    }catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    resp.sendRedirect("booklist");
	}

}
