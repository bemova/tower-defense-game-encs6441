package core.applicationService;

/**
 * this demo was implemented as aDemo for dependecy injection and IOC for the build2
 * @author mojtaba
 * @version 1.0
 */
public interface IAplication {

	/**
	 * Process message.
	 *
	 * @param msg the msg
	 * @param rec the rec
	 * @return true, if successful
	 */
	public boolean processMessage(String msg, String rec);

}