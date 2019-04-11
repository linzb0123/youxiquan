<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport"
          content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <!--[if lt IE 9]>
    <script type="text/javascript" src="lib/html5shiv.js"></script>
    <script type="text/javascript" src="lib/respond.min.js"></script>
    <![endif]-->
    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css"/>
    <link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin"/>
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css"/>
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js"></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->
    <title>游戏关注</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
    .avatar {
        width: 50px;
        height: 50px;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 游戏圈管理 <span
        class="c-gray en">&gt;</span> 游戏关注 <a id="btn-refresh" class="btn btn-success radius r"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="form-search" class="text-c">信息：
        <input type="text" class="input-text" style="width:250px" placeholder="输入用户姓名、游戏名等信息" id="searchKey"
               name="searchKey">
        <button id="searchButton" type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i>
            搜帖子
        </button>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
        </span>
        <span class="r">共有数据：<strong id="userFocusListCount">0</strong> 条</span>
    </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="30"><input type="checkbox" name="" value=""></th>
                <th width="40">ID</th>
                <th width="40">用户名</th>
                <th width="45">游戏名</th>
                <th width="80">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<%--<script type="text/javascript" src="/lib/datatables/dataTables.colReorder.min.js"></script>--%>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/lib/common.js"></script>
<script type="text/javascript">

    $(function () {
        $('.table').dataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
                url: "/userFocus/list",
                type: 'GET',
                data: {
                    "searchKey": "",
                },
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return "<input name=\"checkbox\" value=\"" + row.id + "\" type=\"checkbox\" value=\"\">";
                    }
                },
                {"data": "id"},
                {"data": "username",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"user_show('用户信息'," + row.userId + ",'user-show','360','400')\">"+data+"</a>";
                    }
                },
                {"data": "gameName",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"game_show('游戏信息'," + row.gameId + ",'game-show','360','400')\">"+data+"</a>";
                    }
                },
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return  "<a title=\"删除\" href=\"javascript:;\" onclick=\"userFocus_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
                    }
                }
            ],
            "aaSorting": [[1, "desc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                // {"bVisible": false, "aTargets": [4, 6]}, //控制列的隐藏显示
                {"orderable": false, "aTargets": [0, 3]}// 制定列不参与排序
            ],
            colReorder: true
        });

        userFocus_count();
    })



    /*用户信息-查看*/
    function user_show(title,userId,url,w,h){
        $.ajax({
            type: 'GET',
            url: '/user/getUser/' + userId,
            dataType: 'json',
            success: function(data){
                if(data != null && data.result != null)
                {
                    id = userId;
                    username = data.result.username;
                    phone = data.result.phone;
                    sex = data.result.sex;
                    headimgurl = data.result.headimgurl;
                    createTime = data.result.createTime;
                    userLocation = data.result.location;
                    info = data.result.info;
                    layer_show(title,url,w,h);
                }
                else{
                    layer.alert('没有该用户的信息',{title: '错误信息',icon: 2});
                }

            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        });
    }

    /*游戏信息-查看*/
    function game_show(title,gameId,url,w,h){
        $.ajax({
            type: 'GET',
            url: '/game/getGame/' + gameId,
            dataType: 'json',
            success: function(data){
                if(data != null && data.result != null)
                {
                    id = data.result.id;
                    gameName = data.result.gameName;
                    photo = data.result.photo;
                    info = data.result.info;
                    createTime = data.result.createTime;
                    orderNum = data.result.orderNum;
                    number = data.result.number;
                    layer_show(title,url,w,h);
                }
                else{
                    layer.alert('没有该游戏的信息',{title: '错误信息',icon: 2});
                }

            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        });
    }

    /*统计用户反馈数*/
    function userFocus_count() {
        $.ajax({
            url: "/userFocus/count",
            type: "GET",
            success: function (data) {
                if (data.success == false) {
                    layer.alert(data.message, {title: '错误信息', icon: 2});
                    return;
                }
                $("#userFocusListCount").html(data.recordsTotal);
            },
            error: function (XMLHttpRequest) {
                layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
            }
        });
    }



    /*帖子-删除*/
    function userFocus_del(obj, id) {
        layer.confirm('确认要删除id为\'' + id + '\'的关注信息吗？', {icon: 0}, function (index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/userFocus/remove/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    userFocus_count();
                    refresh();
                    layer.msg('已删除!', {icon: 1, time: 1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }

    /*批量删除*/
    function datadel() {
        var cks = document.getElementsByName("checkbox");
        var count = 0, ids = "";
        for (var i = 0; i < cks.length; i++) {
            if (cks[i].checked) {
                count++;
                ids += cks[i].value + ",";
            }
        }
        if (count == 0) {
            layer.msg('您还未勾选任何数据!', {icon: 5, time: 3000});
            return;
        }
        /*去除末尾逗号*/
        if (ids.length > 0) {
            ids = ids.substring(0, ids.length - 1);
        }
        layer.confirm('确认要删除所选的' + count + '条数据吗？', {icon: 0}, function (index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/userFocus/remove/' + ids,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    userFocus_count();
                    refresh();
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }

    /*成功后的回显示*/
    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }


    /*多条件查询*/
    $("#form-search").validate({
        rules: {
            searchKey: {
                required: false,
            },
        },
        onkeyup: false,
        focusCleanup: false,
        success: "valid",
        submitHandler: function (form) {
            var searchKey = $('#searchKey').val();
            var param = {
                "searchKey": searchKey,
            };
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.reload();
        }
    });
</script>
</body>
</html>
