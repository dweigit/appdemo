<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<ul class="sidebar-menu">
    <li class="header"></li>
    <li class="treeview">
        <a href="#"><i class="fa fa-th"></i> <span>表单</span>
            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
        </a>
        <ul class="treeview-menu">
            <li><a href="/page/form1">from1</a></li>
            <li><a href="/page/form2">form2</a></li>
            <li><a href="/page/form3">form3</a></li>
            <li><a href="/page/form4">form4</a></li>
        </ul>
    </li>
    <li class="treeview">
        <a href="#">
            <i class="fa fa-th"></i><span>表格</span><span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
        </a>
        <ul class="treeview-menu">
            <li><a href="/page/table1">table1</a></li>
            <li><a href="/page/table2">table2</a></li>
            <li><a href="/page/table3">table3</a></li>
            <li><a href="/page/table4">table4</a></li>
        </ul>
    </li>
    <li><a href="/page/icon"><i class="fa fa-th "></i><span>icon</span></a></li>
    <li><a href="#"><i class="fa fa-th"></i> <span>Another Link</span></a></li>
    <li class="treeview">
        <a href="#"><i class="fa fa-th"></i> <span>Multilevel</span>
            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
        </a>
        <ul class="treeview-menu">
            <li><a href="/table2">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
        </ul>
    </li>
    <li class="treeview">
        <a href="#"><i class="fa fa-th"></i> <span>Setting</span>
            <span class="pull-right-container"><i class="fa fa-angle-left pull-right"></i></span>
        </a>
        <ul class="treeview-menu">
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
            <li><a href="#">Link in level 2</a></li>
        </ul>
    </li>
</ul>

<script type="text/javascript">
    $(function(){
        //菜单选中初始化
        var path = window.location.pathname;
        $("a[href='"+path+"']").parent("li").addClass("active");
        $("a[href='"+path+"']").parents("li.treeview").addClass("active");
        //面包屑导航初始化
        var li_html = $("a[href='"+path+"']").parent("li").html();
        $(".breadcrumb").append("<li>"+li_html+"</li>");

    });
</script>