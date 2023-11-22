package com.sunbeam.dmc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/addbook")
public class AddBookServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest req  ,HttpServletResponse res) throws IOException
	{
	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	out.println("<html>");
	out.println("<head>");
	out.println("<title>Edit Book</title>");
	out.println("</head>");
	out.println("<body>");
	out.println("<form method='post' action='addbook'>");
	out.printf("Id: <input type='text' name='id' /> <br/><br/>\n");
	out.printf("Name: <input type='text' name='name'/><br/><br/> \n");
	out.printf("Author: <input type='text' name='author'/><br/><br/>\n");
	out.printf("Subject: <input type='text' name='subject'/><br/><br/>\n");
	out.printf("Price: <input type='text' name='price' /><br/><br/>\n");
	out.println("<input type='submit' value='Add Book'/>");
	out.println("</form>");


	out.println("</body>");
	out.println("</html>");
	ServletContext ctx = req.getServletContext();
			String appTitle = ctx.getInitParameter("app.title");
		out.println("<h3>" + appTitle + "</h3>");
		String color = ctx.getInitParameter("color");
		out.printf("<body bgcolor='%s'>\r\n", color);
		


	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookId = req.getParameter("id");
		String name = req.getParameter("name");
		String author = req.getParameter("author");
		String subject = req.getParameter("subject");
		String price = req.getParameter("price");
		
		Book b = new Book(Integer.parseInt(bookId), name, author, subject, Double.parseDouble(price));
		System.out.println("add book++"+b);
		try(BookDao dao = new BookDao()) {
			dao.save(b);
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("booklist");
	}
}
