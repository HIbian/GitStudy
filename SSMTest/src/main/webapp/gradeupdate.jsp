<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9
  Time: 15:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-班级修改</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
    <script type="text/javascript">
        $(function () {//动态加载下拉框
            $.ajax({
                url:"${pageContext.request.contextPath}/course/getcourseLists",
                type:"get",
                dataType:"json",
                success:function (courses) {
                    var str="";
                    for (var i = 0; i <courses.length ; i++) {
                        if (courses[i].id!=${g.id}){
                            str+="<option value='"+courses[i].id+"' >"+courses[i].name+"</option>" ;
                        }else{
                            str+="<option value='"+courses[i].id+"' selected>"+courses[i].name+"</option>" ;
                        }

                    }
                    $("#cds").html(str);
                    layui.form.render("select");
                }
            });
        });
    </script>
</head>
<body>

<div class="layui-container" style="margin-top: 5px">
    <form class="layui-form" action="/grade/realupdate" method="post">
        <div class="layui-form-item">
            <label class="layui-form-label">班级序号</label>
            <div class="layui-input-block">
                <input type="text" name="id" readonly="readonly" autocomplete="off"
                       class="layui-input" id="f1" value="${g.id}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级名称</label>
            <div class="layui-input-block">
                <input type="text" name="name" id="f2" lay-verify="name" autocomplete="off"
                       placeholder="请输入名称" class="layui-input" value="${g.name}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级学科</label>
            <div class="layui-input-block">
                <select name="cid" id="cds">
                    <%--ajax--%>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">开班日期</label>
            <div class="layui-input-block">
                <input type="text" name="createdate" readonly="readonly" id="f4" autocomplete="off" class="layui-input" value="<fmt:formatDate value="${g.createdate}"/>">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级周期</label>
            <div class="layui-input-block">
                <input type="text" name="week" id="f5" lay-verify="name" autocomplete="off"
                       placeholder="请输入周期" class="layui-input" value="${g.week}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">班级地址</label>
            <div class="layui-input-block">
                <input type="text" name="location" id="f6" lay-verify="name" autocomplete="off"
                       placeholder="请输入地址" class="layui-input" value="${g.location}">
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
