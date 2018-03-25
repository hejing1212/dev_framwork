package com.hy.sys.jcaptcha;

import java.awt.Color;
import java.awt.Font;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.octo.captcha.CaptchaFactory;
import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.RandomRangeColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.RandomTextPaster;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.FileDictionary;
import com.octo.captcha.component.word.wordgenerator.ComposeDictionaryWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.CaptchaServiceException;
import com.octo.captcha.service.captchastore.FastHashMapCaptchaStore;

public class JCaptcha {
	public static final EsManageableImageCaptchaService captchaService = new EsManageableImageCaptchaService(new FastHashMapCaptchaStore(), generatorCaptchaService(), 180, 100000, 75000);
	  
 
	/**
	 * 判断验证码
	 * 
	 * @param request
	 * @param userCaptchaResponse
	 * @return
	 */
	public static boolean validateResponse(HttpServletRequest request, String userCaptchaResponse) {
		if (request.getSession(false) == null)
			return false;

		boolean validated = false;
		try {
			String id = request.getSession().getId();
			validated = captchaService.validateResponseForID(id, userCaptchaResponse).booleanValue();
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validated;
	}

	public static boolean hasCaptcha(HttpServletRequest request, String userCaptchaResponse) {
		if (request.getSession(false) == null)
			return false;
		boolean validated = false;
		try {
			String id = request.getSession().getId();
			validated = captchaService.hasCapcha(id, userCaptchaResponse);
		} catch (CaptchaServiceException e) {
			e.printStackTrace();
		}
		return validated;
	}

	/**
	 * 自定义背景色
     * 根据SpringBean的配置文件编写的代码实现
     * */
    @SuppressWarnings("deprecation")
    public static GenericCaptchaEngine generatorCaptchaService(){
        //生成随机颜色，参数分别表示RGBA的取值范围
        RandomRangeColorGenerator textColor = new RandomRangeColorGenerator(new int[]{0,255},new int[]{0,180},new int[]{0,210},new int[]{255,255});
        //随机文字多少和颜色，参数1和2表示最少生成多少个文字和最多生成多少个
		RandomTextPaster randomTextPaster = new RandomTextPaster(4, 5, textColor,true);
        //生成背景的大小这里是宽85高40的图片，也可以设置背景颜色和随机背景颜色，这里使用默认的白色
        // UniColorBackgroundGenerator colorbgGen = new UniColorBackgroundGenerator(85,40);
		UniColorBackgroundGenerator colorbgGen=new UniColorBackgroundGenerator(85,40,new Color(255,157,2));
		//BackgroundGenerator colorbgGen = new  GradientBackgroundGenerator(new Integer(180), new Integer(50),Color.gray,Color.yellow); 
		 
        //随机生成的字体大小和字体类型，参数1和2表示最小和最大的字体大小，第三个表示随机的字体
        RandomFontGenerator randomFontGenerator = new RandomFontGenerator(20, 25, new Font[]{new Font("Arial", 0, 10),new Font("Tahoma",0,10)});
        //结合上面的对象构件一个从文本生成图片的对象
        ComposedWordToImage cwti = new ComposedWordToImage(randomFontGenerator,colorbgGen,randomTextPaster);
        //随机文本的字典，这里是使用jcaptcha-1.0-all.jar中的文本字典，字典名称为toddlist.properties
        ComposeDictionaryWordGenerator cdwg = new ComposeDictionaryWordGenerator(new FileDictionary("toddlist"));
 
        GimpyFactory gf = new GimpyFactory(cdwg, cwti);
 
        GenericCaptchaEngine gce = new GenericCaptchaEngine(new CaptchaFactory[]{gf});
        //返回一个Service对象，这里180是验证码存在的时间，单位是秒，200000是最大存储大小
        
        return gce;
    }
}
