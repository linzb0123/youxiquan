<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/11/13
  Time: 17:56
  To change this template use File | Settings | File Templates.
--%>
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
    <script type="text/javascript" src="/lib/html5shiv.js"></script>
    <script type="text/javascript" src="/lib/respond.min.js"></script>
    <![endif]-->
    <link href="/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
    <link href="/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
    <link href="/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
    <link href="/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title> GAME COMMUNITY 后台登录</title>
</head>
<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
    <div id="loginform" class="loginBox">
        <form class="form form-horizontal" action="" method="post">
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
                <div class="formControls col-xs-8">
                    <input id="username" name="username" type="text" placeholder="账户" class="input-text size-L">
                </div>
            </div>
            <div class="row cl">
                <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
                <div class="formControls col-xs-8">
                    <input id="password" name="password" type="password" placeholder="密码" class="input-text size-L">
                </div>
            </div>
            <%--<div class="row cl">--%>
                <%--<div class="formControls col-xs-8 col-xs-offset-3">--%>
                    <%--<input class="input-text size-L" type="text" placeholder="验证码" onblur="if(this.value==''){this.value='验证码:'}" onclick="if(this.value=='验证码:'){this.value='';}" value="验证码:" style="width:150px;">--%>
                    <%--<img src=""> <a id="kanbuq" href="javascript:;">看不清，换一张</a> </div>--%>
            <%--</div>--%>
            <%--<div class="row cl">--%>
                <%--<div class="formControls col-xs-8 col-xs-offset-3">--%>
                    <%--<label for="online">--%>
                        <%--<input type="checkbox" name="online" id="online" value="">--%>
                        <%--使我保持登录状态</label>--%>
                <%--</div>--%>
            <%--</div>--%>
            <div class="row cl">
                <div class="formControls col-xs-8 col-xs-offset-3">
                    <input name="" type="button" id="loginButton"  class="btn btn-success radius size-L" value=" 登 录 ">
                    <input name="" type="reset" class="btn btn-default radius size-L" value=" 取 消 ">
                </div>
            </div>
        </form>
    </div>
</div>
<div class="footer">Copyright © 2018 by Zexin Yao,Zibin Lin,Zefeng Wu,Qiang Feng</div>
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript">

   // layer.msg("用户名或密码不能为空");
        $('#loginButton').click(function () {
            $("#loginButton").val("登录中...");
            $("#loginButton").attr("disabled","disabled");
            var name=$("#username").val();
            var pass=$("#password").val();
            if(name==""||pass==""){
                layer.msg("用户名或密码不能为空");
                $("#loginButton").val(" 登    录 ");
                $("#loginButton").removeAttr("disabled");
                return false;
            }
            var reg = /^[0-9A-Za-z]+$/;
            if(!reg.exec(name))
            {
                layer.msg("用户名格式有误");
                $("#loginButton").val("登录");
                $("#loginButton").removeAttr("disabled");
                return;
            }
            $.ajax({
                url: '/user/doLogin?t=' + (new Date()).getTime(), // 加随机数防止缓存
                type: 'POST',
                dataType: 'json',
                data: {
                    username: name,
                    password: pass,
                },
                success: function (data) {
                    if(data.code==1){
                        window.location.href="/";
                    }else{
                        layer.msg(data.msg);
                        $("#loginButton").val("登录");
                        $("#loginButton").removeAttr("disabled");
                    }
                },
                error:function(XMLHttpRequest){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                    $("#loginButton").val("登录");
                    $("#loginButton").removeAttr("disabled");
                }
            });
        });

</script>
</body>
</html>