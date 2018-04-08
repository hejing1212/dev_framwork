/**
 * AJAX图片文件上传
 * @author hejing
 */
var basePath=getRootPath();
function upload_cover(obj,url,name) {
        ajax_upload(obj.id,url, function(data) {               //function(data)是上传图片的成功后的回调方法
            var isrc = data.relatPath.replace(/\/\//g, '/'); //获取的图片的绝对路径
            $('#image').attr('src', basePath+isrc).data('img-src',isrc); //给<input>的src赋值去显示图片
            $('#'+name).val(isrc);
        });
    }

    function ajax_upload(feid, url,callback) {                 //具体的上传图片方法
    	var image=$("input[name='uploadFile']").attr("id");
        if (image_check(feid)) {                           //自己添加的文件后缀名的验证
            $.ajaxFileUpload({
                fileElementId:image,                        //需要上传的文件域的ID，即<input type="file">的ID。
                url: url,                                   //后台方法的路径
                type: 'post',                               //当要提交自定义参数时，这个参数要设置成post
                dataType: 'json',                           //服务器返回的数据类型。可以为xml,script,json,html。如果不填写，jQuery会自动判断。
                secureuri: false,                           //是否启用安全提交，默认为false。
                async : true,                                //是否是异步
                success: function(data) {                     //提交成功后自动执行的处理函数，参数data就是服务器返回的数据。
                    if (callback) callback.call(this, data);
                },
                error: function(data, status, e) {            //提交失败自动执行的处理函数。
                    console.error(e);
                }
            });
        }
    }
    function image_check(feid) {                               //自己添加的文件后缀名的验证
       // var img = document.getElementById(feid);
    	var img=$("#"+feid).filebox("getValue");
        return /.(jpg|png|gif|bmp)$/.test(img)?true:(function() {
            $.messager.alert("操作提示", '图片格式仅支持jpg、png、gif、bmp格式，且区分大小写。', "info");
            return false;
        })();
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