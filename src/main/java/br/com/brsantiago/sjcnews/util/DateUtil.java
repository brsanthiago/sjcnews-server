package br.com.brsantiago.sjcnews.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
	static String formatRfc2822 = "EEE, dd MMM yyyy HH:mm:ss Z";
	static String formatLocal = "EEE, d MMM yyyy";
	private static String date;

	public static String getCurrDate() {
		date = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss").format(new Date());
		return date;
	}

	// converts rss publish date into a readable format
	public static String getDate(final String pubDate) throws ParseException {
		Date date = getDateObj(pubDate);
		return new SimpleDateFormat(formatLocal).format(date);
	}

	// get Date object from pub date
	public static Date getDateObj(final String pubDate) throws ParseException {
		SimpleDateFormat pubDateFormat = new SimpleDateFormat(formatRfc2822,
				Locale.ENGLISH); // rss spec
		Date date = null;
		try {
			date = pubDateFormat.parse(pubDate);
		} catch (ParseException e) {
			pubDateFormat = new SimpleDateFormat(formatLocal); // fallback
			date = pubDateFormat.parse(pubDate);
		}
		return date;
	}
}
