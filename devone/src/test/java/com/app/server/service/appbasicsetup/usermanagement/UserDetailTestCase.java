package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
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
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserDetailTestCase extends EntityTestCriteria {

    /**
     * UserDetailRepository Variable
     */
    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

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

    private UserDetail createUserDetail(Boolean isSave) throws Exception {
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("YhAoOm7b9XonCqlvtvAuaMMSIg6wUUHCI7LLYCgHBko2MjQFfF");
        useraccessdomain.setDomainIcon("3PD6WaGHxZlEQNnDa3VZhQEtIFMNtFbKXRAhox6QiroqbbaYbh");
        useraccessdomain.setDomainDescription("xLvEIiVTpjUlDbtj8C8pJ9m7JFqHZAwpt1Kt8d2SZtnNuy7Xq7");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("MkjwEbstvcgiMVuqiWimUA7iOETL1QYymeOXUsju1Cb7OzgBqk");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("G1UKSiDor4lPjCDIbOtDyXXxaBsTFm428yvLYONJWTCQEVzQ33");
        useraccesslevel.setLevelHelp("WwBpJCe6yXhzEbbbfqL2i6AO3S5yO00c43LNUgWEx22QUS14Bp");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("Qzwubh6jCnIHEUYb2WIp8Bdhrp6VG88uBJRrSmRjaJ3DH6KDyL");
        useraccesslevel.setLevelIcon("Vv8DYY9bAATWT2mtZJn73BRmOIXq5lyJDaIomp15Tq0UZEB1cl");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        UserDetail userdetail = new UserDetail();
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948524255l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(23221);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1483948524297l));
        userdetail.setIsLocked(1);
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setAuthAlgo("YmOmrj5vSBeq7apJSTIZDmD6dfwwXUPG2vf7GxAFmgKVI6ftLn");
        userdetail.setSessionTimeout(2730);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("8xkJoUDL9MHle43S5GQb3LiTmuFMdIb4NEZ7YAGFxPeHTjKPGT");
        question.setQuestion("WdKI6ZExXgVA5RQItGRDCXlM4NALFc7UYBFynWtBLXP4zBS1qf");
        question.setQuestionDetails("kfWGWdeMvVBGB9YZ3Bp0qND7q8devAgUvIS6j5xVlE5HTYhJ0Z");
        question.setLevelid(11);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setAnswer("mlgRI5qKjbOuL885wj03QxcSQ4USoE4UyRkNvTiVTod7k73PVD");
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("YPXTKle6xFvFTtziVyXupmF9QM1kQ6vA");
        userdata.setPassword("KGEK3fxBk8BzrCsW1pmlOnlNDJsgnqkmJID5RlcSYwnZJBUNfF");
        userdata.setOneTimePasswordExpiry(4);
        userdata.setLast5Passwords("8tgIEwM6cC5sDt4Hq2I0FcR9OI5dzlIKo7ZUnBw9WPUmapf3A2");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1483948524542l));
        userdetail.setUserData(userdata);
        userdetail.setEntityValidator(entityValidator);
        return userdetail;
    }

    @Test
    public void test1Save() {
        try {
            UserDetail userdetail = createUserDetail(true);
            userdetail.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userdetail.isValid();
            userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2findByuserAccessDomainId() {
        try {
            java.util.List<UserDetail> listofuserAccessDomainId = userdetailRepository.findByUserAccessDomainId((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
            if (listofuserAccessDomainId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2findByuserAccessLevelId() {
        try {
            java.util.List<UserDetail> listofuserAccessLevelId = userdetailRepository.findByUserAccessLevelId((java.lang.String) map.get("UserAccessLevelPrimaryKey"));
            if (listofuserAccessLevelId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserDetailPrimaryKey"));
            userdetailRepository.remove((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserDetail(EntityTestCriteria contraints, UserDetail userdetail) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userdetail.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userdetailRepository.save(userdetail);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "userAccessCode", 66858));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "multiFactorAuthEnabled", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "genTempOneTimeAuth", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "allowMultipleLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "isLocked", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isDeleted", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "changeAuthNextLogin", 2));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "authAlgo", "1SCdRcOavFHsLqsGg1qYLis6cXbwxIG1Q2YL4FguPL8CzWsDNKrXi9xkc12WvEOOP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "sessionTimeout", 4353));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                UserDetail userdetail = createUserDetail(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = userdetail.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        userdetail.setUserAccessCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 2:
                        userdetail.setMultiFactorAuthEnabled(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 3:
                        userdetail.setGenTempOneTimeAuth(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 4:
                        userdetail.setAllowMultipleLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 5:
                        userdetail.setIsLocked(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 6:
                        userdetail.setIsDeleted(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 7:
                        userdetail.setChangeAuthNextLogin(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 8:
                        userdetail.setAuthAlgo(contraints.getNegativeValue().toString());
                        validateUserDetail(contraints, userdetail);
                        failureCount++;
                        break;
                    case 9:
                        userdetail.setSessionTimeout(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateUserDetail(contraints, userdetail);
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
