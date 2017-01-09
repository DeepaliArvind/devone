package com.app.server.service.organization.contactmanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
import com.app.shared.organization.contactmanagement.CoreContacts;
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
import com.app.shared.organization.contactmanagement.Gender;
import com.app.server.repository.organization.contactmanagement.GenderRepository;
import com.app.shared.organization.contactmanagement.Title;
import com.app.server.repository.organization.contactmanagement.TitleRepository;
import com.app.shared.organization.locationmanagement.Timezone;
import com.app.server.repository.organization.locationmanagement.TimezoneRepository;
import com.app.shared.organization.locationmanagement.Language;
import com.app.server.repository.organization.locationmanagement.LanguageRepository;
import com.app.shared.organization.locationmanagement.Address;
import com.app.server.repository.organization.locationmanagement.AddressRepository;
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
public class CoreContactsTestCase extends EntityTestCriteria {

    /**
     * CoreContactsRepository Variable
     */
    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    private CoreContacts createCoreContacts(Boolean isSave) throws Exception {
        Gender gender = new Gender();
        gender.setGender("OBZBRLxkZs6N1dTPd8tFeXpA15rrhjel5LnamcJmg3dr3yh9GI");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("nY2Fm2XCa21nCytV0RChPBFSe4N2kYVDV5ELiZ3YbAb9ZvSvHe");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("Qk8ii0DtWn09bWIA5PXecdu8fBTFD5ZELF7CxOfJXxUFvbYUA4");
        timezone.setCities("LQ96feiOcJHC5KffmVqcxSFRAigduLTpXHEKBu4xvc7FlCTCnM");
        timezone.setGmtLabel("jL1BFDIY4dGrhwfzSEFqegQ2X9KVpQl68A2lqwax3wi437lZWl");
        timezone.setUtcdifference(3);
        timezone.setCountry("Uxmro43LpIovaaYF7FRy4K1xpKoe3xmkGiNCGwTv8NYnYnYh0r");
        Language language = new Language();
        language.setLanguageType("MDdjyFLj3EBAklpHGqoDwYWjJ9H060oU");
        language.setAlpha3("Ptt");
        language.setAlpha4("QYva");
        language.setLanguageIcon("9alTxuqeOH87g0OZsr01HyS3xPkb5YwovcKbOE00PuGhr7no8G");
        language.setAlpha4parentid(8);
        language.setLanguage("biMUfmeUvTTnz8YhKjeBrg4Gx3yWvo2QSgHOGc6LWWAIN3YtNs");
        language.setAlpha2("Ym");
        language.setLanguageDescription("QK064NLVKpQkVNV506UFBsTgcsrmR6XVlBXDU0lEGicQwf4xvB");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeFirstName("eqYJtGgOii9ywTrcED8zrCYZDt5gv3DYuYW70EIk1hXJS8UHZn");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1483948519934l));
        corecontacts.setNativeLastName("t6qafnM0xmaxGmYT6Kb3pueCOKa6FHw7MHVsTfgt5tl2cqSV4m");
        corecontacts.setLastName("6RU65fJYQw1pNVnDIL0OM6s2NrklU22tow07CS40Ld9bz1kbnD");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("ZKRa3fqeRc8FL2z5tKV8ei6XQqY5yuf9pvSC8xiI29WvBVEHs2");
        corecontacts.setMiddleName("nqj8tYyVpZRdfEyYdFVT03slNvxRJ35QPAFoexJDSK2fTZFudk");
        corecontacts.setEmailId("ZFEVLaYLquSMcQ9ZLzuFnE1LP2J27WOYktFEufJqf8bdisF92X");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setAge(113);
        corecontacts.setNativeTitle("zkQaQVtZw2gJVYVYkYWupaWtithj0ZosvUWMk0kfE7y9IQIRev");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("KcLKdZeQJMRRaHKH3jfM");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1483948520051l));
        corecontacts.setNativeMiddleName("60QKu8jWjNycByYDcptucMWJMQNnULBgzOyqihpCoUEUcCknNn");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCurrencyCode("wNR");
        country.setCountryName("qrY8opBS1UUtpKr0GCZ1yiQ569JAA71pRhC8pR6TNU0y17ULVm");
        country.setCountryCode2("3fO");
        country.setCapitalLatitude(9);
        country.setCapitalLongitude(11);
        country.setIsoNumeric(66);
        country.setCountryCode1("s85");
        country.setCurrencySymbol("OOMnKIbWDJnXXG5oUqqfymuXwuiECQJM");
        country.setCapital("tCozw0t9SAHqqgfVglGydcCOvk0B929F");
        country.setCountryFlag("MGRBuqQxdom7kEViKX6zF9TPHRf2aR72LwbrApmMM5hLHRojwi");
        country.setCurrencyName("e2K0Ifhx5xJ9TdUf2IABc9s6Sr9Tvy4s8JIWcGriyMmie9KMVd");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("Eg9r1H0WugN5LzLzxCoWZcYzAmf6wnypGqlBSi1imKVtSAPWRJ");
        addresstype.setAddressType("o8TAjIJssIfci95ynezfh3VN74eu6vwDVM6zBKWgJ1TRPNSmZ3");
        addresstype.setAddressTypeIcon("nKCX6IAKlrsps2kPCwRugKQQi2mlZa8Xn2Vtn5kgQi49je1IyU");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(2);
        city.setCityDescription("ESYHlaxqTjXrdNHOit44knyWb2Nm4cj6RibiH5p1mjxFYnW8Q7");
        State state = new State();
        state.setStateCode(1);
        state.setStateFlag("u9JMzoB4nylIgVSBgjsRIx34IKyFwZIITYqI8tMB2rlkoGi37H");
        state.setStateCodeChar3("SSZurtPANh45DJ1JyUo1jenqVDL8yKQU");
        state.setStateCapitalLongitude(2);
        state.setStateCapitalLatitude(1);
        state.setStateCode(2);
        state.setStateFlag("4jHe0uis7WH9AvHYngO5QMrbgJMhpJWMEtO12lKmmXmznCpVnq");
        state.setStateCodeChar3("edTBAcAziBTIfbRzZ9jcUSVkvawqrO9k");
        state.setStateCapitalLongitude(8);
        state.setStateCapitalLatitude(9);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("bivzV36rVCKGwiZFgCxhXt8UCODdLusPV7lVK0BN6T5gxrul2l");
        state.setStateCapital("ESYcBSq4inLNCds75ykoxF1uhy3qlhdkGD5r3rhBUhGzPgiv6v");
        state.setStateCodeChar2("bFChbskFM33dB20RU3aB4ppm7IKnpWJH");
        state.setStateDescription("8TWfIWkivGQEAsfgwEj6a3kW7o7eFDgqBqqttJmGFmA4WROwHn");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(10);
        city.setCityDescription("e1h4Lkw6mmzw2T4T9sg2ZDeKWCNCNrkGkAV8MYFWgZSBaJrclR");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("fCisUeHx7r4hpcAYJPylA2fXog7RZxMX");
        city.setCityName("uoQWa5P8HDjTErmOeul6S2UEizksAbD2tpvXj3NzVnHEyRviQ8");
        city.setCityFlag("bxUcVrMpXAx0UHaNpjp2WpuX92E6v21KVfWeKKg9NRtNL29tNa");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(9);
        city.setCityCode(3);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("S78iHSIglKEaf7wDuBkNF8fuInvgNvTRIAAapH79dLsNG4qk3K");
        address.setAddress2("haknGzqNXCCK0lpIj23QKNm2DxPhWnZUBruIwEq5HhYnLUQYQP");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("59pawo");
        address.setLongitude("rEcHwWp3FnFaKi5y39o5Vevg74BENp7UGO8NKsH9HIj1LKEKc6");
        address.setLatitude("ai9JkVdCg9UXwvoqmGJgfeqM82uhpurOLpwbPNF8J5yjz4UW8I");
        address.setAddress3("90F4XO2DJi4JXkRfnrDBYgqb5AcSLcUoYhzCRI2j42iq3dI6ka");
        address.setAddressLabel("3MuclAF4jeW");
        address.setStateId((java.lang.String) StateTest._getPrimarykey());
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        corecontacts.setEntityValidator(entityValidator);
        return corecontacts;
    }

    @Test
    public void test1Save() {
        try {
            CoreContacts corecontacts = createCoreContacts(true);
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            corecontacts.isValid();
            corecontactsRepository.save(corecontacts);
            map.put("CoreContactsPrimaryKey", corecontacts._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private GenderRepository<Gender> genderRepository;

    @Autowired
    private TitleRepository<Title> titleRepository;

    @Autowired
    private TimezoneRepository<Timezone> timezoneRepository;

    @Autowired
    private LanguageRepository<Language> languageRepository;

    @Autowired
    private AddressRepository<Address> addressRepository;

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
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            CoreContacts corecontacts = corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
            corecontacts.setNativeFirstName("hbWzI6IrWtdhj2Uy9VGelif73otwaMglNjiwKNEF7hzuuBEgnu");
            corecontacts.setDateofbirth(new java.sql.Timestamp(1483948520352l));
            corecontacts.setNativeLastName("BXaFmopLdeDNovUOUU2mIGLQcvtDCbNG31SuAJymtYCtTKQTxt");
            corecontacts.setLastName("zik6ONRNAymChaPdB5GiOVOTmlYPtfmGwmm0kjRuwUX9UQulPk");
            corecontacts.setFirstName("AWZtwjNkUTQi4ZP4IwnMMdI0Yk2W4so2tsTAhxsoAB2qHaj5gH");
            corecontacts.setMiddleName("pOuK8g8r4YVFwAWsUwp1UJOWriwzbxQBFNrOw6wpTHJgqfVkRk");
            corecontacts.setEmailId("iqw13YL0cbUWEZRuUReKiA4msNkpx50WBMDzIHYHlx1kUjjAnm");
            corecontacts.setAge(39);
            corecontacts.setVersionId(1);
            corecontacts.setNativeTitle("7TJ6Ezh6FW5njYcCZUvqfTdVaV3spdSiGHKklGe1ULhMce3u7l");
            corecontacts.setPhoneNumber("qHkeJdyRRzdjOBh55XWW");
            corecontacts.setApproximateDOB(new java.sql.Timestamp(1483948520492l));
            corecontacts.setNativeMiddleName("YY3lmbj6rQil8kkZQAmf6pQvfvSN7i7FpjhrDofGiKNarmvLQL");
            corecontacts.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            corecontactsRepository.update(corecontacts);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBygenderId() {
        try {
            java.util.List<CoreContacts> listofgenderId = corecontactsRepository.findByGenderId((java.lang.String) map.get("GenderPrimaryKey"));
            if (listofgenderId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBytitleId() {
        try {
            java.util.List<CoreContacts> listoftitleId = corecontactsRepository.findByTitleId((java.lang.String) map.get("TitlePrimaryKey"));
            if (listoftitleId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.findById((java.lang.String) map.get("CoreContactsPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findBynativeLanguageCode() {
        try {
            java.util.List<CoreContacts> listofnativeLanguageCode = corecontactsRepository.findByNativeLanguageCode((java.lang.String) map.get("LanguagePrimaryKey"));
            if (listofnativeLanguageCode.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CoreContactsPrimaryKey"));
            corecontactsRepository.remove((java.lang.String) map.get("CoreContactsPrimaryKey")); /* Deleting refrenced data */
            cityRepository.remove((java.lang.String) map.get("CityPrimaryKey")); /* Deleting refrenced data */
            stateRepository.remove((java.lang.String) map.get("StatePrimaryKey")); /* Deleting refrenced data */
            addresstypeRepository.remove((java.lang.String) map.get("AddressTypePrimaryKey")); /* Deleting refrenced data */
            countryRepository.remove((java.lang.String) map.get("CountryPrimaryKey")); /* Deleting refrenced data */
            languageRepository.remove((java.lang.String) map.get("LanguagePrimaryKey")); /* Deleting refrenced data */
            timezoneRepository.remove((java.lang.String) map.get("TimezonePrimaryKey")); /* Deleting refrenced data */
            titleRepository.remove((java.lang.String) map.get("TitlePrimaryKey")); /* Deleting refrenced data */
            genderRepository.remove((java.lang.String) map.get("GenderPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCoreContacts(EntityTestCriteria contraints, CoreContacts corecontacts) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            corecontacts.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            corecontactsRepository.save(corecontacts);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "firstName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "firstName", "NeH7IQdSqRAigznHrFVOQCLS1CTT1qLsQ6707U5eX7T4PtbPwLarG9s2mf0gNOr9hlAdhNXPpgdVHY9fTijShkVOZc0aaCMS3dzpWvth700Sq8ja3xfzYGoyDK70so2S7URHXPqcJfMqZXHB1aSFJI1Cn2LsQdndKQtPicq0sfC9lsfaxjrnFxAQso42GHY4xSI2VsvCYbMq1PU64JPMzH9pZkqVt07Jyv0i8zjlamAFwFnXLLyH6nfFOcmen7XBM"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "middleName", "LJYnst9zlzFWdQ9Nulxh39HciZSV92o5mC4db9h9iSA2LXk5eJF8VH9eeXsUQQ2NwWVpw5nFPhrIFAOJhgUAsxSGvxBq6zXoLEr3wadJlVMS7PAEWD7duWQ6HXYbexLuhPgLWgU1DhZQqbiDQNRtAIaLR8XssbQ671RZEaK9YxNPUmky5S1md0vL2hWfEUUOTfme8YWdaAGZzoAIRTX99kXBIIoQ1idjm8JwbLmGTNuGh6jR4HuEgM0yqcpRULTOV"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 4, "lastName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "lastName", "a18XQSQWfQqaAfWMR6FUFsHyKQsxP6f8rfOJcAqoNVx6D0fbbA6CJ5RUzFRweWPWKV0ATSnXhV2p36ziQoBWoVikoFABPogTxz7SToEjn6sqGlBuDNDyyIVG3AOhPcMHi0M5d3C3UvxwUW9z6MTOaTHgAt3piv5GFyzxplXi54yEPHSbcCZUSHg3SVB9ZdpK6HRfp9UgB5CjSb949kCmuqRevXeYRXpr3sGJqzm2BPxqC916inxvnR6GD620jBfym"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "nativeTitle", "B95s9aF9m0znT3XacOTt9uZkV0bzEsi3pqJfowtKLuVrOHG8G2ooEPR7FeOfVDO8E"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "nativeFirstName", "oX5WDSimT6Kbqfdwdb4n94tvG9LQbeNXmTbAdHUv5w0YFDRLSQm0B19OALUbHAYaEKpP8Vld2O27HJjoitVtVHlgpmmOrXxKgyFtXYh2uwi0rbuXK0icS5QIbYrle5L0tXKbNJ8uy5lHIGid25hF8o9OvWi0nzxDqgASexoeMqVazwRGveSsRc2BQFR7jytCbP3uvOlNU1sHKkQVb0PdXBu76dkXNcoO9aTPNUWKQ8vVgRVSk1Tib1ZRXUglb26I4"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 8, "nativeMiddleName", "qvLToOivJQ3CDQsqLBLDHshWF9ZmG83jM6247Yp1L5LIWfFdhawwiRI3Yli7B9u4047cTOO1NSm3LyLG3IzZH626kPSNh6BQGp5e3FDyBncNVJ15RBEX4vwDdzSLuKxoVfKBasZfHX8kSQsku3T0dFtsf9kdM3gkE0xeS83FrbALVFhJzI1HkzePn5hKCV5AT7RkT7kw8XB9qY9iKFITP9YrhCBeWH8xECyWG1sfpFJiQD7uvd9zocaUI26vcCKf6"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "nativeLastName", "RZdS0C2yjMziQChNow0svP27zWPW77ctN0rJ7ndjLcXox07ruSjrUNcqAd85XhSR2FnD2m0h8bz6SLK3pzhCwpZND1vcEV3LwCSjli4HSbspzgOpLX6ceDrSGwQEEXbCQPOKP85Nx2pGXAIsliphPOHkgs4TGLNMaFwfpOv1y4ovYi04j1PAZAPK71QfWcXFOSJqH00jbVyBi8hhtjG1xMNeJ94bcXXajbItwwacvBWHe50kK5LaUg54FC14yorrd"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 10, "age", 154));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 11, "emailId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "emailId", "3sPTooxWjBHl2cg2GCjY7WolftTWPrQo68FZblyIXenN6YLDkTUuVcFxCKYsvOY8RBYLYXjZQeUPt0dW3x9fLvupQC7OiUaQiVofgOHHQxJkN7y19lrRWvpahHdJgR8DdF5kxiJjyCaLFjbOLHNl34OOCQBMmRSsqqVEkAUIYEsR4UzcUHWm7pUrzJ1QAIndehrabWPgrne0Ntav6ZgwxAWcFvj53kDyRDHLDrlLuNY0VY2HUEH4qlSplDqmHzj1o"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 13, "phoneNumber", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 14, "phoneNumber", "exHqba31Nq8vUeFilzhHK"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                CoreContacts corecontacts = createCoreContacts(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = corecontacts.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 2:
                        corecontacts.setFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 3:
                        corecontacts.setMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 4:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 5:
                        corecontacts.setLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 6:
                        corecontacts.setNativeTitle(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 7:
                        corecontacts.setNativeFirstName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 8:
                        corecontacts.setNativeMiddleName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 9:
                        corecontacts.setNativeLastName(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 10:
                        corecontacts.setAge(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 11:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 12:
                        corecontacts.setEmailId(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 13:
                        field.setAccessible(true);
                        field.set(corecontacts, null);
                        validateCoreContacts(contraints, corecontacts);
                        failureCount++;
                        break;
                    case 14:
                        corecontacts.setPhoneNumber(contraints.getNegativeValue().toString());
                        validateCoreContacts(contraints, corecontacts);
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
