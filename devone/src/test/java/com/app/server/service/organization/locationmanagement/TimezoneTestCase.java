package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Timezone;
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
public class TimezoneTestCase extends EntityTestCriteria {

    /**
     * TimezoneRepository Variable
     */
    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

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

    private Timezone createTimezone(Boolean isSave) throws Exception {
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("qjfbSmBjUeCmfWs5rpi0iWIvICVklZCnhFUBkekbKB02BveosP");
        timezone.setCities("1aqfszGO72tP9xOjgdhY1lZGJsqZaR7SlXJRYaocNGy9nzee9n");
        timezone.setGmtLabel("hyCrE303sEm8i1Y09ayKuEKNLbXdqqc2KEpURK1rMmbNeIzWCk");
        timezone.setUtcdifference(1);
        timezone.setCountry("mNQIzJm6FuvBYrpMwuPOvDCZOrhGVyhy3lEIWAWrx3h6n9Ok9g");
        timezone.setEntityValidator(entityValidator);
        return timezone;
    }

    @Test
    public void test1Save() {
        try {
            Timezone timezone = createTimezone(true);
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            timezone.isValid();
            timezoneRepository.save(timezone);
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            Timezone timezone = timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
            timezone.setVersionId(1);
            timezone.setTimeZoneLabel("R8p0AhR0rXd9SdlBtUSZ1yC69yaa2nNOueFVYwiFI8PGF1QoJA");
            timezone.setCities("yEz5IcaDWwuHtAy6UZg7fguGIteQ6sb3ozzu1lNEl6XdnSaBv9");
            timezone.setGmtLabel("q8KmOJLh904m69bFdIg7nAuRI5rlxskuVzx3uag2pwA3kJqO2A");
            timezone.setUtcdifference(10);
            timezone.setCountry("7MHCYnpZdBGcclh279ENOm7fwFGOnlyCUox1NQpWJ5Mz0TWiVq");
            timezone.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            timezoneRepository.update(timezone);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.findById((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("TimezonePrimaryKey"));
            timezoneRepository.remove((java.lang.String) map.get("TimezonePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateTimezone(EntityTestCriteria contraints, Timezone timezone) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            timezone.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            timezone.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            timezoneRepository.save(timezone);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "utcdifference", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "utcdifference", 19));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "gmtLabel", "aweO951JvEZ1J4kuWng9kgc4wdCTCwzKjMHKE1hrA21dLTna9wo7gMECHhpUzmZN5mZE9jaDglafv6lMJwnnD1aAJmYfiVJ1lLQnELX7Ml9HpthU3UsbiKAb3y2D9XQywJtSAmQJhUoq6Q73RsKkXE63clka0OIQvM0U2MPh5Vf93nNVW1HsBu1YYucLtPVrZbv6wgFXhVVFpLtkHxcmpQfMeyCXcms3UtYzWjSz8WNBMxvSFjqouOUf4Cbdk4dDd"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "timeZoneLabel", "16uOoArKUOXhJMh9OIRnO4J2qCzRUW3a5WJUqYfyaAfEZvIvQFkSALWvw0uD7pO5jxQ5Uqva9KAp2gz6Q5oSywdkDmMc2hScYPYzlGh7RBR2RhdNwYrzIKpxoe8Ao6cyX39E65KnpvrFv6BbqPrnrdqy7kXeLC2zOkbuO1YgkBArhJT3fVPhFnfjGPsaJQb35etgHYo1MOvUcZ8qah4hQCQ5bxCm0u42LeXU8UiEn5tqIh7sKIOW1QxBASxamRp27"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "country", "hcUknzPA4vF0DEwfoz7osfr5umajxeCFcLgUGNv2szLEjXBxmUZPBGfZILcBifg2LFW5e3xlgnZENckEcObWcVBTNNq9g8XpIBB2jGjOkGtmvjyCX4hVRu2p6P3zrzioJwAkdyPEA19FmsZ5mSy2vdlJj8XfUeGdnb3mE7I5LHvsMdSyTlUYZ6ynbGkIc0C90XnVkjY9Z5lHvzSH5HJdtv3HtmVaHEvD89OVUsUdSq33UvmzXu33XWtVlJF74I5bE"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cities", "Er9b1hmQDBqtBP9ij8mi5giEKmlYtJgr7WbCZh4t8SOcQ23oYdv28Xx7Tr2W72TmVoDVwNM3PCuCg9FwLoH1azk8CWEkzAM9n8y2E4TXTbkTUXCfZx6VDQ982AvAzPJvPg3DBJlOYu7p0vvtbOBwkfLySF5c4kjpYoMj6Dkgz7JrpFFPwNOjT5PuFsAFQnPHsVNXPcL40Eg1zgwsVRXBBxqjU0g4jfjyZnpS5vZiuxNvQgYqZUTXok0yzfrU5JkjU"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Timezone timezone = createTimezone(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = timezone.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(timezone, null);
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 2:
                        timezone.setUtcdifference(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 3:
                        timezone.setGmtLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 4:
                        timezone.setTimeZoneLabel(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 5:
                        timezone.setCountry(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
                        failureCount++;
                        break;
                    case 6:
                        timezone.setCities(contraints.getNegativeValue().toString());
                        validateTimezone(contraints, timezone);
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
