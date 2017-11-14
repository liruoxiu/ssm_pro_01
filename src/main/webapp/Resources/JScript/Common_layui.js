var bjhnd={};//定义命名空间为bjhnd
var bjhdab={};

bjhnd.core = {};
bjhnd.core.maintabs = [];



layui.use(['layer','element', 'form'], function(){

  var layer = layui.layer
  ,element=layui.element
  ,form = layui.form
  ,$ = layui.$;
  //
/*  setIframe();
  $(window).on('resize', setIframe);  */
  
  //监听侧边导航按钮
	element.on('nav(sidebar)',function($elem) {
		var $a = $elem.find("a");
		//获取hash来切换选项卡，假设当前地址的hash为lay-id对应的值
		var layid = $a.attr("id");
		var page_url=$a.data("url");
		var page_text=$a.children("span").text();
		
		$('.layui-body').children('.ibox').children('.ibox-title').html("<h5>"+page_text+"</h5>");
//		$('.layui-body').children('.ibox').children('.ibox-content').load($a.data("url"), function(){
//			//$.parser.parse();
//			$.parser.parse($('.layui-body'));
//		});

		
		$('#pp').panel({
		    href:$a.data("url"),
		    onLoad:function(){
		        //alert('loaded successfully');
/*		    	var oldHeight = $('#pp').panel('panel').outerHeight();
		    	$('#pp').panel('resize', {height:'auto'});
		    	var newHeight = $('#pp').panel('panel').outerHeight();
		    	$('#pp').layout('resize',{
		    		height: ($('#pp').height() + newHeight - oldHeight)
		    	});*/
/*		    	$('#pp').resizable({
		    	    maxHeight:oldHeight-96
		    	});*/
//		    	console.info(oldHeight);
//		    	alert(oldHeight);
		    }
		});
		
		
		
	/*	if ($('[lay-id="' + layid+'"]').length === 0) {	
		
			var data = {
				url : $a.data("url"),
				title : $a.children("span").text(),
				//content: '选项卡的内容', //支持传入html
				id: $a.attr("id")
			}
			if (data.url != undefined) {
				element.tabAdd('demo',data);
				// frame高度自适应
				var $content = $('.layui-tab-content');
				$content.find('iframe').each(function() {
					$(this).height($content.height());
				});
				  
				element.tabChange('demo', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
			}
		} else{
			element.tabChange('demo', layid); //假设当前地址为：http://a.com#test1=222，那么选项卡会自动切换到“发送消息”这一项
		}*/
	});
	
		/*
	  
	  //监听Tab切换，以改变地址hash值
	  element.on('tab(demo)', function(){
	    location.hash = 'demo='+ this.getAttribute('lay-id');
	  });
	  
	  element.tabAdd('demo', {
		  title: '选项卡的标题'
		  ,content: '选项卡的内容' //支持传入html
		  ,id: '选项卡标题的lay-id属性值'
		});  
	  //一些事件监听
	  element.on('tab(demo)', function(data){
	    console.log(data);
	  });*/
	
	  $.getJSON("System/systemInitialization.action", function(result) {
			// 生成一级主菜单
			$(document).attr("title", result.title);
			$("#SystemTitle").text(result.title);
			if (result.menu != null)
			{
				InitMenu(result.menu);	
				element.init();
			}
		});
		InitMenu = function(data)
		{
			var menu_obj =$("#side-menu");
			var Item;
			var subItem;
			$.each(data, function(key, field) {
				// 创建一个A元素
				try {
					 Item=$("<li class=\"layui-nav-item \"><a class=\"\" href=\"javascript:;\">" + field.name + "</a></li>");
					 subItem = initSubMenu(field.subMenu);
					 Item.append(subItem);
					 menu_obj.append(Item); 
					 //console.info(Item);
					 //加入主菜单					 
				} catch (e) {
					console.error(e);
				}
			});
		}
		initSubMenu= function(data)
		{
			var menuItem = $("<dl class=\"layui-nav-child\"></dl>");
			$.each(data, function(id, value)
			{
				var div = $("<dd><a href=\"javascript:;\" data-url=\""+value.attributes.url+"\" id=\"menuItem"+value.id+"\"><span>" + value.text + "</span></a></dd>")
				menuItem.append(div);
			});
			return menuItem;
		}	
		
  layer.msg('Hello World');
});

