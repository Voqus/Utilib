package com.utilib.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger
{
	private static volatile Logger _logger = null;
	
	private Logger()
	{}
	
	/**
	 * @return an instance of this class.
	 */
	public static Logger getInstance()
	{
		if (_logger == null)
		{
			synchronized (Logger.class)
			{
				if (_logger == null)
					_logger = new Logger();
			}
		}
		
		return _logger;
	}
	
	/**
	 * Logs the message to the screen.
	 * 
	 * @param logLevel
	 * @param message
	 */
	public void log(final LogLevel logLevel, final String message)
	{
		// Setting up the time stamp format
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		String timestamp = sdf.format(new Date());
		
		switch (logLevel)
		{
			case NORMAL:
				System.out.println("[" + timestamp + "] : " + message);
				break;
			case FATAL:
				System.err.println("[" + timestamp + "] : " + message);
				break;
			case WARNING:
				break;
			default:
				break;
		}
	}
	
}
