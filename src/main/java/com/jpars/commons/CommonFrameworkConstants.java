/**
 * 
 */
package com.jpars.commons;

/**
 * @author ashraf_sarhan
 *
 */
public class CommonFrameworkConstants {

	public static final String DELIMITER = "_";
	public static final String BANG_DELIMITER = "!";
	public static final String COMMA_DELIMITER = ",";
	public static final String PIPE_DELIMITER = "|";
	public static final String UNDERSCORE_DELIMITER = "_";
	public static final String PERIOD_DELIMITER = ".";

	public static final String OPEN_PARENTHESIS = "(";
	public static final String CLOSE_PARENTHESIS = ")";
	public static final String ADD_DELIMITER = "+";

	public static final String URL_EQUALS = "=";
	public static final String URL_AND = "&";
	public static final String URL_DELIMITER = "/";
	public static final String URL_QUERY = "?";
	public static final String URL_PORT_DELIMITER = ":";
	public static final String URL_HTTP_HEADER = "http://";
	public static final String URL_SECURE_HTTP_HEADER = "https://";
	public static final String URL_ENCODE = "UTF-8";

	// debug post params
	public static final String POST_DEBUG = "debug";
	public static final String POST_ENV = "env";
	public static final String POST_SECURITY_KEY = "skey";
	public static final String POST_RESET = "reset";
	public static final String POST_USE_STRICT_XML = "strict";

	// available languages
	public static final String LANG_ENGLISH = "en";
	public static final String LANG_GERMAN = "de";
	public static final String LANG_SPANISH = "es";
	public static final String LANG_CHINESE = "zh";

	// available currencies
	public static final String CURRENCY_US_DOLLARS = "usd";
	public static final String CURRENCY_EUROS = "eur";
	public static final String CURRENCY_YEN = "yen";

	// version
	public static final String URL_CONTEXT_VERSION_1 = "v1";
	public static final String URL_CONTEXT_VERSION_2 = "v2";

	public static final String URL_CONTEXT_VERSION_BASE = "v";
	public static final int API_VERSION_1 = 1;
	public static final int API_VERSION_2 = 2;
	public static final int API_VERSION_3 = 3;
	public static final int API_VERSION_4 = 4;
	public static final int API_VERSION_5 = 5;
	public static final int API_VERSION_6 = 6;
	public static final int API_VERSION_7 = 7;
	public static final int API_VERSION_8 = 8;
	public static final int API_VERSION_9 = 9;
	public static final int API_VERSION_10 = 10;

	public static final int INVALID_VERSION = -1;

	// language
	public static final String URL_CONTEXT_LANG_EN = "en";

	// response servlet
	public static final String FORMAT_TYPE_JSON = "json";
	public static final String FORMAT_TYPE_XML = "xml";

	public static final String XML_HEADER_STANDARD = "<?xml version=\"1.0\" encoding=\"utf-8\"?>";

	public static final String CONTENT_TYPE_JSONP = "text/javascript; chartset=UTF-8";
	public static final String CONTENT_TYPE_JSON = "application/json; charset=UTF-8";
	public static final String CONTENT_TYPE_XML = "text/xml; charset=UTF-8";

	public static final String X_RATELIMIT_LIMIT = "X-RateLimit-Limit";
	public static final String X_RATELIMIT_REMAINING = "X-RateLimit-Remaining";

	public static final String TAG_RESPONSE_STATUS = "response";
	public static final String ATTR_RESPONSE_STATUS_SUCCESS = "success";
	public static final String TAG_SERVICE_META_DATA = "serviceMetaData";
	public static final String TAG_TRANSACTION_AREA = "transactionArea";

	// fmt and callback
	public static final String PARAM_FORMAT = "fmt";
	public static final String PARAM_CALLBACKFN = "callbackfn";

	public static final String REQUEST_ERROR_STATUS = "errorstatusmsg";

	// used to standarize on environment passed as System.property
	public static final String ENVIRONMENT_KEY = "environment";
	public static final String ENVIRONMENT_DEVELOPMENT = "dev";
	public static final String ENVIRONMENT_QUALITY = "qa";
	public static final String ENVIRONMENT_PRODUCTION = "prod";

	public static final String ARROW_JVM_INSTNAME_KEY = "instname";

	public static final String PROPERTY_NUM_LISTENER_TASKS = "listener.tasks";
	public static final String PROPERTY_NUM_BACKGROUND_TASKS = "background.tasks";
	public static final String PROPERTY_API_USER_TTL = "apiuser.ttl";

	public static final long DEFAULT_TTL = 1800000;

	private CommonFrameworkConstants()
	{
		throw new AssertionError();
	}

}
