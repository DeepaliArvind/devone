package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CountryRepository;
import com.app.shared.organization.locationmanagement.Country;
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
public class CountryTestCase extends EntityTestCriteria {

    /**
     * CountryRepository Variable
     */
    @Autowired
    private CountryRepository<Country> countryRepository;

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

    private Country createCountry(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencyCode("X3N");
        country.setCountryName("es6NNP3sjd4CCguuSs48m5dgz5Iq9rYhuzkjNFhtGvHu1tgyc3");
        country.setCountryCode2("WW2");
        country.setCapitalLatitude(6);
        country.setCapitalLongitude(3);
        country.setIsoNumeric(326);
        country.setCountryCode1("CLN");
        country.setCurrencySymbol("dhSw6WKZItgeorzZB9XJO2HiQBpiXARn");
        country.setCapital("S4en5oPcqww6Resc7THElY93uMeoVNFG");
        country.setCountryFlag("V5f2ldZaE4rf0SSONr0Xcat74UUYkjAYvn9Hg3TyXDBPSrNbRp");
        country.setCurrencyName("3aL0XBbLeVf0MSK9XWhVELWC1dNiC5353aoaf7IXBxWJf6FyDY");
        country.setEntityValidator(entityValidator);
        return country;
    }

    @Test
    public void test1Save() {
        try {
            Country country = createCountry(true);
            country.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            country.isValid();
            countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CountryPrimaryKey"));
            Country country = countryRepository.findById((java.lang.String) map.get("CountryPrimaryKey"));
            country.setCurrencyCode("hV8");
            country.setCountryName("Dvz1vbfV9DDlMj2iLa3AY8bRNWOyxARepwMgfBK116TQW5kq5n");
            country.setCountryCode2("hYg");
            country.setCapitalLatitude(9);
            country.setCapitalLongitude(7);
            country.setIsoNumeric(129);
            country.setCountryCode1("Urg");
            country.setCurrencySymbol("QlNMD6SaNmI87GPakMtHnHja7flmf4nA");
            country.setCapital("HvqlanMg5SWM3cNsHbvzN29dq9AdKU9z");
            country.setCountryFlag("Hn02kq1MnpgHd15Nrbt3JNzB4sR14sLbtcwtZMUiXXpk64xXv5");
            country.setCurrencyName("mxNFPEkiK3fJnwEal0OzfiEUiQe7GknJmYM6FabD4hoEiivfpi");
            country.setVersionId(1);
            country.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            countryRepository.update(country);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CountryPrimaryKey"));
            countryRepository.findById((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CountryPrimaryKey"));
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCountry(EntityTestCriteria contraints, Country country) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            country.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            country.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            country.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            countryRepository.save(country);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "countryName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "countryName", "bJgqg5w4zZrBQwR6HC8F0uSMTlNRrCNcT8uksrCJM8lQho0Y80kT2ShQtAuP3cgYd3dOvEWYwejMZQ2Wv7j4TwguU3joRAGaaxN82WEEroEF7PxBvPngmgFLBhGn4iYVs"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "countryCode1", "mlYe"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "countryCode2", "oFSe"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "countryFlag", "JRRHVaS0WKnaS4SlJZAlkxzN89sjJov7B0PUdygknLCvB8FtJxQSrBY7MvMUhm5wG"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "capital", "TxyH4UHYx4Mk4guVWy62lHOLBOkfQlKzU"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "currencyCode", "83Rt"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "currencyName", "hfTsF5hFs4ACAxLEuCjb35cI1jSrL1O7qoryUvuEE4N2rdyOyBoDtiYCKSQPc49Sw6XRhKvRQi4IuXEsRlCRBr7t2ExHvdvd4Yjz7AW5iozthycF8liWzpixAzrY044Yp"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "currencySymbol", "ov1ia11FU3P2cmJhLHho2uWdHl8GeEruG"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "capitalLatitude", 13));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "capitalLongitude", 15));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "isoNumeric", 1620));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Country country = createCountry(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = country.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(country, null);
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 2:
                        country.setCountryName(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 3:
                        country.setCountryCode1(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 4:
                        country.setCountryCode2(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 5:
                        country.setCountryFlag(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 6:
                        country.setCapital(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 7:
                        country.setCurrencyCode(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 8:
                        country.setCurrencyName(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 9:
                        country.setCurrencySymbol(contraints.getNegativeValue().toString());
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 10:
                        country.setCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 11:
                        country.setCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCountry(contraints, country);
                        failureCount++;
                        break;
                    case 12:
                        country.setIsoNumeric(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCountry(contraints, country);
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
