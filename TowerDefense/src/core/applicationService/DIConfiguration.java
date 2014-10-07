package core.applicationService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import Infrastructure.loggin.Log4jLogger;
import core.contract.ITDLogger;

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