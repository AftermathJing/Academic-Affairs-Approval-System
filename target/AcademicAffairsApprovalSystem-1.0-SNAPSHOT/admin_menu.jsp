<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Admin Menu</title>
    <style>
        /* 整体页面样式 */
        body {
            margin: 0;
            padding: 0;
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
        }

        /* 状态栏样式 */
        .state {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 50px;
            background-color: #333;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            box-sizing: border-box;
            padding: 0 20px;
            z-index: 1;
            font-family: "Hiragino Sans GB", "PingFang SC", "Helvetica Neue", Arial, sans-serif;
            color: #fff;
            text-align: center;
        }


        /*导航栏样式*/
        .nav-container {
            position: absolute;
            left: 0;
            top: 50px;
            background-color: #333;
            width: 200px;
            height: calc(100% - 50px);
            padding-top: 20px;
            box-sizing: border-box;
            font-size: 18px;
            text-align: center;
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: flex-start;
            overflow: hidden;
        }

        .title {
            font-family: "Hiragino Sans GB", "PingFang SC", "Helvetica Neue", Arial, sans-serif;
            color: white;
            text-align: center;
        }

        .nav-container ul {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        .nav-container li a {
            margin-bottom: 10px;
            padding: 10px;
            width: 160px;
            background-color: #444;
            color: #fff;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            display: block; /* 让链接元素铺满整个选项空间 */
            line-height: 30px; /* 定义行高，居中文本内容 */
            text-align: center; /* 居中文本内容 */
        }

        .nav-container li.active a {
            background-color: #03e9f4;
            color: #fff;
        }

        .nav-container li:hover a {
            background-color: #777;
        }

        .nav-container #login_out.active a {
            background-color: darkred;
            color: #fff;
        }

        .nav-container #login_out:hover a {
            background-color: darkred;
        }

        /* 内容区域的样式 */
        .content {
            position: absolute;
            top: 50px;
            left: 200px;
            width: calc(100% - 200px);
            height: calc(100% - 50px);
            background-color: #fff;
            padding: 20px;
            box-sizing: border-box;
            font-size: 16px;
            overflow: hidden;
        }

        /* 表格样式 */
        .content table {
            border-collapse: collapse;
            width: 100%;
        }

        .content th, td {
            text-align: left;
            padding: 8px;
        }

        .content tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        /* 按钮样式 */
        .content .ui-button {
            text-align: center;
        }

        .content button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-align: center;
        }

        .content button:hover {
            background-color: #45a049;
        }

        .content input[type=text] {
            width: 100%;
            padding: 6px 10px;
            margin: 8px 0;
            box-sizing: border-box;
            border: 2px solid #ccc;
            border-radius: 4px;
        }

        /* 分页查询样式 */
        .content .pagination {
            margin-top: 20px;
            text-align: center;
            font-size: 1.2em;
        }

        .content.pagination a {
            color: black;
            float: left;
            padding: 8px 16px;
            text-decoration: none;
            transition: background-color .3s;
        }

        .content.pagination a.active {
            background-color: #4CAF50;
            color: white;
        }

        .content.pagination a:hover:not(.active) {
            background-color: #ddd;
        }

        @media only screen and (max-width: 400px) {
            div.pagination a {
                font-size: 0.8em;
                padding: 6px 12px;
            }
        }

    </style>
</head>
<body>

<%
    // 获取 session 对象
    HttpSession s = request.getSession();

    // 获取 Session 中 key 为 "name" 的属性值
    String name = (String) s.getAttribute("name");
%>

<!-- 状态栏 -->
<h1 class="state">
    欢迎${name}管理员使用Academic Affairs Approval System <!-- 使用EL表达式输出用户名 -->
</h1>

