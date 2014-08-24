package com.jpars.mock;

import javax.xml.bind.annotation.XmlElement;

import com.jpars.parser.BeanConverter;
import com.jpars.parser.FieldsOrder;


@SuppressWarnings("restriction")
@BeanConverter
@FieldsOrder(fields={"mpn","description","notificationDate","counterfitMethods"})
public class CounterFitDto {
	
	public String counterfitMethods;		
	
	public String description;	
	
	public String mpn;		
	
	public String notificationDate;
	
	public String getCounterfitMethods() {
		return counterfitMethods;
	}
	public void setCounterfitMethods(String counterfitMethods) {
		this.counterfitMethods = counterfitMethods;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMpn() {
		return mpn;
	}
	public void setMpn(String mpn) {
		this.mpn = mpn;
	}
	public String getNotificationDate() {
		return notificationDate;
	}
	public void setNotificationDate(String notificationDate) {
		this.notificationDate = notificationDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	@XmlElement(name="Source")
	private String source;		
	@XmlElement(name="Supplier")
	private String supplier;
	

}
