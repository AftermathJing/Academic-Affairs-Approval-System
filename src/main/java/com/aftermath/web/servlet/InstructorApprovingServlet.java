package com.aftermath.web.servlet;

import com.aftermath.pojo.Approval;
import com.aftermath.pojo.Instructor;
import com.aftermath.pojo.Student;
import com.aftermath.service.InstructorApprovingService;
import com.aftermath.service.InstructorCourseService;
import com.aftermath.service.StudentApprovingService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.List;

@WebServlet(name = "ServletInstructorApproving", urlPatterns = {"/instructor/approving"})
public class InstructorApprovingServlet extends HttpServlet {
    InstructorApprovingService service = new InstructorApprovingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("approving".equals(target)) {
            HttpSession session = req.getSession();
            Instructor instructor = (Instructor) session.getAttribute("user");
            // 查询课程信息
            List<Approval> approvals = service.queryAllApproval(instructor.getId());

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
            req.getRequestDispatcher("/instructor_menu.jsp").forward(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>查询失败！</h1>");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("approving".equals(target)) {
            Enumeration<String> parameterNames = req.getParameterNames();
            StringBuilder htmlPage = new StringBuilder();
            while (parameterNames.hasMoreElements()) {
                String parameterName = parameterNames.nextElement();
                if (parameterName.startsWith("course_")) {
                    String[] parts = parameterName.split("_");
                    String courseId = parts[1];
                    String studentId = parts[2];
                    String value = req.getParameter(parameterName);
                    if (value.endsWith("_agree")) {
                        // 同意选课申请
                        service.pass(studentId, courseId);
                    } else if (value.endsWith("_reject")) {
                        // 驳回选课申请
                        service.reject(studentId, courseId);
                    }

                    // 检查选课记录的审批状态是否全部更新
                    Boolean status = service.queryStatus(studentId, courseId);
                    if (status == null) {
                        htmlPage.append("<p>学号为")
                                .append(studentId)
                                .append(",")
                                .append("课程号为")
                                .append(courseId)
                                .append("的记录状态更新失败！</p>");
                    }
                }
            }
            if ("".equals(htmlPage.toString())) {
                doGet(req, resp);
            } else {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.println(htmlPage);
            }
        }
    }
}
