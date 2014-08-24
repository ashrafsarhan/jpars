/**
 * 
 */
package com.jpars.commons;
import java.text.Normalizer;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.regex.Pattern;
/**
 * @author ashraf_sarhan
 *
 */
public class ValidationUtils {

	public static final Pattern SEARCH_PATTERN = Pattern
			.compile("^[a-zA-Z0-9\\s.\\-\\^\\&\\*\\%\\(\\)\\_\\/\\@,\\:\\#\\+\\!\\~\\|\"]+$");

	public static final String POST_CHECKBOX_ON = "on";
	public static final String URL_DELIMITER = "/";

	public ValidationUtils()
	{
		super();
	}

	@Deprecated
	public static List<String> getContextParams(String requestContext)
	{
		return StringUtil.getContextParams(requestContext);
	}

	@Deprecated
	public static List<String> getPathParams(String requestPath)
	{
		return StringUtil.getPathParams(requestPath);
	}

	@Deprecated
	public static List<String> expandStringDelimited(String input, String delim)
	{
		return StringUtil.expandStringDelimited(input, delim);
	}

	@Deprecated
	public static Queue<String> expandStringDelimitedToFIFO(String input,
			String delim)
	{
		return StringUtil.expandStringDelimitedToFIFO(input, delim);
	}

	@Deprecated
	public static Stack<String> expandStringDelimitedToFILO(String input,
			String delim)
	{
		return StringUtil.expandStringDelimitedToFILO(input, delim);
	}

	/**
	 * Validates the input based on the pattern passed in
	 * 
	 * @param input
	 * @param pattern
	 * @return
	 */
	public static boolean validateString(String input, Pattern pattern)
	{
		if (input != null)
		{
			if (!StringUtil.isEmpty(input))
			{
				// we're gonna give it a pattern to validate against
				String canonical = canonicalize(input);
				if (pattern.matcher(canonical).matches())
				{
					return true;
				}
			}
		}

		return false;
	}

	/**
	 * Checks to see if the checkbox is selected.
	 * 
	 * @param input
	 * @return
	 */
	public static boolean isSelectedCheckbox(String input)
	{
		if (input != null)
		{
			if (input.equals(POST_CHECKBOX_ON))
			{
				return true;
			}
		}

		// if the string is null, it means that the param was never passed,
		// which means its not checked
		return false;
	}

	/**
	 * validate that a particular string is a number
	 * 
	 * @param input
	 * @return
	 * @throws NumberFormatException
	 */
	public static int validateNumber(String input) throws NumberFormatException
	{
		if (input != null)
		{
			if (!StringUtil.isEmpty(input))
			{
				return Integer.parseInt(input);
			}
		}

		throw new NumberFormatException("invalid number");
	}

	// Simplifies input to its simplest form to make encoding tricks more
	// difficult

	/**
	 * Normalize the string so that we can perform basic whitelist checks
	 * 
	 * @param input
	 * @return
	 */
	public static String canonicalize(String input)
	{
		String canonical = Normalizer.normalize(input, Normalizer.Form.NFD);
		return canonical;
	}

	/**
	 * creates a delimited string from a list of strings
	 * 
	 * @param input
	 * @param delimiter
	 * @return
	 */
	@Deprecated
	public static String createDelimitedString(List<String> input,
			String delimiter)
	{
		return StringUtil.createDelimitedString(input, delimiter);
	}

	@Deprecated
	public static String createDelimitedStringUsingElement(String element,
			String delimiter, int number)
	{
		return StringUtil.createDelimitedStringUsingElement(element, delimiter,
				number);
	}

	public static boolean isNotNullAndEmpty(String s)
	{
		if (s != null && !StringUtil.isEmpty(s))
		{
			return true;
		}

		return false;
	}
	
	public static boolean isEmptyStringAfterTrim(String s) {

		if (s != null)
		{
			s.trim();
			if (!StringUtil.isEmpty(s)) {
				return true;	
			} 

		}
		return false;
	}

	public static boolean isNotNullAndEmptyAfterTrim(String s)
	{
		if (s != null && !StringUtil.isEmpty(s))
		{
			String st = s.trim();
			if (st != null && !StringUtil.isEmpty(st))
			{
				return true;
			}
		}

		return false;
	}

	public static boolean isNotNullAndEmpty(List<?> myList)
	{
		if (myList != null && !myList.isEmpty())
		{
			return true;
		}

		return false;
	}

	public static boolean isNotNullAndEmpty(Stack<?> myStack)
	{
		if (myStack != null && !myStack.isEmpty())
		{
			return true;
		}

		return false;
	}

	public static boolean isNotNullAndEmpty(Queue<?> myQueue)
	{
		if (myQueue != null && !myQueue.isEmpty())
		{
			return true;
		}

		return false;
	}

	public static boolean isNotNullAndEmpty(Map<?, ?> myMap)
	{
		if (myMap != null && !myMap.isEmpty())
		{
			return true;
		}

		return false;
	}

	public static boolean isNotNullAndEmpty(Set<?> mySet)
	{
		if (mySet != null && !mySet.isEmpty())
		{
			return true;
		}

		return false;
	}

	// returning an empty string is safer than null
	@Deprecated
	public static String truncateString(String input, int maxSize)
	{
		return StringUtil.truncateString(input, maxSize);
	}

	@Deprecated
	public static String surroundString(String input, String delimiter)
	{
		return StringUtil.surroundString(input, delimiter);
	}

}
