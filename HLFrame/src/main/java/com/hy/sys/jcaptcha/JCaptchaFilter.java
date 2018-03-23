package com.hy.sys.jcaptcha;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 生成验证码
 * 
 * @author 
 *
 */
public class JCaptchaFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		/*response.setDateHeader("Expires", 0L);
		response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		response.setHeader("Pragma", "no-cache");
		response.setContentType("image/jpeg");

		String id = request.getRequestedSessionId();
		BufferedImage bi = JCaptcha.captchaService.getImageChallengeForID(id);
		 
		ServletOutputStream out = response.getOutputStream();
		ImageIO.write(bi, "jpg", out);
		try {
			out.flush();
		} finally {
			out.close();
		}*/
		CreateImg(request,response);
	}
	
	protected void CreateImg(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        byte[] captChallengeAsJpeg = null;
         
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
         
        String captchaId = req.getSession().getId();
        BufferedImage challenge = CaptchaServiceSingleton.getService().getImageChallengeForID(captchaId,req.getLocale());
         
        JPEGImageEncoder jpegEncoder = JPEGCodec.createJPEGEncoder(jpegOutputStream);
        jpegEncoder.encode(challenge);
         
        captChallengeAsJpeg = jpegOutputStream.toByteArray();
         
         resp.setHeader("Cache-Control", "no-store");
         resp.setHeader("Pragma", "no-cache");
         resp.setDateHeader("Expires", 0);
         resp.setContentType("image/jpeg");
          
         ServletOutputStream respOutputStream = resp.getOutputStream();
         respOutputStream.write(captChallengeAsJpeg);
         respOutputStream.flush();
         respOutputStream.close();
    }

}

 
