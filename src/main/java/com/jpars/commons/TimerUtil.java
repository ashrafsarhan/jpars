/**
 * 
 */
package com.jpars.commons;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author ashraf_sarhan
 *
 */
public class TimerUtil {

	public static final long NANO_TO_MILLI = 1000000;
	public static final long MILLI_TO_SECONDS = 1000;
	public static final long SECONDS_TO_MIN = 60;
	public static final long MIN_TO_HOUR = 60;
	public static final long HOUR_TO_DAY = 24;

	private long start = -1;
	private long end = -1;

	public TimerUtil()
	{
		this.start();
	}

	public long getStart()
	{
		return start;
	}

	public long getEnd()
	{
		return end;
	}

	public void setStart(long start)
	{
		this.start = start;
	}

	public void setEnd(long end)
	{
		this.end = end;
	}

	public void start()
	{
		this.start = System.nanoTime();
	}

	public void stop()
	{
		this.end = System.nanoTime();
	}

	public void reset()
	{
		this.start = System.nanoTime();
		this.end = -1;
	}

	public String printDiff(TimeUnit unit)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(Long.toString(getDiff(unit)));

		return addUnits(sb.toString(), unit);
	}

	public String printDiffPrecise(TimeUnit unit)
	{
		StringBuilder sb = new StringBuilder();

		double d = getDiffPrecise(unit);
		NumberFormat nf = new DecimalFormat("#0.###");

		sb.append(nf.format(d));

		return addUnits(sb.toString(), unit);
	}

	private static String addUnits(String orig, TimeUnit unit)
	{
		StringBuilder sb = new StringBuilder();

		if (ValidationUtils.isNotNullAndEmpty(orig))
		{
			sb.append(orig);

			if (unit != null)
			{
				if (unit.equals(TimeUnit.SECONDS))
				{
					sb.append(" sec");
				}
				else if (unit.equals(TimeUnit.MILLISECONDS))
				{
					sb.append(" ms");
				}
				else if (unit.equals(TimeUnit.MINUTES))
				{
					sb.append(" mins");
				}
				else if (unit.equals(TimeUnit.HOURS))
				{
					sb.append(" hrs");
				}
				else
				{
					sb.append(" ns");
				}
			}
			else
			{
				sb.append(" ns");
			}
		}

		return sb.toString();
	}

	private static long getDiff(long input, TimeUnit unit)
	{
		if (input < 0)
		{
			input = 0;
		}

		if (unit != null)
		{
			if (unit.equals(TimeUnit.DAYS))
			{
				input = input
						/ (NANO_TO_MILLI * MILLI_TO_SECONDS * SECONDS_TO_MIN
								* MIN_TO_HOUR * HOUR_TO_DAY);
			}
			else if (unit.equals(TimeUnit.HOURS))
			{
				input = input
						/ (NANO_TO_MILLI * MILLI_TO_SECONDS * SECONDS_TO_MIN * MIN_TO_HOUR);
			}
			else if (unit.equals(TimeUnit.MINUTES))
			{
				input = input
						/ (NANO_TO_MILLI * MILLI_TO_SECONDS * SECONDS_TO_MIN);
			}
			else if (unit.equals(TimeUnit.SECONDS))
			{
				input = input / (NANO_TO_MILLI * MILLI_TO_SECONDS);
			}
			else if (unit.equals(TimeUnit.MILLISECONDS))
			{
				input = input / (NANO_TO_MILLI);
			}
		}

		return input;
	}

	private static long getMilliDiff(long input, TimeUnit unit)
	{
		if (input < 0)
		{
			input = 0;
		}

		if (unit != null)
		{
			if (unit.equals(TimeUnit.DAYS))
			{
				input = input
						/ (MILLI_TO_SECONDS * SECONDS_TO_MIN * MIN_TO_HOUR * HOUR_TO_DAY);
			}
			else if (unit.equals(TimeUnit.HOURS))
			{
				input = input
						/ (MILLI_TO_SECONDS * SECONDS_TO_MIN * MIN_TO_HOUR);
			}
			else if (unit.equals(TimeUnit.MINUTES))
			{
				input = input / (MILLI_TO_SECONDS * SECONDS_TO_MIN);
			}
			else if (unit.equals(TimeUnit.SECONDS))
			{
				input = input / (MILLI_TO_SECONDS);
			}
		}

		return input;
	}

	private static double getMilliDiffPrecise(long input, TimeUnit unit)
	{
		double transform = (double) input;

		if (transform < 0)
		{
			transform = 0.0;
		}

		if (unit != null)
		{
			if (unit.equals(TimeUnit.DAYS))
			{
				transform = transform
						/ (double) (MILLI_TO_SECONDS * SECONDS_TO_MIN
								* MIN_TO_HOUR * HOUR_TO_DAY);
			}
			else if (unit.equals(TimeUnit.HOURS))
			{
				transform = transform
						/ (double) (MILLI_TO_SECONDS * SECONDS_TO_MIN * MIN_TO_HOUR);
			}
			else if (unit.equals(TimeUnit.MINUTES))
			{
				transform = transform
						/ (double) (MILLI_TO_SECONDS * SECONDS_TO_MIN);
			}
			else if (unit.equals(TimeUnit.SECONDS))
			{
				transform = transform / (double) (MILLI_TO_SECONDS);
			}
		}

		return transform;
	}

	private static double getDiffPrecise(long input, TimeUnit unit)
	{
		double transform = input;

		if (transform < 0)
		{
			transform = 0.0;
		}

		if (unit != null)
		{

			if (unit.equals(TimeUnit.DAYS))
			{
				transform = transform
						/ (double) (NANO_TO_MILLI * MILLI_TO_SECONDS
								* SECONDS_TO_MIN * MIN_TO_HOUR * HOUR_TO_DAY);
			}
			else if (unit.equals(TimeUnit.HOURS))
			{
				transform = transform
						/ (double) (NANO_TO_MILLI * MILLI_TO_SECONDS
								* SECONDS_TO_MIN * MIN_TO_HOUR);
			}
			else if (unit.equals(TimeUnit.MINUTES))
			{
				transform = transform
						/ (double) (NANO_TO_MILLI * MILLI_TO_SECONDS * SECONDS_TO_MIN);
			}
			else if (unit.equals(TimeUnit.SECONDS))
			{
				transform = transform
						/ ((double) NANO_TO_MILLI * MILLI_TO_SECONDS);
			}
			else if (unit.equals(TimeUnit.MILLISECONDS))
			{
				transform = transform / (double) (NANO_TO_MILLI);
			}
		}
		return transform;
	}

	public long getDiff(TimeUnit unit)
	{
		this.stop();
		long input = end - start;

		return getDiff(input, unit);
	}

	public double getDiffPrecise(TimeUnit unit)
	{
		this.stop();
		long input = end - start;

		return getDiffPrecise(input, unit);

	}

	public static double convertTimes(long input, TimeUnit unit,
			TimeUnit toConvertUnit)
	{
		double result = 0;

		if (input > 0)
		{
			if (unit != null && toConvertUnit != null)
			{

				if (unit == toConvertUnit)
				{
					return (double) input;
				}

				if (TimeUnit.MILLISECONDS.equals(unit))
				{
					if (TimeUnit.SECONDS.equals(toConvertUnit))
					{
						result = input / MILLI_TO_SECONDS;
					}
					else
					{
						throw new IllegalArgumentException(
								"not yet implemented");
					}
				}
				else
				{
					throw new IllegalArgumentException("not yet implemented");
				}
			}
		}

		return result;
	}

	public static String printNanoConversion(long input, TimeUnit unit)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(Long.toString(getDiff(input, unit)));

		return addUnits(sb.toString(), unit);
	}

	public static String printMilliConversion(long input, TimeUnit unit)
	{
		StringBuilder sb = new StringBuilder();

		sb.append(Long.toString(getMilliDiff(input, unit)));

		return addUnits(sb.toString(), unit);
	}

	public static String printNanoConversionPrecise(long input, TimeUnit unit)
	{
		StringBuilder sb = new StringBuilder();

		double d = getDiffPrecise(input, unit);
		NumberFormat nf = new DecimalFormat("#0.###");

		sb.append(nf.format(d));

		return addUnits(sb.toString(), unit);
	}

	public static long getMilliConversion(long input, TimeUnit unit)
	{
		return getMilliDiff(input, unit);
	}

	public static double getMilliConversionPrecise(long input, TimeUnit unit)
	{
		return getMilliDiffPrecise(input, unit);
	}

	public static long getNanoConversion(long input, TimeUnit unit)
	{
		return getDiff(input, unit);
	}

	public static double getNanoConversionPrecise(long input, TimeUnit unit)
	{
		return getDiffPrecise(input, unit);
	}

	@Override
	public String toString()
	{
		return "TimerUtil [start=" + start + ", end=" + end + "]";
	}
}
