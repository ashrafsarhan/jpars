package com.jpars.mock;

import java.util.ArrayList;
import java.util.List;

import com.jpars.parser.BeanConverter;
import com.jpars.parser.BeanListConverter;
import com.jpars.parser.FieldsOrder;

@BeanConverter
@FieldsOrder(fields={"partStatus","lTBDate","source","counterFitsList"})
public class LifeCycleDto {
	
	
	public String partStatus;	
	
	public String lTBDate;
	
	public String source;
	
	@BeanListConverter
	public List<CounterFitDto> counterFitsList = new ArrayList<CounterFitDto>();

	
	public String getPartStatus() {
		return partStatus;
	}
	public void setPartStatus(String partStatus) {
		this.partStatus = partStatus;
	}
	public String getlTBDate() {
		return lTBDate;
	}
	public void setlTBDate(String lTBDate) {
		this.lTBDate = lTBDate;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}	
	
	public List<CounterFitDto> getCounterFitsList() {
		return counterFitsList;
	}
	public void setCounterFitsList(List<CounterFitDto> counterFitsList) {
		this.counterFitsList = counterFitsList;
	}
	
	

}
