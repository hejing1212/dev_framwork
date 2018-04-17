package com.hy.cb.utils.api;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import com.hy.sys.core.utils.HlFramePropertiesUtil;

/**
 * UEditor文件上传辅助类
 * 
 */
public class Uploader {

	// 文件大小常量, 单位kb
	private static final int MAX_SIZE = 500 * 1024;
	// 输出文件地址
	private String url = "";
	// 上传文件名
	private String fileName = "";
	// 状态
	private String state = "";
	// 文件类型
	private String type = "";
	// 原始文件名
	private String originalName = "";
	// 文件大小
	private String size = "";

	private HttpServletRequest request = null;
	private String title = "";

 
	// 文件允许格式
	private String[] allowFiles = { ".rar", ".doc", ".docx", ".zip",
			".pdf", ".txt", ".swf", ".wmv", ".gif", ".png", ".jpg",
			".jpeg", ".bmp" };
	// 文件大小限制，单位Byte
	private long maxSize = 0;

	private static HashMap<String, String> ERROR_INFO = new HashMap<String, String>();

	static {
		ERROR_INFO.put("SUCCESS", "SUCCESS"); // 默认成功
		// 未包含文件上传域
		ERROR_INFO.put("NOFILE",
				"\\u672a\\u5305\\u542b\\u6587\\u4ef6\\u4e0a\\u4f20\\u57df");
		// 不允许的文件格式
		ERROR_INFO.put("TYPE",
				"\\u4e0d\\u5141\\u8bb8\\u7684\\u6587\\u4ef6\\u683c\\u5f0f");
		// 文件大小超出限制
		ERROR_INFO.put("SIZE",
				"\\u6587\\u4ef6\\u5927\\u5c0f\\u8d85\\u51fa\\u9650\\u5236");
		// 请求类型错误
		ERROR_INFO.put("ENTYPE",
				"\\u8bf7\\u6c42\\u7c7b\\u578b\\u9519\\u8bef");
		// 上传请求异常
		ERROR_INFO.put("REQUEST",
				"\\u4e0a\\u4f20\\u8bf7\\u6c42\\u5f02\\u5e38");
		// 未找到上传文件
		ERROR_INFO.put("FILE",
				"\\u672a\\u627e\\u5230\\u4e0a\\u4f20\\u6587\\u4ef6");
		// IO异常
		ERROR_INFO.put("IO", "IO\\u5f02\\u5e38");
		// 目录创建失败
		ERROR_INFO.put("DIR",
				"\\u76ee\\u5f55\\u521b\\u5efa\\u5931\\u8d25");
		// 未知错误
		ERROR_INFO.put("UNKNOWN", "\\u672a\\u77e5\\u9519\\u8bef");
	}
	private Map<String, String> params = null;
	// 上传的文件数据
	private InputStream inputStream = null;

	public static final String ENCODEING = System.getProperties()
			.getProperty("file.encoding");

	public Uploader(HttpServletRequest request) {
		this.request = request;
		this.params = new HashMap<String, String>();

		this.setMaxSize(Uploader.MAX_SIZE);

		this.parseParams();

	}


	/**
	 * 接受并保存以base64格式上传的文件
	 * 
	 * @param fieldName
	 */
	public void uploadBase64(String fieldName) {
		String savePath = this.getFolder(HlFramePropertiesUtil.getConfig("upload.address"));
		String base64Data = this.request.getParameter(fieldName);
		this.url = savePath + "/" + this.fileName;
		try {
			File outFile = new File(this.getPhysicalPath(this.url));
			OutputStream ro = new FileOutputStream(outFile);
			byte[] b = Base64.decode(base64Data);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			ro.write(b);
			ro.flush();
			ro.close();
			this.state = ERROR_INFO.get("SUCCESS");
		} catch (Exception e) {
			this.state = ERROR_INFO.get("IO");
		}
	}

