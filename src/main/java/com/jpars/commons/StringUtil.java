/**
 * 
 */
package com.jpars.commons;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import org.apache.log4j.Logger;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * @author ashraf_sarhan
 *
 */
public class StringUtil {
	
	private static Logger logger = Logger.getLogger(StringUtil.class.getSimpleName());
	
	public static final String YES = "y";
	public static final String NO = "n";

	public static final String TRUE_CHAR = "t";
	public static final String FALSE_CHAR = "f";

	public static final String TRUE = "true";
	public static final String FALSE = "false";

	public static final String ON = "on";
	public static final String OFF = "off";

	public static final String YES_FULL = "yes";
	public static final String NO_FULL = "no";

	public static final String URL_DELIMITER = "/";

	public static final Pattern SEARCH_PATTERN = Pattern
			.compile("^[a-zA-Z0-9\\s.\\-\\^\\&\\*\\%\\(\\)\\_\\/\\@,\\:\\#\\+\\!\\~\\|\"]+$");

	public static enum BOOLEAN_STRING_TYPE
	{
		Y_N, YES_NO, TRUE_FALSE, T_F, ON_OFF
	};

	public StringUtil()
	{

	}

	/**
	 * return true if matches yes, y, true, on else returns false for everything
	 * else
	 * 
	 * @param input
	 * @return
	 */
	public static boolean convertStringToBoolean(String input)
	{
		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			if (YES.equalsIgnoreCase(input) || YES_FULL.equalsIgnoreCase(input)
					|| TRUE.equalsIgnoreCase(input)
					|| TRUE_CHAR.equalsIgnoreCase(input)
					|| ON.equalsIgnoreCase(input))
			{
				return true;
			}
		}

