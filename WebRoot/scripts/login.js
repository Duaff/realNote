//登录事件处理
function func_checklogin(){
	    //清除提示信息
	    $("#count_msg").text("");
	    $("#password_msg").text("");
		//获取用户名和密码
		var name = $("#count").val().trim();
		var password = $("#password").val().trim();
		//数据格式检查
		var check = true;//通过校验
		if(name==""){
			$("#count_msg").text("用户名不能为空");
			check = false;
		}
		if(password==""){
		    $("#password_msg").text("密码不能为空");
		    check = false;
		}
		//通过检查发送Ajax请求
		if(check){
		  //采用HTTP Basic Autentication模式传输
		  var msg = name+":"+password;
		  //将消息采用base64编码
		  var base_msg = Base64.encode(msg);
		  //将身份信息写入HTTP Header发送ajax请求传输
		   $.ajax({
		     url:path+"/user/login.form",
		     type:"post",
		     dataType:"json",
		     beforeSend:function(xhr){//发送请求前执行
		     	xhr.setRequestHeader("Authorization","Basic "+base_msg);
		     },
		     success:function(result){//result是服务器返回的NoteResult结构的json对象
		        if(result.status==0){
		          //成功,将用户信息写入Cookie
		          var userId = result.data.userId;
		          var token = result.data.token;
		          addCookie("userId",userId,0.5);
		          addCookie("token",token,0.5);
		          //进入edit.html
		          window.location.href="edit.html";
		        }else if(result.status==1){
		          //用户名错误
		          $("#count_msg").text(result.msg);
		        }else if(result.status==2){
		          //密码错误
		          $("#password_msg").text(result.msg);
		        }
		     },
		     error:function(){
		     	alert("系统错误,登录失败");
		     }
		   });
		}
	};
	
	//注册事件处理
	function func_regist(){
		//获取表单信息
		var name = $("#regist_username").val().trim();
		var nick = $("#nickname").val().trim();
		var password = $("#regist_password").val().trim();
		//TODO 检查数据格式
		//发送ajax请求
		$.ajax({
		  url:path+"/user/regist.form",
		  type:"post",
		  data:{"cn_user_name":name,
		  		"cn_user_password":password,
		  		"cn_user_desc":nick},
		  dataType:"json",
		  success:function(result){
		  	if(result.status==0){//成功
		  		$("#back").click();//调用返回按钮的单击操作
		  	}else if(result.status==1){//用户名重复
		  	    $("#warning_1 span").html(result.msg);
		  	    $("#warning_1").show();
		  	}
		  },
		  error:function(){
		  	alert("系统错误,注册失败");
		  }
		  
		});
	};