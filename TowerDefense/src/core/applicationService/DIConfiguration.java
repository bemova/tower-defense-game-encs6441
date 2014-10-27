package core.applicationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import Infrastructure.loggin.Log4jLogger;
import core.contract.ITDLogger;
/**
 * this demo was implemented as aDemo for dependecy injection and IOC for the build2
 * @author mojtaba
 * @version 1.0
 */
@Configuration
@ComponentScan(value={"core.applicationService"})
public class DIConfiguration {

	/**
	 * Gets the message service.
	 *
	 * @return the message service
	 */
	@Bean
	public ITDLogger getMessageService(){
		return new Log4jLogger();
	}
}