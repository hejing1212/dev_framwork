package com.hy.sys.utils;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.hy.sys.core.utils.HlFramePropertiesUtil;

/**
 * UEditor文件上传辅助类
 * 
 */
public class FileUpload {
	/**
	 * 单个文件上传
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> upload(HttpServletRequest request) throws Exception {
		
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest.getFile("uploadFile");        
        //文件夹不存在则创建
        //上传多文件存储路径
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext servletContext = webApplicationContext.getServletContext();
        // 得到文件绝对路径
       
        String rootDir=servletContext.getRealPath("/");
        String savePath = servletContext.getRealPath("/")+HlFramePropertiesUtil.getConfig("upload.address");  //获取设置的文件上传根目录
        File fdir=getChildDirectory(savePath);
        String oriName = file.getOriginalFilename();
        String extName=oriName.substring(oriName.lastIndexOf("."),oriName.length());
        String newName = UUID.randomUUID()+extName;
        File tempFile = new File(fdir.getPath()+File.separator+newName);
        file.transferTo(tempFile);

        String relatDir=tempFile.getPath().substring(rootDir.length()-1, tempFile.getPath().length()).replaceAll("\\\\", "/");
        Map<String, Object> result = new HashMap<>();
        result.put("oriName", oriName);
        result.put("realName", tempFile.getName());
        result.put("relatPath", relatDir);
        result.put("code", "1");
        result.put("msg", "文件上传成功");
        return result;
    }
	
	public static List<String> uploadService(String path,HttpServletResponse response,
			HttpServletRequest request)throws ServletException, IOException {
		 //处理中文乱码问题
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); 
         
        //检查请求是否是multipart/form-data类型
        if(!ServletFileUpload.isMultipartContent(request)){  //不是multipart/form-data类型
            throw new RuntimeException("表单的enctype属性不是multipart/form-data类型！！");
        }
        //创建上传所需要的两个对象
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory); //解析器依赖于工厂
        //创建容器来接受解析的内容
        List<FileItem> items = new ArrayList<FileItem>();
        //将上传的文件信息放入容器中
        try {
            items = sfu.parseRequest(request);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        List<String> list=new ArrayList<String>();
        //遍历容器,处理解析的内容;封装两个方法，一个处理普通表单域，一个处理文件的表单域
        for(FileItem item : items){
            if(item.isFormField()){
                handleFormField(item);
            }else{
            	list.add(handleUploadField(item,path)) ;
            }
        }    
		return list;
	}
	
	/**
     * 处理普通表单域
     * @param item
     */
    private static void handleFormField(FileItem item) {
        String fieldName = item.getFieldName(); //得到表单域的name的值
        String value = "";
        try {
            value = item.getString("utf-8");  //得到普通表单域中所输入的值
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //打印到控制台
        System.out.println("fieldName:"+fieldName+"--value:"+value);
    }
    
    
    /**
     * 处理文件的表单域
     * @param item
     */
    private static String handleUploadField(FileItem item,String serverPath) {
        String fileName = item.getName();  //得到上传文件的文件名
        if(fileName!=null && !"".equals(fileName)){
            //控制只能上传图片
            if(!item.getContentType().startsWith("image")){
                return null;
            }
            //向控制台打印文件信息
            System.out.println("fileName:"+fileName);
            System.out.println("fileSize:"+item.getSize());
        }
        //创建子目录
        File childDirectory = getChildDirectory(serverPath);
        String extName=fileName.substring(fileName.lastIndexOf("."),fileName.length());

        String saveFileName=UUID.randomUUID()+extName;
        //写入服务器或者磁盘
        try {
            item.write(new File(childDirectory.toString(),saveFileName));
            return saveFileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }        
    }
    
    /**
     * 按照时间创建子目录，防止一个目录中文件过多，不利于以后遍历查找
     * @param path
     * @return
     */
    private static File getChildDirectory(String path) {
        Date currTime = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String time = sdf.format(currTime);

        File file = new File(path,time);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }
}