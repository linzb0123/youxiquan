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
    <title>长期帖子</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 帖子管理 <span
        class="c-gray en">&gt;</span> 长期帖子 <a id="btn-refresh" class="btn btn-success radius r"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="form-search" class="text-c"> 日期范围：
        <input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'maxDate\')||\'%y-%M-%d\'}' })" id="minDate"
               name="minDate" class="input-text Wdate" style="width:120px;">
        -
        <input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'minDate\')}',maxDate:'%y-%M-{%d+1}' })"
               id="maxDate" name="maxDate" class="input-text Wdate" style="width:120px;">
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
        <span class="r">共有数据：<strong id="longNewsListCount">0</strong> 条</span>
    </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="30"><input type="checkbox" name="" value=""></th>
                <th width="40">帖子ID</th>
                <th width="40">用户名</th>
                <th width="40">游戏名称</th>
                <th width="200">帖子内容</th>
                <th width="80">创建时间</th>
                <th width="60">回帖数</th>
                <th width="60">点赞数</th>
                <th width="60">排序</th>
                <th width="50">状态</th>
                <th width="110">操作</th>
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
                url: "/longNews/list",
                type: 'GET',
                data: {
                    "searchKey": "",
                    "minDate": "",
                    "maxDate": "",
                },
            },
            "columns": [
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        return "<input name=\"checkbox\" value=\"" + row.id + "\" type=\"checkbox\" value=\"\">";
                    }
                },
                {"data": "id",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"longNews_detail('帖子详情'," + data + ",'longNews-detail','800','600')\">"+data+"</a>";
                    }
                },
                {"data": "user",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"user_show('用户信息'," + row.userId + ",'user-show','360','400')\">"+data.username+"</a>";
                    }
                },
                {"data": "game",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"game_show('游戏信息'," + row.gameId + ",'game-show','360','400')\">"+data.gameName+"</a>";
                    }
                },
                {"data": "message"},
                {"data": "createTime",
                    render: function (data, type, row, meta) {
                        return date(data);
                    }
                },
                {"data": "replyNum"},
                {"data": "praiseNum"},
                {"data": "orderNum",
                    render: function (data, type, row, meta) {
                        if (data == 0) {
                            return "<span class=\"label label-success radius td-status\">置顶</span>";
                        }
                        else {
                            return "<span class=\"label label-success radius td-status\">"+data+"</span>";;
                        }
                    }
                },
                {"data": "status",
                    render: function (data, type, row, meta) {
                        if (data == 1) {
                            return "<span class=\"label label-defant radius td-status\">屏蔽</span>";
                        }
                        else if (data == 0) {
                            return "<span class=\"label label-success radius td-status\">正常</span>";
                        }
                    }
                },
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                            if( row.status == 0 && row.orderNum == 0 ){
                                return "<a id=\"td-manage\" style=\"text-decoration:none\" onClick=\"longNews_unstick(this," + row.id + ")\" href=\"javascript:;\" title=\"取消置顶\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe698;</i></a>" +
                                    "<a title=\"屏蔽\" href=\"javascript:;\" onclick=\"longNews_stop(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe631;</i></a>" +
                                    "<a title=\"删除\" href=\"javascript:;\" onclick=\"longNews_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
                            }else if( row.status == 1 && row.orderNum != 0){
                                return "<a id=\"td-manage\" style=\"text-decoration:none\" onClick=\"longNews_stick(this," + row.id + ")\" href=\"javascript:;\" title=\"置顶\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe699;</i></a>" +
                                    "<a title=\"正常\" href=\"javascript:;\" onclick=\"longNews_start(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe604;</i></a>" +
                                    "<a title=\"删除\" href=\"javascript:;\" onclick=\"longNews_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
                            }else if( row.status == 0 && row.orderNum != 0  ){
                                return "<a id=\"td-manage\" style=\"text-decoration:none\" onClick=\"longNews_stick(this," + row.id + ")\" href=\"javascript:;\" title=\"置顶\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe699;</i></a>" +
                                "<a title=\"屏蔽\" href=\"javascript:;\" onclick=\"longNews_stop(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe631;</i></a>" +
                                "<a title=\"删除\" href=\"javascript:;\" onclick=\"longNews_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
                            }else if( row.status == 1 && row.orderNum == 0 ){
                                return "<a id=\"td-manage\" style=\"text-decoration:none\" onClick=\"longNews_unstick(this," + row.id + ")\" href=\"javascript:;\" title=\"取消置顶\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe698;</i></a>"  +
                                    "<a title=\"正常\" href=\"javascript:;\" onclick=\"longNews_start(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe604;</i></a>" +
                                    "<a title=\"删除\" href=\"javascript:;\" onclick=\"longNews_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
                            }
                    }
                }
            ],
            "aaSorting": [[8, "desc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                // {"bVisible": false, "aTargets": [4, 6]}, //控制列的隐藏显示
                {"orderable": false, "aTargets": [0, 10]}// 制定列不参与排序
            ],
            colReorder: true
        });

        longNews_count();
    })

    /*帖子详情*/
    function longNews_detail(title,id,url,w,h){
        $.ajax({
            type: 'GET',
            url: '/longNews/getDetail/' + id,
            dataType: 'json',
            success: function(data){
                if(data != null && data.result != null)
                {
                    username = data.result.user.username;
                    message = data.result.message;
                    createTime = data.result.createTime;
                    headimgurl = data.result.user.headimgurl;
                    longNewsReplyDto = data.result.longNewsReply2Dtos;
                    status = data.result.status;
                    orderNum = data.result.orderNum;
                    praiseNum = data.result.praiseNum;
                    replyNum = data.result.replyNum;
                    layer_show(title,url,w,h);
                }
                else{
                    layer.alert('没有该帖子的信息',{title: '错误信息',icon: 2});
                }

            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status,{title: '错误信息',icon: 2});
            }
        });
    }

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
                    console.log(headimgurl);
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
    function longNews_count() {
        $.ajax({
            url: "/longNews/count",
            type: "GET",
            success: function (data) {
                if (data.success == false) {
                    layer.alert(data.message, {title: '错误信息', icon: 2});
                    return;
                }
                $("#longNewsListCount").html(data.recordsTotal);
            },
            error: function (XMLHttpRequest) {
                layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
            }
        });
    }

    /*长期帖子-置顶*/
    function longNews_stick(obj, id) {
        layer.confirm('确认要置顶吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/longNews/stick/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('置顶!',{icon:1,time:1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }

    /*长期帖子-取消置顶(默认设置为999)*/
    function longNews_unstick(obj, id) {
        layer.confirm('确认要取消置顶吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/longNews/unstick/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('取消置顶!',{icon:1,time:1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }


    /*即时帖子-屏蔽*/
    function longNews_stop(obj, id) {
        layer.confirm('确认要屏蔽吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/longNews/stop/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('屏蔽!',{icon:1,time:1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }

    /*即时帖子-正常*/
    function longNews_start(obj, id) {
        layer.confirm('确认要正常吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/longNews/start/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('正常!',{icon:1,time:1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });
    }

    /*帖子-删除*/
    function longNews_del(obj, id) {
        layer.confirm('确认要删除id为\'' + id + '\'的即时帖子吗？', {icon: 0}, function (index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/longNews/remove/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    longNews_count();
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
                url: '/longNews/remove/' + ids,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    longNews_count();
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
            minDate: {
                required: true,
            },
            maxDate: {
                required: true,
            },
            searchKey: {
                required: false,
            },
        },
        onkeyup: false,
        focusCleanup: false,
        success: "valid",
        submitHandler: function (form) {
            var searchKey = $('#searchKey').val();
            var minDate = $('#minDate').val();
            var maxDate = $('#maxDate').val();
            var param = {
                "searchKey": searchKey,
                "minDate": minDate,
                "maxDate": maxDate
            };
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.reload();
        }
    });
</script>
</body>
</html>
