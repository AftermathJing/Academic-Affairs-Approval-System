package com.aftermath.web.servlet;

import com.aftermath.pojo.Course;
import com.aftermath.service.AdminCourseService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "ServletAdminCourse", urlPatterns = {"/admin/course"})
public class AdminCourseServlet extends HttpServlet {
    AdminCourseService service = new AdminCourseService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("course".equals(target)) {
            // 查询课程信息
            List<Course> courses = service.queryCourse();

            // 进行分页处理
            int pageSize = 4; //每页显示4条记录
            int totalPage = (int) Math.ceil(courses.size() / (double) pageSize); // 查询结果总共需要显示多少页
            int page = 1; // 默认显示第一页
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page")); // 根据请求参数获取当前页码，如果没有则默认显示第一页
            }
            int startRow = (page - 1) * pageSize; // 计算每一页的起始行数
            int endRow = Math.min(startRow + pageSize, courses.size()); // 计算每一页的结束行数
            List<Course> pageCourses = courses.subList(startRow, endRow); // 截取当前页的记录

            req.setAttribute("total_page", totalPage);
            req.setAttribute("page", page);
            req.setAttribute("courses_course", pageCourses);
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
        if ("course".equals(target)) {
            String deleteId = req.getParameter("delete_id");
            if (deleteId == null){
                String courseId = req.getParameter("course_id");
                if(service.existSameIdCourse(courseId)) {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>该课程编号已存在！</h1>");
                    return;
                }
                String courseName = req.getParameter("course_name");
                // 中文数据出现乱码，解决乱码问题
                byte[] courseNameBytes = courseName.getBytes(StandardCharsets.ISO_8859_1);
                courseName = new String(courseNameBytes, StandardCharsets.UTF_8);
                if(service.existSameNameCourse(courseName)) {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>该课程名已存在！</h1>");
                    return;
                }
                String creditsString = req.getParameter("credits");
                Double credits;

                try {
                    credits = Double.valueOf(creditsString);
                } catch (NumberFormatException e) {
                    // 处理异常情况
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>学分格式错误！</h1>");
                    return;
                }
                if (credits > 50 || credits < 0) {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>学分必须介于0到50！</h1>");
                    return;
                }

                String beginYearString = req.getParameter("begin_year");
                String semester = req.getParameter("semester");
                String notes = req.getParameter("notes");
                // 中文数据出现乱码，解决乱码问题
                byte[] notesBytes = notes.getBytes(StandardCharsets.ISO_8859_1);
                notes = new String(notesBytes, StandardCharsets.UTF_8);
                Short beginYear;

                try {
                    beginYear = Short.valueOf(beginYearString);
                } catch (NumberFormatException e) {
                    // 处理异常情况
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>开课年份格式错误！</h1>");
                    return;
                }
                if (beginYear < 2000 || beginYear > 2100) {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>开课年份必须介于2000到2100！</h1>");
                    return;
                }

                service.insertCourse(courseId, courseName, credits, beginYear, semester, notes);
                boolean flag = service.insertSuccess(courseId);
                if (flag) {
                    doGet(req, resp);
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>插入课程失败！</h1>");
                }
            } else {
                service.deleteCourse(deleteId);
                if (service.existSameIdCourse(deleteId)) {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>删除课程失败！</h1>");
                } else {
                    doGet(req, resp);
                }
                doGet(req, resp);
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>操作失败！</h1>");
        }
    }
}
