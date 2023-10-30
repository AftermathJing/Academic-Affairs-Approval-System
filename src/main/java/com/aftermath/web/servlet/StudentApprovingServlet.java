package com.aftermath.web.servlet;

import com.aftermath.pojo.Approval;
import com.aftermath.pojo.Student;
import com.aftermath.service.StudentApprovingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletStudentApproving", urlPatterns = {"/student/approving"})
public class StudentApprovingServlet extends HttpServlet {
    StudentApprovingService service = new StudentApprovingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("approving".equals(target)) {
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("user");
            // 查询课程信息
            List<Approval> approvals = service.queryAllApproval(student.getId());

            // 进行分页处理
            int pageSize = 5; //每页显示5条记录
            int totalPage = (int) Math.ceil(approvals.size() / (double) pageSize); // 查询结果总共需要显示多少页
            int page = 1; // 默认显示第一页
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page")); // 根据请求参数获取当前页码，如果没有则默认显示第一页
            }
            int startRow = (page - 1) * pageSize; // 计算每一页的起始行数
            int endRow = Math.min(startRow + pageSize, approvals.size()); // 计算每一页的结束行数
            List<Approval> pageApprovals = approvals.subList(startRow, endRow); // 截取当前页的记录

            req.setAttribute("total_page", totalPage);
            req.setAttribute("page", page);
            req.setAttribute("courses_approving", pageApprovals);
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
        String courseId = req.getParameter("course_id");
        if ("approving".equals(target) && courseId != null) {
            HttpSession session = req.getSession();
            Student student = (Student) session.getAttribute("user");
            service.cancelChoose(student.getId(), courseId);
            boolean flag = service.existChoose(student.getId(), courseId);
            if (flag) {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.println("<h1>取消选课失败！</h1>");
            } else {
                doGet(req, resp);
            }
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>取消选课失败！</h1>");
        }
    }
}
