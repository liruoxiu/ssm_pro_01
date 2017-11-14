   $.ajaxSetup({  
        contentType:"application/x-www-form-urlencoded;charset=utf-8",  
        complete:function(XMLHttpRequest,textStatus){  
            //通过XMLHttpRequest取得响应头，sessionstatus，  
            var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");   
            if(sessionstatus=="timeout"){  
            //如果超时就处理 ，指定要跳转的页面  
                window.location = "Login.action";  
            }  
        }  
    });  
   
   
   /**
    * 获取元素的outerHTML
    */
   $.fn.outerHTML = function() {

       // IE, Chrome & Safari will comply with the non-standard outerHTML, all others (FF) will have a fall-back for cloning
       return (!this.length) ? this : (this[0].outerHTML ||
       (function(el) {
           var div = document.createElement('div');
           div.appendChild(el.cloneNode(true));
           var contents = div.innerHTML;
           div = null;
           return contents;
       })(this[0]));

   };