<!-- 导航栏 -->
<div class="nav-container">
    <div class="nav">
        <form>
            <fieldset>
                <legend class="title">导航栏</legend>
                <ul>
                    <li id="user">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/user?action=user"
                           data-target="course">用户管理</a>
                    </li>
                    <li id="course">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/course?action=course"
                           data-target="choose">课程管理</a>
                    </li>
                    <li id="authority">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/authority?action=authority"
                           data-target="authority">权限管理</a>
                    </li>
                    <li id="approving">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/approving?action=approving"
                           data-target="approving">审核进度</a>
                    </li>
                    <li id="select">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/select?action=select"
                           data-target="select">多条件查询</a>
                    </li>
                    <li id="flow">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/flow?action=flow"
                           data-target="flow">查看审批流</a>
                    </li>
                    <li id="add_flow">
                        <a href="/AcademicAffairsApprovalSystem_war/admin/add_flow?action=add_flow"
                           data-target="add_flow">添加审批流</a>
                    </li>
                    <li id="login_out">
                        <a href="/AcademicAffairsApprovalSystem_war/loginOut?action=loginOut"
                           data-target="approving">退出登录</a>
                    </li>
                    <li style="display:none"><p id="targetId" style="display:none">${action}</p></li>
                </ul>
            </fieldset>
        </form>
    </div>
</div>