		return false;
	}

	public static String convertBooleanToString(boolean enable)
	{
		return convertBooleanToString(enable, BOOLEAN_STRING_TYPE.Y_N);
	}

	/**
	 * convert a boolean to a string default is true or false
	 * 
	 * @param enable
	 * @param type
	 * @return
	 */
	public static String convertBooleanToString(boolean enable,
			BOOLEAN_STRING_TYPE type)
	{
		String result = "";

		if (type != null)
		{
			switch (type)
			{
			case Y_N:
				if (enable)
				{
					result = YES;
				}
				else
				{
					result = NO;
				}
				break;
			case YES_NO:
				if (enable)
				{
					result = YES_FULL;
				}
				else
				{
					result = NO_FULL;
				}
				break;
			case ON_OFF:
				if (enable)
				{
					result = ON;
				}
				else
				{
					result = OFF;
				}
				break;

			case T_F:
				if (enable)
				{
					result = TRUE_CHAR;
				}
				else
				{
					result = FALSE_CHAR;
				}
				break;
			case TRUE_FALSE:
			default:
				if (enable)
				{
					result = TRUE;
				}
				else
				{
					result = FALSE;
				}
			}
		}

		return result;
	}

	public static List<String> covertUrlPathParams(String input,
			boolean useLower, boolean validate)
	{
		// so on the path we'll do the string tokenizing
		List<String> params = new ArrayList<String>();

		// check for null path
		// for example /part/search
		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			String workingModel = input;

			if (useLower)
			{
				workingModel = input.toLowerCase();
			}

			String[] st = workingModel.split(Pattern.quote(URL_DELIMITER));

			for (String tString : st)
			{
				if (ValidationUtils.isNotNullAndEmpty(tString))
				{
					if (validate)
					{
						String canString = ValidationUtils.canonicalize(tString
								.trim());
						if (ValidationUtils.validateString(canString,
								SEARCH_PATTERN))
						{
							params.add(canString);
						}
					}
					else
					{
						params.add(tString);
					}
				}
			}
		}

		return params;
	}

	@Deprecated
	public static List<String> getContextParams(String requestContext)
	{
		return covertUrlPathParams(requestContext, true, true);
	}

	@Deprecated
	public static List<String> getPathParams(String requestPath)
	{
		return covertUrlPathParams(requestPath, false, true);
	}

	public static List<String> expandStringDelimited(String input, String delim)
	{
		List<String> myStrings = new ArrayList<String>();

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			String[] splitParams = input.split(delim);
			if (splitParams != null && splitParams.length >= 1)
			{
				for (String s : splitParams)
				{
					if (ValidationUtils.isNotNullAndEmpty(s))
					{
						myStrings.add(s);
					}
				}
			}
		}

		return myStrings;
	}

	public static Queue<String> expandStringDelimitedToFIFO(String input,
			String delim)
	{
		Queue<String> myStrings = new LinkedList<String>();

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			String[] splitParams = input.split(delim);
			if (splitParams != null && splitParams.length >= 1)
			{
				for (String s : splitParams)
				{
					if (ValidationUtils.isNotNullAndEmpty(s))
					{
						myStrings.add(s);
					}
				}
			}
		}

		return myStrings;
	}

	public static Stack<String> expandStringDelimitedToFILO(String input,
			String delim)
	{
		Stack<String> myStrings = new Stack<String>();

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			String[] splitParams = input.split(delim);
			if (splitParams != null && splitParams.length >= 1)
			{
				for (String s : splitParams)
				{
					if (ValidationUtils.isNotNullAndEmpty(s))
					{
						myStrings.push(s);
					}
				}
			}
		}

		return myStrings;
	}

	/**
	 * creates a delimited string from a list of strings
	 * 
	 * @param input
	 * @param delimiter
	 * @return
	 */
	public static String createDelimitedString(List<String> input,
			String delimiter)
	{
		StringBuilder sb = new StringBuilder();

		boolean first = true;
		for (String s : input)
		{
			if (first)
			{
				sb.append(s);
				first = false;
			}
			else
			{
				sb.append(delimiter);
				sb.append(s);
			}
		}

		return sb.toString();
	}

	public static String createDelimitedStringUsingElement(String element,
			String delimiter, int number)
	{
		StringBuilder sb = new StringBuilder();

		if (number > 0 && ValidationUtils.isNotNullAndEmpty(element))
		{

			boolean first = true;
			for (int i = 0; i < number; i++)
			{
				if (first)
				{
					sb.append(element);
					first = false;
				}
				else
				{
					sb.append(delimiter);
					sb.append(element);
				}
			}
		}
		return sb.toString();
	}

	// returning an empty string is safer than null
	public static String truncateString(String input, int maxSize)
	{
		String result = "";

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			if (input.length() > maxSize)
			{
				result = input.substring(0, maxSize);
			}
			else
			{
				result = input;
			}
		}

		return result;
	}

	public static String surroundString(String input, String delimiter)
	{
		StringBuilder sb = new StringBuilder();
		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			sb.append(delimiter);
			sb.append(input);
			sb.append(delimiter);
		}
		return sb.toString();
	}

	public static String compress(String str)
	{
		StringBuilder sb = new StringBuilder();

		if (ValidationUtils.isNotNullAndEmpty(str))
		{
			logger.info("String length : " + str.length());

			try
			{
				ByteArrayOutputStream baos = new ByteArrayOutputStream(
						str.length());
				GZIPOutputStream gzip = new GZIPOutputStream(baos);
				gzip.write(str.getBytes());
				gzip.close();

				sb.append(Base64.encode(baos.toByteArray()));
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			logger.info(
					"Output String length : " + sb.length());
		}

		return sb.toString();
	}

	public static String decompress(String str)
	{
		StringBuilder sb = new StringBuilder();

		if (ValidationUtils.isNotNullAndEmpty(str))
		{
			logger.info(
					"Input String length : " + str.length());
			try
			{
				byte[] base64data = Base64.decode(str);

				final int BUFFER_SIZE = 32;
				ByteArrayInputStream is = new ByteArrayInputStream(base64data);
				GZIPInputStream gis = new GZIPInputStream(is, BUFFER_SIZE);
				byte[] data = new byte[BUFFER_SIZE];
				int bytesRead;
				while ((bytesRead = gis.read(data)) != -1)
				{
					sb.append(new String(data, 0, bytesRead));
				}
				gis.close();
				is.close();
			}
			catch (Exception e)
			{
				// e.printStackTrace();
				logger.error("error decompressing: " + e);
			}

			logger.info(
					"Output String length : " + sb.length());
		}

		return sb.toString();
	}

	public static double convertStringToDouble(String input)
			throws Exception
	{
		double result = 0.0;

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			try
			{
				result = Double.parseDouble(input);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new Exception("error in converting: " + e);
			}
		}

		return result;
	}

	public static long convertStringToLong(String input)
			throws Exception
	{
		long result = 0;

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			try
			{
				result = Long.parseLong(input);
			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new Exception("error in converting: " + e);
			}
		}

		return result;
	}

	public static int convertStringToInt(String input)
			throws Exception
	{
		int result = 0;

		if (ValidationUtils.isNotNullAndEmpty(input))
		{
			try
			{
				result = Integer.parseInt(input);

			}
			catch (Exception e)
			{
				e.printStackTrace();
				throw new Exception("error in converting: " + e);
			}
		}

		return result;
	}
	
	public static String removeSpecialChar(String str) {
		
		String resultString = str.replaceAll("[^\\p{L}\\p{Nd}]", "");
		
		return resultString;
	}
	
	public static boolean isEmpty(String str) {
		
		if(str.length() == 0) {
			return true;
		} else {
			return false;
		}
		
	}

}
