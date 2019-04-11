
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="Bookmark" href="/favicon.ico" >
    <link rel="Shortcut Icon" href="/favicon.ico" />
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
    <!--/meta 作为公共模版分离出去-->

    <%--<link href="/lib/webuploader/0.1.5/webuploader.css" rel="stylesheet" type="text/css" />--%>
</head>
<body>
<div class="page-container">
    <form action="" method="post" class="form form-horizontal" id="form-article-add">
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">游戏名称：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" value="" placeholder="" id="gameName" name="gameName">
            </div>
        </div>

        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">游戏介绍：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <textarea name="info" id="info" cols="" rows="" class="textarea"  placeholder="说点什么...最少输入10个字符"></textarea>
                <p class="textarea-numberbar"><em class="textarea-length" id="count">0</em>/<span id="max_count">500</span></p>
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">排序值：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text"  placeholder="" id="orderNum" name="orderNum" value="9999">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">关注人数：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <input type="text" class="input-text" id="number" name="number" value="0">
            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">游戏分类：</label>
            <div class="skin-minimal">

            </div>
        </div>
        <div class="row cl">
            <label class="form-label col-xs-4 col-sm-2">游戏图标：</label>
            <div class="formControls col-xs-8 col-sm-9">
                <div class="uploader-container">
                    <div class="game-img" style="margin-bottom: 10px;">
                        <img class="album-img" id="photo" style="width: 140px;height: auto;" src="">
                        <input type="hidden" name="photo" id="photoSrc">
                        <input id="uploaderInput" class="js_file" type="file" accept="image/*" multiple="" style="display: none;">
                        <button onclick="clickInput();" type="button" id="chooseBtn" class="btn btn-secondary radius" style="vertical-align:bottom">选择图片</button>
                        <button onclick="uploadImage();" type="button" id="uploadBtn" class="btn btn-secondary radius" style="vertical-align:bottom;display: none">开始上传</button>
                    </div>

                </div>
            </div>
        </div>

        <div class="row cl">
            <div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
            <button class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i> 添加</button>
            <button onClick="layer_close();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
            </div>
        </div>
    </form>
