package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.City;
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
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class CityTestCase extends EntityTestCriteria {

    /**
     * CityRepository Variable
     */
    @Autowired
    private CityRepository<City> cityRepository;

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

    private City createCity(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencyCode("Ufm");
        country.setCountryName("reC9X9xuZcRLF719sSrvRAz2aFuX8x2XvSHtgbOyqs5uFN4Qn9");
        country.setCountryCode2("LLA");
        country.setCapitalLatitude(10);
        country.setCapitalLongitude(1);
        country.setIsoNumeric(690);
        country.setCountryCode1("VXy");
        country.setCurrencySymbol("Ew7NbW1Ly80ZQH7PnKvg1UCydiF2bRWv");
        country.setCapital("PMkz66K33K4BtMA7YCrDrrNFwwl116sN");
        country.setCountryFlag("KqszOVYAYpGjTf36hS1Sn5o6ZPIZmVZQi4B6Nb4z4D7tST8q2D");
        country.setCurrencyName("85n6de4MAy4HH3jElP33OfWDXgP0dlAmm1KZI3x4zY9PkcnIUO");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("6rgDV4LZskVR7A636DzHf0A4LuOixLTRkD5WotDlJTFcITL1oj");
        state.setStateCodeChar3("lQMRB3TJVzLP1hW3960fUsEOF8AffJLy");
        state.setStateCapitalLongitude(3);
        state.setStateCapitalLatitude(4);
        state.setStateCode(2);
        state.setStateFlag("QEdvseRZzsslVnpvnqIQOO6NhQkw1YniB4DfKdqEko133kBdAM");
        state.setStateCodeChar3("mnPHBpP2FNTIyqBQFuSSeMoWb4XK6gdq");
        state.setStateCapitalLongitude(5);
        state.setStateCapitalLatitude(8);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("VR73FCYal55jOwlDToJhneY0CzvJhCSZ4PvvSkaRgpx36PCYEo");
        state.setStateCapital("TF42zArJdeQQeWBkL0FFwm3VzDsh6XXENLRbfLpB8SNSKmT3I9");
        state.setStateCodeChar2("N4JXTGUMUji8DGZrYxaZSEUhtxftdBmB");
        state.setStateDescription("h7ECDLiyzdgSczDzNmYbrjBvYlu7UsnrDBP4sVNKHvSqlP50JA");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(4);
        city.setCityDescription("YBPsWNMoDjQWsmnJwuWNlzRGZ4uipwOLIZRnRgtiXgFhMSqHPq");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("zCukbj7xNRQYEEcxASqnBzkPGGBWs0Ax");
        city.setCityName("Ufb1b3uqZgUXamqpZk5XwjNwmFy81jGjBA3wsvX53IHAaaeZqa");
        city.setCityFlag("fZIsJsyRl0BCApPyKtSpXKiRZ4ASfaG7kxLCS80fEyFRBmmdGT");
        city.setStateId((java.lang.String) StateTest._getPrimarykey());
        city.setCityLatitude(5);
        city.setCityCode(2);
        city.setEntityValidator(entityValidator);
        return city;
    }

    @Test
    public void test1Save() {
        try {
            City city = createCity(true);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            city.isValid();
            cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            City city = cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
            city.setCityLongitude(9);
            city.setCityDescription("niDM5ngfBhkbGjx6j60Ld0wQeX5m6JTnrUa6ARA2ZqGSbK7MM3");
            city.setCityCodeChar2("S0LSnCm3IDWiG3z2H1yVUWXiNy8qxewf");
            city.setCityName("ny5b6up8F39lAyPIp4QParwPbFzBsUveDBqlb6TVadbgx2O3bj");
            city.setVersionId(1);
            city.setCityFlag("U7gPLmBq4kRDl4a3lTnOt7Sci4t2CTxZOwm0p2P1XPUrDN54sT");
            city.setCityLatitude(2);
            city.setCityCode(2);
            city.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cityRepository.update(city);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<City> listofcountryId = cityRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<City> listofstateId = cityRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.findById((java.lang.String) map.get("CityPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CityPrimaryKey"));
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCity(EntityTestCriteria contraints, City city) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            city.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            city.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            city.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cityRepository.save(city);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "cityName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "cityName", "CuUIGJRAXWf1qsMzKa6YOt49DIxpsD5dCErE7ROEcNpSFB2pYxPG1ks3ESmhXv1UPL1KgMr0B7686aXZtWBE17ZolkBVCvg3kpkFNL4f2IGucG7BAuvxFXTjrS3chWocMX1V8UFlXLggVmKFirsiHf3HKNzPWpTpcNhjiY2WWoX4QHyyGqQZA0olCi9mRO5feJXi6TLKb2jZRiZoDmsiIPu4ihwhAyicWc4sxwjPfEwYc6UzuvhNcngBmpjZlMpvq"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "cityCodeChar2", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "cityCodeChar2", "YqjsXQljgfCVUTomQzczh0rwvYrqdO7d6"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "cityCode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "cityCode", 6));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "cityDescription", "webrSGwaO370EzmDjLdIuIvLAbfPD5wzLWROm70KuRRvBckg1zA6PKfTl84Iw327s5B9PNho8cWT492V85GrzOu72X0hvtP5wgXHPtnQ8sOl48VKkt7ebeBH9UUe3B9un"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "cityFlag", "02H52DKV5zaflvZsrerYiYworexShGKgF7GtNSFzQS3vSxAf3CGFpXpId7KsiQpMtGphsF8EbDUc43NRFEPPbtenP319kYbgbqvYhW5S3Qme4NaNPcTGwk4IR1NPwg2Ly"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "cityLatitude", 14));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "cityLongitude", 22));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                City city = createCity(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = city.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 2:
                        city.setCityName(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 4:
                        city.setCityCodeChar2(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(city, null);
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 6:
                        city.setCityCode(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 7:
                        city.setCityDescription(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 8:
                        city.setCityFlag(contraints.getNegativeValue().toString());
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 9:
                        city.setCityLatitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
                        failureCount++;
                        break;
                    case 10:
                        city.setCityLongitude(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCity(contraints, city);
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
