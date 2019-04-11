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
    <title>查看帖子</title>
</head>
<style>
    .text-r {
        width: 70px;
        vertical-align: top;
    }
    .avatar{
        width: 50px;
        height: 50px;
        font-size:16px;
    }
    .line{
        height: 1px;
        width: 95%;
        background: black;
        -webkit-box-shadow: 3px 3px 6px #666;
        -moz-box-shadow: 3px 3px 6px #666;
        box-shadow: 3px 3px 6px #b2b2b2;
    }
</style>
<body>
<div id="head">
    <div class="cl pd-15" style=" background-color:#5bacb6">
        <img id="headimgurl" class="avatar l" src="">
        <dl style="margin-left:60px; color:#fff">
            <dt>
                <span id="username" class="f-18"></span>
            </dt>
            <dd id="createTime" class="f-12" style="margin-left:0"></dd>
            <dd id="message" class="pt-5 f-13" style="margin-left:0"></dd>
            <dd class="pt-5 f-13" style="margin-left:0">
                <span id="praiseNum"></span>&nbsp;<i class="Hui-iconfont" style='font-size:16px;'>&#xe697;</i>&nbsp;&nbsp;&nbsp;
                <span id="replyNum"></span>&nbsp;<i class="Hui-iconfont" style='font-size:16px;'>&#xe6b3;</i>
            </dd>
        </dl>
    </div>
</div>
<%--<div class="cl pd-10 ">--%>
    <%--<img id="Replyphoto" class="avatar l" src="">--%>
    <%--<dl style="margin-left:60px; color:black">--%>
        <%--<dt>--%>
            <%--<span  class="f-18">小黑</span>--%>
        <%--</dt>--%>
        <%--<dd  class="f-12" style="margin-left:0">2018-11-23 15:13:56</dd>--%>
        <%--<dd  class="pt-5 f-13 pd-10" style="margin-left:0">这真的是一个好帖子，我真的太喜欢了，太厉害额</dd>--%>
        <%--<div class="line"></div>--%>
    <%--</dl>--%>
<%--</div>--%>

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
        $("#headimgurl").attr("src", parent.headimgurl);
        $("#username").html(parent.username);
        $("#message").html(parent.message);
        $("#createTime").html(dateAll(parent.createTime));
        $("#praiseNum").html(parent.praiseNum);
        $("#replyNum").html(parent.replyNum);
        var replyResult = parent.longNewsReplyDto;
        for(var i=0; i<replyResult.length;i++){
            var str = "<div class=\"cl pd-10 \">\n" +
                "    <img id=\"Replyphoto\" class=\"avatar l\" src=\""+replyResult[i].head+"\">\n" +
                "    <dl style=\"margin-left:60px; color:black\">\n" +
                "        <dt>\n" +
                "            <span  class=\"f-18\">"+replyResult[i].replyUserName+"</span>\n" +
                "        </dt>\n" +
                "        <dd  class=\"f-12\" style=\"margin-left:0\">"+dateAll(replyResult[i].createTime)+"</dd>\n" +
                "        <dd  class=\"pt-5 f-13 pd-10\" style=\"margin-left:0\">"+replyResult[i].message+"</dd>\n" +
                "        <div class=\"line\"></div>\n" +
                "    </dl>\n" +
                "</div>";
            $("#head").append(str);
        }
    })
</script>
</body>
</html>
