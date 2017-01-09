package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.StateRepository;
import com.app.shared.organization.locationmanagement.State;
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
import com.app.shared.organization.locationmanagement.Country;
import com.app.server.repository.organization.locationmanagement.CountryRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class StateTestCase extends EntityTestCriteria {

    /**
     * StateRepository Variable
     */
    @Autowired
    private StateRepository<State> stateRepository;

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

    private State createState(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencyCode("kJS");
        country.setCountryName("ZrRVaXVQUfXkiJa1bBG4z2k3bkvKhSkgpr1uwK9Jwwqch4IAnl");
        country.setCountryCode2("y7T");
        country.setCapitalLatitude(11);
        country.setCapitalLongitude(8);
        country.setIsoNumeric(430);
        country.setCountryCode1("WAd");
        country.setCurrencySymbol("WrbIHlcGYX8OzzemQ9vteA7c1GQtYZdr");
        country.setCapital("E2qVWKxxVpq393OCeUvAHq8Ec5Es291s");
        country.setCountryFlag("mkasOApz4Xvzg7gFdUeOQfz4vtwmpvMaEggeV71dkPFOuGmEWg");
        country.setCurrencyName("fJpMo3nJRpNfCcLQYvbuMmzfQ25PWmhWPzIgyyz2qqmoabO48T");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("WZuF2yIsrrtHcZqeTrW2GtamrvTISFqbX6etXcP5tWeNmyhucx");
        state.setStateCodeChar3("JAJoOSytllCqVurJXAbOGA900jsa3XqO");
        state.setStateCapitalLongitude(5);
        state.setStateCapitalLatitude(6);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey());
        state.setStateName("oHrkuW6AvyRjiiSU3gAwMKT23FF8Wz9CEq2IFGVsdSOQ6jYaej");
        state.setStateCapital("UUc80QuSBIjpnjoGtxMBgPfopMh3NwRF2oSguKs4IlzRncBwvP");
        state.setStateCodeChar2("87vL8YVBTOkyE76emWBOpYgNtylhjq95");
        state.setStateDescription("2u8ymzjkdWPMGk6lXzDSkFbdyJf73GbARP6XaTB6fFW7DlATXP");
        state.setEntityValidator(entityValidator);
        return state;
    }

    @Test
    public void test1Save() {
        try {
            State state = createState(true);
            state.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            state.isValid();
            stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            State state = stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
            state.setStateCode(2);
            state.setStateFlag("kA1qdG9DKQEOVcaToa2jG7mBfTwRTCvRUVAt3tpoLYMxVM4GcN");
            state.setStateCodeChar3("0BuwQ9aPjoyobALihvarMLCVkeRr6vY8");
            state.setStateCapitalLongitude(5);
            state.setStateCapitalLatitude(9);
            state.setVersionId(1);
            state.setStateName("Gb9Y9vBNhV42QZPYbVG4oi7GObfksDSdaGxjKDbTW1pxCdslB8");
            state.setStateCapital("vkcNhAIcYr4mBReiaGV5WRJxp8OWEhvhYi1CSWyZb7VDkPGBwi");
            state.setStateCodeChar2("cvCxvqk15rrULzKCcTFCadq1WdN3SSi9");
            state.setStateDescription("WI98GQR3yyN9ivWIjBYWg5d7wakDC6veioEr863z4zrHNV8spC");
            state.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            stateRepository.update(state);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.findById((java.lang.String) map.get("StatePrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<State> listofcountryId = stateRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("StatePrimaryKey"));
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateState(EntityTestCriteria contraints, State state) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            state.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            state.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            state.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            stateRepository.save(state);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "stateName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "stateName", "LmEp0eVkoLcr1eDdcg3TDzS4nhOiWJocJ9IxDduW65ulY8i77db0TeRVPlg5JWDHOsEuuwa9QuxRWkj0E6k4394v8PG4kkWOXoG6aUcd8RNfdRCrl7ixLCuWj9GOFv8H9VmpS4qhwDPbJOAswVqG345wkYtzGaa5MZqvmuurnELWjLP5OKtB5SkdJo9lIITpGaODcjDqqUc2i1n2OZRGyOnp5ndRSpOOrC1DtqUSJdG2ALNTych5391hI25e6kwpy"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "stateCode", 4));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "stateCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "stateCodeChar2", "m1WsfeCKiduvRhrMPPUAwGRMZiSlaUK0L"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "stateCodeChar3", "59K9UbigzfGGevP4EKNhH9C8eEWtt4Lxb"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "stateDescription", "tZjqVj8CMkj7hFT6DhnlHPp5XROKFtcCXZBEizC01qi5mcA4kNnRHzlLeV8x7JG0ztyFt1snRwoiL3zCGmuzsu3wCmwRQ5THOIVIa2Cz9uAswFdyj0mn0LMGAaiQNRIn7oz8WciKCDpaCQNgoFmEoiEN2ZyLgGpDjBznIPLMJcFvU2nIxWpNjA9dZDKSPbegjYl83cGzpF9oE4zIsYTlOPT9aeJUOIGCXmBwiibHrSwMOEeG6dbhdL138hrlP7R32"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "stateFlag", "2SivxKAN0C3SKL4avgfVPaqyyOefFLQBsdmzxFjWqbPaRsuudAN2lzQR2PZJ0ZepebFI5cubR4bVw68zcWkTw6CLcjDvvBxrVxHWBI4ifZSBVSngwvTxZbmfQRbYQdeqU"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "stateCapital", "X7nlRHqCoVaOtt0gzncSOrsD08KRMsILja7cjiPPfM2mvPqrFeJjtmEiWZchct0UAB75zNYrW2c5qDhd23dK95pd1n2Dti3nhd5eTsrLSvUYsYdYnvmkPnM4spdBSV88b"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "stateCapitalLatitude", 21));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "stateCapitalLongitude", 14));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                State state = createState(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = state.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 2:
                        state.setStateName(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 3:
                        state.setStateCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(state, null);
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 5:
                        state.setStateCodeChar2(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 6:
                        state.setStateCodeChar3(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 7:
                        state.setStateDescription(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 8:
                        state.setStateFlag(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 9:
                        state.setStateCapital(contraints.getNegativeValue().toString());
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 10:
                        state.setStateCapitalLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
                        failureCount++;
                        break;
                    case 11:
                        state.setStateCapitalLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateState(contraints, state);
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
