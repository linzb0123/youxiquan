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

    <title>游戏类型</title>
    <style>
        .table > tbody > tr > td {
            text-align: center;
        }
    </style>
</head>

<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 游戏圈管理 <span class="c-gray en">&gt;</span> 类型列表 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" ><i class="Hui-iconfont">&#xe68f;</i></a></nav>
<div class="page-container">
    <div class="cl pd-5 bg-1 bk-gray mt-20"> <span class="l"><a href="javascript:;" onclick="add_type()" class="btn btn-primary radius"><i class="Hui-iconfont">&#xe600;</i> 添加类型</a></span>
        <span class="r">共有数据：<strong id="gamecount">88</strong> 条</span> </div>
    <div class="mt-20" style="margin-bottom: 70px">
        <table class="table table-border table-bordered table-hover table-bg table-sort" width="100%" id="dataTable">
            <thead>
            <tr class="text-c">
                <th width="10"><input type="checkbox" name="" value=""></th>
                <th width="25">ID</th>
                <th width="80">类型名称</th>
                <th width="30">操作</th>
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
<script type="text/javascript" src="/lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="/lib/common.js"></script>
<script type="text/javascript">
    $(function(){
        $('#dataTable').dataTable({
            serverSide: true,//开启服务器模式
            "processing": true,//加载显示提示
            "ajax": {
                url:"/game/getTypeList",
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
                { "data": "type"},
                {"data": null,
                    render: function (data, type, row, meta) {
                        return "<a title=\"编辑\" href=\"javascript:;\"  onclick=\"edit_type('"+row.type+"',"+row.id+")\" class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6df;</i></a>" +
                            "<a title=\"删除\" href=\"javascript:;\"  onclick=\"del_type('"+row.type+"')\"  class=\"ml-5\" style=\"text-decoration:none\"><i class=\"Hui-iconfont\" style='font-size:16px;'>&#xe6e2;</i></a>";

                    }
                }
            ],
            "pageLength":30,
            "lengthMenu":[10,30,50,100],
            "aaSorting": [[ 1, "asc" ]],//默认第几个排序
            "bStateSave": true,//状态保存
            "aoColumnDefs": [
                // {"bVisible": false, "aTargets": [ 1 ]}, //控制列的隐藏显示
                {"orderable":false,"aTargets":[0]}// 制定列不参与排序
            ],
            colReorder: true
        });
        type_count();
    });
    /*统计*/
    function type_count() {
        $.ajax({
            url: "/game/TypeCount",
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


    function edit_type(name,id) {
        layer.prompt({title: '编辑类型', formType: 0,value:name}, function(text, index){
            $.ajax({
                url: '/game/editType',
                type: 'POST',
                data:{"type":text,"id":id},
                success: function (data) {
                    if(data.code===1){
                        refresh();
                        layer.alert("编辑成功");
                    }else{
                        layer.alert("编辑失败，请重试！");
                    }
                },
                error:function(XMLHttpRequest){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                }
            });
            layer.close(index);

        });
    }

    function add_type() {
        layer.prompt({title: '请输入游戏类型', formType: 0,value:name}, function(text, index){
            $.ajax({
                url: '/game/addType',
                type: 'POST',
                data:{"type":text},
                success: function (data) {
                    if(data.code===1){
                        refresh();
                        layer.alert("添加成功");
                    }else{
                        layer.alert("添加失败，请重试！");
                    }
                },
                error:function(XMLHttpRequest){
                    layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                }
            });
            layer.close(index);

        });
    }

    function del_type(name) {
        var index = layer.load(3);
        $.ajax({
            url: '/game/delType',
            type: 'POST',
            data:{"type":name},
            success: function (data) {
                layer.close(index);
                if(data.code===1){
                    refresh();
                    layer.alert("删除成功");
                }else{
                    layer.alert("删除失败，请重试！");
                }
            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        });
    }

    /**
     * 提示成功信息
     * @param content
     */
    function msgSuccess(content) {
        layer.msg(content, {icon: 1, time: 3000});
    }


</script>
</body>
</html>
