package com.bismay.app.finance.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * This is the application context initializer class used to activate the
 * profile based on environment.
 *
 */
public class ContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

	private static final Logger logger = LoggerFactory.getLogger(ContextInitializer.class);

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationContextInitializer#initialize(org.
	 * springframework.context.ConfigurableApplicationContext)
	 */
	public void initialize(ConfigurableApplicationContext applicationContext) {
		ConfigurableEnvironment applicationEnvironment = applicationContext.getEnvironment();

		try {
			final String platform = System.getenv("PLATFORM");
			if (platform != null || platform.equalsIgnoreCase("HEROKU")) {
				applicationEnvironment.addActiveProfile("heroku");
			}
		} catch (NullPointerException ne) {
			applicationEnvironment.addActiveProfile("local");
		}

	}

}