package com.app.server.service.appbasicsetup.aaa;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.aaa.PublicApiRepository;
import com.app.shared.appbasicsetup.aaa.PublicApi;
import org.springframework.beans.factory.annotation.Autowired;
import com.athena.server.pluggable.utils.helper.RuntimeLogInfoHelper;
import com.athena.server.pluggable.utils.helper.EntityValidatorHelper;
import com.app.server.service.RandomValueGenerator;
import java.util.HashMap;
import java.util.List;
import com.spartan.healthmeter.entity.scheduler.ArtMethodCallStack;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.After;
import org.springframework.mock.web.MockServletContext;
import com.spartan.pluggable.logger.api.LogManagerFactory;
import com.athena.server.pluggable.utils.AppLoggerConstant;
import org.springframework.web.context.request.RequestContextHolder;
import com.spartan.pluggable.logger.event.RequestHeaderBean;
import com.spartan.pluggable.logger.api.RuntimeLogUserInfoBean;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.junit.Assert;
import com.athena.server.pluggable.interfaces.CommonEntityInterface.RECORD_TYPE;
import org.junit.Test;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class PublicApiTestCase extends EntityTestCriteria {

    /**
     * PublicApiRepository Variable
     */
    @Autowired
    private PublicApiRepository<PublicApi> publicapiRepository;

    /**
     * RuntimeLogInfoHelper Variable
     */
    @Autowired
    private RuntimeLogInfoHelper runtimeLogInfoHelper;

    /**
     * EntityValidator Variable
     */
    @Autowired
    private EntityValidatorHelper<Object> entityValidator;

    /**
     * RandomValueGenerator Variable
     */
    private RandomValueGenerator valueGenerator = new RandomValueGenerator();

    private static HashMap<String, Object> map = new HashMap<String, Object>();

    /**
     * List of EntityCriteria for negative Testing
     */
    private static List<EntityTestCriteria> entityConstraint;

    /**
     *  Variable to calculate health status
     */
    @Autowired
    private ArtMethodCallStack methodCallStack;

    /**
     * MockHttpSession Variable
     */
    protected MockHttpSession session;

    /**
     * MockHttpServletRequest Variable
     */
    protected MockHttpServletRequest request;

    /**
     * MockHttpServletResponse Variable
     */
    protected MockHttpServletResponse response;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final MockServletContext mockServletContext = new MockServletContext("file:src/main/webapp");
        try {
            final String _path = mockServletContext.getRealPath("/WEB-INF/conf/");
            LogManagerFactory.createLogManager(_path, AppLoggerConstant.LOGGER_ID);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @AfterClass
    public static void setUpAfterClass() throws Exception {
        map.clear();
        map = null;
    }

    protected void startSession() {
        session = new MockHttpSession();
    }

    protected void endSession() {
        session.clearAttributes();
        session.invalidate();
    }

    protected void startRequest() {
        request = new MockHttpServletRequest();
        request.setSession(session);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
    }

    protected void endRequest() {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).requestCompleted();
        RequestContextHolder.resetRequestAttributes();
    }

    @Before
    public void before() {
        startSession();
        startRequest();
        setBeans();
    }

    @After
    public void after() {
        endSession();
        endRequest();
    }

    private void setBeans() {
        runtimeLogInfoHelper.createRuntimeLogUserInfo("customer", "AAAAA", request.getRemoteHost());
        Assert.assertNotNull(runtimeLogInfoHelper);
        methodCallStack.setRequestId(java.util.UUID.randomUUID().toString().toUpperCase());
        entityConstraint = addingListOfFieldForNegativeTesting();
        runtimeLogInfoHelper.setRequestHeaderBean(new RequestHeaderBean(new RuntimeLogUserInfoBean("AAAA", "AAAA", request.getRemoteHost(), 0, 0, 0), "", methodCallStack.getRequestId()));
    }

    private PublicApi createPublicApi(Boolean isSave) throws Exception {
        PublicApi publicapi = new PublicApi();
        publicapi.setApiData("e2WrvfkXDmIFaQi81GAGqHdhfhkUDa5xb7eV1VaDqcu6qUsFkN");
        publicapi.setSchedulerDetails("RZcyZmR9A1TcFTHKo19AywqwHc9w1bkw86De9qP6rb769ZKQF1");
        publicapi.setEntityValidator(entityValidator);
        return publicapi;
    }

    @Test
    public void test1Save() {
        try {
            PublicApi publicapi = createPublicApi(true);
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            publicapi.isValid();
            publicapiRepository.save(publicapi);
            map.put("PublicApiPrimaryKey", publicapi._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            PublicApi publicapi = publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
            publicapi.setApiData("2zP2UCk5fiiMsUxKEhfhc6c1VxvjNNqaaK3HsQ0MTbze96ZJb9");
            publicapi.setSchedulerDetails("IXlB9oCx71nWwV3WynxerTV4QbEX9gsW7jbQCVh3jHTsPVu7oI");
            publicapi.setVersionId(1);
            publicapi.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            publicapiRepository.update(publicapi);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.findById((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PublicApiPrimaryKey"));
            publicapiRepository.remove((java.lang.String) map.get("PublicApiPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePublicApi(EntityTestCriteria contraints, PublicApi publicapi) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            publicapi.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            publicapiRepository.save(publicapi);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "apiData", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "apiData", "0lQvlTyJc3yKo4iJaITPLVKDwdaBsBmeIKW8covm3YbU7tJ8ZIdO5DNHfu2lnxdsuEqTLUJoM9uaUrKeUMlynAcxHJRhkiOSKyJeMhl9myPaodX7vW6OurkJOIOCnmd9hwjiEylOmkpjplfgCT4JSA8pDv6FCru9yA6K5IDk7Q97bTc32Io8LlZNVM4tuSdPwEXRlKUTRO8enkAdV3j3L7VWjJssznofklhcX3mQP9xlfBAXrUpjlI28IdaVwWkfl"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "schedulerDetails", "UrgZs1EyBMJZqCvqsptR9M863V3802jTkaoc2wNNXIYj3wLMmAI2A0a67Y7HmvwBpqsrAl2NYrH3OnFoiuejtm59LqfX85JKBvmwT67y46gJGPfEJ86F7610XA77s8hnNkXlDSMZ7Wseu8xRupKJLNsStCyTT0v1epH1DnFX6Dk6shlGfmbTDRql11TdJCHefuc8Lz4F0nXbyoxwO9E89KV4KOzbEfTPavFoX8vKR45tlfpKkxy84B987zFZEWtLJ"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PublicApi publicapi = createPublicApi(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = publicapi.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(publicapi, null);
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 2:
                        publicapi.setApiData(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                    case 3:
                        publicapi.setSchedulerDetails(contraints.getNegativeValue().toString());
                        validatePublicApi(contraints, publicapi);
                        failureCount++;
                        break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (failureCount > 0) {
            Assert.fail();
        }
    }
}
