package com.lrx.Common;

import java.util.HashMap;
import java.util.Map;

//import com.google.common.collect.Maps;

public class Page {


	private int pageNo; // 当前页码
	private int pageSize; // 每页行数
	private int totalRecord; // 总记录数
	private int totalPage; // 总页数
	private Map<String, Object> params; // 查询条件
	//private List<T> result = new ArrayList<T>();
	private boolean entityOrField; // true:需要分页的地方，传入的参数就是Page实体；false:需要分页的地方，传入的参数所代表的实体拥有Page属性
	private String pageStr; // 最终页面显示的底部翻页导航，详细见：getPageStr();
	
	
	public Page() {
		pageNo = 1;
		pageSize = 10;
		totalRecord = 0;
		totalPage = 0;
		params = new HashMap<String, Object>();
	}
	public Page(int pageNo, int pageSize) {
		this.pageNo = pageNo;
		this.pageSize = pageSize;
	}

	/*public  Page<T> newBuilder(int pageNo, int pageSize) {
		Page<T> page = new Page<T>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		return page;
	}*/

	/**
	 * 总件数变化时，更新总页数并计算显示样式
	 */
	private void refreshPage() {
		// 总页数计算
		totalPage = totalRecord % pageSize == 0 ? totalRecord / pageSize
				: (totalRecord / pageSize + 1);
		// 防止超出最末页（浏览途中数据被删除的情况）
		if (pageNo > totalPage && totalPage != 0) {
			pageNo = totalPage;
		}
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		refreshPage();
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public Map<String, Object> getParams() {
		return params;
	}

	public void setParams(Map<String, Object> params) {
		this.params = params;
	}

//	public List<T> getResult() {
//		return result;
//	}
//
//	public void setResult(List<T> result) {
//		this.result = result;
//	}

	public boolean isEntityOrField() {
		return entityOrField;
	}

	public void setEntityOrField(boolean entityOrField) {
		this.entityOrField = entityOrField;
	}

	public String getPageStr() {
		StringBuffer sb = new StringBuffer();
		if (totalRecord > 0) {
			sb.append("	<ul>\n");
			if (pageNo == 1) {
				sb.append("	<li><a>共<font color=red>" + totalRecord
						+ "</font>条</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li><a>首页</a></li>\n");
				sb.append("	<li><a>上页</a></li>\n");
			} else {
				sb.append("	<li><a>共<font color=red>" + totalRecord
						+ "</font>条</a></li>\n");
				sb.append("	<li><input type=\"number\" value=\"\" id=\"toGoPage\" style=\"width:50px;text-align:center;float:left\" placeholder=\"页码\"/></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"toTZ();\"  class=\"btn btn-mini btn-success\">跳转</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage(1)\">首页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("
						+ (pageNo - 1) + ")\">上页</a></li>\n");
			}
			int showTag = 5;// 分页标签显示数量
			int startTag = 1;
			if (pageNo > showTag) {
				startTag = pageNo - 1;
			}
			int endTag = startTag + showTag - 1;
			for (int i = startTag; i <= totalPage && i <= endTag; i++) {
				if (pageNo == i){
					sb.append("<li><a><font color='#808080'>" + i
							+ "</font></a></li>\n");
				}else{
					sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("
							+ i + ")\">" + i + "</a></li>\n");
				}
			}
			if (pageNo == totalPage) {
				sb.append("	<li><a>下页</a></li>\n");
				sb.append("	<li><a>尾页</a></li>\n");
			} else {
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("
						+ (pageNo + 1) + ")\">下页</a></li>\n");
				sb.append("	<li style=\"cursor:pointer;\"><a onclick=\"nextPage("
						+ totalPage + ")\">尾页</a></li>\n");
			}
			sb.append("	<li><a>第" + pageNo + "页</a></li>\n");
			sb.append("	<li><a>共" + totalPage + "页</a></li>\n");

			sb.append("	<li><select title='显示条数' style=\"width:55px;float:left;\" onchange=\"changeCount(this.value)\">\n");
			sb.append("	<option value='" + pageSize + "'>" + pageSize
					+ "</option>\n");
			sb.append("	<option value='5'>5</option>\n");
			sb.append("	<option value='10'>10</option>\n");
			sb.append("	<option value='20'>20</option>\n");
			sb.append("	<option value='30'>30</option>\n");
			sb.append("	<option value='40'>40</option>\n");
			sb.append("	<option value='50'>50</option>\n");
			sb.append("	<option value='60'>60</option>\n");
			sb.append("	<option value='70'>70</option>\n");
			sb.append("	<option value='80'>80</option>\n");
			sb.append("	<option value='90'>90</option>\n");
			sb.append("	<option value='99'>99</option>\n");
			sb.append("	</select>\n");
			sb.append("	</li>\n");

			sb.append("</ul>\n");
			sb.append("<script type=\"text/javascript\">\n");

			// 换页函数
			sb.append("function nextPage(page){");
			sb.append(" window.parent.jzts();");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";}\n");
			sb.append("		else{url += \"?"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";}\n");
			sb.append("		url = url + page + \"&"
					+ (entityOrField ? "pageSize" : "page.pageSize") + "="
					+ pageSize + "\";\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('pageNo')>-1){\n");
			sb.append("				var reg = /pageNo=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'pageNo=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"&"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";}\n");
			sb.append("		url = url + page + \"&"
					+ (entityOrField ? "pageSize" : "page.pageSize") + "="
					+ pageSize + "\";\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 调整每页显示条数
			sb.append("function changeCount(value){");
			sb.append(" window.parent.jzts();");
			sb.append("	if(true && document.forms[0]){\n");
			sb.append("		var url = document.forms[0].getAttribute(\"action\");\n");
			sb.append("		if(url.indexOf('?')>-1){url += \"&"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";}\n");
			sb.append("		else{url += \"?"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";}\n");
			sb.append("		url = url + \"1&"
					+ (entityOrField ? "pageSize" : "page.pageSize")
					+ "=\"+value;\n");
			sb.append("		document.forms[0].action = url;\n");
			sb.append("		document.forms[0].submit();\n");
			sb.append("	}else{\n");
			sb.append("		var url = document.location+'';\n");
			sb.append("		if(url.indexOf('?')>-1){\n");
			sb.append("			if(url.indexOf('pageNo')>-1){\n");
			sb.append("				var reg = /pageNo=\\d*/g;\n");
			sb.append("				url = url.replace(reg,'pageNo=');\n");
			sb.append("			}else{\n");
			sb.append("				url += \"1&"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";\n");
			sb.append("			}\n");
			sb.append("		}else{url += \"?"
					+ (entityOrField ? "pageNo" : "page.pageNo") + "=\";}\n");
			sb.append("		url = url + \"&"
					+ (entityOrField ? "pageSize" : "page.pageSize")
					+ "=\"+value;\n");
			sb.append("		document.location = url;\n");
			sb.append("	}\n");
			sb.append("}\n");

			// 跳转函数
			sb.append("function toTZ(){");
			sb.append("var toPaggeVlue = document.getElementById(\"toGoPage\").value;");
			sb.append("if(toPaggeVlue == ''){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("if(isNaN(Number(toPaggeVlue))){document.getElementById(\"toGoPage\").value=1;return;}");
			sb.append("nextPage(toPaggeVlue);");
			sb.append("}\n");
			sb.append("</script>\n");
		}
		pageStr = sb.toString();
		return pageStr;
	}

	public void setPageStr(String pageStr) {
		this.pageStr = pageStr;
	}


}