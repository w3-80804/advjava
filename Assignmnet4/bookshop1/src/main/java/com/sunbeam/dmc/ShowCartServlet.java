package com.sunbeam.dmc;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

   
@WebServlet("/cart")
public class ShowCartServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session = req.getSession();
		ArrayList<Integer> cart = (ArrayList<Integer>) session.getAttribute("cart");

		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		
		out.println("<html>");
		out.println("<head>");
		out.println("<title>Cart</title>");
		out.println("</head>");
		out.println("<body>");

		
		String userName = (String) session.getAttribute("uname");
		out.printf("Hello, %s <hr/>\n", userName);

		out.println("<table border='1'>");
		out.println("<thead>");
		out.println("<tr>");
		out.println("<th>Id</th>");
		out.println("<th>Name</th>");
		out.println("<th>Author</th>");
		out.println("<th>Subject</th>");
		out.println("<th>Price</th>");
		out.println("</tr>");
		out.println("</thead>");
		out.println("<tbody>");
		ServletContext ctx = req.getServletContext();
				String appTitle = ctx.getInitParameter("app.title");
				out.println("<h3>" + appTitle + "</h3>");
				String color = ctx.getInitParameter("color");
				out.printf("<body bgcolor='%s'>\r\n", color);
		
		try(BookDao dao = new BookDao()) {
			for (int id: cart) {
				Book book = dao.findById(id);
				out.println("<tr>");
				out.printf("<td>%d</td>\n", id);
				out.printf("<td>%s</td>\n", book.getName());
				out.printf("<td>%s</td>\n", book.getAuthor());
			out.printf("<td>%s</td>\n", book.getSubject());		
			out.printf("<td>%2f</td>\n",book.getPrice());
			out.println("</tr>");
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

		out.println("</tbody>");
		out.println("</table>");
		out.println("<a href='subjects'>Subjects</a>");
		out.println("<a href='signout'>Sign Out</a>");
		out.println("</body>");
				out.println("</html>");
		
			}
			@Override
			protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
				doGet(req, resp);
			}
		}
		