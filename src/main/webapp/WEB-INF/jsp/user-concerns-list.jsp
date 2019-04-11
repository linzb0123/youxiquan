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
    <title>用户关注</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 游戏圈管理 <span
        class="c-gray en">&gt;</span> 用户关注 <a id="btn-refresh" class="btn btn-success radius r"
                                              style="line-height:1.6em;margin-top:3px"
                                              href="javascript:location.replace(location.href);" title="刷新"><i
        class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <form id="form-search" class="text-c">
        <%--<input type="text" onfocus="WdatePicker({ maxDate:'#F{$dp.$D(\'maxDate\')||\'%y-%M-%d\'}' })" id="minDate"--%>
               <%--name="minDate" class="input-text Wdate" style="width:120px;">--%>
        <%-----%>
        <%--<input type="text" onfocus="WdatePicker({ minDate:'#F{$dp.$D(\'minDate\')}',maxDate:'%y-%M-{%d+1}' })"--%>
               <%--id="maxDate" name="maxDate" class="input-text Wdate" style="width:120px;">--%>
        <input type="text" class="input-text" style="width:250px" placeholder="通过用户Id或者姓名,查找关注他的人" id="searchKey"
               name="searchKey">
        <button id="searchButton" type="submit" class="btn btn-success radius" id="" name=""><i class="Hui-iconfont">&#xe665;</i>
            搜帖子
        </button>
    </form>
    <div class="cl pd-5 bg-1 bk-gray mt-20">
        <span class="l">
            <a href="javascript:;" onclick="datadel()" class="btn btn-danger radius"><i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a>
        </span>
        <span class="r">共有数据：<strong id="userConcernsListCount">0</strong> 条</span>
    </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="40">ID</th>
                <th width="40">关注人</th>
                <th width="40">被关注人</th>
                <th width="200">关注时间</th>
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
                url: "/userConcerns/list",
                type: 'GET',
                data: {
                    "searchKey": "",
                    // "minDate": "",
                    // "maxDate": "",
                },
            },
            "columns": [
                {"data": "id"},
                {"data": "fromUser",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"user_show('用户信息'," + data.id + ",'user-show','360','400')\">"+data.username+"</a>";
                    }
                },
                {"data": "toUser",
                    render: function(data, type, row, meta){
                        return "<u style=\"cursor:pointer\" class=\"text-primary\" onclick=\"user_show('用户信息'," + data.id + ",'user-show','360','400')\">"+data.username+"</a>";
                    }
                },
                {"data": "createtime",
                    render: function (data, type, row, meta) {
                        return date(data);
                    }
                },
            ],
            "aaSorting": [[3, "desc"]],//默认第几个排序
            "bStateSave": false,//状态保存
            "aoColumnDefs": [
                // {"bVisible": false, "aTargets": [4, 6]}, //控制列的隐藏显示
                // {"orderable": false, "aTargets": [0]}// 制定列不参与排序
            ],
            colReorder: true
        });

        userConcerns_count();
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

    /*统计用户反馈数*/
    function userConcerns_count() {
        $.ajax({
            url: "/userConcerns/count",
            type: "GET",
            success: function (data) {
                if (data.success == false) {
                    layer.alert(data.message, {title: '错误信息', icon: 2});
                    return;
                }
                $("#userConcernsListCount").html(data.recordsTotal);
            },
            error: function (XMLHttpRequest) {
                layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
            }
        });
    }

    /*成功后的回显示*/
    function msgSuccess(content){
        layer.msg(content, {icon: 1,time:3000});
    }


    /*多条件查询*/
    $("#form-search").validate({
        rules: {
            // minDate: {
            //     required: true,
            // },
            // maxDate: {
            //     required: true,
            // },
            searchKey: {
                required: false,
            },
        },
        onkeyup: false,
        focusCleanup: false,
        success: "valid",
        submitHandler: function (form) {
            var searchKey = $('#searchKey').val();
            // var minDate = $('#minDate').val();
            // var maxDate = $('#maxDate').val();
            var param = {
                "searchKey": searchKey,
                // "minDate": minDate,
                // "maxDate": maxDate
            };
            var table = $('.table').DataTable();
            table.settings()[0].ajax.data = param;
            table.ajax.reload();
        }
    });
</script>
</body>
</html>
