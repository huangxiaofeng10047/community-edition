package org.alfresco.rest.api.tests;

public class EnterpriseTestApi extends AbstractTestApi
{
	@Override
	protected TestFixture getTestFixture() throws Exception
	{
		return EnterprisePublicApiTestFixture.getInstance();
	}

	@Override
	protected TestFixture getTestFixture(boolean createTestData) throws Exception
	{
		return EnterprisePublicApiTestFixture.getInstance(createTestData);
	}
}
