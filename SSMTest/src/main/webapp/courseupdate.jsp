<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-学科修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
    <%--<script type="text/javascript">--%>
        <%--$(function () {--%>
            <%--//获取请求参数--%>
            <%--var json=getData();--%>
            <%--//解码参数值并解析json--%>
            <%--var obj=JSON.parse(decodeURI(json));--%>
            <%--//显示数据--%>
            <%--showData(obj);--%>
        <%--})--%>
        <%--//获取传递的参数信息--%>
        <%--function getData() {--%>
            <%--var url=window.location.search;--%>
            <%--index=url.indexOf("?");--%>
            <%--if(index>-1){--%>
                <%--var str = url.substr(index+1);--%>
                <%--if(str.indexOf('=')){--%>
                    <%--return str.split('=')[1];--%>
                <%--}--%>
            <%--}--%>
            <%--return null;--%>
        <%--}--%>
        <%--//显示数据--%>
        <%--function showData(obj) {--%>
            <%--$("#f1").val(obj.id);--%>
            <%--$("#f2").val(obj.name);--%>
            <%--$("#f3").val(obj.createdate);--%>
            <%--$("#f4").val(obj.week);--%>
            <%--$("#f5").val(obj.type);--%>
        <%--}--%>
    <%--</script>--%>
</head>
<body>

<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="${pageContext.request.contextPath}/course/realupdate" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">学科序号</label>
            <div class="layui-input-block">
                <input type="text" name="id" readonly="readonly" autocomplete="off" value="${c.id}"
                       class="layui-input" id="f1">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学科名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" id="f2" lay-verify="name" autocomplete="off" value="${c.name}"
                       placeholder="请输入名称" class="layui-input">
            </div>
        </div>

        <div class="layui-form-item">
            <label class="layui-form-label">成立日期</label>
            <div class="layui-input-block">
                <input type="text" name="createdate" readonly="readonly" id="f3" autocomplete="off" class="layui-input" value="<fmt:formatDate value="${c.createdate}" pattern="yyyy-MM-dd"/>">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学科周期</label>
            <div class="layui-input-block">
                <input type="text" name="week" id="f4" lay-verify="name" autocomplete="off"
                       placeholder="请输入周期" class="layui-input" value="${c.week}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学科类型</label>
            <div class="layui-input-block">
                <select class="" name="type">
                    <option value="业余" ${c.type=="业余"?"selected":""}>业余</option>
                    <option value="普通" ${c.type=="普通"?"selected":""}>普通</option>
                    <option value="精品" ${c.type=="精品"?"selected":""}>精品</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <input class="layui-btn"  style="margin-left: 10%"  type="submit" value="确认修改">
        </div>
    </form>
</div>

<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>
    layui.use([ 'form', 'laydate' ],
        function() {
            var form = layui.form, layer = layui.layer, laydate = layui.laydate;

        });
</script>
</body>
</html>
