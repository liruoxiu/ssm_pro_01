package com.lrx.Util;

import java.io.InputStream;

import org.jaxen.JaxenException;
import org.jaxen.jdom.JDOMXPath;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                   

public class ConfigManager {
	static Document pathxmldoc = null;

	public static Document getPathXmlDoc() {

		if (pathxmldoc != null) {
			return pathxmldoc;
		} else {
			SAXBuilder builder = new SAXBuilder();
			// synchronized(doc){

			InputStream ins = ConfigManager.class
					.getResourceAsStream("/config.xml");
			try {
				pathxmldoc = builder.build(ins);
			} catch (JDOMException e) {
				// TODO 鑷姩鐢熸垚 catch 鍧�
				e.printStackTrace();
				// }
			} catch (Exception e) {
				// TODO 鑷姩鐢熸垚 catch 鍧�
				e.printStackTrace();
			}
		}
		return pathxmldoc;
	}

	/**
	 * 鏍规嵁閰嶇疆鏂囦欢鏍囩鍚嶇О锛屼粠閰嶇疆鏂囦欢涓鍙栨爣绛炬墍瀵瑰簲鐨刄RL璺緞
	 * 
	 * @throws Exception
	 */
	public static String getItemValue(String textlabel) {
		// return "dcp";

		String pathtext = null;
		Document doc = ConfigManager.getPathXmlDoc();

		JDOMXPath xpath = null;
		try {
			xpath = new JDOMXPath("configuration/item/" + textlabel);
		} catch (JaxenException e) {
			// TODO 鑷姩鐢熸垚 catch 鍧�
			e.printStackTrace();
		}
		Object obj = null;
		try {
			obj = xpath.selectSingleNode(doc);
		} catch (JaxenException e) {
			// TODO 鑷姩鐢熸垚 catch 鍧�
			e.printStackTrace();
		}
		if (obj != null) {
			Element elm = (Element) obj;
			pathtext = elm.getText();
		}

		return pathtext;

	}
}