<!-- 内容区域 -->
<div class="content">
    <%-- 查看用户界面 --%>
    <form action="user" method="POST">
        <%-- 教师 --%>
        <c:if test="${not empty user_instructor}">
            <%-- 教师表 --%>
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>教师ID</th>
                        <th>教师姓名</th>
                        <th>密码</th>
                        <th>备注</th>
                        <th>删除用户</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${user_instructor}" var="instructor">
                        <tr>
                            <td><c:out value="${instructor.id}"/></td>
                            <td><c:out value="${instructor.name}"/></td>
                            <td><c:out value="${instructor.password}"/></td>
                            <td><c:out value="${instructor.notes}"/></td>
                            <td>
                                <label for="user_${instructor.id}">
                                    <input type="radio" id="user_${instructor.id}" name="user_id"
                                           value="instructor_${instructor.id}">
                                    删除
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- 分页查询 --%>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${instructor_total_page}">
                    <c:choose>
                        <c:when test="${i == instructor_page}">
                            <span>${i}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/AcademicAffairsApprovalSystem_war/admin/user?action=user&amp;instructor_page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </c:if>

        <%-- 学生 --%>
        <c:if test="${not empty user_student}">
            <%-- 学生表 --%>
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>学生ID</th>
                        <th>学生姓名</th>
                        <th>年级</th>
                        <th>密码</th>
                        <th>删除用户</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${user_student}" var="student">
                        <tr>
                            <td><c:out value="${student.id}"/></td>
                            <td><c:out value="${student.name}"/></td>
                            <td><c:out value="${student.grade}"/></td>
                            <td><c:out value="${student.password}"/></td>
                            <td>
                                <label for="user_${student.id}">
                                    <input type="radio" id="user_${student.id}" name="user_id"
                                           value="student_${student.id}">
                                    删除
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- 分页查询 --%>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${student_total_page}">
                    <c:choose>
                        <c:when test="${i == student_page}">
                            <span>${i}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/AcademicAffairsApprovalSystem_war/admin/user?action=user&amp;student_page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </c:if>

        <%-- 管理员 --%>
        <c:if test="${not empty user_admin}">
            <%-- 管理员表 --%>
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>管理员ID</th>
                        <th>管理员姓名</th>
                        <th>密码</th>
                        <th>删除用户</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${user_admin}" var="admin">
                        <tr>
                            <td><c:out value="${admin.id}"/></td>
                            <td><c:out value="${admin.name}"/></td>
                            <td><c:out value="${admin.password}"/></td>
                            <td>
                                <label for="user_${admin.id}">
                                    <input type="radio" id="user_${admin.id}" name="user_id"
                                           value="admin_${admin.id}">
                                    删除
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <%-- 分页查询 --%>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${admin_total_page}">
                    <c:choose>
                        <c:when test="${i == admin_page}">
                            <span>${i}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/AcademicAffairsApprovalSystem_war/admin/user?action=user&amp;admin_page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <input type="hidden" name="action" value="user">
            <div class="ui-button">
                <button type="submit" onclick="return validate_delete_user()">提交</button>
            </div>
        </c:if>
    </form>

    <%-- 课程管理界面 --%>
    <c:if test="${not empty courses_course}">
        <form action="course" method="POST">
                <%-- 课程表 --%>
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>学分</th>
                        <th>学期</th>
                        <th>开课年份</th>
                        <th>备注</th>
                        <th>删除课程</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses_course}" var="course">
                        <tr>
                            <td><c:out value="${course.id}"/></td>
                            <td><c:out value="${course.name}"/></td>
                            <td><c:out value="${course.credits}"/></td>
                            <td><c:out value="${course.semester}"/></td>
                            <td><c:out value="${course.beginYear}"/></td>
                            <td><c:out value="${course.notes}"/></td>
                            <td>
                                <label for="user_${course.id}">
                                    <input type="radio" id="user_${course.id}" name="delete_id"
                                           value="${course.id}">
                                    删除
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
                <%-- 分页查询 --%>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${total_page}">
                    <c:choose>
                        <c:when test="${i == page}">
                            <span>${i}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/AcademicAffairsApprovalSystem_war/admin/course?action=course&amp;page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <input type="hidden" name="action" value="course">
            <div class="ui-button">
                <button type="submit" onclick="return validate_delete_course()">删除</button>
            </div>
        </form>
        <form action="course" method="POST">
            <div>
                <label for="course_id">课程编号：</label>
                <input type="text" required id="course_id" name="course_id">
            </div>
            <div>
                <label for="course_name">课程名：</label>
                <input type="text" required id="course_name" name="course_name">
            </div>
            <div>
                <label for="credits">学分：</label>
                <input type="text" required id="credits" name="credits">
            </div>
            <div>
                <label for="begin_year">开课年份：</label>
                <input type="text" required id="begin_year" name="begin_year">
            </div>
            <div>
                学期：
                <label for="fall">
                    <input type="radio" id="fall" name="semester"
                           value="Fall">
                    秋季学期
                </label>
                <label for="spring">
                    <input type="radio" id="spring" name="semester"
                           value="Spring">
                    春季学期
                </label>
            </div>
            <div>
                <label for="notes">备注：</label>
                <input type="text" required id="notes" name="notes">
            </div>
            <input type="hidden" name="action" value="course">
            <div class="ui-button">
                <button type="submit" onclick="return validate_add_course()">提交</button>
            </div>
        </form>
    </c:if>

    <%-- 权限管理界面 --%>
    <c:if test="${action eq \"authority\"}">
        <%-- 查找教师 --%>
        <form action="authority" method="post">
            <div>
                <label for="select_instructor_name">教师名：</label>
                <input type="text" id="select_instructor_name" name="input_instructor_name">
            </div>
            <div>
                <button type="submit">查询</button>
            </div>
            <input type="hidden" name="action" value="authority">
        </form>
        <%-- 选择要修改权限的教师 --%>
        <c:if test="${not empty target_instructor}">
            <form action="authority" method="post">
                    <%-- 教师表 --%>
                <div>
                    <table border="1">
                        <thead>
                        <tr>
                            <th>教师ID</th>
                            <th>教师姓名</th>
                            <th>目前权限</th>
                            <th>修改权限</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${target_instructor}" var="instructor">
                            <tr>
                                <td><c:out value="${instructor.id}"/></td>
                                <td><c:out value="${instructor.name}"/></td>
                                <td><c:out value="${instructor.notes}"/></td>
                                <c:if test="${instructor.notes eq \"主管教师\"}">
                                    <td>
                                        <label for="authority_${instructor.id}">
                                            <input type="radio" id="authority_${instructor.id}"
                                                   name="authority_modify"
                                                   value="${instructor.id}_teacher">
                                            修改为授课教师
                                        </label>
                                    </td>
                                </c:if>
                                <c:if test="${instructor.notes eq \"授课教师\"}">
                                    <td>
                                        <label for="authority_${instructor.id}">
                                            <input type="radio" id="authority_${instructor.id}"
                                                   name="authority_modify"
                                                   value="${instructor.id}_supervisor">
                                            修改为主管教师
                                        </label>
                                    </td>
                                </c:if>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <input type="hidden" name="action" value="authority">
                <div class="ui-button">
                    <button type="submit" onclick="return validate_authority()">提交</button>
                </div>
            </form>
        </c:if>
    </c:if>

    <%-- 审批进度界面  --%>
    <c:if test="${not empty courses_approving}">
        <%-- 选课表 --%>
        <form action="approving" method="POST">
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>学生学号</th>
                        <th>学生姓名</th>
                        <th>开课年份</th>
                        <th>学期</th>
                        <th>选课理由</th>
                        <th>审批进度</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses_approving}" var="approval">
                        <tr>
                            <td><c:out value="${approval.courseId}"/></td>
                            <td><c:out value="${approval.courseName}"/></td>
                            <td><c:out value="${approval.studentId}"/></td>
                            <td><c:out value="${approval.studentName}"/></td>
                            <td><c:out value="${approval.beginYear}"/></td>
                            <td><c:out value="${approval.semester}"/></td>
                            <td><c:out value="${approval.chooseNotes}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${approval.status == null}">
                                        审核中
                                    </c:when>
                                    <c:when test="${approval.status == false}">
                                        驳回
                                    </c:when>
                                    <c:when test="${approval.status == true}">
                                        通过
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
                <%-- 分页查询 --%>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${total_page}">
                    <c:choose>
                        <c:when test="${i == page}">
                            <span>${i}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/AcademicAffairsApprovalSystem_war/admin/approving?action=approving&amp;page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
        </form>
        <form action="approving" method="POST">
            <input type="hidden" name="action" value="approving">
            <div class="ui-button">
                <button type="submit">导出审批记录</button>
            </div>
        </form>
    </c:if>

    <%--多条件查询--%>
    <c:if test="${action eq \"select\"}">
        <form action="select" method="post">
            <div>
                <label for="select_course_name">课程名：</label>
                <input type="text" id="select_course_name" name="input_course_name">
            </div>
            <div>
                <button type="submit">查询</button>
            </div>
            <input type="hidden" name="action" value="select">
        </form>
        <form action="select" method="post">
            <div>
                <label for="student_name">学生名：</label>
                <input type="text" id="student_name" name="input_student_name">
            </div>
            <div>
                <button type="submit">查询</button>
            </div>
            <input type="hidden" name="action" value="select">
        </form>
        <form action="select" method="post">
            <div>
                <span>
                    <label>状态：</label>
                    <input type="radio" id="status1" name="input_status" value="agree">
                    <label for="status1">通过</label>
                    <input type="radio" id="status2" name="input_status" value="reject">
                    <label for="status2">驳回</label>
                    <input type="radio" id="status3" name="input_status" value="approving">
                    <label for="status3">审核中</label>
                </span>
            </div>
            <div>
                <button type="submit">查询</button>
            </div>
            <input type="hidden" name="action" value="select">
        </form>
    </c:if>

    <%-- 多条件查询到的审批  --%>
    <c:if test="${not empty courses_select}">
        <%-- 审批表 --%>
        <form action="select" method="POST">
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>学生学号</th>
                        <th>学生姓名</th>
                        <th>开课年份</th>
                        <th>学期</th>
                        <th>选课理由</th>
                        <th>审批进度</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses_select}" var="approval">
                        <tr>
                            <td><c:out value="${approval.courseId}"/></td>
                            <td><c:out value="${approval.courseName}"/></td>
                            <td><c:out value="${approval.studentId}"/></td>
                            <td><c:out value="${approval.studentName}"/></td>
                            <td><c:out value="${approval.beginYear}"/></td>
                            <td><c:out value="${approval.semester}"/></td>
                            <td><c:out value="${approval.chooseNotes}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${approval.status == true}">
                                        通过
                                    </c:when>
                                    <c:when test="${approval.status == null}">
                                        审核中
                                    </c:when>
                                    <c:when test="${approval.status == false}">
                                        驳回
                                    </c:when>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </form>
    </c:if>

    <%-- 查看审批流界面  --%>
    <c:if test="${not empty flow_select}">
        <form action="flow" method="POST">
                <%-- 审批流表 --%>
            <div>
                <table border="1">
                    <thead>
                    <tr>
                        <th>课程编号</th>
                        <th>课程名称</th>
                        <th>主管教师</th>
                        <th>授课教师</th>
                        <th>删除审批流</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${flow_select}" var="flow">
                        <tr>
                            <td><c:out value="${flow.courseId}"/></td>
                            <td><c:out value="${flow.courseName}"/></td>
                            <td><c:out value="${flow.supervisorInstructor}"/></td>
                            <td><c:out value="${flow.teacherInstructor}"/></td>
                            <td>
                                <label for="user_${flow.courseId}">
                                    <input type="radio" id="user_${flow.courseId}" name="delete_flow"
                                           value="${flow.courseId}">
                                    删除
                                </label>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
                <%-- 分页查询 --%>
            <div class="pagination">
                <c:forEach var="i" begin="1" end="${total_page}">
                    <c:choose>
                        <c:when test="${i == page}">
                            <span>${i}</span>&nbsp;
                        </c:when>
                        <c:otherwise>
                            <a href="/AcademicAffairsApprovalSystem_war/admin/flow?action=flow&amp;page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <input type="hidden" name="action" value="flow">
            <div class="ui-button">
                <button type="submit" onclick="return validate_delete_flow()">删除</button>
            </div>
        </form>
    </c:if>

    <%-- 添加审批流界面  --%>
    <c:if test="${action eq \"add_flow\"}">
        <%-- 输入课程名  --%>
        <c:if test="${empty courses_add_flow}">
            <form action="add_flow" method="post">
                <div>
                    <label for="add_flow_name">课程名：</label>
                    <input type="text" id="add_flow_name" name="input_course_name">
                </div>
                <div>
                    <button type="submit">查询</button>
                </div>
                <input type="hidden" name="action" value="add_flow">
            </form>
        </c:if>
        <%-- 添加审批流  --%>
        <c:if test="${not empty courses_add_flow}">
            <form action="add_flow" method="post">
                    <%-- 课程表 --%>
                <div>
                    <table border="1">
                        <caption>课程列表</caption>
                        <thead>
                        <tr>
                            <th>课程编号</th>
                            <th>课程名称</th>
                            <th>学分</th>
                            <th>学期</th>
                            <th>开课年份</th>
                            <th>备注</th>
                            <th>选择</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${courses_add_flow}" var="course">
                            <tr>
                                <td><c:out value="${course.id}"/></td>
                                <td><c:out value="${course.name}"/></td>
                                <td><c:out value="${course.credits}"/></td>
                                <td><c:out value="${course.semester}"/></td>
                                <td><c:out value="${course.beginYear}"/></td>
                                <td><c:out value="${course.notes}"/></td>
                                <td>
                                    <label for="add_flow_${course.id}">
                                        <input type="radio" id="add_flow_${course.id}" name="add_flow_course_id"
                                               value="${course.id}">
                                        选择
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                    <%-- 主管教师表 --%>
                <div>
                    <table border="1">
                        <caption>主管教师列表</caption>
                        <thead>
                        <tr>
                            <th>教师ID</th>
                            <th>教师姓名</th>
                            <th>备注</th>
                            <th>选择</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${supervisor_instructors}" var="instructor">
                            <tr>
                                <td><c:out value="${instructor.id}"/></td>
                                <td><c:out value="${instructor.name}"/></td>
                                <td><c:out value="${instructor.notes}"/></td>
                                <td>
                                    <label for="supervisor_instructor_${instructor.id}">
                                        <input type="checkbox" id="supervisor_instructor_${instructor.id}"
                                               name="supervisor_instructor"
                                               value="${instructor.name}(${instructor.id})">
                                        选择
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>

                    <%-- 授课教师表 --%>
                <div>
                    <table border="1">
                        <caption>授课教师列表</caption>
                        <thead>
                        <tr>
                            <th>教师ID</th>
                            <th>教师姓名</th>
                            <th>备注</th>
                            <th>选择</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${teacher_instructors}" var="instructor">
                            <tr>
                                <td><c:out value="${instructor.id}"/></td>
                                <td><c:out value="${instructor.name}"/></td>
                                <td><c:out value="${instructor.notes}"/></td>
                                <td>
                                    <label for="teacher_instructor_${instructor.id}">
                                        <input type="checkbox" id="teacher_instructor_${instructor.id}"
                                               name="teacher_instructor"
                                               value="${instructor.name}(${instructor.id})">
                                        选择
                                    </label>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <input type="hidden" name="action" value="add_flow">
                <div class="ui-button">
                    <button type="submit" onclick="return validate_add_flow()">提交</button>
                </div>
            </form>
        </c:if>
    </c:if>

