package com.sunbeam.dmc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/editbooks")
public class EditBookServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String bookId = req.getParameter("id");
		Book b = null;
		try(BookDao dao = new BookDao()) {
			int id = Integer.parseInt(bookId);
			b = dao.findById(id);
		}catch (Exception e) {
			e.printStackTrace();
		}
		if(b == null) {
			resp.sendRedirect("booklist");
			return;
		}
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head>");		out.println("<title>Edit Book</title>");
		out.println("</head>");
		out.println("<body>");
		ServletContext ctx = req.getServletContext();
				String appTitle = ctx.getInitParameter("app.title");
				out.println("<h3>" + appTitle + "</h3>");
				String color = ctx.getInitParameter("color");
				out.printf("<body bgcolor='%s'>\r\n", color);
		
		HttpSession session = req.getSession();
		String userName = (String) session.getAttribute("uname");
		out.printf("Hello, %s <hr/>\n", userName);

		out.println("<form method='post' action='editbook'>");
		
		
		out.printf("Id: <input type='text' name='id' value='%d' readonly/> <br/><br/>\n", b.getId());
		out.printf("Name: <input type='text' name='name' value='%s'/><br/><br/>\n", b.getName());
		out.printf("Author: <input type='text' name='author' value='%s'/><br/><br/>\n", b.getAuthor());
		out.printf("Subject: <input type='text' name='subject' value='%s'/><br/><br/>\n", b.getSubject());
		out.printf("Price: <input type='text' name='price' value='%.2f'/><br/><br/>\n", b.getPrice());
		out.println("<input type='submit' value='Update Book'/>");
		
		out.println("</form>");
		out.println("</body>");
		out.println("</html>");
	}
}

