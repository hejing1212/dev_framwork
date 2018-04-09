package com.hy.cb.utils.api;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hy.sys.utils.StringTools;
import com.hy.sys.utils.logs.LogUtil;

/**
 * 自定义拦截器，判断请求是否有权限
 * 
 * @author linrb
 * 
 */
public class ApiAuthorizationInterceptor extends HandlerInterceptorAdapter {

	protected static final String CHARACTER_ENCODING = "UTF-8";


	public String encodeStr(String str) {
		try {
			return new String(str.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 请求处理之前进行调用 API拦截器 记录信息:访问时间</br>
	 * Controller路径</br>
	 * 对应方法名</br>
	 * 请求参数信息</br>
	 * 请求相对路径</br>
	 * 请求处理时长
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception {
		request.setCharacterEncoding(CHARACTER_ENCODING);

		// 获取appKey
		String appSign = request.getParameter("appKey");
		String app = "1101010";
		if (StringTools.isEmpty(appSign) || !app.equals(appSign)) {
			writeResult("非法请求", response);
			return false;
		}
		long startTime = System.currentTimeMillis();
		request.setAttribute("startTime", startTime);

		return true;
	}

	/**
	 * 后处理回调方法 在当前请求进行处理之后，也就是Controller
	 * 方法调用之后执行，但是它会在DispatcherServlet进行视图返回渲染之前被调用，
	 * 所以我们可以在这个方法中对Controller处理之后的ModelAndView对象进行操作
	 */
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		System.out.println("进入postHandle");

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		long executeTime = endTime - startTime;
		if (handler instanceof HandlerMethod) {
			StringBuilder sb = new StringBuilder(1000);
			sb.append("CostTime  : ").append(executeTime).append("ms")
					.append("\n");
			sb.append(
					"-------------------------------------------------------------------------------");
			System.out.println(sb.toString());
		}
	}

	/**
	 * 整个请求处理完毕回调方法 该方法将在整个请求完成之后，也就是说在视图渲染之后进行调用，主要用于进行一些资源的释放
	 */
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception ex) throws Exception {
		System.out.println("进入afterCompletion");
		super.afterCompletion(request, response, handler, ex);
	}

	/**
	 * 获得Response响应对象, 返回相关数据
	 * 
	 * @return
	 */
	public void writeResult(String reuslt,
			HttpServletResponse response) {

		try {
			response.setCharacterEncoding(CHARACTER_ENCODING);
			response.setContentType(
					"text/html;charset=" + CHARACTER_ENCODING);
			response.getOutputStream()
					.write(reuslt.getBytes(CHARACTER_ENCODING));
			response.getOutputStream().flush();
		} catch (Exception e) {
			e.printStackTrace();
			LogUtil.info(this, e);
		} finally {
			try {
				if (response.getOutputStream() != null) {
					response.getOutputStream().close();
				}
			} catch (Exception e) {
				e.printStackTrace();
				LogUtil.info(this, e);
			}
		}
	}

}
