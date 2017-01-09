package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.PasswordPolicyRepository;
import com.app.shared.appbasicsetup.usermanagement.PasswordPolicy;
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
public class PasswordPolicyTestCase extends EntityTestCriteria {

    /**
     * PasswordPolicyRepository Variable
     */
    @Autowired
    private PasswordPolicyRepository<PasswordPolicy> passwordpolicyRepository;

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

    private PasswordPolicy createPasswordPolicy(Boolean isSave) throws Exception {
        PasswordPolicy passwordpolicy = new PasswordPolicy();
        passwordpolicy.setMinSmallLetters(9);
        passwordpolicy.setPolicyName("Ow51FZSZ1ScwItasyT2ibHZqHB8YmYAF8k8tzTSUhmH5pHJUcc");
        passwordpolicy.setPolicyDescription("ZH0cKb6EjuwraFRScmq5N0zsw16tDejbnMgse104lvKkNT2DPX");
        passwordpolicy.setAllowedSpecialLetters("Sthxd7FjJ1drK2JfKbdBu3LZxnL1sKgUMlWHC9CQ3hIWSoNoQO");
        passwordpolicy.setMinPwdLength(3);
        passwordpolicy.setMinCapitalLetters(7);
        passwordpolicy.setMinSpecialLetters(6);
        passwordpolicy.setMinNumericValues(1);
        passwordpolicy.setMaxPwdLength(5);
        passwordpolicy.setEntityValidator(entityValidator);
        return passwordpolicy;
    }

    @Test
    public void test1Save() {
        try {
            PasswordPolicy passwordpolicy = createPasswordPolicy(true);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordpolicy.isValid();
            passwordpolicyRepository.save(passwordpolicy);
            map.put("PasswordPolicyPrimaryKey", passwordpolicy._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            PasswordPolicy passwordpolicy = passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
            passwordpolicy.setMinSmallLetters(1);
            passwordpolicy.setPolicyName("0VY6eWQzxa9KpVimHV0eTE81TDLHPeU44W3SFjTfKhOcQvQiMc");
            passwordpolicy.setPolicyDescription("C1OH63OPifSlZducZ8giGpBqwUZShcONDEiO1bWj0hbrWuZ8hT");
            passwordpolicy.setVersionId(1);
            passwordpolicy.setAllowedSpecialLetters("pOO5MP3Uyfjc3tJTLF3ZsjHGuX16fe6EToRcY7VdagGU7oKNBq");
            passwordpolicy.setMinPwdLength(8);
            passwordpolicy.setMinCapitalLetters(2);
            passwordpolicy.setMinSpecialLetters(4);
            passwordpolicy.setMinNumericValues(6);
            passwordpolicy.setMaxPwdLength(5);
            passwordpolicy.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordpolicyRepository.update(passwordpolicy);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.findById((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PasswordPolicyPrimaryKey"));
            passwordpolicyRepository.remove((java.lang.String) map.get("PasswordPolicyPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordPolicy(EntityTestCriteria contraints, PasswordPolicy passwordpolicy) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordpolicy.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordpolicyRepository.save(passwordpolicy);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "policyName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "policyName", "PZbFqNeryttBieXVvvFVvRtDAC3ocqagmjGMATxNjPzoRGoaAY5GZzf1PitTJ3iJMrXbuZth40VNFbzaoUZ2p0gjmYgcbeG9Qyj2dse1TiJHYYU5DkgH3cZtWQOAW7U9N0LqVYPdWMbQYqQrVYYsJYkXh00yYZMeGiu0YXMhKfgaPvLyZZaMTaUqvTLkfcytrObEBm93LvSQJ0LQgvZ6zfGYGD3mhOEpMD5ivLm16VYHipbjtFUC7CpBpXDOc6sQP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "policyDescription", "3I3n3dh705aZ3XrBJMKzAmCYJEgEbLipEN4eC5bAK9l4uNPguYvtK3uPeHquxZvhWbrCFVIIQ1Er1Dv4A2if4XJ5Wxbs0EJ65Mft2HORksStiXVFNffxUwp2enLCHqVMxEwi0h9nUREZeNahxsqnHUZovaTNcj5g0wQwapmeg39Hdf0OuOy9Z53zHiKy5UtRki7CvmA6lZBSoUJcik2aiGzINWObrTgtfJTBaaX2NsYJEQziVY82BXgFELsnIgFa3"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "maxPwdLength", 55));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "minPwdLength", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "minPwdLength", 22));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "minCapitalLetters", 14));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "minSmallLetters", 14));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "minNumericValues", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "minSpecialLetters", 22));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "allowedSpecialLetters", "GWFkA9HLATfaZWAhVtTucfAwjsVvjA0uJAnEwHqvgNRV520xSm0GteaVPsOaEd3oZ7a3IDLQT4ZvAWZVo5eLuvvKJWnYEJk12VksD5akNETsNE4sKsbhEvtANyMv7Cdq2royMS5mDscdqIE84OAX0WpdSeLmT8lnedD2HDCKB950TNNApoCLu1p9tHpkWysJHYJSZAlw7BBH0xOikyC7FF3UWWNGKjYAJr9XULcUaBd3LGYT8rvUyTYZlkaFqrUTj"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PasswordPolicy passwordpolicy = createPasswordPolicy(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordpolicy.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 2:
                        passwordpolicy.setPolicyName(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 3:
                        passwordpolicy.setPolicyDescription(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 4:
                        passwordpolicy.setMaxPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(passwordpolicy, null);
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 6:
                        passwordpolicy.setMinPwdLength(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 7:
                        passwordpolicy.setMinCapitalLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 8:
                        passwordpolicy.setMinSmallLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 9:
                        passwordpolicy.setMinNumericValues(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 10:
                        passwordpolicy.setMinSpecialLetters(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordPolicy(contraints, passwordpolicy);
                        failureCount++;
                        break;
                    case 11:
                        passwordpolicy.setAllowedSpecialLetters(contraints.getNegativeValue().toString());
                        validatePasswordPolicy(contraints, passwordpolicy);
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
