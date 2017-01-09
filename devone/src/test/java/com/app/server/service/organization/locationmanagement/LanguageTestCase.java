package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Language;
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
public class LanguageTestCase extends EntityTestCriteria {

    /**
     * LanguageRepository Variable
     */
    @Autowired
    private LanguageRepository<Language> languageRepository;

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

    private Language createLanguage(Boolean isSave) throws Exception {
        Language language = new Language();
        language.setLanguageType("krTzlxqR5up2ufXfLrNHGK4OB9c0V8tt");
        language.setAlpha3("Hlv");
        language.setAlpha4("pdmb");
        language.setLanguageIcon("vk3lu62j9eTAWQsSJR1Zkh8gzHmzIpwd88VEORtgIhudH70slz");
        language.setAlpha4parentid(2);
        language.setLanguage("pSRH0gPKGqjUdpyiNzubvTKcAcDoRL0cuzYxt0vNv17SHlyOxV");
        language.setAlpha2("vL");
        language.setLanguageDescription("ICP5fgUtYluXN3qckzfCaeBRYDUnumpBiFNiSuxsXIxn03e9Bq");
        language.setEntityValidator(entityValidator);
        return language;
    }

    @Test
    public void test1Save() {
        try {
            Language language = createLanguage(true);
            language.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            language.isValid();
            languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            Language language = languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
            language.setLanguageType("XXs4gnOdha9ZxpIx48AHqwYbZtFkjOuE");
            language.setAlpha3("1yP");
            language.setAlpha4("4Hie");
            language.setLanguageIcon("DFCqbBHfcqTo5mneCORBAsZ049DnXR5QkIipvmRPyvIgZfaxrr");
            language.setAlpha4parentid(9);
            language.setLanguage("whG97JVtZ3OqS6TqG7JDSk01o4kyGbucLGRxmt2cQwL1vFMUTt");
            language.setAlpha2("cZ");
            language.setLanguageDescription("aq5kgnia5lmY8QjeCrZq5qbME7PQYdRJh2m79kWK7UDQmbDtft");
            language.setVersionId(1);
            language.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            languageRepository.update(language);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.findById((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LanguagePrimaryKey"));
            languageRepository.remove((java.lang.String) map.get("LanguagePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateLanguage(EntityTestCriteria contraints, Language language) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            language.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            language.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            language.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            languageRepository.save(language);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "language", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "language", "6MhCYQRuk5EimgN1fwdN7rbwvw3ckkT5ucNvXZ3j76WxcDebrSn6cUrprzdY2MUtRyQzLgoJlDFuy9NgmfsJdyrktiNGyRxD5puGmlcSNGEvTjCbL2hsuepf2z8SXMCN7TENHDF6P1n7bU3vYfpt4JP3G02KjzrMdXvtJwVIpgHpYXMSVGIztTROrnXSxDK89OiWnHlljR5FRexgj5xx9Lj8x2flw3r9gCeRfl55jw3YXNHV00hhMBUMK3HKZkdYM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "languageType", "jT9wnjy6VwsXFcKUanxJgIZhgMQU1c7Bv"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "languageDescription", "4oLuLrgIPDuSceJv2tt67DXYKUbhOkqKGaI3LQdO3rOBXfYtoXBwoUcKZ8ORdDXwaGMd7liaBsY39dVuEVnEdvnCfKAUhIERTpGLMTnQIENKE7aFr68H4yVHDIn13VsKIZAmO3YbPfxNaBCqqdL9taXM4tp8FEnctrX0FTUcKTHYm9fTUivVarprGYlcD2FpBeYi7FBLQyCiYXjgEH6GhF1TNkwP5o8mjQpWO7z7YlIualH1xVsToVLSQrRYzHjGq"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "languageIcon", "BNj3kj2Qwmp7Nhnvtf9TmQiZvZJkGnTfgnBmxh6BozKBjHQ3s5PVGtDvWaFXWdwIipj3cLvHU9fClWHQmcEPTLDTQgmu7RbSZOgcVavhYv6cREvaF2uIgACrpMrSWUE0n"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "alpha2", "NxJ"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "alpha3", "XocR"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "alpha4", "0k2ZP"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "alpha4parentid", 19));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Language language = createLanguage(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = language.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(language, null);
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 2:
                        language.setLanguage(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 3:
                        language.setLanguageType(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 4:
                        language.setLanguageDescription(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 5:
                        language.setLanguageIcon(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 6:
                        language.setAlpha2(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 7:
                        language.setAlpha3(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 8:
                        language.setAlpha4(contraints.getNegativeValue().toString());
                        validateLanguage(contraints, language);
                        failureCount++;
                        break;
                    case 9:
                        language.setAlpha4parentid(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLanguage(contraints, language);
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
