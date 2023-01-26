package com.example.helloworld;

import java.io.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        String action = req.getParameter("action");
        req.getSession().invalidate();
        HttpSession newSession = req.getSession(true);
        newSession.setMaxInactiveInterval(300);
        if(action.equals("login")) {
            // do something to validate email and password
            boolean authenticated = true; // set true if validation is successfull

            if(authenticated) {

                newSession.setAttribute("username",email);
                resp.sendRedirect("home.jsp");
            }else {
                resp.sendRedirect("index.jsp");
            }
        }else if(action.equals("logout")) {
            req.getSession().invalidate();
            resp.sendRedirect("index.jsp");
        }else {
            resp.sendRedirect("index.jsp");
        }

        //System.out.println(email+" "+password+" "+action);
    }

    public void destroy() {
    }
}