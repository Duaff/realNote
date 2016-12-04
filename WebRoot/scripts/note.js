//根据笔记ID提取笔记信息
function loadnote(){
	    //清空原有标题和内容
		$("#input_note_title").val("");
		um.setContent("");
	    //将当前笔记设置为选中状态
	    $("#note_list li a").removeClass("checked");
		$(this).find("a").addClass("checked");
		//获取点中的笔记ID
		var noteId = $(this).data("noteId");
		//发送ajax请求
		$.ajax({
		  url:path+"/note/loadnote.form",
		  type:"post",
		  data:{"noteId":noteId},
		  dataType:"json",
		  success:function(result){
		     if(result.status==0 && result.data != null){//提取笔记信息
		       var noteTitle = result.data.cn_note_title;
		       var noteBody = result.data.cn_note_body;
		       $("#input_note_title").val(noteTitle);//将笔记标题设置到输入框
		       um.setContent(noteBody);//将笔记内容放入编辑器
		     }
		  },
		  error:function(){
		    alert("系统异常,载入笔记信息失败");
		  }
		});
	};
//根据笔记本加载笔记信息
function loadnotes(){
	    //将当前li设置成选中状态
	    $("#book_list li a").removeClass("checked");
	    $(this).find("a").addClass("checked");
	    //获取单击的笔记本的ID
		var bookId = $(this).data("bookId");
		//发送ajax请求
		$.ajax({
		  url:path+"/note/loadnotes.form",
		  type:"post",
		  data:{"bookId":bookId},
		  dataType:"json",
		  success:function(result){
		  	if(result.status==0){
		  	   //获取笔记信息生成列表元素
		  	   var notes = result.data;//note的json对象数组
		  	   $("#note_list li").remove();//清除原有笔记列表
		  	   for(var i=0;i<notes.length;i++){
		  	      var noteTitle = notes[i].cn_note_title;
		  	      var noteId = notes[i].cn_note_id;
		  	      //生成笔记li列表元素
      			  var str_li = '<li class="online">';
				  str_li += '<a>';
				  str_li += '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				  str_li += '</a>';
				  str_li += '<div class="note_menu" tabindex="-1">';
				  str_li += '	<dl>';
				  str_li += '		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
				  str_li += '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
				  str_li += '	    <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
				  str_li += '	</dl>';
				  str_li += '</div>';
				  str_li += '</li>';
				  var $li = $(str_li);//将li字符串转成jQuery对象
				  $li.data("noteId",noteId);//将笔记id绑定到li元素上
				  $("#note_list").append($li);//添加到笔记ul中
		  	   }
		  	}
		  },
		  error:function(){
		  	alert("系统异常,加载笔记失败");
		  }
		});
	};

//显示添加笔记对话框
function showAddnoteWindow(){
	//异步发送请求,将返回结果填充到can元素中
	$("#can").load("./alert/alert_note.html");
	$("#can").show();//显示对话框div
	$(".opacity_bg").show();//显示背景色
};

//创建笔记操作
function addnote(){
	//获取笔记名,用户ID,笔记本ID
	var noteTitle = $("#input_note").val().trim();
	var bookId = $("#book_list a[class='checked']").parent().data("bookId");
	//TODO 格式检查
	//发送ajax
	$.ajax({
	  url:path+"/note/add.form",
	  type:"post",
	  data:{"cn_notebook_id":bookId,
	  		"cn_user_id":user,
	  		"cn_note_title":noteTitle},
	  dataType:"json",
	  success:function(result){
	  	if(result.status==0){//成功
	  	  hideWindow();//关闭对话框
	  	  //为笔记列表添加一项li
	  	  var noteId = result.data;//获取笔记ID
	  	  var str_li = '<li class="online">';
			  str_li += '<a>';
			  str_li += '	<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
			  str_li += '</a>';
			  str_li += '<div class="note_menu" tabindex="-1">';
			  str_li += '	<dl>';
			  str_li += '		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
			  str_li += '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
			  str_li += '	    <dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
			  str_li += '	</dl>';
			  str_li += '</div>';
			  str_li += '</li>';
			  var $li = $(str_li);//将li字符串转成jQuery对象
			  $li.data("noteId",noteId);//将笔记id绑定到li元素上
			  $("#note_list").prepend($li);//添加到笔记ul中
			  //将当前追加li设置成选中
			  $("#note_list li:first").click();
	  	}
	  },
	  error:function(){
	  	alert("系统异常,创建笔记失败");
	  }
	});
};
	
	