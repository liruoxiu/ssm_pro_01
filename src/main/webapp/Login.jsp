<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>汽车煤入厂验收系统</title>
    <link href="Resources/Style/bootstrap.min.css" rel="stylesheet">
    <link href="Resources/Style/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
    <link href="Resources/Style/animate.min.css" rel="stylesheet">
    <link href="Resources/Style/style.min.css" rel="stylesheet">
    <link href="Resources/Style/login.min.css" rel="stylesheet">
	
	<script type="text/javascript" src="Resources/JScript/jquery.min.js"></script>
	<!-- <script type="text/javascript" src="Resources/JScript/jquery.easyui.min.js"></script> -->
	<!-- <script type="text/javascript" src="Resources/JScript/respond.min.js"></script> -->
	
    <script src="Resources/JScript/bootstrap.min.js?v=3.3.6"></script>
	

    	
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
						console.info(result);
						if (result.success)
						{
							console.info('跳转');
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
<body class="signin">
    <div class="signinpanel">
        <div class="row">
            <div class="col-sm-7">
                <div class="signin-info">
                    <div class="logopanel m-b">
                        <h1>汽车煤入厂验收2</h1>
                    </div>
                    <div class="m-b"></div>
                    <h4>欢迎使用 <strong>后台管理系统</strong></h4>
                    <ul class="m-b">
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势一</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势二</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势三</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势四</li>
                        <li><i class="fa fa-arrow-circle-o-right m-r-xs"></i> 优势五</li>
                    </ul>
                    <strong>还没有账号？ <a href="#">立即注册&raquo;</a></strong>
                </div>
            </div>
            <div class="col-sm-5">
                <form method="post" >
                    <h4 class="no-margins">登录：</h4>
                    <p class="m-t-md">登录后台管理系统</p>
                    <input type="text" class="form-control uname" placeholder="用户名"   id="userid" name="userId" onkeyup="javascript:enter(event,'userId');" autofocus/>
                    <input type="password" class="form-control pword m-b" placeholder="密码"  id="password" name="password"  onkeyup="javascript:enter(event,'password');"/>
                    <a href="#">忘记密码了？</a>
                    <div id="tip" class="alert alert-danger" role="alert" style="display:none;"></div>
                    <button type="button" class="btn btn-success btn-block" onclick="submitForm()">登 录</button>
                </form>
            </div>
        </div>
        <div class="signup-footer">
            <div class="pull-left">
                &copy; 2015 All Rights Reserved. 
            </div>
        </div>
    </div>
</body>

</html>
