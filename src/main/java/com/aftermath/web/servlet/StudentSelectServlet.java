package com.aftermath.web.servlet;

import com.aftermath.pojo.Approval;
import com.aftermath.pojo.Student;
import com.aftermath.service.StudentSelectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.ArrayList;

@WebServlet(name = "ServletStudentSelect", urlPatterns = {"/student/select"})
public class StudentSelectServlet extends HttpServlet {
    StudentSelectService service = new StudentSelectService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("select".equals(target)) {
            req.setAttribute("action", target);
            req.getRequestDispatcher("/student_menu.jsp").forward(req, resp);
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
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("user");

            String courseId = req.getParameter("course_id");
            if (courseId != null){ //取消选课
                service.cancelChoose(student.getId(), courseId);
                boolean flag = service.existChoose(student.getId(), courseId);
                if (flag) {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>取消选课失败！</h1>");
                }
            }
            String courseName = req.getParameter("input_course_name");
            String status = req.getParameter("input_status");
            List<Approval> approvals = new ArrayList<>();
            if (courseName == null) {
                if ("approving".equals(status)) {
                    approvals = service.queryStatusNull(student.getId());
                } else if ("reject".equals(status)) {
                    approvals = service.queryStatusFalse(student.getId());
                }
            } else {
                // 中文数据出现乱码，解决乱码问题
                byte[] bytes = courseName.getBytes(StandardCharsets.ISO_8859_1);
                courseName = new String(bytes, StandardCharsets.UTF_8);
                approvals = service.queryByCourseName(courseName, student.getId());
            }
            if (!approvals.isEmpty()) {
                req.setAttribute("courses_select", approvals);
            }
            req.setAttribute("action", target);
            req.getRequestDispatcher("/student_menu.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>查询失败！</h1>");
        }
    }
}
