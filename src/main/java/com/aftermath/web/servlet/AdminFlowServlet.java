package com.aftermath.web.servlet;

import com.aftermath.pojo.Flow;
import com.aftermath.service.AdminFlowService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletAdminFlow", urlPatterns = {"/admin/flow"})
public class AdminFlowServlet extends HttpServlet {

    AdminFlowService service = new AdminFlowService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("flow".equals(target)) {
            // 查询课程信息
            List<Flow> flows = service.queryFlow();

            // 进行分页处理
            int pageSize = 6; //每页显示4条记录
            int totalPage = (int) Math.ceil(flows.size() / (double) pageSize); // 查询结果总共需要显示多少页
            int page = 1; // 默认显示第一页
            if (req.getParameter("page") != null) {
                page = Integer.parseInt(req.getParameter("page")); // 根据请求参数获取当前页码，如果没有则默认显示第一页
            }
            int startRow = (page - 1) * pageSize; // 计算每一页的起始行数
            int endRow = Math.min(startRow + pageSize, flows.size()); // 计算每一页的结束行数
            List<Flow> pageFlow = flows.subList(startRow, endRow); // 截取当前页的记录

            req.setAttribute("total_page", totalPage);
            req.setAttribute("page", page);
            req.setAttribute("flow_select", pageFlow);
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
        if ("flow".equals(target)) {
            // 删除审批流
            String deleteFlow = req.getParameter("delete_flow");
            service.deleteFlow(deleteFlow);
            if (service.existFlow(deleteFlow)) {
                resp.setContentType("text/html;charset=utf-8");
                PrintWriter out = resp.getWriter();
                out.println("<h1>删除审批流失败！</h1>");
            } else {
                doGet(req, resp);
            }
            doGet(req, resp);
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>删除失败！</h1>");
        }
    }
}
