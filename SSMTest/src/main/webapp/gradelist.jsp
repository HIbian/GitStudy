<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9
  Time: 14:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-班级列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        function chengesize() {
            var size = $("#selectPageSize").val();
            location.href = "${pageContext.request.contextPath}/grade/list/1?pageSize=" + size;
        }
        function gotoPage() {
            var pageIndex = $("#pageIndex").val();
            location.href="${pageContext.request.contextPath}/grade/list/"+pageIndex+"?pageSize=${selectPage.size}";
        }
    </script>
</head>
<body>
<div class="layui-container">
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>序号</td>
            <td>班级名称</td>
            <td>班级人数</td>
            <td>学科名称</td>
            <td>周数</td>
            <td>位置</td>
            <td>开班日期</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageBean}" var="g">
        <tr>
            <td>${g.id}</td>
            <td>${g.name}</td>
            <td>${g.count}</td>
            <td>${g.course.name}</td>
            <td>${g.week}</td>
            <td>${g.location}</td>
            <td><fmt:formatDate value="${g.createdate}" pattern="yyyy-MM-dd"/></td>
            <td><a class="layui-btn layui-btn-mini" href="/grade/goupdate/${g.id}">编辑</a>
                <a class="layui-btn layui-btn-danger layui-btn-mini"
                   lay-event="del" onclick="deleteCourse(${g.id});">删除</a></td>
        </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
        <a href="/grade/list/${selectPage.current==1?1:selectPage.current-1}?pageSize=${selectPage.size}" class="layui-laypage-prev">
            <i class="layui-icon">&lt;</i>
        </a>
        <c:forEach items="${pageList}" var="p">
            <c:if test="${p!=selectPage.current}">
                <a href="/grade/list/${p}?pageSize=${selectPage.size}">${p}</a>
            </c:if>
            <c:if test="${p==selectPage.current}">
                <span style="color:#009688;font-weight: bold;">${p}</span>
            </c:if>
        </c:forEach>
        <a href="/grade/list/${selectPage.current==(selectPage.total%selectPage.size==0?selectPage.total/selectPage.size:selectPage.total/selectPage.size+1)?selectPage.current:selectPage.current+1}?pageSize=${selectPage.size}"
           class="layui-laypage-next">
            <i class="layui-icon">&gt;</i>
        </a>
        <span class="layui-laypage-skip">到第
							   <input type="text" min="1"  value="${selectPage.current}" class="layui-input" id="pageIndex">页
								<button type="button" class="layui-laypage-btn" onclick="gotoPage()">确定</button>
							</span>
        <span class="layui-laypage-count">共 ${selectPage.total} 条</span>
        <span class="layui-laypage-limits">
							 <select id="selectPageSize" onchange="chengesize()" lay-ignore="">
							        <option value="10" ${selectPage.size==10?"selected":""}>10 条/页</option>
									<option value="20" ${selectPage.size==20?"selected":""}>20 条/页</option>
									<option value="30" ${selectPage.size==30?"selected":""}>30 条/页</option>
									<option value="40" ${selectPage.size==40?"selected":""}>40 条/页</option>
							</select>
		</span>
    </div>
</div>

<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>

<script type="text/javascript">
    function deleteCourse(id){
        layui.use('table', function() {
            layer.confirm('是否确认删除班级?',function() {
                location.href="${pageContext.request.contextPath}/grade/del/"+id;
                layer.msg("删除成功", {icon : 6});
            });
        });
    }
</script>

</body>
</html>
