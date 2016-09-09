<%--
  Created by IntelliJ IDEA.
  User: dengwei06015
  Date: 2016/6/8
  Time: 15:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<html>
<head>
    <title>xss_filter</title>
</head>
<body>
source:
<textarea id="text1"></textarea>
result:
<textarea id="text2"></textarea>
<input type="button" id="btn_ok" value="提交"/>
</body>
<script src="${ctx}/js/jquery.min.js"></script>
<script>
    $("#btn_ok").on('click',function () {
        $.ajax({
            url: '${ctx}/xss_filter',
            data: {
                content: $("#text1").val()
            },
            type: 'post',
            cache: false,
            dataType: 'json',
            success: function (data) {
                $("#text2").val(data.result)
            },
            error: function () {
                view("异常！");
            }
        });
    });

</script>
</html>
