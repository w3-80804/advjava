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

	@WebServlet("/books")
	public class Bookservlet extends HttpServlet {
		private static final long serialVersionUID = 1L;

		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			String subject = req.getParameter("subject");
			
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Books</title>");
			out.println("</head>");
			out.println("<body>");
			ServletContext ctx = req.getServletContext();
					String appTitle = ctx.getInitParameter("app.title");
					out.println("<h3>" + appTitle + "</h3>");
					
					String color = ctx.getInitParameter("color");
					out.printf("<body bgcolor='%s'>\r\n", color);
			try(BookDao bookDao = new BookDao()) {
			List<Book> list = bookDao.findBySubject(subject);
				
			out.println("<form method='post' action='addcart'>");
				for (Book b: list) {
					
					out.printf("<input type='checkbox' name='book' value='%d'/> %s<br/>\n", 
							b.getId(), b.getName());
				}
				out.println("<input type='submit' value='Add Cart'/>");
				out.println("</form>");
			} catch (Exception e) {
				e.printStackTrace();
			}
			out.println("</body>");
			out.println("</html>");
			
		}
		
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			doGet(req, resp);
		}
	}



