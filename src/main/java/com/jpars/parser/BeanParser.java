/*
 * 
 */
package com.jpars.parser;

public class BeanParser {
	
	private static BasicBeanParser xmlParser;
	
	private static BasicBeanParser jsonParser;
	
	
	private BeanParser(){
		
	}
	
	public static BasicBeanParser CreateParser(ParseFormat fmt) {
		
		if (ParseFormat.xml.equals(fmt)) {
			if (xmlParser == null) {
				xmlParser = new BeanXmlParser();	
			}
			return xmlParser;
		}else if (ParseFormat.json.equals(fmt)) {
			if (jsonParser == null) {
				jsonParser = new BeanJsonParser();	
			}
			return jsonParser;
		}else {
			throw new IllegalArgumentException("Invalid Format Type");
		}
	}
	
}
