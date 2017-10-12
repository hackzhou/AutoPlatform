package com.auto.test.core.ui.code.log;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Log{
	private static final String LOG_FORMAT = "%s [main] %s %s - %s";
	private Logger logger = null;
	private StringBuffer sb = null;
	private SimpleDateFormat sdf = null;
	
	public Log(Class<?> c) {
		super();
		this.logger = LoggerFactory.getLogger(c);
		this.sb = new StringBuffer();
		this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
	}

	public void info(String msg){
		logger.info(msg);
		sb.append(String.format(LOG_FORMAT, sdf.format(new Date()), "INFO ", logger.getName(), msg)).append("\r\n");
	};

	public void debug(String msg){
		logger.info(msg);
		sb.append(String.format(LOG_FORMAT, sdf.format(new Date()), "DEBUG", logger.getName(), msg)).append("\r\n");
	};
	
	public void warn(String msg){
		logger.info(msg);
		sb.append(String.format(LOG_FORMAT, sdf.format(new Date()), "WARN ", logger.getName(), msg)).append("\r\n");
	};
	
	public void error(String msg){
		logger.error(msg);
		sb.append(String.format(LOG_FORMAT, sdf.format(new Date()), "ERROR", logger.getName(), msg)).append("\r\n");
	}

	@Override
	public String toString() {
		return sb.toString();
	};

}
