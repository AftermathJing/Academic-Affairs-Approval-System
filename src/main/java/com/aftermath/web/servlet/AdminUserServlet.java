package com.aftermath.web.servlet;

import com.aftermath.pojo.Admin;
import com.aftermath.pojo.Instructor;
import com.aftermath.pojo.Student;
import com.aftermath.service.AdminUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletAdminUser", urlPatterns = {"/admin/user"})
public class AdminUserServlet extends HttpServlet {

    AdminUserService service = new AdminUserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("user".equals(target)) {
            List<Student> students = service.selectStudents();
            List<Instructor> instructors = service.selectInstructors();
            List<Admin> admins = service.selectAdmins();

            // 进行分页处理
            int pageSize = 4; //每页显示5条记录

            int studentTotalPage = (int) Math.ceil(students.size() / (double) pageSize); // 查询结果总共需要显示多少页
            int studentPage = 1; // 默认显示第一页
            if (req.getParameter("student_page") != null) {
                studentPage = Integer.parseInt(req.getParameter("student_page")); // 根据请求参数获取当前页码，如果没有则默认显示第一页
            }
            int startRow1 = (studentPage - 1) * pageSize; // 计算每一页的起始行数
            int endRow1 = Math.min(startRow1 + pageSize, students.size()); // 计算每一页的结束行数
            List<Student> pageStudents = students.subList(startRow1, endRow1); // 截取当前页的记录

            // 进行分页处理
            int instructorTotalPage = (int) Math.ceil(instructors.size() / (double) pageSize); // 查询结果总共需要显示多少页
            int instructorPage = 1; // 默认显示第一页
            if (req.getParameter("instructor_page") != null) {
                instructorPage = Integer.parseInt(req.getParameter("instructor_page")); // 根据请求参数获取当前页码，如果没有则默认显示第一页
            }
            int startRow2 = (instructorPage - 1) * pageSize; // 计算每一页的起始行数
            int endRow2 = Math.min(startRow2 + pageSize, instructors.size()); // 计算每一页的结束行数
            List<Instructor> pageInstructors = instructors.subList(startRow2, endRow2); // 截取当前页的记录


            // 进行分页处理
            int adminTotalPage = (int) Math.ceil(admins.size() / (double) pageSize); // 查询结果总共需要显示多少页
            int adminPage = 1; // 默认显示第一页
            if (req.getParameter("admin_page") != null) {
                adminPage = Integer.parseInt(req.getParameter("admin_page")); // 根据请求参数获取当前页码，如果没有则默认显示第一页
            }
            int startRow3 = (adminPage - 1) * pageSize; // 计算每一页的起始行数
            int endRow3 = Math.min(startRow3 + pageSize, admins.size()); // 计算每一页的结束行数
            List<Admin> pageAdmins = admins.subList(startRow3, endRow3); // 截取当前页的记录

            req.setAttribute("admin_total_page", adminTotalPage);
            req.setAttribute("admin_page", adminPage);
            req.setAttribute("user_admin", pageAdmins);

            req.setAttribute("student_total_page", studentTotalPage);
            req.setAttribute("student_page", studentPage);
            req.setAttribute("user_student", pageStudents);

            req.setAttribute("instructor_total_page", instructorTotalPage);
            req.setAttribute("instructor_page", instructorPage);
            req.setAttribute("user_instructor", pageInstructors);

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
        if ("user".equals(target)) {
            String userId = req.getParameter("user_id");
            if (!userId.isEmpty()) {
                String[] parts = userId.split("_");
                switch (parts[0]) {
                    case "instructor":
                        // 删除老师
                        service.deleteInstructor(parts[1]);
                        if (service.existInstructor(parts[1])) {
                            resp.setContentType("text/html;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.println("<h1>删除老师失败！</h1>");
                        }
                        break;
                    case "student":
                        // 删除学生
                        service.deleteStudent(parts[1]);
                        if (service.existStudent(parts[1])) {
                            resp.setContentType("text/html;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.println("<h1>删除学生失败！</h1>");
                        }
                        break;
                    case "admin":
                        // 删除管理员
                        service.deleteAdmin(parts[1]);
                        if (service.existAdmin(parts[1])) {
                            resp.setContentType("text/html;charset=utf-8");
                            PrintWriter out = resp.getWriter();
                            out.println("<h1>删除管理员失败！</h1>");
                        }
                        break;
                }
            }
            doGet(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>删除失败！</h1>");
        }
    }
}
