<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9
  Time: 17:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-学员列表</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {//动态加载下拉框
            $.ajax({
                url:"${pageContext.request.contextPath}/grade/getGradeLists",
                type:"get",
                dataType:"json",
                success:function (grades) {
                    var str="<option value='-1' ${stu.gid==-1?"selected":""} >--请选择--</option>" ;
                    for (var i = 0; i <grades.length ; i++) {
                        if (${stu.gid}!=grades[i].id){
                            str+="<option value='"+grades[i].id+"' >"+grades[i].name+"</option>" ;
                        }else {
                            str+="<option value='"+grades[i].id+"' selected >"+grades[i].name+"</option>" ;
                        }
                    }
                    $("#fg").html(str);
                    layui.form.render("select");
                }
            });
        });
    </script>
</head>
<body>
<div class="layui-container">
    <div class="layui-row" style="margin-top: 10px">
        <form action="/student/list/1/${selectPage.size}" method="post">
        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">姓名：</label>
                <div class="layui-input-block">
                    <input type="text" name="name" id="no" class="layui-input" placeholder="学生姓名" value="${stu.name}">
                </div>
            </div>
        </div>
        <div class="layui-col-xs3" style="margin-right: 20px">
            <div class="layui-form-item layui-form-text">
                <label class="layui-form-label">班级：</label>
                <div class="layui-input-block">
                    <select class="layui-input" id="fg" name="gid">

                    </select>
                </div>
            </div>
        </div>
        <div class="layui-col-xs2">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <%--<button class="layui-btn" onclick="searchData()"><i class="layui-icon layui-icon-search">搜索</i></button>--%>
                    <input type="submit" class="layui-btn" value="搜索">
                </div>
            </div>
        </div>
        </form>

        <div class="layui-col-xs2">
            <div class="layui-form-item">
                <div class="layui-input-block">
                    <a class="layui-btn layui-btn-mini layui-btn-mini" href="javascript:alert('导出中...');" lay-event="detail">导出Excel</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="layui-container">
    <table class="layui-table" id="tbdata" lay-filter="tbop">
        <thead>
        <tr>
            <td>学号</td>
            <td>姓名</td>
            <td>班级</td>
            <td>性别</td>
            <td>手机号</td>
            <td>邮箱</td>
            <td>操作</td>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${pageBean}" var="s">
            <tr>
                <td>${s.no}</td>
                <td>${s.name}</td>
                <td>${s.grade.name}</td>
                <td>${s.sex}</td>
                <td>${s.phone}</td>
                <td>${s.email}</td>
                <td><a class="layui-btn layui-btn-mini" href="/student/goupdate/${s.id}">编辑</a>
                    <a class="layui-btn layui-btn-mini layui-btn-mini" href="/student/detial/${s.id}" lay-event="detail">查看详情</a>
                    <a class="layui-btn layui-btn-danger layui-btn-mini" lay-event="del" onclick="deleteStudent(${s.id});">删除</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="layui-box layui-laypage layui-laypage-default" id="layui-laypage-1">
        <a href="/student/list/${selectPage.hasPrevious()?selectPage.current-1:1}/${selectPage.size}?name=${stu.name}&gid=${stu.gid}" class="layui-laypage-prev">
            <i class="layui-icon">&lt;</i>
        </a>
        <c:forEach items="${pageList}" var="p">
            <c:if test="${p!=selectPage.current}">
                <a href="/student/list/${p}/${selectPage.size}?name=${stu.name}&gid=${stu.gid}">${p}</a>
            </c:if>
            <c:if test="${p==selectPage.current}">
                <span style="color:#009688;font-weight: bold;">${p}</span>
            </c:if>
        </c:forEach>
        <a href="/student/list/${selectPage.hasNext()?selectPage.current+1:selectPage.current}/${selectPage.size}?name=${stu.name}&gid=${stu.gid}"
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
    function chengesize() {
        var size = $("#selectPageSize").val();
        location.href = "${pageContext.request.contextPath}/student/list/1/" + size;
    }
    function gotoPage() {
        var pageIndex = $("#pageIndex").val();
        location.href="${pageContext.request.contextPath}/student/list/"+pageIndex+"/${selectPage.size}?name=${stu.name}&gid=${stu.gid}";
    }

    function deleteStudent(id){
        layui.use('table', function() {
            layer.confirm('是否确认删除学生?',function() {
                location.href="${pageContext.request.contextPath}/student/del/"+id;
                layer.msg("删除成功", {icon : 6});
                // layer.msg("删除失败", {icon : 5});
            });
        });
    }
</script>


</body>
</html>
