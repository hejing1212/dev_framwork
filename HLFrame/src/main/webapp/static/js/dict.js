/**
 * 显示数据字典值
 */

//以下为数据字典设置相关，一个界面中只要一个该方法	
    var dictJson="";		
		//设置用户状态
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
		
		
	function getRootPath(){
		var strFullPath=window.document.location.href;
		var strPath=window.document.location.pathname;
		var pos=strFullPath.indexOf(strPath);
		var prePath=strFullPath.substring(0,pos);
		var postPath=strPath.substring(0,strPath.substr(1).indexOf('/')+1);
		return(prePath+postPath);
		}	