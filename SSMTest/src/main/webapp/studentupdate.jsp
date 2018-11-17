<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/11/9
  Time: 19:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>滴答办公系统-学员更新</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/media/layui/css/layui.css" media="all">
    <script type="text/javascript" src="${pageContext.request.contextPath}/media/js/jquery.min.js"></script>
    <script type="text/javascript">
        $(function () {//动态加载下拉框,并回显数据
            $.ajax({
                url:"${pageContext.request.contextPath}/grade/getGradeLists",
                type:"get",
                dataType:"json",
                success:function (grades) {
                    var str="<option value='-1' ${s.gid==-1?"selected":""} >--请选择--</option>" ;
                    for (var i = 0; i <grades.length ; i++) {
                        if (${s.gid}!=grades[i].id){
                            str+="<option value='"+grades[i].id+"' >"+grades[i].name+"</option>" ;
                        }else {
                            str+="<option value='"+grades[i].id+"' selected >"+grades[i].name+"</option>" ;
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
    <form class="layui-form" action="/student/realupdate" method="post">
        <div class="layui-form-item">

            <input type="hidden" name="id" value="${s.id}"/>
            <input type="hidden" name="flag" value="${s.flag}"/>
            <input type="hidden" name="photo" value="${s.photo}"/>
            <input type="hidden" name="del" value="${s.del}"/>

            <label class="layui-form-label">学员学号</label>
            <div class="layui-input-block">
                <input type="text" name="no" lay-verify="name" autocomplete="off"
                       placeholder="请输入学号" id="no1" class="layui-input" value="${s.no}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学员姓名</label>
            <div class="layui-input-block">
                <input type="text" name="name" lay-verify="name" autocomplete="off"
                       placeholder="请输入姓名" class="layui-input" value="${s.name}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">所属班级</label>
            <div class="layui-input-block">
                <select name="gid" id="cds">
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="男" title="男" ${s.sex=="男"?"checked":""}>
                <input type="radio" name="sex" value="女" title="女" ${s.sex=="女"?"checked":""}>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">邮箱</label>
            <div class="layui-input-inline">
                <input type="text" name="email" lay-verify="required"
                       placeholder="请输入有效" autocomplete="off" class="layui-input" value="${s.email}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">手机号</label>
            <div class="layui-input-inline">
                <input type="text" name="phone" lay-verify="required"
                       placeholder="请输入手机号" autocomplete="off" class="layui-input" value="${s.phone}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">QQ</label>
            <div class="layui-input-inline">
                <input type="text" name="qq" lay-verify="required"
                       placeholder="请输入QQ" autocomplete="off" class="layui-input" value="${s.qq}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">身份证号</label>
            <div class="layui-input-inline">
                <input type="text" name="cardno" lay-verify="required"
                       placeholder="请输入身份证号" autocomplete="off" class="layui-input" value="${s.cardno}">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">毕业学校</label>
            <div class="layui-input-inline">
                <input type="text" name="school" lay-verify="required"
                       placeholder="请输入毕业学校" autocomplete="off" class="layui-input" value="${s.school}" >
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">学历</label>
            <div class="layui-input-inline">
                <select name="education">
                    <option ${s.education=="初中"?"selected":""}>初中</option>
                    <option ${s.education=="高中"?"selected":""}>高中</option>
                    <option ${s.education=="专科"?"selected":""}>专科</option>
                    <option ${s.education=="本科"?"selected":""}>本科</option>
                    <option ${s.education=="硕士"?"selected":""}>硕士</option>
                    <option ${s.education=="博士"?"selected":""}>博士</option>
                </select>
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">出生日期</label>
            <div class="layui-input-inline">
                <input type="date" name="birthday" id="date1" autocomplete="off"
                       class="layui-input" value="<fmt:formatDate value="${s.birthday}" pattern="yyyy-MM-dd"/>">
            </div>
        </div>
        <div class="layui-form-item">
            <label class="layui-form-label">入学日期</label>
            <div class="layui-input-inline">
                <input type="date" name="createdate" id="date2" autocomplete="off"
                       class="layui-input" value="<fmt:formatDate value="${s.createdate}" pattern="yyyy-MM-dd"/>" />
            </div>
        </div>
        <div class="layui-form-item">
            <input class="layui-btn"  style="margin-left: 10%" id="btn1" type="submit"
                   value="确认更新">
        </div>
    </form>
</div>


<script src="${pageContext.request.contextPath}/media/layui/layui.js"></script>
<!-- 注意：如果你直接复制所有代码到本地，上述js路径需要改成你本地的 -->
<script>

    var form;
    layui.use(
        [ 'form','upload', 'layedit', 'laydate' ],
        function() {
            form = layui.form, layer = layui.layer, layedit = layui.layedit, laydate = layui.laydate;
            var upload = layui.upload;
            //日期
            laydate.render({
                elem : '#date1'
            });
            laydate.render({
                elem : '#date2'
            });
            initData();
        });
</script>
</body>
</html>
