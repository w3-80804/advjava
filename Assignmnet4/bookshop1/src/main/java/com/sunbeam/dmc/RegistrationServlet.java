package com.sunbeam.dmc;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/signup")
public class RegistrationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String address = req.getParameter("address");
        String birthStr = req.getParameter("birth");
        String mobile = req.getParameter("mobile");
        String email = req.getParameter("email");
        String password = req.getParameter("passwd");

      
        Date birthDate = Date.valueOf(birthStr);
        User u = new User(0, name, password, mobile, email, address, birthDate, 1, "ROLE_CUSTOMER");
        
        int count = 0;
        try (UserDao userDao = new UserDao()) {
            count = userDao.save(u);
        } catch (Exception e) {
            e.printStackTrace();
        }

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Register User</title>");
        out.println("</head>");
        out.println("<body>");
        if (count == 1) {
            out.println("<h3>User registered successfully.</h3>");
            out.println("<a href='index.html'>Sign In</a>");
        } else {
            out.println("<h3>User registration failed.</h3>");
            out.println("<a href='signup.html'>Sign Up</a>");
        }
        out.println("</body>");
        out.println("</html>");
    }
}
