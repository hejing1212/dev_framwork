package com.hy.sys.tag;

import java.io.IOException;
import java.util.Set;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.alibaba.fastjson.JSON;
import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.utils.DictUtils;

public class DictKey2Json extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String itemCode;

	public int doStartTag() throws JspTagException {
		return EVAL_PAGE;
	}

	public int doEndTag() throws JspTagException {
		try {
			JspWriter out = this.pageContext.getOut();
			out.print(dictName().toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return EVAL_PAGE;
	}

	public StringBuffer dictName() {
		StringBuffer sb = new StringBuffer(); 
		Set<SysDataDictItem> dictItem = DictUtils.get(this.getItemCode()); 
		if (dictItem == null)
			sb.append("未找到该编号的数据字典！");
		else {
			sb.append(JSON.toJSONString(dictItem));
			
		}
		return sb;
	}

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}

	

	
}
