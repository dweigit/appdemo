<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title> pjax</title>
    <script src="${ctx}/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${ctx}/js/jquery.pjax.js"></script>
    <script type="text/javascript">
        $(function () {
            $.ajax({
                url: "/json",    //请求的url地址
                dataType: "json",   //返回格式为json
                async: true, //请求是否异步，默认为异步，这也是ajax重要特性
                data: {"id": "value"},    //参数值
                type: "GET",   //请求方式
                beforeSend: function () {
                    //请求前的处理
                },
                success: function (req) {
                    alert(req);
                    //请求成功时处理
                },
                complete: function () {
                    //请求完成的处理
                },
                error: function () {
                    //请求出错处理
                }
            });
        });
    </script>
</head>

<body>
<h2>demo page</h2>
<div class="error"></div>
</body>
</html>