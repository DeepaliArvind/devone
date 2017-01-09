package com.app.server.service.organization.locationmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
import com.app.shared.organization.locationmanagement.Address;
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
import com.app.shared.organization.locationmanagement.AddressType;
import com.app.server.repository.organization.locationmanagement.AddressTypeRepository;
import com.app.shared.organization.locationmanagement.City;
import com.app.server.repository.organization.locationmanagement.CityRepository;
import com.app.shared.organization.locationmanagement.State;
import com.app.server.repository.organization.locationmanagement.StateRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class AddressTestCase extends EntityTestCriteria {

    /**
     * AddressRepository Variable
     */
    @Autowired
    private AddressRepository<Address> addressRepository;

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

    private Address createAddress(Boolean isSave) throws Exception {
        Country country = new Country();
        country.setCurrencyCode("s15");
        country.setCountryName("3Iw8KuFgWLirVXKpK5AM7EB3Mnhe7xtGXaxU4M1p6dTR9eq8qU");
        country.setCountryCode2("v6U");
        country.setCapitalLatitude(2);
        country.setCapitalLongitude(6);
        country.setIsoNumeric(961);
        country.setCountryCode1("L5J");
        country.setCurrencySymbol("pxKbkA6CPpmH5TyqhR8Wvuyh7UHTtCXA");
        country.setCapital("mKggxgejQjl4pH6nQyHBVHlphbEwOdlQ");
        country.setCountryFlag("g9HoSBUjUUAD2C8Xaq2mAUKfrQGtnN9tl1Ype6WXvuv6cRr5eO");
        country.setCurrencyName("4zuWuyRFUa04GbsYdHSLqiMDkAFuXdntC0itreW1u4d8DSwjZz");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("d1E1g6NYUHZFOvSLY5rtG130kEAcT7bwzGdYnVAwtmGxpeEoVz");
        addresstype.setAddressType("iLS1CP6W92aPg3FqfNfmkRS15z0vq0fakOn8O29to7cNM8NFkr");
        addresstype.setAddressTypeIcon("AhLxod0xET46T2YS0GfxCr56HPfHO5YghsTI1sIa1DgBW7o74O");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(10);
        city.setCityDescription("Y34PglXAG370aemnhiftqdnk5WxawxPmSfV8POGUxvJrRK1cWi");
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("aLWNJVxXbaS3WSmBq8lUM8s4V39AreHsrjbpzwyfsUszYo3ZX9");
        state.setStateCodeChar3("2ofoTJ1ivtE4UtnCgKR3Q3dKMtUuKhaI");
        state.setStateCapitalLongitude(10);
        state.setStateCapitalLatitude(10);
        state.setStateCode(2);
        state.setStateFlag("11cGtxNtUP6DkoETME3JjY1R5VEcmK6A9wQ8X0t98YB1P9XhlL");
        state.setStateCodeChar3("GoCIhEbISmolmnte1XBD9zkvxccFXEiF");
        state.setStateCapitalLongitude(9);
        state.setStateCapitalLatitude(3);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("OkdaiEjemJ8HMHQXvdEF535sveVlKVvlubJoLQDmz9yqs7zu7N");
        state.setStateCapital("mxPCftL7sYBbXDgzyxBE0ha0m3V2NDaliclx58OsnESmrDC7Zt");
        state.setStateCodeChar2("KjeIbf4bSR9aiRowuK75sWGymZ8v1un3");
        state.setStateDescription("qmOJOS4kVL9hdi06hSCpFgJQm9qbMqwC9vp714CKK2UOLsInyq");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(11);
        city.setCityDescription("TkyVCpMHUlnVy69DHYNaMX7JjObk6MbgEe0sZUdwc39pIRvCn6");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("RDr19Yc6mWLH6cx4ah0sh5XViHVQxTDj");
        city.setCityName("LJwCDlJc53AQ1TTtlReCpHMxVsXzENCNv1Z7iHtGQZFAxyDJka");
        city.setCityFlag("V7zepZfyBHhXJBLZj0tMLOroKEnqMFRWRGJ6NGMG2NyZoAGJES");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(11);
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        Address address = new Address();
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("wjr20q3bgmPgSzoAWw9eBBEtXXsIqyOr9fIwOlKhmzK7cawVPg");
        address.setAddress2("IpBzlrMSxvxs0Zvjg7eIFYZ8eEvT49xYBtPFwZx3tDCrIJ4UGo");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("T6WAoW");
        address.setLongitude("MzhOh9ISuLysVNYsgBugd98uTQbuwGlOaxEqUBPGkzQMZ18ICf");
        address.setLatitude("mf4hSScGsJtscS7WU7wmx3KBw1VhxTHoIfURmJz33BjwYHydnX");
        address.setAddress3("xvcKVkTc6DCcNQHebfOoABrhPV4l1YnO8RGglfETAL7UMAFFUp");
        address.setAddressLabel("aF0ghjLBkL5");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        address.setEntityValidator(entityValidator);
        return address;
    }

    @Test
    public void test1Save() {
        try {
            Address address = createAddress(true);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            address.isValid();
            addressRepository.save(address);
            map.put("AddressPrimaryKey", address._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CountryRepository<Country> countryRepository;

    @Autowired
    private AddressTypeRepository<AddressType> addresstypeRepository;

    @Autowired
    private CityRepository<City> cityRepository;

    @Autowired
    private StateRepository<State> stateRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            Address address = addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
            address.setAddress1("Oj51rXNYqK3rXhEjiIYZ2iW8YNJzOt0Ffkw0XRg8qb6aBXK7St");
            address.setAddress2("SwGMVAJ5zalhf5Kdp0hRgCeiEtsF3DabW1xYK6q0VjOyM5AUsX");
            address.setZipcode("pa276j");
            address.setLongitude("4bDwsz2tbe01mDzXjCKfewQohS86DkjxwSgad52gyJr1NI09oD");
            address.setLatitude("ruw1rdrO90S56sjOv5Xcix9wk1cOibWH5i3k5wvZPGWQVnam8X");
            address.setAddress3("s02LFzESutAxvX9JS5E5PXSntC7Q0HzycanYym98d5GtDVtz87");
            address.setAddressLabel("vlw4oJugt7K");
            address.setVersionId(1);
            address.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            addressRepository.update(address);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycountryId() {
        try {
            java.util.List<Address> listofcountryId = addressRepository.findByCountryId((java.lang.String) map.get("CountryPrimaryKey"));
            if (listofcountryId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByaddressTypeId() {
        try {
            java.util.List<Address> listofaddressTypeId = addressRepository.findByAddressTypeId((java.lang.String) map.get("AddressTypePrimaryKey"));
            if (listofaddressTypeId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.findById((java.lang.String) map.get("AddressPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBycityId() {
        try {
            java.util.List<Address> listofcityId = addressRepository.findByCityId((java.lang.String) map.get("CityPrimaryKey"));
            if (listofcityId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBystateId() {
        try {
            java.util.List<Address> listofstateId = addressRepository.findByStateId((java.lang.String) map.get("StatePrimaryKey"));
            if (listofstateId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AddressPrimaryKey"));
            addressRepository.remove((java.lang.String) map.get("AddressPrimaryKey")); /* Deleting refrenced data */
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.remove((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAddress(EntityTestCriteria contraints, Address address) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            address.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            address.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            address.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            addressRepository.save(address);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "addressLabel", "DTIupNiKLNO4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "address1", "1t0uH7urVcnb79jKtQ1m6M51Z6ulICF8ss5x7L282TKu0gNjji0xNYvVx"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "address2", "3F2exZGgadis3DYMy7ySZBcI5axOEA0vbKlJDQxZJGDUUTwbg726YJ2Dn"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "address3", "Fmcc0YwaFIJD8SECJs6rHfxY7cbkjuHt3G7dzO2flhJpXIsAQ9hJ31UBi"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "zipcode", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "zipcode", "SITPJCj"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "latitude", "nh1Jlj8xNkYkDlk7HZCGmbuzPdiPTwUhxkbyT1XPM6kNkEmMJAo4keGxjAJktgYef"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "longitude", "nA7DYtbYczYiWyGUmOdLpsezl9T4jphK4W98DHMbcHTL6n1Kza8CN9UfLpq0EJL5x"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Address address = createAddress(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = address.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        address.setAddressLabel(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 2:
                        address.setAddress1(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 3:
                        address.setAddress2(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 4:
                        address.setAddress3(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(address, null);
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 6:
                        address.setZipcode(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 7:
                        address.setLatitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
                        failureCount++;
                        break;
                    case 8:
                        address.setLongitude(contraints.getNegativeValue().toString());
                        validateAddress(contraints, address);
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
