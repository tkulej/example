/*
 * Created with [y] hybris Service SDK version 3.9.0.
 */
package sap.com;

import com.hybris.api.console.web.ApiConsoleFeature;
import com.hybris.jersey.features.JsonFeature;
import com.hybris.jersey.features.JerseyFeature;
import com.hybris.jersey.features.SecurityFeature;
import com.hybris.jersey.features.BeanValidationFeature;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;

import sap.com.api.generated.ApiFeature;

/**
 * Defines the REST application.
 */
public class JerseyApplication extends ResourceConfig
{
	/**
	 * Initialized the jersey application.
	 */
	public JerseyApplication()
	{
		// enable error responses in JSON format
		register(JerseyFeature.class);

		// enable JSON support
		register(JsonFeature.class);

		// enable custom resources
		register(ApiFeature.class);

		// hybris-scopes support for @RolesAllowed
		register(SecurityFeature.class);

		// bean validation support
		register(BeanValidationFeature.class);
		
		// enable api-console
		register(ApiConsoleFeature.class);
		
		// log incoming requests
		register(LoggingFilter.class);
	}
}
