/**
 * 
 */
package com.jpars.parser;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

import com.jpars.commons.TimerUtil;

/**
 * @author ashraf_sarhan
 *
 */
@SuppressWarnings("rawtypes")
public class BeanJsonParser extends BasicBeanParser {
	
	private static Logger logger = Logger.getLogger(BeanJsonParser.class.getSimpleName());
	
	private void beanToJson(Object bean,JSONObject parentObject) {
		Field[] fields = sortFields(bean.getClass().getFields(),bean);	
		JSONObject childJsonObject = new JSONObject();
		try{
				for(Field f:fields){
					 if(f.getType().getName().contains("List")&& !f.isAnnotationPresent(BeanListConverter.class)) {
						 listToJson(bean, f, childJsonObject);
					     parentObject.put(f.getName(), childJsonObject);
					 }	
					 else if(f.getType().getName().contains("Map")) {
						 mapToJson(bean, f, parentObject);
					 }
				     else if(f.getType().isAnnotationPresent(BeanConverter.class)) {
				    	 beanToJson(f.get(bean), childJsonObject);	
				    	 parentObject.put(f.getName(), childJsonObject);
				     }	
					 else if(f.isAnnotationPresent(BeanListConverter.class)){
						 List fList =(List) invokeGet(f, bean);
						 JSONArray childJsonObjects = new JSONArray();
						 for(Object o:fList){
							 JSONObject childJson = new JSONObject();
							 beanToJson(o, childJson);
							 childJsonObjects.put(childJson);
						 }
						 parentObject.put(f.getName(), childJsonObjects);
					 }
					 else
						 fieldToJson(bean, f, parentObject);
				}	
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	private void fieldToJson(Object bean,Field f,JSONObject parentObject) {
		parentObject.put(f.getName(), invokeGet(f, bean));
	}
	
	private void listToJson(Object bean,Field f,JSONObject parentObject){
		List dataList = (List) invokeGet(f, bean);
		JSONArray jsonArray = new JSONArray();
		for(Object o:dataList) {
			jsonArray.put(o);
		}	
		parentObject.put(f.getName(), jsonArray);
	}

	private void mapToJson(Object bean,Field f,JSONObject parentObject) {
		Map dataMap = (Map) invokeGet(f, bean);		
		JSONObject jsonObject = new JSONObject();
		for(Object k:dataMap.keySet()) {
			jsonObject.put((String) k, dataMap.get(k));
		}
		parentObject.put(f.getName(), jsonObject);
	}

	@Override
	public String parse(Object bean) {
		
		TimerUtil timer = new TimerUtil();
		
		JSONObject parentObject = new JSONObject();
		beanToJson(bean, parentObject);
		String json = parentObject.toString();
		
		timer.stop();
		
		logger.debug("JPars JSON Result: " + json);
		logger.debug("JPars JSON Parsing Time: "
				+ timer.getDiff(TimeUnit.MILLISECONDS));
		
		return json;
	}

}
