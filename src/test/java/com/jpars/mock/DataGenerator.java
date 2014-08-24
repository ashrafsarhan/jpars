/**
 * 
 */
package com.jpars.mock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ashraf_sarhan
 * 
 */
public class DataGenerator {

	public static PartDetailResultWrapper prepareHeavyLoadData() {

		PartDetailResultWrapper wrapper = new PartDetailResultWrapper();
		wrapper.setStatus("Successful Operation");
		PartSearchResultsDTO partSearchResultDTO = null;
		List<PartSearchResultsDTO> partSearchResultsDTOs = new ArrayList<PartSearchResultsDTO>();

		for (int i = 0; i < 100; i++) {
			partSearchResultDTO = new PartSearchResultsDTO();
			partSearchResultDTO.setFranchisedInventoryData(getFranData());
			partSearchResultDTO.setLifeCycleData(getLCData());
			partSearchResultsDTOs.add(partSearchResultDTO);
		}

		wrapper.setPartDetailResult(partSearchResultsDTOs);

		return wrapper;
	}

	public static PartDetailResultWrapper prepareSimpleData() {

		PartDetailResultWrapper wrapper = new PartDetailResultWrapper();
		wrapper.setStatus("Successful Operation");
		PartSearchResultsDTO partSearchResultDTO = null;
		List<PartSearchResultsDTO> partSearchResultsDTOs = new ArrayList<PartSearchResultsDTO>();

		for (int i = 0; i < 2; i++) {
			partSearchResultDTO = new PartSearchResultsDTO();
			partSearchResultDTO.setFranchisedInventoryData(getFranData());
			partSearchResultDTO.setLifeCycleData(getLCData());
			partSearchResultsDTOs.add(partSearchResultDTO);
		}

		wrapper.setPartDetailResult(partSearchResultsDTOs);

		return wrapper;
	}

	private static List<FranchisedInventoryDto> getFranData() {

		List<FranchisedInventoryDto> franchisedInventoryData = new ArrayList<FranchisedInventoryDto>();
		FranchisedInventoryDto franchisedInventoryDto = null;
		Map<String, String> features = null;

		for (int i = 0; i < 1; i++) {
			franchisedInventoryDto = new FranchisedInventoryDto();
			features = new LinkedHashMap<String, String>();
			features.put("Quantity", "10");
			features.put("BuyNowLink", "http://buynow.com");
			features.put("Distributor", "Vishay");
			franchisedInventoryDto.setFeatures(features);
			franchisedInventoryData.add(franchisedInventoryDto);
		}

		return franchisedInventoryData;
	}

	private static List<CounterFitDto> getFitData() {
		List<CounterFitDto> counterFitsList = new ArrayList<CounterFitDto>();
		CounterFitDto counterFitDto = null;

		for (int i = 0; i < 1; i++) {
			counterFitDto = new CounterFitDto();
			counterFitDto.setCounterfitMethods("method");
			counterFitDto.setDescription("desc");
			counterFitDto.setMpn("bav99");
			counterFitDto.setNotificationDate("16-2-2014");
			counterFitDto.setSource("http://counter.pdf");
			counterFitDto.setSupplier("Rohm");
			counterFitsList.add(counterFitDto);
		}

		return counterFitsList;
	}

	private static LifeCycleDto getLCData() {
		LifeCycleDto lifeCycleDto = new LifeCycleDto();

		lifeCycleDto.setPartStatus("active");
		lifeCycleDto.setSource("http://lcSource.pdf");
		lifeCycleDto.setlTBDate("16-2-2014");
		lifeCycleDto.setCounterFitsList(getFitData());

		return lifeCycleDto;
	}

}
