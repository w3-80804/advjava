package com.sunbeam.dmc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/booklist")
public class BookListServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Book List</title>");
		out.println("</head>");
		out.println("<body>");
		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Id</th>");
		out.println("<th>Name</th>");
		out.println("<th>Author</th>");
		out.println("<th>Subject</th>");
		out.println("<th>Price</th>");
		out.println("<th colspan=2>Action</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		ServletContext ctx = req.getServletContext();
				String appTitle = ctx.getInitParameter("app.title");
			out.println("<h3>" + appTitle + "</h3>");
			
			String color = ctx.getInitParameter("color");
			out.printf("<body bgcolor='%s'>\r\n", color);
		try(BookDao bookDao = new BookDao()) {
			List<Book> list = bookDao.findAll();
			for (Book b: list) {
				out.println("<tr>");
				out.printf("<td>%d</td>\n", b.getId());
				out.printf("<td>%s</td>\n", b.getName());
				out.printf("<td>%s</td>\n", b.getAuthor());
				out.printf("<td>%s</td>\n", b.getSubject());
				out.printf("<td>%.2f</td>\n", b.getPrice());
				out.printf("<td><a href='editbooks?id=%d'>Edit</a></td>\n", b.getId());
				out.printf("<td><a href='deletebooks?id=%d'>Delete</a></td>\n", b.getId());
				out.println("</tr>");
				
			}
	} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("</tbody>");
		out.println("</table>");
		out.printf("<td><a href='addbook'>Add Book</a></td>\n");
		out.println("</br>");
		out.printf("<td><a href='logout'>Logout</a></td>\n");
		out.println("</body>");
		out.println("</html>");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
