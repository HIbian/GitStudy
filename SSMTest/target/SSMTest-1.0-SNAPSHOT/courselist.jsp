<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/7
  Time: 17:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-学科列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
    function chengesize() {
        var size = $("#selectPageSize").val();
            location.href = "${pageContext.request.contextPath}/course/list/1?pageSize=" + size;
    }
    function gotoPage() {
        var pageIndex = $("#pageIndex").val();
        location.href="${pageContext.request.contextPath}/course/list/"+pageIndex+"?pageSize=${pageBean.size}";
    }
    </script>
</head>
<body>
<div class="layui-container">
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>序号</td>
            <td>学科名称</td>
            <td>周数</td>
            <td>成立日期</td>
            <td>类型</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageBean.records}" var="c">
            <tr>
                <td>${c.id}</td>
                <td>${c.name}</td>
                <td>${c.week}</td>
                <td><fmt:formatDate value="${c.createdate}"/></td>
                <td>${c.type}</td>
                <td><a class="layui-btn layui-btn-mini" href="${pageContext.request.contextPath}/course/goupdate/${c.id}">编辑</a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" onclick="deleteCourse(${c.id});">删除</a></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
        <a href="/course/list/${pageBean.current==1?1:pageBean.current-1}?pageSize=${pageBean.size}" class="layui-laypage-prev">
            <i class="layui-icon">&lt;</i>
        </a>
        <c:forEach items="${pagelist}" var="p">
            <c:if test="${p!=pageBean.current}">
                <a href="/course/list/${p}?pageSize=${pageBean.size}">${p}</a>
            </c:if>
            <c:if test="${p==pageBean.current}">
                <span style="color:#009688;font-weight: bold;">${p}</span>
            </c:if>
        </c:forEach>
        <a href="/course/list/${pageBean.current==(pageBean.total%pageBean.size==0?pageBean.total/pageBean.size:pageBean.total/pageBean.size+1)?pageBean.current:pageBean.current+1}?pageSize=${pageBean.size}"
           class="layui-laypage-next">
            <i class="layui-icon">&gt;</i>
        </a>
        <span class="layui-laypage-skip">到第
							   <input type="text" min="1"  value="${pageBean.current}" class="layui-input" id="pageIndex">页
								<button type="button" class="layui-laypage-btn" onclick="gotoPage()">确定</button>
							</span>
        <span class="layui-laypage-count">共 ${pageBean.total} 条</span>
        <span class="layui-laypage-limits">
							 <select id="selectPageSize" onchange="chengesize()" lay-ignore="">
							        <option value="10" ${pageBean.size==10?"selected":""}>10 条/页</option>
									<option value="20" ${pageBean.size==20?"selected":""}>20 条/页</option>
									<option value="30" ${pageBean.size==30?"selected":""}>30 条/页</option>
									<option value="40" ${pageBean.size==40?"selected":""}>40 条/页</option>
							</select>
		</span>
    </div>
</div>
<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
<script type="text/javascript">
    function deleteCourse(cid) {
        layui.use('table', function () {
            layer.confirm('是否确认删除学科?', function () {
                location.href="${pageContext.request.contextPath}/course/del/"+cid;
                layer.msg("删除成功", {icon: 6});
                // layer.msg("删除失败", {icon: 5});
            });
        });
    }
</script>
</body>
</html>
