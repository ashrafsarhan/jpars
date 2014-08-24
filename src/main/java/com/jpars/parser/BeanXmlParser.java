/**
 * 
 */
package com.jpars.parser;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.jpars.commons.TimerUtil;

/**
 * @author ashraf_sarhan
 *
 */
@SuppressWarnings("rawtypes")
public class BeanXmlParser extends BasicBeanParser {
	
	private static Logger logger = Logger.getLogger(BeanXmlParser.class.getSimpleName());
	
	private void beanToXML(Object bean, Element rootElement) {
		Field[] fields = sortFields(bean.getClass().getFields(),bean);		
		try{
				for(Field f:fields){
					 if(f.getType().getName().contains("List")&& !f.isAnnotationPresent(BeanListConverter.class))
						 listToXML(bean, f, rootElement);
					 else if(f.getType().getName().contains("Map"))
						 mapToXML(bean, f, rootElement);
					 else if(f.getType().isAnnotationPresent(BeanConverter.class))
						 beanToXML(f.get(bean), rootElement);	
					 else if(f.isAnnotationPresent(BeanListConverter.class)){
						 Element childElement = rootElement.addElement(f.getName());
						 List fList =(List) invokeGet(f, bean);
						 for(Object o:fList){
							  Element element = childElement.addElement(o.getClass().getSimpleName());
							  beanToXML(o, element);
						 }
					 }
					 else
						 fieldToXML(bean, f, rootElement);
				}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void fieldToXML(Object bean,Field f,Element rootElement){
		rootElement.addElement(f.getName()).addText(invokeGet(f, bean).toString());
	}
	
	private void listToXML(Object bean,Field f,Element rootElement){
		List dataList = (List) invokeGet(f, bean);
		Element listElement = rootElement.addElement(f.getName());
		for(Object o:dataList) {
			listElement.addElement(f.getName()).addText(o.toString());
		}
	}
	
	private void mapToXML(Object bean,Field f,Element rootElement) {
		Map dataMap = (Map) invokeGet(f, bean);		
		Element mapElement = rootElement.addElement(f.getName());
		for(Object k:dataMap.keySet()) {
			mapElement.addElement(k.toString()).addText(dataMap.get(k).toString());
		}
	}

	@Override
	public String parse(Object bean) {
		
		TimerUtil timer = new TimerUtil();
		
		Document doc = DocumentHelper.createDocument();
		// root element
		Element rootElement = doc.addElement(bean.getClass().getSimpleName());
		beanToXML(bean, rootElement);	
		
		String xml = doc.asXML();
		
		timer.stop();
		
		logger.debug("JPars XML Result: " + xml);
		logger.debug("JPars XML Parsing Time: "
				+ timer.getDiff(TimeUnit.MILLISECONDS));
		
		return xml;
	}

	
}
