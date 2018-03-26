package com.hy.sys.tag;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspTagException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

import com.hy.sys.entity.SysDataDictItem;
import com.hy.sys.utils.DictUtils;

public class DictKey2Value extends TagSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String key;

	private String nodeKey;

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
		Set<SysDataDictItem> dictionary = DictUtils.get(this.getNodeKey()); 
		if (dictionary == null)
			sb.append("未发现NodeKey");
		else {
			for(SysDataDictItem item:dictionary) {
				if (item.getItemValue()==key)
					sb.append(item.getItemName());
			}
		}
		return sb;
	}

	public String getNodeKey() {
		return nodeKey;
	}

	public void setNodeKey(String nodeKey) {
		this.nodeKey = nodeKey;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
