package com.sunbeam.dmc;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/editbook")
public class UpdateBookServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookId = req.getParameter("id");
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String subject = req.getParameter("subject");
		String price = req.getParameter("price");
		
		Book b = new Book(Integer.parseInt(bookId), name, author, subject, Double.parseDouble(price));
		System.out.println("update book servlet:++"+b);
		try(BookDao dao = new BookDao()) {
			dao.update(b);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("booklist");
	}
}