</div>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="/lib/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="/static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<%--<script type="text/javascript" src="/lib/webuploader/0.1.5/webuploader.min.js"></script>--%>
<script type="text/javascript" src="/lib/common.js"></script>
<script type="text/javascript">
    $(function(){
        getAllTypes();
        $("#photoSrc").val($("#photo").attr("src"));
        $('.skin-minimal input').iCheck({
            checkboxClass: 'icheckbox-blue',
            radioClass: 'iradio-blue',
            increaseArea: '20%'
        });
        var max = $('#max_count').text();
        $('#count').text($('#info').val().length);
        $('#info').on('input', function(){
            var text = $(this).val();
            var len = text.length;
            $('#count').text(len);
            if(len > max){
                $('#count').text(max);
                $('#info').val(text.substring(0,max));
            }
        });


//保存发布
        $("#form-article-add").validate({
            rules:{
                gameName:{
                    required:true,
                    maxlength: 50
                },
                info:{
                    required:true,
                    minlength: 10,
                    maxlength: 500
                },
                orderNum:{
                    required:true,
                    digits:true
                },
                number:{
                    required:true,
                    digits:true
                },
                type:{
                    required:true,
                },
            },
            messages: {
                username: {
                    required: "请输入游戏名",
                    minlength: "最长50"
                },
                info: {
                    required: "请输入游戏介绍",
                    minlength: "长度不能小于 10 ",
                    maxlength:"长度不能大于 500"
                },
                orderNum: {
                    required: "请输入排列数",
                    digits:"必须为整数"
                },
                number: {
                    required: "请输入关注人数",
                    digits:"必须为整数"
                },
                types: {
                    required: "最少选择一个",
                }
            },
            onkeyup:false,
            focusCleanup:false,
            success:"valid",
            submitHandler:function(form){
                var index = layer.load(3);
                $(form).ajaxSubmit({
                    url: "/game/add",
                    type: "POST",
                    success: function(data) {
                        layer.close(index);
                        if(data.code===1){
                            parent.refresh();
                            parent.msgSuccess("添加成功");
                            var index = parent.layer.getFrameIndex(window.name);
                            parent.layer.close(index);
                        }else{
                            layer.alert('添加失败! '+data.message, {title: '错误信息',icon: 2});
                        }
                    },
                    error:function(XMLHttpRequest) {
                        layer.close(index);
                        layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
                    }
                });
            }
        });



        chooseImage();
    });

    function getAllTypes(){
        var template="<div class=\"check-box\">" +
            "                    <input type=\"checkbox\" id=\"#forId#\" name=\"types\" value=\"#value#\">" +
            "                    <label for=\"#forId#\">#value#</label>" +
            "                </div>";
        $.ajax({
            url: '/game/typeList?t=' + (new Date()).getTime(), // 加随机数防止缓存
            type: 'GET',
            success: function (data) {
                if(data.code==1){
                   console.log(data);
                   var types = data.content.types;
                   var $checkNode = $(".skin-minimal");
                   var forIdreg = new RegExp("#forId#","g");//g,表示全部替换。
                    var valuereg = new RegExp("#value#","g");//g,表示全部替换。
                   for(var i=0;i<types.length;i++){
                       $checkNode.append(template.replace(forIdreg,"checkbox-"+i).replace(valuereg,types[i].type));
                   }
                    $('.skin-minimal input').iCheck({
                        checkboxClass: 'icheckbox-blue',
                        radioClass: 'iradio-blue',
                        increaseArea: '20%'
                    });
                }else{
                    layer.alert("获取游戏类别失败");
                }
            },
            error:function(XMLHttpRequest){
                layer.alert('数据处理失败! 错误码:'+XMLHttpRequest.status+' 错误信息:'+JSON.parse(XMLHttpRequest.responseText).message,{title: '错误信息',icon: 2});
            }
        });
    }
    function clickInput(){
        $("#uploaderInput").click();
    }
    var uploadFile;
    function chooseImage(){
        var f = document.querySelector('.js_file');
        f.onchange = function () {
            // // 允许上传的图片类型
            var allowTypes = ['image/jpg', 'image/jpeg', 'image/png', 'image/gif'];
            // // 1024KB，也就是 10MB
            var maxSize = 1024 * 1024 * 10;
            var file  = this.files[0];
            if(file===undefined){
                return;
            }
            if (allowTypes.indexOf(file.type) === -1) {
                layer.alert('该类型不允许上传');
                return;
            }

            if (file.size > maxSize) {
                $.alert('图片最大支持10MB');
                return;
            }
            $("#uploadBtn").show();
            var reader   = new FileReader();

            //readAsDataURL(file),读取文件，将文件以数据URL的形式保存在result的属性中
             reader.readAsDataURL(file);
            //文件加载成功以后，渲染到页面
            reader.onload = function(e) {
                $("#photo").attr("src", e.target.result);
                $("#photo").show();
             }
            uploadFile=file;
        }
    }
    function uploadImage(){
            console.log(uploadFile);
            var formData = new FormData();
            formData.append("file", uploadFile);
            var index = layer.load(1);
            $.ajax({
                url: "/image/imageUpload",
                data: formData,
                type: "POST",
                dataType: "json",
                cache: false,//上传文件无需缓存
                processData: false,//用于对data参数进行序列化处理 这里必须false
                contentType: false, //必须
                success: function (res) {
                    layer.close(index);
                    if(res.code===1)
                    {
                        console.log(res);
                        layer.msg('上传成功');
                        $("#chooseBtn").hide();
                        $("#uploadBtn").hide();
                        $("#photoSrc").val(res.content.url);
                    }
                    else
                    {
                        layer.alert('上传图片失败');
                    }
                },
                error:function(XMLHttpRequest){
                    layer.alert('上传失败! 错误码:'+XMLHttpRequest.status);
                    return;
                }
            });
    }
</script>
</body>
</html>