package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
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
public class UserAccessDomainTestCase extends EntityTestCriteria {

    /**
     * UserAccessDomainRepository Variable
     */
    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

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

    private UserAccessDomain createUserAccessDomain(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("ATMgA6pgWFgzN5uvu4ZR40QBLrP6DHeNUCHS3ZmkhuiJFsmNC4");
        useraccessdomain.setDomainIcon("7eOB3QFlWouacHCLds227xIH6sFDRstkQI0HPwTK3yijhQqUXw");
        useraccessdomain.setDomainDescription("xti9YjVLsFzaiq4jhZZeiAN0lHBaQnPM1XmFVEng7OoUHUkCme");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("bssdwcZnjZRpCNfEvHF3zYoHOna8FKfXKiZhd5AgVcRVGCovDL");
        useraccessdomain.setEntityValidator(entityValidator);
        return useraccessdomain;
    }

    @Test
    public void test1Save() {
        try {
            UserAccessDomain useraccessdomain = createUserAccessDomain(true);
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            useraccessdomain.isValid();
            useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            UserAccessDomain useraccessdomain = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            useraccessdomain.setVersionId(1);
            useraccessdomain.setDomainName("seCfC21P1Qd74veTeIB42TEpc5vO0oUGbNW7sI3IE9MXvj7Hp2");
            useraccessdomain.setDomainIcon("lFLPCqrY9xewkdM8odcX8n0WVYZgzaotuUP3GsoFoDbvVmtSDY");
            useraccessdomain.setDomainDescription("hStC9V0gCDP5yRCi2mrmcObZLNSd1x6vOAakfzz7aQNNp4sW8x");
            useraccessdomain.setUserAccessDomain(50188);
            useraccessdomain.setDomainHelp("DMNOEujHJRUMIOToPJJkAaGQX0zhhJbxyETnmrWRKICBnF93YH");
            useraccessdomain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            useraccessdomainRepository.update(useraccessdomain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserAccessDomainPrimaryKey"));
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserAccessDomain(EntityTestCriteria contraints, UserAccessDomain useraccessdomain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            useraccessdomain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            useraccessdomainRepository.save(useraccessdomain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "userAccessDomain", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "userAccessDomain", 153921));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "domainName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "domainName", "zh2H9ZrOwVi1v6TEgeWq6tVYgWwmzau7O6lv6MD8NCqyEHZrsB1rEzSy8w3JTqnIQ8AdGzoz0FolaMeGmrICzngvk9IW0itolHkDPTQQZa39nlbv6qfidAlHZwH61Fc1QJ04nmUobP3e6FcAtmb5fojCNtozccWwNiEPHEHEkNZhV8IGL53fwNifeD8qgw2P2ABQk0Wm6sMCQsPUSRoQwQIA4gj4EJb03zvV38QWYtoV4mscTofLsKVhKGO5tmTCi"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "domainDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "domainDescription", "5STzAv8QYq0wHcICkEgDAOLI1qGYZdOwYohmPUwGZ2L7AP8nBkGyUJxi3dtPBBqYkpjvM9qjSqedkrViOUDR7YDDCg6seIgHW8LJ9uOPH6klS326d5kVgvBjurXApUqPAF6gEY7MWYuAlfx3tgQdWm2J7bycqwzU3ZdNMqh5FfHMNTsrApjPxaMF1E9VTzMwtVFPh0QGRd1UlRad1BkW1FUp1Ov2jOjBdgEA3Zf7mT2n7PS1izFahzV7WOzVIy53l"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "domainHelp", "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"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "domainIcon", "hQtoCIT9ENDFer3ByHPsZEmDO9eQg5GUjbbbzdvlfKokNomSYymIK9HCQyAZMg9ObIMFVvmbdkMI9CrbSdcZMtVub3XQKLAIqfBXxMHnZ7RXMfyhK5pIi0dGSF29KiEEPFBBZxPMLqMHW4xcpS9JmTPJzyyKo6onaaGtnyIUGeWCznWGM0L6IozVrEnhlTcfEH7Cu5tTaRMJx8vRfoYe2dFbFDUBsrodURINQ9vA8VJMeBUXHeTCmZki1PrI3JfJ0"));
        entityConstraints.add(new EntityTestCriteria(UNIQUE, 9, "CombineUniqueKey", ""));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        UserAccessDomain useraccessdomainUnique = useraccessdomainRepository.findById((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserAccessDomain useraccessdomain = createUserAccessDomain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = useraccessdomain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 2:
                        useraccessdomain.setUserAccessDomain(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 4:
                        useraccessdomain.setDomainName(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(useraccessdomain, null);
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 6:
                        useraccessdomain.setDomainDescription(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 7:
                        useraccessdomain.setDomainHelp(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 8:
                        useraccessdomain.setDomainIcon(contraints.getNegativeValue().toString());
                        validateUserAccessDomain(contraints, useraccessdomain);
                        failureCount++;
                        break;
                    case 9:
                        useraccessdomain.setUserAccessDomain(useraccessdomainUnique.getUserAccessDomain());
                        validateUserAccessDomain(contraints, useraccessdomain);
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
