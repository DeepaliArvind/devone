package com.app.server.service.shoppingcontext.retail;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.shoppingcontext.retail.CartRepository;
import com.app.shared.shoppingcontext.retail.Cart;
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
import com.app.shared.shoppingcontext.retail.Item;
import com.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.app.shared.shoppingcontext.retail.Product;
import com.app.server.repository.shoppingcontext.retail.ProductRepository;
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
public class CartTestCase extends EntityTestCriteria {

    /**
     * CartRepository Variable
     */
    @Autowired
    private CartRepository<Cart> cartRepository;

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

    private Cart createCart(Boolean isSave) throws Exception {
        Item item = new Item();
        item.setImg("IuvdrUt9mJkAK6ne2258LfqouBcjV5XsJ6JxVukOX4dqf1UP8m");
        item.setPrice(2100.0d);
        item.setStock(2147483647);
        Product product = new Product();
        product.setProductIdDesc("T1pLxqOd6UNXJOo7vIM25n0tQpvZlIuTxJdpUgJwSdGMQ0QKtW");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        item.setImg("Z98hqrrmM8uYEA0FiulVo8hMrY4lRIO0VBMJrr3L7i22hHMgw9");
        item.setPrice(3700.0d);
        item.setStock(2147483647);
        item.setProductId((java.lang.String) ProductTest._getPrimarykey()); /* ******Adding refrenced table data */
        item.setItemName("Otp1hyYs4eK9FUTdDxn9pqfaeJuB7xzzDCz7ENMAOknZ9Rk299");
        Item ItemTest = new Item();
        if (isSave) {
            ItemTest = itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        }
        UserDetail userdetail = new UserDetail();
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948533092l));
        userdetail.setMultiFactorAuthEnabled(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("wjmYWJVUygD82WVG2DPfzbd1ePL763b4IqFTZ9rTvlVryEXxIU");
        useraccessdomain.setDomainIcon("1px1bz31SnHjmK62zqLnztrbZxSppPMJ82ssNjiMLG9F6oAdWR");
        useraccessdomain.setDomainDescription("aFHs5igV3Jxty0WnivgMcpmlyATgV7Jd4pfF4lAKbndP1WGh3f");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("uOl8LPZkk5DvTcfUySLB6IHlD9sbDLPYH0suV3A9kKGgcbs0SF");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("lQhIC8Iv5Su4iIfUoRnuxn8hNBHYfQN1iul7M6UMNy0jrcQbKI");
        useraccesslevel.setLevelHelp("SbOUBBpmLsZU64rQ7A9jtBlDPHSN9x5UxlBqcLiHAKNImXVXwz");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("P0O2OJ0Nf7LHT76RE1sLgXj90tc9pJ3YcxMZ3Lo1xajzPXqe5I");
        useraccesslevel.setLevelIcon("Q7BILTn6EUW3KOLLnyhuxsN8rRwbwwBxQeUxWkALpSSCeYAzOx");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948533103l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(51746);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1483948533144l));
        userdetail.setIsLocked(1);
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setAuthAlgo("bQtqV0FN3Y8CsS6HyTHTeiutiYOP1wO1PSbOoLfFxK7A8O8OJ4");
        userdetail.setSessionTimeout(1682);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("h7HsxfLROGQN6tuY5oyBAhpEOV8fK1lN1CDRDk2G5YM941fzbR");
        question.setQuestion("VC9vrsRVQL7YM8eW43po8wWQt6WSYODzAL55Avv3pl1obQ0k9n");
        question.setQuestionDetails("ipZlVOk0u7mrQYnIOjHcTgBQGtXNLzai89MKNUyVli3Flh6l4B");
        question.setLevelid(2);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("0VFbotPccc0e5KpdmV7leNvKLubYdjHwzl0rglodoo4V0HrBMp");
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("9rHswNWSyfoNhADaFXCOYvaREzp45SHb");
        userdata.setPassword("ABdYp3oDNy9530Yce46sfIGIASnuCycrxD9ysgwDxZMt48twWR");
        userdata.setOneTimePasswordExpiry(11);
        userdata.setLast5Passwords("ZtBGtBv3IoV8ShAGZ05EEeJX0Mv9pLopdFddqm4K6HOu5QwQTy");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1483948533401l));
        userdetail.setUserData(userdata);
        UserDetail UserDetailTest = new UserDetail();
        if (isSave) {
            UserDetailTest = userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        }
        Cart cart = new Cart();
        cart.setSubTotal(7200.0d);
        cart.setItemId((java.lang.String) ItemTest._getPrimarykey()); /* ******Adding refrenced table data */
        cart.setUserId((java.lang.String) UserDetailTest._getPrimarykey());
        cart.setQty(2147483647);
        cart.setEntityValidator(entityValidator);
        return cart;
    }

    @Test
    public void test1Save() {
        try {
            Cart cart = createCart(true);
            cart.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            cart.isValid();
            cartRepository.save(cart);
            map.put("CartPrimaryKey", cart._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private ProductRepository<Product> productRepository;

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
            Assert.assertNotNull(map.get("CartPrimaryKey"));
            Cart cart = cartRepository.findById((java.lang.String) map.get("CartPrimaryKey"));
            cart.setVersionId(1);
            cart.setSubTotal(300.0d);
            cart.setQty(2147483647);
            cart.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            cartRepository.update(cart);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByitemId() {
        try {
            java.util.List<Cart> listofitemId = cartRepository.findByItemId((java.lang.String) map.get("ItemPrimaryKey"));
            if (listofitemId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<Cart> listofuserId = cartRepository.findByUserId((java.lang.String) map.get("UserDetailPrimaryKey"));
            if (listofuserId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("CartPrimaryKey"));
            cartRepository.findById((java.lang.String) map.get("CartPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("CartPrimaryKey"));
            cartRepository.remove((java.lang.String) map.get("CartPrimaryKey")); /* Deleting refrenced data */
            userdetailRepository.remove((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey")); /* Deleting refrenced data */
            itemRepository.remove((java.lang.String) map.get("ItemPrimaryKey")); /* Deleting refrenced data */
            productRepository.remove((java.lang.String) map.get("ProductPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateCart(EntityTestCriteria contraints, Cart cart) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            cart.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            cart.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            cart.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            cartRepository.save(cart);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "qty", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 2, "subTotal", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "subTotal", 1.5944280934837955E19d));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Cart cart = createCart(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = cart.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(cart, null);
                        validateCart(contraints, cart);
                        failureCount++;
                        break;
                    case 2:
                        field.setAccessible(true);
                        field.set(cart, null);
                        validateCart(contraints, cart);
                        failureCount++;
                        break;
                    case 3:
                        cart.setSubTotal(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateCart(contraints, cart);
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
