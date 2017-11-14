<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>汽车煤入厂验收系统</title>

    <link rel="stylesheet" type="text/css" href="Resources/Style/bootstrap.min.css" media="screen"/>
	<style> 
		#logindiv {height:475px;width:100%;background:url(Resources/Images/login.png) no-repeat; background-position: center;background-color:rgb(0,91,127)} 
		.login-panel {margin-top: 120px;}
		body {background-color: white; overflow: hidden;}
		#copyright {text-align:center; line-height:60px; bottom:0; left:auto;right:auto; font-size:small; padding:12px; position:relative;}
	</style>
	
	<script type="text/javascript" src="Resources/JScript/jquery.min.js"></script>
	<script type="text/javascript" src="Resources/JScript/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="Resources/JScript/respond.min.js"></script>
	<title>用户登录</title>

	<script type="text/javascript">
		function enter(e, element_id) {
			var ie = navigator.appName == "Microsoft Internet Explorer" ? true : false;
			if (ie) {
				if (window.event.keyCode == 13) {
					doLogin(element_id);
				}
			} else {
				if (isKeyTrigger(e, 13)) {
					doLogin(element_id);
				}
			}
		}

		function doLogin(element_id) {
			if (element_id == "userId") {
				document.getElementById("password").focus();
			} else {
				if (element_id == "password") {
					submitForm();
				}
			}
		}

		function isKeyTrigger(e, keyCode) {
			var nav4 = window.Event ? true : false;
			if (typeof e == "undefined") {
				e = event;
			}
			var whichCode = 0;
			if (nav4) {
				whichCode = e.which;
			} else {
				if (e.type == "keypress" || e.type == "keydown") {
					whichCode = e.keyCode;
				} else {
					whichCode = e.button;
				}
			}
			return (whichCode == keyCode);
		}
		 
		function submitForm()
			 {
				Tip_Obj = $("#tip");
				 if ($("#userid").val()=="")
				 {
					 setTimeout(function(){Tip_Obj.text("");document.getElementById("tip").style.display="none";}, 1500);
					 Tip_Obj.text("用户名不能为空！");
					 document.getElementById("tip").style.display="block";
					 document.getElementById("userid").focus();
					 return;
				 }
				 if ($("#password").val()=="")
				 {
					 setTimeout(function(){Tip_Obj.text("");document.getElementById("tip").style.display="none";}, 1500);
					 Tip_Obj.text("密码不能为空！");
					 document.getElementById("tip").style.display="block";
					 document.getElementById("password").focus();
					 return;
				 }
				 $.ajax({
								url : 'CheckLogin.action',
								method:"post",
								data:{userId:$("#userid").val(), password:$("#password").val()},
								dataType:'json',
								onSubmit : function() {
									return $(this).form("validate");
								},
								success : function(result) {
									//var resultJson = $.parseJSON(result);
									if (result.success)
									{
										window.location = "Main.action";
									}
									else
									{				
										setTimeout(function(){Tip_Obj.text("");document.getElementById("tip").style.display="none";}, 1500);
										Tip_Obj.text("用户名或密码不正确！");
										document.getElementById("tip").style.display="block";	
									}
								}
							});
					
			 };
	</script>
</head>

<body>
    <div id="bannerdiv" class="container">
		<div class="row">
			<div class="col-md-12">
				<image src="Resources/Images/banner.png"/>
			</div>
		</div>
	</div> 
    </div>
	<div id="logindiv">
        <div class="row">
            <div class="col-md-3 col-md-offset-8">
                <div class="login-panel panel panel-primary">
                    <div class="panel-heading">
                        <h3 class="panel-title">用户登录</h3>
                    </div>
                    <div class="panel-body">
                        <form role="form" id="login" method="post" autocomplete="off" >
                            <fieldset>
                                <div class="form-group">
                                    <input class="form-control" placeholder="用户名" id="userid" name="userId"
										onkeyup="javascript:enter(event,'userId');" autofocus>
                                </div>
                                <div class="form-group">
                                    <input class="form-control" placeholder="密码" id="password" name="password" type="password" onkeyup="javascript:enter(event,'password');">
                                </div>
                                <div id="tip" class="alert alert-danger" role="alert" style="display:none;"></div>
                                <a onclick="submitForm()" class="btn btn-lg btn-primary btn-block">登录</a>
                            </fieldset>
                        </form>
                    </div>
                </div>
            </div>
        </div>
	</div>
	<div id="copyright">
		Copyright&copy;2014-2015&nbsp;&nbsp;北京华电奥博科技有限公司&nbsp;版权所有
	</div>
</body>

</html>
