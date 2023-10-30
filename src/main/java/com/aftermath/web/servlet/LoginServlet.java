package com.aftermath.web.servlet;

import com.aftermath.pojo.Admin;
import com.aftermath.pojo.Instructor;
import com.aftermath.pojo.Student;
import com.aftermath.service.LoginService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "ServletLogin", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    private final LoginService service = new LoginService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收用户名和密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Student student = service.selectStudent(username, password);

        if (student == null) {
            Instructor instructor = service.selectInstructor(username, password);
            if (instructor == null) {
                Admin admin = service.selectAdmin(username, password);
                if (admin == null) {
                    System.out.println("登陆失败");
                    req.setAttribute("login_msg", "用户名或密码错误");
                    req.getRequestDispatcher("/index.jsp").forward(req, resp);
                } else {
                    HttpSession session = req.getSession();
                    String contextPath = req.getContextPath();
                    session.setAttribute("user", admin);
                    session.setAttribute("name", admin.getName());
                    session.setAttribute("login", "admin");
                    resp.sendRedirect(contextPath + "/admin");
                }
            } else {
                HttpSession session = req.getSession();
                String contextPath = req.getContextPath();
                session.setAttribute("user", instructor);
                session.setAttribute("name", instructor.getName());
                session.setAttribute("notes", instructor.getNotes());
                session.setAttribute("login", "instructor");
                resp.sendRedirect(contextPath + "/instructor");
            }
        } else {
            HttpSession session = req.getSession();
            String contextPath = req.getContextPath();
            session.setAttribute("user", student);
            session.setAttribute("name", student.getName());
            session.setAttribute("login", "student");
            resp.sendRedirect(contextPath + "/student");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
