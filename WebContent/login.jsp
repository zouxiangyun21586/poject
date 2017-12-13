<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
    <title>Login</title>
    <script>
        if (window != window.top) top.location.href = self.location.href;
    </script>
    <link href="plugins/layui/css/layui.css" rel="stylesheet" />
    <link href="plugins/font-awesome/css/font-awesome.css" rel="stylesheet" />
    <link href="build/css/login.css" rel="stylesheet" />
    <link href="plugins/sideshow/css/normalize.css" rel="stylesheet" />
    <link href="plugins/sideshow/css/demo.css" rel="stylesheet" />
    <link href="plugins/sideshow/css/component.css" rel="stylesheet" />
    <!--[if IE]>
        <script src="./plugins/sideshow/js/html5.js"></script>
    <![endif]-->
    <style>
        canvas {
            position: absolute;
            z-index: -1;
        }
        
        .kit-login-box header h1 {
            line-height: normal;
        }
        
        .kit-login-box header {
            height: auto;
        }
        
        .content {
            position: relative;
        }
        
        .codrops-demos {
            position: absolute;
            bottom: 0;
            left: 40%;
            z-index: 10;
        }
        
        .codrops-demos a {
            border: 2px solid rgba(242, 242, 242, 0.41);
            color: rgba(255, 255, 255, 0.51);
        }
        
        .kit-pull-right button,
        .kit-login-main .layui-form-item input {
            background-color: transparent;
            color: white;
        }
        
        .kit-login-main .layui-form-item input::-webkit-input-placeholder {
            color: white;
        }
        
        .kit-login-main .layui-form-item input:-moz-placeholder {
            color: white;
        }
        
        .kit-login-main .layui-form-item input::-moz-placeholder {
            color: white;
        }
        
        .kit-login-main .layui-form-item input:-ms-input-placeholder {
            color: white;
        }
        
        .kit-pull-right button:hover {
            border-color: #009688;
            color: #009688
        }
    </style>
</head>


<body class="kit-login-bg">
    <div class="container demo-1">
        <div class="content">
            <div id="large-header" class="large-header">
                <canvas id="demo-canvas"></canvas>
                <div class="kit-login-box">
                    <header>
                        <h1>MOUBAO LOGIN</h1>
                    </header>
                    <div class="kit-login-main">
                        <form action="/" class="layui-form" method="post">
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe612;</i>
                                </label>
                                <input type="text" name="userName" lay-verify="required" autocomplete="off" placeholder="请输入用户名" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe642;</i>
                                </label>
                                <input type="password" name="password" lay-verify="required" autocomplete="off" placeholder="请输入密码" class="layui-input">
                            </div>
                            <div class="layui-form-item">
                                <label class="kit-login-icon">
                                    <i class="layui-icon">&#xe642;</i>
                                </label>
                                <input type="text" name="validCode" autocomplete="off" placeholder="请输入验证码" class="layui-input">
                                <span class="form-code" id="changeCode" style="position:absolute;right:2px; top:2px;">
                                    <img src="images/yzm.png" id="refImg" style="cursor:pointer;" title="验证码"/>
                                </span>
                            </div>
                            <div class="layui-form-item">
                                <div class="kit-pull-left kit-login-remember">
                                    <input type="checkbox" name="rememberMe" value="true" lay-skin="primary" checked title="记住密码">
                                </div>
                                <div class="kit-pull-right">
                                    <button class="layui-btn layui-btn-primary" lay-submit lay-filter="login">
                                        <i class="fa fa-sign-in" aria-hidden="true"></i> 登录
                                    </button>
                                </div>
                                <div class="kit-clear"></div>
                            </div>
                        </form>
                    </div>
                    <footer>
                        <p>某宝 - <a href="#" style="color:white; font-size:18px;" target="_blank">www.moubao.cn</a></p>
                    </footer>
                </div>
            </div>
        </div>
    </div>
    <!-- /container -->

    <script src="plugins/layui/layui.js"></script>
    <script src="plugins/sideshow/js/TweenLite.min.js"></script>
    <script src="plugins/sideshow/js/EasePack.min.js"></script>
    <script src="plugins/sideshow/js/rAF.js"></script>
    <script src="plugins/sideshow/js/demo-1.js"></script>
    <script>
        layui.use(['layer', 'form'], function() {
            var layer = layui.layer,
                $ = layui.jquery,
                form = layui.form;

            $('#changeCode').on('click', function() {//单击验证码触发该函数
            	alert('aaaaaaa');
                $('#changeCode > img')[0].src = 'images/yzm.png';
            });
            var index = layer.load(2, {
                shade: [0.3, '#333']
            });
            $(window).on('load', function() {
                layer.close(index);
                form.on('submit(login)', function(data) {// 点击登录触发该函数
                    var loadIndex = layer.load(2, {
                        shade: [0.3, '#333']
                    });
                    //以下写登录ajax
                    
                    	//登录跳转代码
                      setTimeout(function() {
                     	 location.href = 'index.jsp';
                      }, 1500);
                    
                    
                    
                    
                    return false;
                });
            }());

        });
    </script>
</body>

</html>