package com.app.server.service.appbasicsetup.usermanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.usermanagement.LoginRepository;
import com.app.shared.appbasicsetup.usermanagement.Login;
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
import com.app.shared.organization.contactmanagement.CoreContacts;
import com.app.server.repository.organization.contactmanagement.CoreContactsRepository;
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

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class LoginTestCase extends EntityTestCriteria {

    /**
     * LoginRepository Variable
     */
    @Autowired
    private LoginRepository<Login> loginRepository;

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

    private Login createLogin(Boolean isSave) throws Exception {
        CoreContacts corecontacts = new CoreContacts();
        corecontacts.setNativeFirstName("MDaTed25Nh5Acr6sECZ6MXR2Z5GAM6UNuZzVegtox0oGLct5jZ");
        Gender gender = new Gender();
        gender.setGender("RrFhtoS6gXxHMn3SLNUuw6I3gTlewwji5PLxqA4OfijeMtjPgC");
        Gender GenderTest = new Gender();
        if (isSave) {
            GenderTest = genderRepository.save(gender);
            map.put("GenderPrimaryKey", gender._getPrimarykey());
        }
        Title title = new Title();
        title.setTitles("GD03Hvp54jgom136JvqerneCz24Hj2Iz2GAB38cNYp45H5liRB");
        Title TitleTest = new Title();
        if (isSave) {
            TitleTest = titleRepository.save(title);
            map.put("TitlePrimaryKey", title._getPrimarykey());
        }
        Timezone timezone = new Timezone();
        timezone.setTimeZoneLabel("QWlGTvqly6NpQtPFipPLoEgVxhY5cJzPfEb47ldBo2H1yim7PM");
        timezone.setCities("WBpRkfhflgHfa2wwp4Bse1tY5mh1CsQNGQpWHwLuUXq4WglMRe");
        timezone.setGmtLabel("Pa9LmMVtqzZEOs0FC2lWISimLCSa2qwR6Lq6bOQSI7TKETyw6N");
        timezone.setUtcdifference(8);
        timezone.setCountry("QvRaV9GLUGgqfuCunorLXQkFvsv2VQwbViQYy6UQyATWWaGGBH");
        Language language = new Language();
        language.setLanguageType("Zjg96TGnsEzbyWjZs13Wsl17TvI5Licx");
        language.setAlpha3("8WD");
        language.setAlpha4("33f3");
        language.setLanguageIcon("lIK6nIyLdPJ83gHgkViO0EqlK6yiOTAYjRZ77o3MVXBoaTTmzk");
        language.setAlpha4parentid(5);
        language.setLanguage("c5uiTWaTOtvlSTESytl091PqmTUWLbDMvJzyHiO0TPXf7wqEQi");
        language.setAlpha2("2L");
        language.setLanguageDescription("g5ctpg9NNu6wLJl8SLCy0EMTVn8KZRdLiPRQfFryhNLPxkyUPY");
        Language LanguageTest = new Language();
        if (isSave) {
            LanguageTest = languageRepository.save(language);
            map.put("LanguagePrimaryKey", language._getPrimarykey());
        }
        corecontacts.setNativeFirstName("NbKPnGj7eLnxoRZJfjodgG2hpG4GJGNm40pcgeX5xz7NlFVmeI");
        corecontacts.setGenderId((java.lang.String) GenderTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setDateofbirth(new java.sql.Timestamp(1483948525919l));
        corecontacts.setNativeLastName("QAaVoNAkrTAy3dxuE19EUXydsD5Gq7KcvbJM0Xa1tqry7uwoNR");
        corecontacts.setLastName("U6rOWLbUgfZFMZLgQpA8VN35mSAxDGjmeiCXkli0hL5KzhAFXK");
        corecontacts.setTitleId((java.lang.String) TitleTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setFirstName("kS5jQIUsjSYM1EN8DQ89K1tLiklxl4TLMvlSxRmbjTn5t95Z0s");
        corecontacts.setMiddleName("ac7v8wESsGzISJz6nh7OqTvbkbuqphaUTdaXOEPlMPzxteHYKX");
        corecontacts.setEmailId("VkDojDBPsXaEliR77hAnW8dzcyc3ObXuhcuhQ8qUub7McxSc6B");
        timezone.setTimeZoneId(null);
        corecontacts.setTimezone(isSave ? timezoneRepository.save(timezone) : timezone);
        if (isSave) {
            map.put("TimezonePrimaryKey", timezone._getPrimarykey());
        }
        corecontacts.setAge(9);
        corecontacts.setNativeTitle("HiWJ8bdP0H4lch8Re3atN0oTcEg43tSfsn44f61lm0D6mr5r5S");
        corecontacts.setNativeLanguageCode((java.lang.String) LanguageTest._getPrimarykey()); /* ******Adding refrenced table data */
        corecontacts.setPhoneNumber("NzbrXWUcKOmPCYpoSDZk");
        corecontacts.setApproximateDOB(new java.sql.Timestamp(1483948526031l));
        corecontacts.setNativeMiddleName("bsDMrXO3CoWfiyJlCsAmDaTkLl7opk4BDjJVylCfE1qmHw35jx");
        java.util.List<Address> listOfAddress = new java.util.ArrayList<Address>();
        Address address = new Address();
        Country country = new Country();
        country.setCurrencyCode("RLe");
        country.setCountryName("HBpNwMoPwXI9lPwqgfAM7XGYGQzXr144yoFEV5Z9Q30wVBG2m2");
        country.setCountryCode2("VyK");
        country.setCapitalLatitude(9);
        country.setCapitalLongitude(10);
        country.setIsoNumeric(580);
        country.setCountryCode1("53a");
        country.setCurrencySymbol("jkm4tm6ESxYwG4kNxDPJVh1HidAMZris");
        country.setCapital("lncDTroC2LkkqqO4HXted76G7uTjCrHC");
        country.setCountryFlag("kwVyX1PoC9dTTAaV9asNoNYhnMdYv07vNSXVQiVwEkbVXy6IAi");
        country.setCurrencyName("mbaEEcFrqvBr9O9e0jobfRF3T6b11MEeLeNa9eyKfHaQNnzJgt");
        Country CountryTest = new Country();
        if (isSave) {
            CountryTest = countryRepository.save(country);
            map.put("CountryPrimaryKey", country._getPrimarykey());
        }
        AddressType addresstype = new AddressType();
        addresstype.setAddressTypeDesc("UWBBlPIkdWrflx9MuJujcObUKjpE99NdPeebjc7HSvonG0f0kt");
        addresstype.setAddressType("0jKduSbdk8S2HuSuE7gZEmTtIha6AKO7kupwvfGVvc7PTEznRa");
        addresstype.setAddressTypeIcon("DGC8q9fdRzPv3G8hfNkO78VmSEibJRX0TEILYmbs55lqFPEaaA");
        AddressType AddressTypeTest = new AddressType();
        if (isSave) {
            AddressTypeTest = addresstypeRepository.save(addresstype);
            map.put("AddressTypePrimaryKey", addresstype._getPrimarykey());
        }
        City city = new City();
        city.setCityLongitude(6);
        city.setCityDescription("mOhkyjUV0uJtmDWgkFeaWUxCr9yFynPHmFdQhis6S1RLZyqgzE");
        State state = new State();
        state.setStateCode(2);
        state.setStateFlag("1fAoC8cUo5FzsjuQ4l85NvAFyH2oQQeO7yPPjNfHM7qvK3RvTB");
        state.setStateCodeChar3("wH2imkpAciRcg2GkO3deVXwa73VLPSte");
        state.setStateCapitalLongitude(1);
        state.setStateCapitalLatitude(3);
        state.setStateCode(2);
        state.setStateFlag("B4J9t8nnCQbVHn6pyXaBUg31yO9LInR1OJaHND7fjG4ckk0ALt");
        state.setStateCodeChar3("uUPY6ksHkjMC4iB3wPS746yc5NOgt03R");
        state.setStateCapitalLongitude(5);
        state.setStateCapitalLatitude(6);
        state.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        state.setStateName("odDLRzoRH5m1zTtjvL56hhVny4EjE5YqS5WztDIhmRWgsGv0b1");
        state.setStateCapital("E0AcCroSCTgg5EQzSKlD1KuJulo7CZGPdx6qdIUOoY6ROEOpBm");
        state.setStateCodeChar2("nfSDylZMXiPROOwTMpl8i2JgtLtS6jdf");
        state.setStateDescription("2G2VZPRtxPMdSDos5WGAEIlDrR3dRDHFxCfH8Y0ies3yruTD4O");
        State StateTest = new State();
        if (isSave) {
            StateTest = stateRepository.save(state);
            map.put("StatePrimaryKey", state._getPrimarykey());
        }
        city.setCityLongitude(3);
        city.setCityDescription("shifU4Q4MgN3C5c5wyCFbVh64ZGfnPo8debkGtDwj5V53e1NS5");
        city.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityCodeChar2("wcfiuXn0lJtakJT4SqxG8kVkOdT7gGU4");
        city.setCityName("m0Wr0hAYm2P5OAHIZxr2cHom8FguqxJ4Ahe9K5SrxGjTqYuUan");
        city.setCityFlag("836vK2r1siuivUednYvsiua222BPnLI5UMhQ8laigVXbrDFdCd");
        city.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        city.setCityLatitude(10);
        city.setCityCode(2);
        City CityTest = new City();
        if (isSave) {
            CityTest = cityRepository.save(city);
            map.put("CityPrimaryKey", city._getPrimarykey());
        }
        address.setCountryId((java.lang.String) CountryTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setAddress1("RQsFCLtNfEapscKz94NatrrUolqCkKatxYrFWrbcONwDlBjy0r");
        address.setAddress2("j2SsleSYb86ECVR5L5IgH2Sm0QVuwp7b2I0sGwgTFo7dIQmCM6");
        address.setAddressTypeId((java.lang.String) AddressTypeTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setCityId((java.lang.String) CityTest._getPrimarykey()); /* ******Adding refrenced table data */
        address.setZipcode("Ou3ZEP");
        address.setLongitude("VMt5t8pLPZSQHkUdltDPXtlUP5g6txhneHkzAZwVppsnDtWyy3");
        address.setLatitude("3T77nEV6v5gpsMdy9G4m4sSEFaguTVRsiCK3hZIxuOEc1KhT6d");
        address.setAddress3("VqFsLQMWEEUDPvBB87zYiQX8vvRyAKHp6fu0wuotjdBYN5IGKP");
        address.setAddressLabel("crjEvCZHENd");
        address.setStateId((java.lang.String) StateTest._getPrimarykey()); /* ******Adding refrenced table data */
        listOfAddress.add(address);
        corecontacts.addAllAddress(listOfAddress);
        UserDetail userdetail = new UserDetail();
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948526356l));
        userdetail.setMultiFactorAuthEnabled(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("Er8JFs4R4bjOvUdx7moIP4Wq2QDDYFq9eNhxYFxTZdCBymLJIY");
        useraccessdomain.setDomainIcon("9BdtEFnwPVLjw74C8CraZlzCWKBGh0Db78cVUOM1D1lp7ePE8y");
        useraccessdomain.setDomainDescription("wXTmwlOJnsFWdEuz262jR8gUaSOz7jSfVUHZSp4xkHz4ZsjGZa");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("mbZ6PHwPmKJAm6ABnoFCTBXptPTGo6WOQxFaPaoPnlKs83QTIS");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("V57H0EDOM4mdKeYyOuTwQ37uGoz2OMXCbRtChQ1cgNfJHyQnZy");
        useraccesslevel.setLevelHelp("Q9u4kGp6cv4IcvZ25ud0HlU4MnpiOMewrTojo8AlfZLokgIMon");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("Bmn6uTvqHjxAtlhG66WQ08GezG4iTUxQf4xmI33YHaLEoXXvi1");
        useraccesslevel.setLevelIcon("TuxRA14A26cGh3r7j4Sjem1Z1xQgIInXxDsJSd9HxOQW6jA3fT");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948526383l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(47157);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1483948526423l));
        userdetail.setIsLocked(1);
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setAuthAlgo("VvDqysPAqz9Db2qdP9TtKX7lL8et08dz8AhP7XWzxLYqgHSRmV");
        userdetail.setSessionTimeout(639);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("rW0sKKZaembZsxIf1lGiCJvZKhEUqsdSRqBJVsAAjJ6Oq9uG23");
        question.setQuestion("ZlpRusRp8xI08vjiXg8jaa0cwHEXoOD4fRreqACitD34BrSqX9");
        question.setQuestionDetails("aETSY6t5mF7gZ21xuFpFTyjwJViWoOmFF7c6XPpMN5v5khHTMv");
        question.setLevelid(8);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey());
        passrecovery.setAnswer("NoHxTcToJbMaSInSQ5HXhunMZpqoLzj5hLPJBZfouJDFDoopQE");
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("b6xvoO4DsdjlhF5iQO5rVyUeQD8K4vEN");
        userdata.setPassword("bZzQ20OsSgWeeQ8jdIOl13LDi0zWn6kPsyd1y1UuW3nAohFRN2");
        userdata.setOneTimePasswordExpiry(3);
        userdata.setLast5Passwords("Wq3Z2KiCbehbaMC95KtcUgFMYzJg8NVcykfvuy9xiy3uxTkg2Y");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1483948526763l));
        userdetail.setUserData(userdata);
        Login login = new Login();
        login.setServerAuthImage("M05sVbowplVBcOyb39dYqTV3HPyXt3Pr");
        corecontacts.setContactId(null);
        login.setCoreContacts(corecontacts);
        login.setServerAuthText("qnnTdduAjHAOpln5");
        userdetail.setUserId(null);
        login.setUserDetail(userdetail);
        login.setFailedLoginAttempts(11);
        login.setLoginId("mUbGhfvAPG8Q4ckZ5KJemUUoLTaZUfLPOD2d3y2Xm2ko3xczR5");
        login.setIsAuthenticated(true);
        login.setEntityValidator(entityValidator);
        return login;
    }

    @Test
    public void test1Save() {
        try {
            Login login = createLogin(true);
            login.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            login.isValid();
            loginRepository.save(login);
            map.put("LoginPrimaryKey", login._getPrimarykey());
            map.put("CoreContactsPrimaryKey", login.getCoreContacts()._getPrimarykey());
            map.put("UserDetailPrimaryKey", login.getUserDetail()._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private CoreContactsRepository<CoreContacts> corecontactsRepository;

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

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            Login login = loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
            login.setServerAuthImage("bNEcCjGcA9FTzo9KkgMOOy99GabaVuJ1");
            login.setServerAuthText("lKT3vo4qj3G1JJ3K");
            login.setVersionId(1);
            login.setFailedLoginAttempts(4);
            login.setLoginId("IkjLYvg0KL1T4jByIpR0jRKT1esbdssjEyP5Ihy6TJPNNQCfbs");
            login.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            loginRepository.update(login);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.findById((java.lang.String) map.get("LoginPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void testNQFindMappedUser() {
        try {
            loginRepository.FindMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testNQFindUnMappedUser() {
        try {
            loginRepository.FindUnMappedUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("LoginPrimaryKey"));
            loginRepository.remove((java.lang.String) map.get("LoginPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
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

    private void validateLogin(EntityTestCriteria contraints, Login login) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            login.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            login.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            login.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            loginRepository.save(login);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "loginId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "loginId", "3QsM2QAf1ULrt2mI7UYqnijhyYMBCd00NfNn5WSatx4azPEB4qJo64XcE12d4CPrRii6Es7UFASQebL8bIS631AugGxgE3tr3YjG9gAyyGBhxUVrHAt2WIei8ugFdKJe0jtMJVwkCYIoQsz2he01HIaBzSUKPeRpkXbUrIXrp2vvHsvqckrDaQmbycqxhZzBFSve1QtcW"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "serverAuthImage", "QOyDSWR1Tj0l2LXecaYmtrJ4aLMrUpnAA"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "serverAuthText", "euelZlJD3a6tMycPK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "failedLoginAttempts", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "isAuthenticated", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Login login = createLogin(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = login.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(login, null);
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 2:
                        login.setLoginId(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 3:
                        login.setServerAuthImage(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 4:
                        login.setServerAuthText(contraints.getNegativeValue().toString());
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 5:
                        login.setFailedLoginAttempts(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateLogin(contraints, login);
                        failureCount++;
                        break;
                    case 6:
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
