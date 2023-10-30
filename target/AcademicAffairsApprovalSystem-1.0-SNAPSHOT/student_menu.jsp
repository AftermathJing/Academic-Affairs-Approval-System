<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Student Menu</title>
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
            padding: 12px 20px;
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
    欢迎${name}同学使用Academic Affairs Approval System <!-- 使用EL表达式输出用户名 -->
</h1>

<!-- 导航栏 -->
<div class="nav-container">
    <div class="nav">
        <form>
            <fieldset>
                <legend class="title">导航栏</legend>
                <ul>
                    <li id="course">
                        <a href="/AcademicAffairsApprovalSystem_war/student/course?action=course" data-target="course">我的课表</a>
                    </li>
                    <li id="choose">
                        <a href="/AcademicAffairsApprovalSystem_war/student/choose?action=choose" data-target="choose">选课</a>
                    </li>
                    <li id="approving">
                        <a href="/AcademicAffairsApprovalSystem_war/student/approving?action=approving"
                           data-target="approving">审核进度</a>
                    </li>
                    <li id="select">
                        <a href="/AcademicAffairsApprovalSystem_war/student/select?action=select"
                           data-target="select">多条件查询</a>
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
    <%-- 我的课表界面 --%>
    <c:if test="${not empty courses_course}">
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
                        <a href="/AcademicAffairsApprovalSystem_war/student/course?action=course&amp;page=${i}">${i}</a>&nbsp;
                    </c:otherwise>
                </c:choose>
            </c:forEach>
        </div>
    </c:if>

    <%-- 选课界面  --%>
    <c:if test="${not empty courses_choose}">
        <%-- 选课表 --%>
        <form action="choose" method="POST">
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
                        <th>是否选课</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses_choose}" var="course">
                        <tr>
                            <td><c:out value="${course.id}"/></td>
                            <td><c:out value="${course.name}"/></td>
                            <td><c:out value="${course.credits}"/></td>
                            <td><c:out value="${course.semester}"/></td>
                            <td><c:out value="${course.beginYear}"/></td>
                            <td><c:out value="${course.notes}"/></td>
                            <td>
                                <label for="course_${course.id}">
                                    <input type="radio" id="course_${course.id}" name="course_id" value="${course.id}">
                                    选课
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
                            <a href="/AcademicAffairsApprovalSystem_war/student/choose?action=choose&amp;page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <div>
                <label for="reason">选课原因：</label>
                <input type="text" required id="reason" name="reason">
            </div>
            <input type="hidden" name="action" value="choose">
            <div class="ui-button">
                <button type="submit" onclick="return validate_course()">提交</button>
            </div>
        </form>
    </c:if>

    <%-- 审批进度页面  --%>
    <c:if test="${not empty courses_approving}">
        <%-- 选课表 --%>
        <form action="approving" method="POST">
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
                        <th>选课理由</th>
                        <th>审批进度</th>
                        <th>取消选课</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses_approving}" var="approval">
                        <tr>
                            <td><c:out value="${approval.courseId}"/></td>
                            <td><c:out value="${approval.courseName}"/></td>
                            <td><c:out value="${approval.credits}"/></td>
                            <td><c:out value="${approval.semester}"/></td>
                            <td><c:out value="${approval.beginYear}"/></td>
                            <td><c:out value="${approval.courseNotes}"/></td>
                            <td><c:out value="${approval.chooseNotes}"/></td>
                            <td>
                                <c:choose>
                                    <c:when test="${approval.status == null}">
                                        审核中
                                    </c:when>
                                    <c:when test="${approval.status == false}">
                                        驳回
                                    </c:when>
                                </c:choose>
                            </td>
                            <td>
                                <label for="course_${approval.courseId}">
                                    <input type="radio" id="course_${approval.courseId}" name="course_id"
                                           value="${approval.courseId}">
                                    取消选课
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
                            <a href="/AcademicAffairsApprovalSystem_war/student/approving?action=approving&amp;page=${i}">${i}</a>&nbsp;
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </div>
            <input type="hidden" name="action" value="approving">
            <div class="ui-button">
                <button type="submit" onclick="return validate_approving()">提交</button>
            </div>
        </form>
    </c:if>

    <%--多条件查询--%>
    <c:if test="${action eq \"select\"}">
        <form action="select" method="post">
            <div>
                <label for="course_name">课程名：</label>
                <input type="text" id="course_name" name="input_course_name">
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
                    <input type="radio" id="status1" name="input_status" value="approving">
                    <label for="status1">审核中</label>
                    <input type="radio" id="status2" name="input_status" value="reject">
                    <label for="status2">驳回</label>
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
                        <th>学分</th>
                        <th>学期</th>
                        <th>开课年份</th>
                        <th>备注</th>
                        <th>选课理由</th>
                        <th>审批进度</th>
                        <th>取消选课</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${courses_select}" var="approval">
                        <tr>
                            <td><c:out value="${approval.courseId}"/></td>
                            <td><c:out value="${approval.courseName}"/></td>
                            <td><c:out value="${approval.credits}"/></td>
                            <td><c:out value="${approval.semester}"/></td>
                            <td><c:out value="${approval.beginYear}"/></td>
                            <td><c:out value="${approval.courseNotes}"/></td>
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
                            <td>
                                <c:if test="${approval.status != true}">
                                <label for="course_${approval.courseId}">
                                    <input type="radio" id="course_${approval.courseId}" name="course_id"
                                           value="${approval.courseId}">
                                    取消选课
                                </label>
                                </c:if>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
            <input type="hidden" name="action" value="select">
            <div class="ui-button">
                <button type="submit" onclick="return validate_approving()">提交</button>
            </div>
        </form>
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

    function validate_course() {
        var radios = document.getElementsByName("course_id");
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
        var reason = document.getElementsByName("reason")[0].value.trim();
        if (reason === "") {
            alert("请填写选课原因");
            return false;
        }
        return true;
    }

    function validate_approving() {
        var radios = document.getElementsByName("course_id");
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

</script>

</body>
</html>
