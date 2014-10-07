package Infrastructure.loggin;

import org.apache.log4j.Logger;

import core.contract.ITDLogger;

public class Log4jLogger implements ITDLogger {

	final static Logger logger = Logger.getLogger(Log4jLogger.class);
	@Override
	public void writer(Object message, Throwable t) {
		logger.error(message, t);
	}

}
