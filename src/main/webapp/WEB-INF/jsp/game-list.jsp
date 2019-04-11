<%--
  Created by IntelliJ IDEA.
  User: lzb
  Date: 2018/11/30
  Time: 19:25
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
    <link rel="stylesheet" type="text/css" href="/static/h-ui/css/H-ui.min.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/H-ui.admin.css" />
    <link rel="stylesheet" type="text/css" href="/lib/Hui-iconfont/1.0.8/iconfont.css" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/skin/default/skin.css" id="skin" />
    <link rel="stylesheet" type="text/css" href="/static/h-ui.admin/css/style.css" />
    <!--[if IE 6]>
    <script type="text/javascript" src="/lib/DD_belatedPNG_0.0.8a-min.js" ></script>
    <script>DD_belatedPNG.fix('*');</script>
    <![endif]-->

    <title>游戏列表</title>
</head>
<style>
    .table > tbody > tr > td {
        text-align: center;
    }
</style>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 游戏圈管理 <span class="c-gray en">&gt;</span> 游戏列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="add_game('添加游戏','game-add')" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加游戏</a></span>
        <span class="r">共有数据：<strong id="gamecount">88</strong> 条</span> </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%">
            <thead>
            <tr class="text-c">
                <th width="20"><input type="checkbox" name="" value=""></th>
                <th width="25">游戏ID</th>
                <th width="45">游戏名称</th>
                <th width="70">游戏图标</th>
                <th width="150">游戏介绍</th>
                <th width="50">游戏类型</th>
                <th width="40">关注人数</th>
                <th width="40">排列</th>
                <th width="30">状态</th>
                <th width="85">创建时间</th>
                <th width="60">操作</th>
            </tr>
            </thead>
        </table>
    </div>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/lib/common.js"></script>
<script type="text/javascript">
    $(function(){
        $('.table').dataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
                url:"/game/getList",
                type: 'GET'
            },
            data: {
                "searchKey": "",
            },
            "columns": [
                { "data": null,
                    render : function(data,type, row, meta) {
                        return "<input name=\"checkbox\" value=\""+row.id+"\" type=\"checkbox\">";
                    }
                },
                { "data": "id"},
                { "data": "gameName"},
                { "data": "photo",
                    render:function(data,type,row,meta){
                        return "<a href=\"javascript:;\" ><i class=\"avatar size-XL radius\"><img alt=\"游戏图标\" src=\""+data+"\"></i></a>"
                    }
                },
                { "data": "info"},
                { "data": "types"},
                { "data": "number"},
                { "data": "orderNum"},
                { "data": "status",
                    render : function(data,type, row, meta) {
                        if (data == 0) {
                            return "<span class=\"label label-success radius td-status\">正常</span>";
                        }
                        else if (data == 1) {
                            return "<span class=\"label label-default radius td-status\">已下架</span>";
                        }else if(data == 2){
                            return "<span class=\"label label-warning radius td-status\">待审核</span>";
                        }else{
                            return "<span class=\"label label-danger radius td-status\">未知</span>";
                        }
                    }
                },
                { "data": "createTime",
                    render : function(data,type, row, meta) {
                        return date(data);
                    }
                },
                {
                    "data": null,
                    render: function (data, type, row, meta) {
                        var rowstr = JSON.stringify(row);
                        if(row.status===0){
                            return "<a title=\"编辑\" href=\"javascript:;\" onclick=\'game_edit(\"游戏编辑\",\"game-edit\","+ rowstr +")\' class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6df;</i></a>" +
                                "<a title=\"下架\" href=\"javascript:;\" onclick=\"game_stop(this," + row.id + ")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6de;</i></a>";

                        }else if(row.status===1){
                            return "<a title=\"编辑\" href=\"javascript:;\" onclick=\'game_edit(\"游戏编辑\",\"game-edit\","+ rowstr +")\' class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6df;</i></a>" +
                                "<a title=\"上架\" href=\"javascript:;\" onclick=\"game_putaway(this," + row.id + ","+row.status+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6dc;</i></a>";

                        }else if(row.status===2){
                            return "<a title=\"编辑\" href=\"javascript:;\" onclick=\'game_edit(\"游戏编辑\",\"game-edit\","+ rowstr +")\' class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6df;</i></a>" +
                                "<a title=\"通过审核\" href=\"javascript:;\" onclick=\"game_putaway(this," + row.id + ","+row.status+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e1;</i></a>";

                        }

                    }
                }
            ],
            "aaSorting": [[ 7, "asc" ]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                {"bVisible": false, "aTargets": [ 1 ]}, //控制列的隐藏显示
                {"orderable":false,"aTargets":[0,1,2,3,4,5]}// 制定列不参与排序
            ],
            colReorder: true
        });
        game_count();
    });
    /*游戏下架*/
    function game_stop(obj, id,status) {
        layer.confirm('确认要下架吗？',{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'POST',
                url: '/game/stop',
                data:{"id":id},
                success: function (data) {
                    layer.close(index);
                    if (data.code !== 1) {
                        layer.alert(data.msg, {title: '错误信息', icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('下架成功!',{icon:1,time:1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });

    }

    /*游戏上架及通过审核*/
    function game_putaway(obj, id,status) {
        var  sss="确认要上架吗?";
        if(status===2) sss="确定要通过审核及上架吗？";
        layer.confirm(sss,{icon:0},function(index) {
            var index = layer.load(3);
            $.ajax({
                type: 'POST',
                url: '/game/putaway',
                data:{"id":id},
                success: function (data) {
                    layer.close(index);
                    if (data.code !== 1) {
                        layer.alert(data.msg, {title: '错误信息', icon: 2});
                        return;
                    }
                    refresh();
                    layer.msg('上架成功!',{icon:1,time:1000});
                },
                error: function (XMLHttpRequest) {
                    layer.close(index);
                    layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
                }
            });
        });

    }

    /*统计*/
    function game_count() {
        $.ajax({
            url: "/game/count",
            type: "GET",
            success: function (data) {
                if (data.code == -1) {
                    layer.alert(data.message, {title: '错误信息', icon: 2});
                    return;
                }
                $("#gamecount").html(data.recordsTotal);
            },
            error: function (XMLHttpRequest) {
                layer.alert('数据处理失败! 错误码:' + XMLHttpRequest.status, {title: '错误信息', icon: 2});
            }
        });
    }
    /*游戏-编辑*/
    var gameData;
    function game_edit(title,url,data){
        gameData=data;
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }


    function add_game(title,url){
        var index = layer.open({
            type: 2,
            title: title,
            content: url
        });
        layer.full(index);
    }


    /**
     * 提示成功信息
     * @param content
     */
    function msgSuccess(content) {
        layer.msg(content, {icon: 1, time: 3000});
    }


    /*个人信息*/
    function edit_type(){
        layer.open({
            type: 1,
            area: ['300px','200px'],
            fix: false, //不固定
            maxmin: true,
            shade:0.4,
            title: '查看信息',
            content: '<div>管理员信息</div>'
        });
    }

</script>
</body>
</html>
