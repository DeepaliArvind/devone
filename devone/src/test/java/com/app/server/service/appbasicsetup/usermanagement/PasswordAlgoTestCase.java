package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.PasswordAlgoRepository;
import com.app.shared.appbasicsetup.usermanagement.PasswordAlgo;
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
public class PasswordAlgoTestCase extends EntityTestCriteria {

    /**
     * PasswordAlgoRepository Variable
     */
    @Autowired
    private PasswordAlgoRepository<PasswordAlgo> passwordalgoRepository;

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

    private PasswordAlgo createPasswordAlgo(Boolean isSave) throws Exception {
        PasswordAlgo passwordalgo = new PasswordAlgo();
        passwordalgo.setAlgoIcon("iDc3zTvLNsjlsJUWhHcvfDfsOaNv5Ml5BPtA74KPMWp9Qn1pyQ");
        passwordalgo.setAlgorithm(6);
        passwordalgo.setAlgoName("olEdtzKPUba4GgxYQQCrLBiIDIv6jiVQaxqeonzmouT87AuVpW");
        passwordalgo.setAlgoDescription("rdDSrNnCDN7Wau16PBiZtUmVGNj3N00uUNl8iS8eMe3GmAXgx8");
        passwordalgo.setAlgoHelp("mMVJCe5H75F5RqOxGfGGrZV2QWBrEN0nSI41q5SNIXyPKiF7Zz");
        passwordalgo.setEntityValidator(entityValidator);
        return passwordalgo;
    }

    @Test
    public void test1Save() {
        try {
            PasswordAlgo passwordalgo = createPasswordAlgo(true);
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            passwordalgo.isValid();
            passwordalgoRepository.save(passwordalgo);
            map.put("PasswordAlgoPrimaryKey", passwordalgo._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            PasswordAlgo passwordalgo = passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
            passwordalgo.setAlgoIcon("Rh8kklgZtPzK1Q5bNjQhjZYi5pLBZHysUqZGJnx5CPrE0hYKvc");
            passwordalgo.setVersionId(1);
            passwordalgo.setAlgorithm(2);
            passwordalgo.setAlgoName("I8kfq3po1M81OCWRXctEsx3k2rm0d20Jh2EbtRpCIpKzUCO5xF");
            passwordalgo.setAlgoDescription("sZrF3dHbrNTAjMqUvvIKJkIqkflrCPzm4lVKp90ZNuECRS5ty9");
            passwordalgo.setAlgoHelp("ca6sDCfYuHZSouMeI355SPjF8Uy5OznBNZHfZaP5zywH0fUg3I");
            passwordalgo.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            passwordalgoRepository.update(passwordalgo);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.findById((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("PasswordAlgoPrimaryKey"));
            passwordalgoRepository.remove((java.lang.String) map.get("PasswordAlgoPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validatePasswordAlgo(EntityTestCriteria contraints, PasswordAlgo passwordalgo) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            passwordalgo.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            passwordalgoRepository.save(passwordalgo);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "algorithm", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "algorithm", 14));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "algoName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "algoName", "gCrtYn5H4LreMfyuaRnG1OrWVoya847QlYeKNr3wsPBXjTI6avrEs6Vp3YCJtZQDmLSPpvBRYdYP41oSIA761UOpoBBcjXbbI0X4xK1KwbFeAlvIwfh10a9pfSUSM7btAdPz64CoEyqG6boexNneDbtKrAbCgeHq9kViNl56ul51WEkcy7NvqFwMpQYggW6F5KoYRqFBYv5EEYnaZg0yEMRRaAMs62JEG5CIRJdpsTeY8KsMTH8qiY13AddvaomFa"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "algoDescription", "KUYEvXko0hNmISTZnHNB5WQdIf9j1ol3qMorKcHfZeRrpN1qTtxvpqIOkgERX2JSAVDPGL9HG7udHKhfKs9FGgiZ3eNIVjq4OhEwTTYUymbPSiU8g3WGoaqG3PjBx6kB2lf0VLiLdwrN2ocf08Ic3ftTr4kyzjle5WywalPn6hQF9b2tWdrJHqwxoxoPvZOR4wb0v236D0vkVi6j5McV0hrtBen9vPmxRzbKSPwTNlcsK2wW1hRIsdPKhdmsdQjbA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "algoIcon", "gnN4dlxoGPNOlya1f9bJ42iUIscLrz401Xcv3Z9tONrna0oHmN2YY8kswJ67zDfay"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "algoHelp", "PQPpax6PWqBgkZPNdgr01hyrBYZEseLNOFJg4sUPthQqdjczDBh9vvjplCMLaNFu3GVNXjS84hvFy30LLT1dQru0eJWtyZHxEouT741qvWJNsUjxmrxhHEVEA9UrhSvYTjxHYCYJz8ghB9NoClIOzU5PX8sUXsGGIM63raoplBJa3qKIT5HBj2dnmA58aL054MHkAEoZlZyVkbLO5nSVt3Mxt5dYr4ILmE8io5UnTfb7Fbw5aApLFViusG71f0EPA"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                PasswordAlgo passwordalgo = createPasswordAlgo(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = passwordalgo.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 2:
                        passwordalgo.setAlgorithm(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(passwordalgo, null);
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 4:
                        passwordalgo.setAlgoName(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 5:
                        passwordalgo.setAlgoDescription(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 6:
                        passwordalgo.setAlgoIcon(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
                        failureCount++;
                        break;
                    case 7:
                        passwordalgo.setAlgoHelp(contraints.getNegativeValue().toString());
                        validatePasswordAlgo(contraints, passwordalgo);
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