</div>
<!-- Javascript代码 -->
<script>
    // 获取所有选项元素
    let options = document.querySelectorAll(".nav-container li");
    let targetId = document.getElementById("targetId").textContent; // 获取<p>标签的内容
    options.forEach((option) => {
        if (option.getAttribute("id") === targetId) { // 如果该选项的ID等于目标ID
            option.classList.add("active"); // 为该选项添加“active”样式
        }
    });

    function validate_add_course() {
        var course_id = document.getElementsByName("course_id")[0].value.trim();
        if (course_id === "") {
            alert("请填写课程编号");
            return false;
        }

        var course_name = document.getElementsByName("course_name")[0].value.trim();
        if (course_name === "") {
            alert("请填写课程名");
            return false;
        }

        var credits = document.getElementsByName("credits")[0].value.trim();
        if (credits === "") {
            alert("请填写课程学分");
            return false;
        }

        var begin_year = document.getElementsByName("begin_year")[0].value.trim();
        if (begin_year === "") {
            alert("请填写课程开课年份");
            return false;
        }

        var semester = document.getElementsByName("semester")[0].value.trim();
        if (semester === "") {
            alert("请填写课程学期");
            return false;
        }

        var notes = document.getElementsByName("notes")[0].value.trim();
        if (notes === "") {
            alert("请填写课程备注");
            return false;
        }
        return true;
    }

    function validate_delete_user() {
        var radios = document.getElementsByName("user_id");
        var selected = false;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                selected = true;
                break;
            }
        }
        if (!selected) {
            alert("请至少选择一个用户");
            return false;
        }
        return true;
    }

    function validate_delete_course() {
        var radios = document.getElementsByName("delete_id");
        var selected = false;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                selected = true;
                break;
            }
        }
        if (!selected) {
            alert("请至少选择一门课程");
            return false;
        }
        return true;
    }

    function validate_delete_flow() {
        var radios = document.getElementsByName("delete_flow");
        var selected = false;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                selected = true;
                break;
            }
        }
        if (!selected) {
            alert("请至少选择一个审批流");
            return false;
        }
        return true;
    }

    function validate_authority() {
        var radios = document.getElementsByName("authority_modify");
        var selected = false;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                selected = true;
                break;
            }
        }
        if (!selected) {
            alert("请至少修改一个老师的权限");
            return false;
        }
        return true;
    }

    function validate_add_flow() {
        var radios = document.getElementsByName("add_flow_course_id");
        var selected_course = false;
        for (var i = 0; i < radios.length; i++) {
            if (radios[i].checked) {
                selected_course = true;
                break;
            }
        }
        if (!selected_course) {
            alert("请至少选择一门课程");
            return false;
        }

        var checkboxes_supervisor_instructors = document.getElementsByName("supervisor_instructor");
        var selected_supervisor_instructor = false;
        for (var i = 0; i < checkboxes_supervisor_instructors.length; i++) {
            if (checkboxes_supervisor_instructors[i].checked) {
                selected_supervisor_instructor = true;
                break;
            }
        }
        if (!selected_supervisor_instructor) {
            alert("请至少选择一位主管教师");
            return false;
        }

        var checkboxes_teacher_instructors = document.getElementsByName("teacher_instructor");
        var selected_teacher_instructor = false;
        for (var i = 0; i < checkboxes_teacher_instructors.length; i++) {
            if (checkboxes_teacher_instructors[i].checked) {
                selected_teacher_instructor = true;
                break;
            }
        }
        if (!selected_teacher_instructor) {
            alert("请至少选择一位授课教师");
            return false;
        }
        return true;
    }

</script>

</body>
</html>
