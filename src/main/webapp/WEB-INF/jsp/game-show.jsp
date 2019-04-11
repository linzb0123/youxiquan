<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>查看游戏</title>
</head>
<style>
    .text-r {
        width: 70px;
        vertical-align: top;
    }

</style>
<body>
<div class="cl pd-20" style=" background-color:#5bacb6">
    <img id="photo" class="avatar size-XL l" src="">
    <dl style="margin-left:80px; color:#fff">
        <dt>
            <span id="id" class="f-18"></span>
        </dt>
        <dd id="gameName" class="pt-10 f-12" style="margin-left:0"></dd>
    </dl>
</div>
<div class="pd-20">
    <table class="table">
        <tbody>
        <%--<tr>--%>
            <%--<th class="text-r" width="80">ID：</th>--%>
            <%--<td id="id"></td>--%>
        <%--</tr>--%>
        <%--<tr>--%>
            <%--<th class="text-r">游戏名：</th>--%>
            <%--<td id="gameName"></td>--%>
        <%--</tr>--%>
        <tr>
            <th class="text-r">游戏简介：</th>
            <td id="info"></td>
        </tr>
        <tr>
            <th class="text-r" >创建时间：</th>
            <td id="createTime"></td>
        </tr>
        <tr>
            <th class="text-r">排序：</th>
            <td id="orderNum"></td>
        </tr>
        <tr>
            <th class="text-r">关注人数：</th>
            <td id="number"></td>
        </tr>
        </tbody>
    </table>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>
<script type="text/javascript" src="lib/common.js"></script>
<!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript">

    $(function() {

        $("#id").html(parent.id);
        $("#gameName").html(parent.gameName);
        $("#info").html(parent.info);
        $("#number").html(parent.number);
        $("#createTime").html(dateAll(parent.createTime));
        $("#orderNum").html(parent.orderNum);
        $("#photo").attr("src", parent.photo);
    })
</script>
</body>
</html>
