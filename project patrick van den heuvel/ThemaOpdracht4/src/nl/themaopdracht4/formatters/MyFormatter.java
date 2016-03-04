/*
 * Developer : Joris Rijkes (Joris_Rijkes@gmail.com)
 * Date : 19 mei 2013
 * All code (c)2013 Joris Rijkes inc. all rights reserved
 */

package nl.themaopdracht4.formatters;


import java.sql.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class MyFormatter extends Formatter {

	private static final String LINE_SEPARATOR = System.getProperty("line.separator");
	
	@Override
	public String format(LogRecord record) {
		StringBuilder sb = new StringBuilder();
		sb.append(new Date(record.getMillis()))
		.append(" ")
		.append(record.getLevel().getLocalizedName())
		.append(": ").append(formatMessage(record))
		.append(LINE_SEPARATOR);
				
	
	return sb.toString();			
	}

}
