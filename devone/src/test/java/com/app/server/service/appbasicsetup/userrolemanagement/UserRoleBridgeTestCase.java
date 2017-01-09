package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.UserRoleBridgeRepository;
import com.app.shared.appbasicsetup.userrolemanagement.UserRoleBridge;
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
import com.app.shared.appbasicsetup.usermanagement.UserDetail;
import com.app.server.repository.appbasicsetup.usermanagement.UserDetailRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessDomain;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessDomainRepository;
import com.app.shared.appbasicsetup.usermanagement.UserAccessLevel;
import com.app.server.repository.appbasicsetup.usermanagement.UserAccessLevelRepository;
import com.app.shared.appbasicsetup.usermanagement.PassRecovery;
import com.app.shared.appbasicsetup.usermanagement.Question;
import com.app.server.repository.appbasicsetup.usermanagement.QuestionRepository;
import com.app.shared.appbasicsetup.usermanagement.UserData;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class UserRoleBridgeTestCase extends EntityTestCriteria {

    /**
     * UserRoleBridgeRepository Variable
     */
    @Autowired
    private UserRoleBridgeRepository<UserRoleBridge> userrolebridgeRepository;

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

    private UserRoleBridge createUserRoleBridge(Boolean isSave) throws Exception {
        UserDetail userdetail = new UserDetail();
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948530166l));
        userdetail.setMultiFactorAuthEnabled(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("YcjHa8iMJDTZ7vqMxBjPEhkov78AYaEwHxR6ojOWRYduRshtX0");
        useraccessdomain.setDomainIcon("30Pi7i4TegAnJXiaTtrHInlVbwwevPlhtq6VJmEMxivXHEAUt5");
        useraccessdomain.setDomainDescription("qk9Ic2fSzdB5bN3iQYqd9RpgFFEAmHGb2Q26R63G3M1lZPJm3Z");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("pMAVmtmRVG82Z17lvxZn3UFdw2hwfe9ABpb6UKrgpyfjX5VFRN");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("qcv8HjKk0aHbinPEqQCfn0aoVBnOADFttAYJzqZPRVcrirEtmg");
        useraccesslevel.setLevelHelp("0MvVWX9MNgpeaz54owIM6hrEu2c2otkM2GPcg8Q4CEYCTOK0hl");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("goikUA1Z2FYInM66rNilFEeqpkDZJ0GyNGbwn2tJSCfDc1abnd");
        useraccesslevel.setLevelIcon("IBrZRsnZMyn14CqPqEVWoO9lX5lbzCZtIIgWM1A39KJwbAjaUr");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948530179l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(34630);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1483948530216l));
        userdetail.setIsLocked(1);
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setAuthAlgo("87mg9RTKP95sUPRGkuDuKXDhl4OcolCwhDUxVuTmTbIBJjNRgt");
        userdetail.setSessionTimeout(2293);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("7fxDzcb6nkduP9tQKidxGiPCkUvTt6JLNCw2Cuk6SDiNyMVrCQ");
        question.setQuestion("hvSjDIfdElO2sf4Woc2lMGmZCKfUDsCVJi3lwKWIUjvoiANXMy");
        question.setQuestionDetails("kB7Dj4uZl9dx2hHtrZqPJLldCthl2CyWP5Bts7E6XUpVfTUTD8");
        question.setLevelid(9);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("pgcjeBER24474yUW9vazCDC04NPT10t4Hm1xSRpBqCd0wCSKKd");
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("aOISLVuu21EEGlck0fMnxZHVfUL8BgBc");
        userdata.setPassword("hsgiywhrypDjRw01LW6lrK4Y6uwcQ6yufS7cAJEwtpGpj2h95G");
        userdata.setOneTimePasswordExpiry(11);
        userdata.setLast5Passwords("me4DqZosgS51UWZ4w02jysdxPrAHpIwr0yq057ws77QgGt7uCD");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1483948530473l));
        userdetail.setUserData(userdata);
        UserDetail UserDetailTest = new UserDetail();
        if (isSave) {
            UserDetailTest = userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        }
        Roles roles = new Roles();
        roles.setRoleName("dOMZd3NeX8ihy6H840CtAPPpNLiYT1t5wemWd2GEvvGYJNZWFP");
        roles.setRoleIcon("uoAPcNUDfcxcpaw3JGd5tqFWRRdVQpqZZz8wO94A4gSRmnbUg8");
        roles.setRoleHelp("Mji7EJbfl0zv6oQdUP6t5qmRg4ruCLRIBFRZ77iKNKc8oCMldt");
        roles.setRoleDescription("Hoy0jarGRYIeTfwGKVBReJTgVckD7Gx6VIgR9iZKTz4NHSs8zh");
        Roles RolesTest = new Roles();
        if (isSave) {
            RolesTest = rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        }
        UserRoleBridge userrolebridge = new UserRoleBridge();
        userrolebridge.setUserId((java.lang.String) UserDetailTest._getPrimarykey()); /* ******Adding refrenced table data */
        userrolebridge.setRoleId((java.lang.String) RolesTest._getPrimarykey());
        userrolebridge.setEntityValidator(entityValidator);
        return userrolebridge;
    }

    @Test
    public void test1Save() {
        try {
            UserRoleBridge userrolebridge = createUserRoleBridge(true);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            userrolebridge.isValid();
            userrolebridgeRepository.save(userrolebridge);
            map.put("UserRoleBridgePrimaryKey", userrolebridge._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private RolesRepository<Roles> rolesRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            UserRoleBridge userrolebridge = userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
            userrolebridge.setVersionId(1);
            userrolebridge.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            userrolebridgeRepository.update(userrolebridge);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<UserRoleBridge> listofuserId = userrolebridgeRepository.findByUserId((java.lang.String) map.get("UserDetailPrimaryKey"));
            if (listofuserId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByroleId() {
        try {
            java.util.List<UserRoleBridge> listofroleId = userrolebridgeRepository.findByRoleId((java.lang.String) map.get("RolesPrimaryKey"));
            if (listofroleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.findById((java.lang.String) map.get("UserRoleBridgePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("UserRoleBridgePrimaryKey"));
            userrolebridgeRepository.remove((java.lang.String) map.get("UserRoleBridgePrimaryKey")); /* Deleting refrenced data */
            rolesRepository.remove((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            userdetailRepository.remove((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateUserRoleBridge(EntityTestCriteria contraints, UserRoleBridge userrolebridge) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            userrolebridge.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            userrolebridgeRepository.save(userrolebridge);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
    }
}
