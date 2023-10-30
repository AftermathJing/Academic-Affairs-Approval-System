package com.aftermath.web.servlet;

import com.aftermath.pojo.Approval;
import com.aftermath.pojo.Instructor;
import com.aftermath.service.AdminSelectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletAdminSelect", urlPatterns = {"/admin/select"})
public class AdminSelectServlet extends HttpServlet {
    AdminSelectService service = new AdminSelectService();
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("select".equals(target)) {
            req.setAttribute("action", target);
            req.getRequestDispatcher("/admin_menu.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>查询失败！</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("select".equals(target)) {
            String courseName = req.getParameter("input_course_name");
            String studentName = req.getParameter("input_student_name");
            String status = req.getParameter("input_status");
            List<Approval> approvals = new ArrayList<>();
            if (status != null) {
                if ("agree".equals(status)) {
                    approvals = service.queryStatusTrue();
                } else if ("reject".equals(status)) {
                    approvals = service.queryStatusFalse();
                } else if ("approving".equals(status)) {
                    approvals = service.queryStatusNull();
                }
            } else if (courseName != null) {
                byte[] bytes = courseName.getBytes(StandardCharsets.ISO_8859_1);
                courseName = new String(bytes, StandardCharsets.UTF_8);
                approvals = service.queryByCourseName(courseName);
            } else {
                byte[] bytes = studentName.getBytes(StandardCharsets.ISO_8859_1);
                studentName = new String(bytes, StandardCharsets.UTF_8);
                approvals = service.queryByStudentName(studentName);
            }

            if (!approvals.isEmpty()) {
                req.setAttribute("courses_select", approvals);
            }
            req.setAttribute("action", target);
            req.getRequestDispatcher("/admin_menu.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>查询失败！</h1>");
        }
    }
}
