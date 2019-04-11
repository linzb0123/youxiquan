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
    <title>用户列表</title>
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
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 用户管理 <span
        class="c-gray en">&gt;</span> 用户列表 <a id="btn-refresh" class="btn btn-success radius r"
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
        <input type="text" class="input-text" style="width:250px" placeholder="输入用户姓名、电话等信息" id="searchKey"
               name="searchKey">
        <button id="searchButton" type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i>
            搜帖子
        </button>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
        </span>
        <span class="r">共有数据：<strong id="userListCount">0</strong> 条</span>
    </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="30"><input type="checkbox" name="" value=""></th>
                <th width="40">ID</th>
                <th width="40">用户名</th>
                <th width="45">电话</th>
                <th width="30">性别</th>
                <th width="60">头像</th>
                <th width="80">地区</th>
                <th width="150">信息</th>
                <th width="80">创建时间</th>
                <th width="50">状态</th>
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
                url: "/user/list",
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
                {"data": "id"},
                {"data": "username"},
                {"data": "phone"},
                {"data": "sex",
                    render: function(data, type, row, meta){
                        if( data == 0 ){
                            return "保密";
                        }else if( data == 2 ){
                            return "女";
                        }else if( data == 1 ){
                            return "男"
                        }
                    }
                },
                {"data": "headimgurl",
                    render: function(data, type, row, meta){
                     return  "<img id=\"headimgurl\" class=\"avatar\" src=\""+ data +"\">" ;
                    }
                },
                {"data": "location"},
                {"data": "info"},
                {"data": "createTime",
                    render: function (data, type, row, meta) {
                        return date(data);
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
                        if( row.status == 0 ){
                            return "<a title=\"屏蔽\" href=\"javascript:;\" onclick=\"user_stop(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe631;</i></a>" +
                                "<a title=\"删除\" href=\"javascript:;\" onclick=\"user_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
                        }else if( row.status ==1 ){
                            return "<a title=\"正常\" href=\"javascript:;\" onclick=\"user_start(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe604;</i></a>" +
                                "<a title=\"删除\" href=\"javascript:;\" onclick=\"user_del(this,"+row.id+ ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";
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

        user_count();
    })


    /*统计用户反馈数*/
    function user_count() {
        $.ajax({
            url: "/user/count",
            type: "GET",
            success: function (data) {
                if (data.success == false) {
                    layer.alert(data.message, {title: '错误信息', icon: 2});
                    return;
                }
                $("#userListCount").html(data.recordsTotal);
            },
            error: function (XMLHttpRequest) {
                layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
            }
        });
    }


    /*即时帖子-屏蔽*/
    function user_stop(obj, id) {
        layer.confirm('确认要屏蔽吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/user/stop/' + id,
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
    function user_start(obj, id) {
        layer.confirm('确认要正常吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/user/start/' + id,
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
    function user_del(obj, id) {
        layer.confirm('确认要删除id为\'' + id + '\'的即时帖子吗？', {icon: 0}, function (index) {
            var index = layer.load(3);
            $.ajax({
                type: 'PUT',
                url: '/user/remove/' + id,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    user_count();
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
                url: '/user/remove/' + ids,
                dataType: 'json',
                success: function (data) {
                    layer.close(index);
                    if (data.success != true) {
                        layer.alert(data.message, {title: '错误信息', icon: 2});
                        return;
                    }
                    layer.msg('已删除!', {icon: 1, time: 1000});
                    user_count();
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