	/**
	 * 接受并保存以base64格式上传的文件
	 * @param request 
	 * @param base64Data  64文件编码
	 * @param path    子目录地址
	 */
	public void uploadFileBase64(HttpServletRequest request,
			String base64Data, String path) {
		HttpSession session = request.getSession();
		ServletContext application = session.getServletContext();
		String serverRealPath = application.getRealPath("/");
		serverRealPath = serverRealPath.substring(0,
				serverRealPath.length() - 1);
		int a = serverRealPath.lastIndexOf(File.separator);
		serverRealPath = serverRealPath.substring(0, a);
        String savePath=serverRealPath+HlFramePropertiesUtil.getConfig("upload.address")+"/"+path;
		File destFile = new File(savePath);
		if (!destFile.exists()) {
			destFile.mkdirs();
		}
		String strpath = savePath+"/"+this.fileName;
		this.url = strpath;
		try {
			File outFile = new File(strpath);
			OutputStream ro = new FileOutputStream(outFile);
			byte[] b = Base64.decode(base64Data);
			for (int i = 0; i < b.length; ++i) {
				if (b[i] < 0) {
					b[i] += 256;
				}
			}
			ro.write(b);
			ro.flush();
			ro.close();
			this.state = ERROR_INFO.get("SUCCESS");
		} catch (Exception e) {
			this.state = ERROR_INFO.get("IO");
		}
	}

	public String getParameter(String name) {

		return this.params.get(name);

	}

	/**
	 * 文件类型判断
	 * 
	 * @param fileName
	 * @return
	 */
	private boolean checkFileType(String fileName) {
		Iterator<String> type = Arrays.asList(this.allowFiles)
				.iterator();
		while (type.hasNext()) {
			String ext = type.next();
			if (fileName.toLowerCase().endsWith(ext)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取文件扩展名
	 * 
	 * @return string
	 */
	private String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}

	private void parseParams() {

		DiskFileItemFactory dff = new DiskFileItemFactory();
		try {
			ServletFileUpload sfu = new ServletFileUpload(dff);
			sfu.setSizeMax(this.maxSize);
			sfu.setHeaderEncoding(Uploader.ENCODEING);

			FileItemIterator fii = sfu.getItemIterator(this.request);

			while (fii.hasNext()) {
				FileItemStream item = fii.next();
				// 普通参数存储
				if (item.isFormField()) {

					this.params.put(item.getFieldName(), this
							.getParameterValue(item.openStream()));

				} else {

					// 只保留一个
					if (this.inputStream == null) {
						this.inputStream = item.openStream();
						this.originalName = item.getName();
						return;
					}

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			this.state = ERROR_INFO.get("UNKNOWN");
		}

	}

	/**
	 * 依据原始文件名生成新文件名
	 * 
	 * @return
	 */
	private String getName(String fileName) {
		Random random = new Random();
		return this.fileName = "" + random.nextInt(10000)
				+ System.currentTimeMillis()
				+ this.getFileExt(fileName);
	}

	/**
	 * 根据字符串创建本地目录 并按照日期建立子目录返回
	 * 
	 * @param path
	 * @return
	 */
	private String getFolder(String path) {
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		path += "/" + formater.format(new Date());
		File dir = new File(this.getPhysicalPath(path));
		if (!dir.exists()) {
			try {
				dir.mkdirs();
			} catch (Exception e) {
				this.state = ERROR_INFO.get("DIR");
				return "";
			}
		}
		return path;
	}

	/**
	 * 根据传入的虚拟路径获取物理路径
	 * 
	 * @param path
	 * @return
	 */
	private String getPhysicalPath(String path) {
		String servletPath = this.request.getServletPath();
		String realPath = this.request.getSession()
				.getServletContext().getRealPath(servletPath);
		return new File(realPath).getParent() + "/" + path;
	}

	/**
	 * 从输入流中获取字符串数据
	 * 
	 * @param in
	 *            给定的输入流， 结果字符串将从该流中读取
	 * @return 从流中读取到的字符串
	 */
	private String getParameterValue(InputStream in) {

		BufferedReader reader = new BufferedReader(
				new InputStreamReader(in));

		String result = "";
		String tmpString = null;

		try {

			while ((tmpString = reader.readLine()) != null) {
				result += tmpString;
			}

		} catch (Exception e) {
			// do nothing
		}

		return result;

	}

	private byte[] getFileOutputStream(InputStream in) {

		try {
			return IOUtils.toByteArray(in);
		} catch (IOException e) {
			return null;
		}

	}
	 
	public void setAllowFiles(String[] allowFiles) {
		this.allowFiles = allowFiles;
	}

	public void setMaxSize(long size) {
		this.maxSize = size * 1024;
	}

	public String getSize() {
		return this.size;
	}

	public String getUrl() {
		return this.url;
	}

	public String getFileName() {
		return this.fileName;
	}
	public void setFileName(String fileName) {
		  this.fileName=fileName;
	}
	public String getState() {
		return this.state;
	}

	public String getTitle() {
		return this.title;
	}

	public String getType() {
		return this.type;
	}

	public String getOriginalName() {
		return this.originalName;
	}
	
	
	
	

}