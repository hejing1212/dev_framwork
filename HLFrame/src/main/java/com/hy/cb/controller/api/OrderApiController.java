package com.hy.cb.controller.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hy.sys.core.controller.AbstractBasicController;

@Controller
@RequestMapping("/api/order")
public class OrderApiController extends AbstractBasicController{

	@Override
	protected void init(ModelMap mode, HttpServletRequest req) {
		// TODO Auto-generated method stub
		
	}

}
