// 自定义tabls方法根据id获取页面信息
$.extend($.fn.tabs.methods, {
	getTabById : function(jq, id) {
		var tabs = $.data(jq[0], 'tabs').tabs;
		for (var i = 0; i < tabs.length; i++) {
			var tab = tabs[i];
			if (tab.panel('options').id == id) {
				return tab;
			}
		}
		return null;
	},
	selectById : function(jq, id) {
		var tab;
		var tabs = $.data(jq[0], 'tabs').tabs;
		for (var i = 0; i < tabs.length; i++) {
			tab = tabs[i];
			if (tab.panel('options').id == id) {
				break;
			}
		}
		if (tab != undefined) {
			var curTabIndex = $("#tabs").tabs("getTabIndex", tab);
			$('#tabs').tabs('select', curTabIndex);
		}
	},
	existsById : function(jq, id) {
		return jq.tabs('getTabById', id) != null;
	},
	closeById : function(jq, id) {
		jq.tabs('selectById', id);
		$("#tabs").find("li.tabs-selected").find("a.tabs-close").click();
	}
});

var mainPlatform = {
	init: function(){
		this.bindEvent();
		loadMenu();
		this._createTopMenu();
	},
	bindEvent: function(){
		var self = this;
		// 顶部大菜单单击事件
		$(document).on('click', '.pf-nav-item', function() {
            $('.pf-nav-item').removeClass('current');
            $(this).addClass('current');

            // 渲染对应侧边菜单
            var m = $(this).data('sort');
            self._createSiderMenu(SystemMenu[m], m);
        });

        $(document).on('click', '.sider-nav .pf-menu-title', function() {
        	$(this).closest('.pf-sider').find('.sider-nav li').removeClass('current');
            $(this).closest('li').addClass('current');

            // if is no-child
            if($(this).closest('.no-child').size() > 0) {
            	var index = $(this).closest('.pf-sider').attr('arrindex');
            	var test=$(this).find('.sider-nav-title').text();
	        	if($('.easyui-tabs1[arrindex='+ index +']').tabs('exists', $(this).find('.sider-nav-title').text())){
	        		$('.easyui-tabs1[arrindex='+ index +']').tabs('select', $(this).find('.sider-nav-title').text())
	        		return false;
	        	}
	        	$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
					title: $(this).find('.sider-nav-title').text(),
					id:$(this).attr('arrmenuid'),
					content: '<iframe class="page-iframe" src="'+ $(this).closest('.no-child').data('href') +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
					closable: true
				});
            }
            //$('iframe').attr('src', $(this).data('src'));
        });

        $(document).on('click', '.pf-logout', function() {
            layer.confirm('您确定要退出吗？', {
              icon: 4,
			  title: '确定退出' //按钮
			}, function(){
			  location.href= 'login.html'; 
			});
        });
        /*右边菜单连接点击后处理*/
        $(document).on('click', '.sider-nav-s li', function(e){
        	var index = $(this).closest('.pf-sider').attr('arrindex');
        	$(this).closest('.pf-sider').find('.active').removeClass('active');
        	$(this).addClass('active');
        	 
        	if($('.easyui-tabs1[arrindex='+ index +']').tabs('exists', $(this).text())){
        		$('.easyui-tabs1[arrindex='+ index +']').tabs('select', $(this).text())
        		return false;
        	}
        	$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
				title: $(this).text(),
				id:$(this).attr('arrmenuid'),
				content: '<iframe class="page-iframe" src="'+ $(this).data('href') +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
				closable: true
			});
        });
        //左侧菜单收起
        $(document).on('click', '.toggle-icon', function() {
            $(this).closest("#pf-bd").toggleClass("toggle");
            $(window).resize();
        });

         //关闭当前
	     $('#mm-tabclose').click(function(){
	         var currtab_title = $('#mm').data("currtab");
	         $(".easyui-tabs1:visible").tabs('close',currtab_title);
	     })
	     //全部关闭
	     $('#mm-tabcloseall').click(function(){
	         $(".easyui-tabs1:visible").find('.tabs li').each(function(i,n){
	             $(".easyui-tabs1:visible").tabs('close', $(n).text());
	         });    
	     });
	     //关闭除当前之外的TAB
	     $('#mm-tabcloseother').click(function(){
	         var currtab_title = $('#mm').data("currtab");
	         $('.tabs-inner span').each(function(i,n){
	             if($(n).text() !== currtab_title)
	                 $(".easyui-tabs1:visible").tabs('close',$(n).text());
	         });    
	     });

	},

	// renderTopMenu
	_createTopMenu: function(){
		var menuStr = '',
			currentIndex = 0;
		for(var i = 0, len = SystemMenu.length; i < len; i++) {
			menuStr += '<li class="pf-nav-item project" data-sort="'+ i +'" arrmenuid="'+SystemMenu[i].menuid+'" data-menu="system_menu_" + i>'+
                      '<a href="javascript:;">'+
                          '<span class="iconfont">'+ SystemMenu[i].iconCls +'</span>'+
                          '<span class="pf-nav-title">'+ SystemMenu[i].text +'</span>'+
                      '</a>'+
                  '</li>';
            // 渲染当前
            if (SystemMenu[i].isCurrent){
            	currentIndex = i;
            	this._createSiderMenu(SystemMenu[i], i);
            }
		}

		$('.pf-nav').html(menuStr);
		$('.pf-nav-item').eq(currentIndex).addClass('current');
	},

	_createSiderMenu: function(menu, index){
		$('.pf-sider').hide();
		this._createPageContainer(index);
		if($('.pf-sider[arrindex='+ index +']').size() > 0) {
			
			$('.pf-sider[arrindex='+ index +']').show();
			return false;
		};
		var menuStr = '<h2 class="pf-model-name">'+
                    '<span class="iconfont">'+ menu.iconCls+'</span>'+
                    '<span class="pf-name">'+ menu.text +'</span>'+
                    '<span class="toggle-icon"></span>'+
                '</h2><ul class="sider-nav">';

        for(var i = 0, len = menu.children.length; i < len; i++){
        	var m = menu.children[i],
        		mstr = '';
        	var str = '';

        	if(m.isCurrent){
        		if(m.children && m.children.length > 0) {
        			str = '<li class="current">';
        		}else{
        			str = '<li class="current no-child" arrmenuid="'+m.menuid+'" data-href="'+basePath+m.href +'">';
        		}
        	}else{
        		if(m.children && m.children.length > 0) {
        			str = '<li>';
        		}else{
        			str = '<li class="no-child" arrmenuid="'+m.menuid+'" data-href="'+basePath+m.href +'">';
        		}
        	}

           str += '<a href="javascript:;" class="pf-menu-title">'+
                '<span class="iconfont sider-nav-icon">'+ m.iconCls +'</span>'+
                '<span class="sider-nav-title">'+ m.text +'</span>'+
                '<i class="iconfont">&#xe642;</i>'+
            '</a>'+
            '<ul class="sider-nav-s">';
            var childStr = '';
            for(var j = 0, jlen = m.children.length; j < jlen; j++){
            	var child = m.children[j];
            	if(child.isCurrent){
            		childStr += '<li class="active" text="'+ child.text +'" arrmenuid="'+child.menuid+'" data-href="' +basePath+ child.href + '"><a href="#">'+ child.text +'</a></li>';
            		$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
						title: child.text,
						id:child.menuid,
						content: '<iframe class="page-iframe" src="'+basePath+ child.href +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
						closable: true
					});
            	}else {
            		childStr += '<li text="'+ child.text +'" arrmenuid="'+child.menuid+'" data-href="' +basePath+ child.href + '"><a href="#">'+ child.text +'</a></li>';
            	}
            }
            mstr = str + childStr + '</ul></li>';
            menuStr += mstr;            
        }
        $('.pf-sider-wrap').append($('<div class="pf-sider" arrindex="'+ index +'"></div>').html(menuStr + '</ul>'));

	},

	_createPageContainer: function(index){
		$('.easyui-tabs1').hide();
		if($('.easyui-tabs1[arrindex='+ index +']').size() > 0){
			$('.easyui-tabs1[arrindex='+ index +']').show();
			return false;
		}
		var $tabs = $('<div id="tabs" class="easyui-tabs1" arrindex="'+ index +'" style="width:100%;height:100%;">');
		$('#pf-page').append($tabs);
		$tabs.tabs({
	      tabHeight: 44,
	      onSelect:function(title, index){
	        var currentTab = $tabs.tabs("getSelected");
	        if(currentTab.find("iframe") && currentTab.find("iframe").size()){
	            currentTab.find("iframe").attr("src",currentTab.find("iframe").attr("src"));
	        }
	        $('.pf-sider:visible').find('.sider-nav-s li').removeClass('active');
	        $('.pf-sider:visible').find('.sider-nav-s li[text='+ title +']').addClass('active');
	      }
	    });

	    $tabs.find('.tabs-header').on('contextmenu', function(e){
	    	e.preventDefault();
	    	if($(e.target).closest('li').size() > 0 || $(e.target).is('li')){
	    		$('#mm').menu('show', {
		             left: e.pageX,
		             top: e.pageY,
		         });
	    		var subtitle = $(e.target).closest('li').size() ? $(e.target).closest('li') : $(e.target);
        		$('#mm').data("currtab",subtitle.text());
	    	}
	    })
	},
	
	/*hejing -add */
	
	_createWindows: function(title,url,icon,tabid){
    	var index =$('.pf-sider').attr('arrindex');   	 
    	if($('.easyui-tabs1[arrindex='+ index +']').tabs('exists', title)){
    		$('.easyui-tabs1[arrindex='+ index +']').tabs('select', title);
    		return false;
    	}
     	var pid=this.getSelected()[0].id;
     	$('.easyui-tabs1[arrindex='+ index +']').tabs('add',{
     		    id:pid+"_"+tabid,
				title: title,
				content: '<iframe class="page-iframe" src="'+ url +'" frameborder="no" border="no" height="100%" width="100%" scrolling="auto"></iframe>',
				closable: true
			});
     },
	
	//刷新父页面datagrid表格数据，关闭当前页面
	parentReloadTabGrid:function (){
		var id=this.getSelected()[0].id;
		var parentId=id.substring(0,id.indexOf("_"));
		if ($("#tabs").tabs("existsById", parentId)) {
			//选中父页面，并刷新
			$('#tabs').tabs('selectById', parentId);
			window.top.reload_Abnormal_Monitor.call();
		}
		if ($("#tabs").tabs("existsById", id)) {
			//选中
			$('#tabs').tabs('selectById',id);
			$('#tabs').tabs('closeById',id);
		}
	},
     
     getSelected:function() {       
    		return $('#tabs').tabs('getSelected');
    	}
	/* add comm*/

};

var SystemMenu="";
var basePath=getRootPath();

function loadMenu(){
	$.ajax({
		url : basePath+"/admin/getMenuList.html",
		type : "get",
		dataType : "json",
		async : false,
		//提交成功后回调的函数
		success : function(data) {
			if (data) {
				  SystemMenu=data; 
			}
		}
	});
}
//得到当前虚拟目录路径 
function getRootPath(){
	var strFullPath=window.document.location.href;
	var strPath=window.document.location.pathname;
	var pos=strFullPath.indexOf(strPath);
	var prePath=strFullPath.substring(0,pos);
	var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
	return(prePath+postPath);
	}	

mainPlatform.init();