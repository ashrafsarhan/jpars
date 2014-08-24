/**
 * 
 */
package com.jpars.mock;

import java.util.List;

import com.jpars.parser.BeanConverter;
import com.jpars.parser.BeanListConverter;
import com.jpars.parser.FieldsOrder;

@BeanConverter
@FieldsOrder(fields={"status","partDetailResult"})
public class PartDetailResultWrapper {

	
	@BeanListConverter
	public List<PartSearchResultsDTO> partDetailResult;

	public String status;
	
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public List<PartSearchResultsDTO> getPartDetailResult() {
		return partDetailResult;
	}


	public void setPartDetailResult(List<PartSearchResultsDTO> partDetailResult) {
		this.partDetailResult = partDetailResult;
	}

	
}
