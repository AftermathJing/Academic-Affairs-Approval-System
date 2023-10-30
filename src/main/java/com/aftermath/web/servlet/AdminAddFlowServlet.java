package com.aftermath.web.servlet;

import com.aftermath.pojo.Course;
import com.aftermath.pojo.Instructor;
import com.aftermath.service.AdminAddFlowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "ServletAdminAddFlow", urlPatterns = {"/admin/add_flow"})
public class AdminAddFlowServlet extends HttpServlet {
    AdminAddFlowService service = new AdminAddFlowService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("add_flow".equals(target)) {
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
        if ("add_flow".equals(target)) {
            String courseName = req.getParameter("input_course_name");
            if (courseName != null) {
                // 中文数据出现乱码，解决乱码问题
                byte[] courseNameBytes = courseName.getBytes(StandardCharsets.ISO_8859_1);
                courseName = new String(courseNameBytes, StandardCharsets.UTF_8);
                if (service.existCourse(courseName)) {
                    Course targetCourse = service.queryCourseByName(courseName);
                    if (!service.existFlow(targetCourse.getId())) {
                        List<Course> courses = new ArrayList<>();
                        courses.add(targetCourse);
                        List<Instructor> supervisorInstructors = service.querySupervisorInstructor(courseName);
                        List<Instructor> teacherInstructors = service.queryTeacherInstructor(courseName);
                        req.setAttribute("courses_add_flow", courses);
                        req.setAttribute("supervisor_instructors", supervisorInstructors);
                        req.setAttribute("teacher_instructors", teacherInstructors);
                        req.setAttribute("action", target);
                        req.getRequestDispatcher("/admin_menu.jsp").forward(req, resp);
                    } else {
                        resp.setContentType("text/html;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.println("<h1>该审批流已存在！</h1>");
                    }
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>该课程不存在！</h1>");
                }

            } else {
                String courseId = req.getParameter("add_flow_course_id");
                if (courseId != null) {
                    String[] supervisorInstructors = req.getParameterValues("supervisor_instructor");
                    String[] teacherInstructors = req.getParameterValues("teacher_instructor");
                    StringBuilder supervisorInstructor = new StringBuilder();
                    StringBuilder teacherInstructor = new StringBuilder();
                    for (String instructor : supervisorInstructors) {
                        // 中文数据出现乱码，解决乱码问题
                        byte[] instructorBytes = instructor.getBytes(StandardCharsets.ISO_8859_1);
                        instructor = new String(instructorBytes, StandardCharsets.UTF_8);
                        supervisorInstructor.append(instructor).append(";");
                    }
                    for (String instructor : teacherInstructors) {
                        // 中文数据出现乱码，解决乱码问题
                        byte[] instructorBytes = instructor.getBytes(StandardCharsets.ISO_8859_1);
                        instructor = new String(instructorBytes, StandardCharsets.UTF_8);
                        teacherInstructor.append(instructor).append(";");
                    }
                    service.addFlow(courseId, supervisorInstructor.toString(), teacherInstructor.toString());
                    if (service.existFlow(courseId)) {
                        doGet(req, resp);
                    } else {
                        resp.setContentType("text/html;charset=utf-8");
                        PrintWriter out = resp.getWriter();
                        out.println("<h1>添加审批流失败！</h1>");
                    }
                } else {
                    resp.setContentType("text/html;charset=utf-8");
                    PrintWriter out = resp.getWriter();
                    out.println("<h1>操作失败！</h1>");
                }
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>操作失败！</h1>");
        }
    }
}
