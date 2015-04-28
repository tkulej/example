/*
 * [y] hybris Platform
 * 
 * Copyright (c) 2000-2015 hybris AG
 * All rights reserved.
 * 
 * This software is the confidential and proprietary information of hybris
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with hybris.
 */
package sap.com.api.generated;


import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.validation.ValidationFeature;

public abstract class AbstractResourceTest extends JerseyTest
{
	protected WebTarget getRootTarget(final String rootResource)
	{
		return client().target(getBaseUri()).path(rootResource);
	}

	@Override
	protected final Application configure()
	{
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);

		final ResourceConfig application = configureApplication();

		// needed for json serialization
		application.register(JacksonFeature.class);

		// bean validation
		application.register(ValidationFeature.class);

		// configure spring context
		application.property("contextConfigLocation", "classpath:/META-INF/applicationContext.xml");

		// disable bean validation for tests
		application.property(ServerProperties.BV_FEATURE_DISABLE, "true");
		return application;
	}

	protected abstract ResourceConfig configureApplication();

	@Override
	protected void configureClient(final ClientConfig config)
	{
		// needed for json serialization
		config.register(JacksonFeature.class);

		super.configureClient(config);
	}
}
