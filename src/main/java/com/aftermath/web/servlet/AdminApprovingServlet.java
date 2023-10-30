package com.aftermath.web.servlet;

import com.aftermath.pojo.Approval;
import com.aftermath.service.AdminApprovingService;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "ServletAdminApproving", urlPatterns = {"/admin/approving"})
public class AdminApprovingServlet extends HttpServlet {

    AdminApprovingService service = new AdminApprovingService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String target = req.getParameter("action");
        if ("approving".equals(target)) {
            // 查询课程信息
            List<Approval> approvals = service.queryAllApproval();

            // 进行分页处理
            int pageSize = 10; //每页显示5条记录
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
        if ("approving".equals(target)) {
            // 1. 查询数据库中所有审批记录
            List<Approval> approvals = service.queryAllApproval();

            // 2. 把审批记录写入 Excel 文件
            XSSFWorkbook workbook = writeApprovalsToExcelFile(approvals);

            // 3. 设置响应头，将 Excel 文件发送给浏览器
            resp.setContentType("approving/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            resp.setHeader("Content-Disposition", "attachment; filename=approvals.xlsx");
            workbook.write(resp.getOutputStream());
            workbook.close();
        } else {
            resp.setContentType("text/html;charset=utf-8");
            PrintWriter out = resp.getWriter();
            out.println("<h1>导出失败！</h1>");
        }
    }

    private XSSFWorkbook writeApprovalsToExcelFile(List<Approval> approvals) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("审批记录");

        // 创建表头
        XSSFRow headerRow = sheet.createRow(0);
        String[] headers = {"学号", "姓名", "课程编号", "课程名称", "学分", "学期", "开课年份", "选课备注", "审核状态", "审核备注"};
        for (int i = 0; i < headers.length; i++) {
            XSSFCell cell = headerRow.createCell(i, CellType.STRING);
            cell.setCellValue(headers[i]);
            // 设置表头的样式
            XSSFCellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            cell.setCellStyle(style);
        }

        // 写入数据
        int rownum = 1;
        for (Approval approval : approvals) {
            XSSFRow row = sheet.createRow(rownum++);
            createCell(row, 0, approval.getStudentId());
            createCell(row, 1, approval.getStudentName());
            createCell(row, 2, approval.getCourseId());
            createCell(row, 3, approval.getCourseName());
            createCell(row, 4, approval.getCredits());
            createCell(row, 5, approval.getSemester());
            createCell(row, 6, approval.getBeginYear());
            createCell(row, 7, approval.getChooseNotes());
            createCell(row, 8, approval.getStatus() != null ? (approval.getStatus() ? "通过" : "驳回") : "审批中");
            createCell(row, 9, approval.getCourseNotes());
        }

        // 调整列宽
        for (int i = 0; i < headers.length; i++) {
            sheet.autoSizeColumn(i);
        }

        return workbook;
    }

    private void createCell(XSSFRow row, int column, Object value) {
        XSSFCell cell = row.createCell(column);
        if (value instanceof String) {
            cell.setCellValue((String) value);
        } else if (value instanceof Short) {
            cell.setCellValue((Short) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        } else {
            // 如果不是字符串、短整型或布尔型，就将其转换为字符串并保存在单元格中
            cell.setCellValue(value.toString());
        }
    }
}
