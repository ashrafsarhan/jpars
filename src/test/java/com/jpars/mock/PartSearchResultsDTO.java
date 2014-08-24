package com.jpars.mock;

import java.util.List;

import com.jpars.parser.BeanConverter;
import com.jpars.parser.BeanListConverter;
import com.jpars.parser.FieldsOrder;

@BeanConverter
@FieldsOrder(fields={"lifeCycleData","franchisedInventoryData"})
public class PartSearchResultsDTO
{
	
	@BeanListConverter
    public List<FranchisedInventoryDto> franchisedInventoryData;
	
    public LifeCycleDto lifeCycleData;	
	
	public List<FranchisedInventoryDto> getFranchisedInventoryData() {
		return franchisedInventoryData;
	}
	public void setFranchisedInventoryData(
			List<FranchisedInventoryDto> franchisedInventoryData) {
		this.franchisedInventoryData = franchisedInventoryData;
	}
	public LifeCycleDto getLifeCycleData() {
		return lifeCycleData;
	}
	public void setLifeCycleData(LifeCycleDto lifeCycleData) {
		this.lifeCycleData = lifeCycleData;
	}    
	    
	
}
