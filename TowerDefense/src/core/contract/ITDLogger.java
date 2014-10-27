package core.contract;

// TODO: Auto-generated Javadoc
/**
 * The Interface ITDLogger.
 */
public interface ITDLogger {
	
	/**
	 * Writer  interface for logging this interface will be used for IOC and dependecy injection
	 *
	 * @param message the message
	 * @param t the t
	 */
	public void writer(Object message, Throwable t);

}
