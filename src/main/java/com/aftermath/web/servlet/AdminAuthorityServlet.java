package com.aftermath.web.servlet;

import com.aftermath.pojo.Instructor;
import com.aftermath.service.AdminAuthorityService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "ServletAdminAuthority", urlPatterns = {"/admin/authority"})
public class AdminAuthorityServlet extends HttpServlet {
    AdminAuthorityService service = new AdminAuthorityService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("authority".equals(target)) {
            req.setAttribute("action", target);
            req.getRequestDispatcher("/admin_menu.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>操作失败！</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("authority".equals(target)) {
            String instructorName = req.getParameter("input_instructor_name");
            if (instructorName != null) {
                // 中文数据出现乱码，解决乱码问题
                byte[] instructorNameBytes = instructorName.getBytes(StandardCharsets.ISO_8859_1);
                instructorName = new String(instructorNameBytes, StandardCharsets.UTF_8);

                List<Instructor> instructors = service.selectInstructorsByName(instructorName);
                req.setAttribute("target_instructor", instructors);
                req.setAttribute("action", target);
                req.getRequestDispatcher("/admin_menu.jsp").forward(req, resp);
            } else {
                String authorityModify = req.getParameter("authority_modify");
                if (authorityModify != null) {
                    boolean flag = false;
                    String[] parts = authorityModify.split("_");
                    if ("teacher".equals(parts[1])) {
                        service.modifyAuthority(parts[0], "授课教师");
                        flag = service.modifySuccess(parts[0], "授课教师");
                    } else if ("supervisor".equals(parts[1])) {
                        service.modifyAuthority(parts[0], "主管教师");
                        flag = service.modifySuccess(parts[0], "主管教师");
                    }
                    if (flag) {
                        doGet(req, resp);
                    } else {
                        resp.setContentType("text/html;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.println("<h1>权限修改失败！</h1>");
                    }
                }
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>操作失败！</h1>");
        }
    }
}
