package com.example.servlet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Проверяем наличие атрибута сеанса "пользователь"
        HttpSession session = request.getSession(); // Use "false" to prevent the creation of a new session
        if (session.getAttribute("user") != null) {
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        } else {
            String login = request.getParameter("login"); // Получаем значение параметра "login" из запроса
            request.setAttribute("login", login);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("login");
        String password = request.getParameter("password");

        // Проверяем логин и пароль
        if (username != null && !password.isEmpty() && (username.equals("user") || username.equals("admin"))) {
            request.getSession().setAttribute("user", username);
            response.sendRedirect(request.getContextPath() + "/user/hello.jsp");
        } else {
            RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
            dispatcher.forward(request, response);
        }
    }

}