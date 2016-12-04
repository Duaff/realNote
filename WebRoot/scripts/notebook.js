//根据用户ID加载笔记本列表
function loadbooks(){
	$.ajax({
		  url:path+"/notebook/loadbooks.form",
		  type:"post",
		  data:{"userId":user},
		  dataType:"json",
		  success:function(result){
		  	if(result.status==0){
		  	   var books = result.data;
		  	   //循环解析笔记本信息生成列表元素
		  	   for(var i=0;i<books.length;i++){
		  	   	 var book = books[i];
		  	   	 //获取笔记本ID
		  	   	 var bookId = books[i].cn_notebook_id;
		  	   	 //获取笔记本名称
				 var bookName = books[i].cn_notebook_name;
				 //生成li元素
				 var $li = $('<li class="online"><a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+bookName+'</a></li>');
			   	 $li.data("bookId",bookId);//将id绑定到当前li元素
			   	 //将li添加到ul区域
			   	 $("#book_list").append($li);
			   }
		  	   //第一个li选中
		  	   $("#book_list li:first").click();//模拟点击操作
		  	}
		  },
		  error:function(){
		  	alert("系统异常,加载笔记本失败");
		  }
		});
};

//弹出添加笔记本对话框
function showAddbookWindow(){
	//异步发送请求,将返回结果填充到can元素中
	$("#can").load("./alert/alert_notebook.html");
	$("#can").show();//显示对话框div
	$(".opacity_bg").show();//显示背景色
};
//隐藏#can对话框
function hideWindow(){
	$("#can").hide();//隐藏对话框div
	$(".opacity_bg").hide();//隐藏背景色
};

function addNoteBook(){
	//获取笔记本名称
	var bookName = $("#input_notebook").val().trim();
	//TODO 检查笔记本名是否为空
	//发送ajax请求
	$.ajax({
	  url:path+"/notebook/add.form",
	  type:"post",
	  data:{"userId":user,"bookName":bookName},
	  dataType:"json",
	  success:function(result){
	  	if(result.status==0){//添加成功
	  		hideWindow();//关闭对话框
	  		//将新笔记本追加到笔记本列表
	  		var $li = $('<li class="online"><a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+bookName+'</a></li>');
		   	var bookId = result.data;
		   	$li.data("bookId",bookId);//将id绑定到当前li元素
		   	 //将li添加到ul区域
		   	$("#book_list").prepend($li);
		   	//将该li选中
		   	$("#book_list li:first").click();//模拟单击
	  	}else if(result.status==1){//重名
	  		$("#bookname_msg").html(result.msg);
	  	}
	  },
	  error:function(){
	  	alert("系统异常,添加笔记本失败");
	  }
	});
};









