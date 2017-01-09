package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
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
public class UserAccessLevelTestCase extends EntityTestCriteria {

    /**
     * UserAccessLevelRepository Variable
     */
    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

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

    private UserAccessLevel createUserAccessLevel(Boolean isSave) throws Exception {
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("y3M5dIMtNGcTelrweJjBtn7EoWyw37NMPc24c5wnw4TBYaTkm7");
        useraccesslevel.setLevelHelp("wVnJ33jAbnNHAVt1i2JYrKZBjcQ522xg1cwmhYAdK40S8rZOdL");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("5nZ64WzqnVkBVKHEMZr1CeuNKC94d7TrTz2DZXWKTfVD3D3qnt");
        useraccesslevel.setLevelIcon("Zg5XwzjqVwnmRLduN29HM6tmJZgu05fhSQU5LbOWWrwNAwcPip");
        useraccesslevel.setEntityValidator(entityValidator);
        return useraccesslevel;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessLevel useraccesslevel = createUserAccessLevel(true);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccesslevel.isValid();
            useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            UserAccessLevel useraccesslevel = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            useraccesslevel.setLevelDescription("PJCnyVnJz67rKKsdK5cjtdVeFBjLFFDAG7dxvLBrs9AJC4LYn7");
            useraccesslevel.setLevelHelp("Dz8samvqBsibQce4zxv5VaZmM2lJXEhoH0vc01nPXt2upEDGmM");
            useraccesslevel.setUserAccessLevel(85382);
            useraccesslevel.setLevelName("eNXjoE2KTkqWo8ZHIauyuUFU2hY23dZCQkZ4MrUx2JBRwhjgHN");
            useraccesslevel.setLevelIcon("7pB5wT4RSQb5iAUW258OIQrtegwn4w603QWthKZfXjGxAuvEAt");
            useraccesslevel.setVersionId(1);
            useraccesslevel.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccesslevelRepository.update(useraccesslevel);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessLevelPrimaryKey"));
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessLevel(EntityTestCriteria contraints, UserAccessLevel useraccesslevel) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccesslevel.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccesslevelRepository.save(useraccesslevel);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessLevel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessLevel", 166672));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "levelName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "levelName", "FzNORpLHxGEcL6keB0lkAm3ULFESSqEaNNbxoPkgBQWGNSyLxVbRZFizoScd6blC2ALXVyponfQkSER8DxVW7pES9yRIdcyUGXGdK2EUyBOHS8JOhwQAFkyZKH20o8KzF4Le0uQGXIrC8jSZgmTlAxyVVcCNcYdrGp6HmZtsQsnRxPFfHpjQvY2T7BEd7pmOWwoaJuzmvTH1ZV4cTZJxYAIphL9DNE5qR1zbrCTzgbZ3nILJMSE8JXiLr8sItswCn"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "levelDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "levelDescription", "GDv94G0QUPnKuEWgIOdVuEFHQRVLsyFGRuPgRkFb6cHlEpo5B8sztZgbrTUXROXyQNCKFgEbm4oaiHASZ17Qw3ZXKRhnCiX8l2oWxQYrwgHA92TlVYtFCioy373nrrtydjNpzmXfTEE8GAsKaUpIQlTePynNhvpi0V6EqLbtEzeWY50WARA9h5djBsD5qfMl7xhJ6HME5oi5kj1fOMGHW0dUfPZnz1Et2fPYANYUNb4qJpVZTYxkkZvbEqqr8W4ni"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "levelHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "levelIcon", "MmPM9pGoE8RPh9Pf5MpSZvVuNRohPPxSqa6NGkK68tYM2hdiTBBjNxyRNritpCH6hOcHVd2FR6nLYZY2clQ6cGX96TRMkbAEFL8wdOS4jGH1vo6c3eTdxcYoSDazFBOzx5xrk5USITedD6WU5wmbAI3VULIGGYrBVuUcY7aYa0UrFp0o6kCSFqAjdy2GjyNXskudcpAYKOoQebi8W5TaXIhaGKZ1fzcOXdqoGG9zShCCk1Y4hiIggzhIx30PECQJw"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessLevel useraccesslevelUnique = useraccesslevelRepository.findById((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessLevel useraccesslevel = createUserAccessLevel(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccesslevel.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 2:
                        useraccesslevel.setUserAccessLevel(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 4:
                        useraccesslevel.setLevelName(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccesslevel, null);
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 6:
                        useraccesslevel.setLevelDescription(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 7:
                        useraccesslevel.setLevelHelp(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 8:
                        useraccesslevel.setLevelIcon(contraints.getNegativeValue().toString());
                        validateUserAccessLevel(contraints, useraccesslevel);
                        failureCount++;
                        break;
                    case 9:
                        useraccesslevel.setUserAccessLevel(useraccesslevelUnique.getUserAccessLevel());
                        validateUserAccessLevel(contraints, useraccesslevel);
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
