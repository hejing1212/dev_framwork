/**
 * 显示数据字典值,结合easyui框架formatter方法使用
 */

//该访求性能低下，每显示一行数据都要遍历	
    var dictJson="";   
	function SetDictName(value, text){
			 if(dictJson==""){
				 $.ajax({
		   				url:getRootPath()+"/sys/dict/getDitcJson.html",
						type:"get",
						dataType:"json",
						async:false,
				   		//提交成功后回调的函数
		             	success: function(data){
		             		dictJson=data;
						} 
					});	
			 }
			var field=this.field; 
			dictJsonArr=JSON.parse(dictJson);
			for(var i = 0, len = dictJsonArr.length; i < len; i++){
				if(dictJsonArr[i].dictCode==field){ 
					var dataDict=dictJsonArr[i].dataDict;
					for(var v = 0, lens = dataDict.length; v < lens; v++){
						if(dataDict[v].itemValue==value){
							return dataDict[v].itemName;
						}
				    }
				}
			}
		}
	
	//该访先对所胡数据生成MAP，然后减少循环，提供性能	
	 var dictMap={};	
	function SetDictNameMap(value, text){
		 if(Object.keys(dictMap).length==0){			 
			 $.ajax({
	   				url:getRootPath()+"/sys/dict/getDitcJson.html",
					type:"get",
					dataType:"json",
					async:false,
			   		//提交成功后回调的函数
	             	success: function(data){
	             		var dictJsonArr=JSON.parse(data);
	             		for(var i = 0, len = dictJsonArr.length; i < len; i++){
	             			var itemMap={};
	             			var dataDict=dictJsonArr[i].dataDict;
	             			for(var v = 0, lens = dataDict.length; v < lens; v++){
	             				itemMap[dataDict[v].itemValue]=dataDict[v].itemName;
            			    }	             			
	             			dictMap[dictJsonArr[i].dictCode]=itemMap;
	            		}
					} 
				});	
		 }
		var field=this.field; 
		return dictMap[field][value];
	}
	
	function SetDictByFieldMap(value, text,index,field){
		 if(Object.keys(dictMap).length==0){			 
			 $.ajax({
	   				url:getRootPath()+"/sys/dict/getDitcJson.html",
					type:"get",
					dataType:"json",
					async:false,
			   		//提交成功后回调的函数
	             	success: function(data){
	             		var dictJsonArr=JSON.parse(data);
	             		for(var i = 0, len = dictJsonArr.length; i < len; i++){
	             			var itemMap={};
	             			var dataDict=dictJsonArr[i].dataDict;
	             			for(var v = 0, lens = dataDict.length; v < lens; v++){
	             				itemMap[dataDict[v].itemValue]=dataDict[v].itemName;
            			    }	             			
	             			dictMap[dictJsonArr[i].dictCode]=itemMap;
	            		}
					} 
				});	
		 } 
		if(Object.keys(dictMap).length>0){
		  return dictMap[field][value];
	    }else{
		 return '';
	}
	}
	
	
	
	function getRootPath(){
		var strFullPath=window.document.location.href;
		var strPath=window.document.location.pathname;
		var pos=strFullPath.indexOf(strPath);
		var prePath=strFullPath.substring(0,pos);
		var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
		return(prePath+postPath);
		}	