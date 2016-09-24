<!DOCTYPE html>
<html>
<head>
    <meta charset='utf-8'>
    <title> pjax</title>
    <script src="${ctx}/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <script src="${ctx}/js/jquery.pjax.js"></script>
    <script type="text/javascript">
        //第一种调用方式,属性为data-pjax的a标签加载pajx
//        $('a[data-pjax]').pjax();
        $(function(){
            $(document).pjax('a', '#main')
        })
//        //第二种调用方式,class属性为js-pjax的a标签加载pjax,加载的位置是id属性为main的标签
//        $('a.js-pjax').pjax('#main', {
//            timeout: null, error: function (xhr, err) {
//                $('.error').text(err + ":未找到该页面")
//            }
//        })
//        //第三种调用方式,id属性为main中的a标签点击执行一个方法
//        $('.tabs a').pjax('#main').live('click', function () {
//            $(".loader").fadeIn("slow");
//            $(".loader").fadeOut("slow");
//        })
    </script>
</head>

<body>

<!--data-pjax为需要接收的另一页面的标签,id为main-->
<a href='/login' data-pjax='#main'>11111111111</a>
<!--id为main的标签接收另一页面,如无法找到目标页面则在class为error处提示错误-->
<a href='/table' class='js-pjax'>222222222222</a>
<!--id为main的标签接收另一页面-->
<div id='main'>
    <div class='loader' style='display:none'>11111</div>
    <div class='tabs'>
        <a href='3.html'>33333333333</a>
    </div>
</div>
<div class="error"></div>
</body>
</html>