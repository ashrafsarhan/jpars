/**
 * 
 */
package com.jpars.test;

import org.junit.Assert;
import org.junit.Test;

import com.jpars.mock.DataGenerator;
import com.jpars.mock.PartDetailResultWrapper;
import com.jpars.parser.BasicBeanParser;
import com.jpars.parser.BeanParser;
import com.jpars.parser.ParseFormat;

/**
 * @author ashraf_sarhan
 * 
 */
public class JParsTest {

	@Test
	public void testJParsXML() {

		PartDetailResultWrapper wrapper = DataGenerator.prepareSimpleData();
		BasicBeanParser parser = BeanParser.CreateParser(ParseFormat.xml);
		String xml = parser.parse(wrapper);
		
		Assert.assertNotNull(">>>>>>>>>> Fail to parse XML <<<<<<<<<<", xml);
	}
	
	@Test
	public void testJParsJSON() {

		PartDetailResultWrapper wrapper = DataGenerator.prepareSimpleData();
		BasicBeanParser parser = BeanParser.CreateParser(ParseFormat.json);
		String json = parser.parse(wrapper);

		Assert.assertNotNull(">>>>>>>>>> Fail to parse JSON <<<<<<<<<<", json);
	}

}
