/**
 * 
 */
package com.jpars.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author ashraf_sarhan
 * 
 */
public abstract class BasicBeanParser {
	
	public abstract String parse(Object bean);


	protected Object invokeGet(Field f, Object object) {
		try {
			Method[] methods = object.getClass().getMethods();
			for (Method m : methods)
				if (m.getName().equalsIgnoreCase("get" + f.getName()))
					return m.invoke(object, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	
	protected Field[] sortFields(Field[] fields, Object bean) {

		if (!bean.getClass().isAnnotationPresent(FieldsOrder.class))
			return fields;

		Field[] sortedFields = new Field[fields.length];
		Annotation annotation = bean.getClass()
				.getAnnotation(FieldsOrder.class);
		FieldsOrder fieldsOrder = (FieldsOrder) annotation;
		String[] orders = fieldsOrder.fields();

		for (int o = 0; o < orders.length; o++)
			for (int i = 0; i < fields.length; i++) {

				if (orders[o].equalsIgnoreCase(fields[i].getName())) {
					sortedFields[o] = fields[i];
				}
			}

		return sortedFields;
	}

}
