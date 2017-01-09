package com.app.server.service.shoppingcontext.retail;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.shoppingcontext.retail.OrderMainRepository;
import com.app.shared.shoppingcontext.retail.OrderMain;
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
import com.app.shared.shoppingcontext.retail.OrderDetail;
import com.app.shared.shoppingcontext.retail.Item;
import com.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.app.shared.shoppingcontext.retail.Product;
import com.app.server.repository.shoppingcontext.retail.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class OrderMainTestCase extends EntityTestCriteria {

    /**
     * OrderMainRepository Variable
     */
    @Autowired
    private OrderMainRepository<OrderMain> ordermainRepository;

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

    private OrderMain createOrderMain(Boolean isSave) throws Exception {
        UserDetail userdetail = new UserDetail();
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948534190l));
        userdetail.setMultiFactorAuthEnabled(1);
        UserAccessDomain useraccessdomain = new UserAccessDomain();
        useraccessdomain.setDomainName("UNTF1gpj2xUCupixhfjItIXyGSiPksxPCdK7TATKDFzVvAXj70");
        useraccessdomain.setDomainIcon("7CnePNEF0KnTqa4G45R64b6wOgCEIjH0VDZBycYJtcsscpJCaj");
        useraccessdomain.setDomainDescription("P3J5MchIkipM3zGzKCSXSZopdabS5fUR6kCAs5moGSGlWjbCIa");
        useraccessdomain.setUserAccessDomain(valueGenerator.getRandomInteger(99999, 0));
        useraccessdomain.setDomainHelp("qWqphQGijP5ttFz9FmZZLv45rYt4ZLYSGtUjSWkninwQC0y9cs");
        UserAccessDomain UserAccessDomainTest = new UserAccessDomain();
        if (isSave) {
            UserAccessDomainTest = useraccessdomainRepository.save(useraccessdomain);
            map.put("UserAccessDomainPrimaryKey", useraccessdomain._getPrimarykey());
        }
        UserAccessLevel useraccesslevel = new UserAccessLevel();
        useraccesslevel.setLevelDescription("VUZLK5wP7gFlVVAo5vZzeytvWeK30dTjOgwwfbLR0onbHEf0bh");
        useraccesslevel.setLevelHelp("mWxgIFXt279wHDi1lBWQl6uOktjeYhfqytzGYxYey98sTkoipE");
        useraccesslevel.setUserAccessLevel(valueGenerator.getRandomInteger(99999, 0));
        useraccesslevel.setLevelName("QBeIV19fckzf7QHe7JoA8TX3i31Ai4rEeM8CzPwyIibJvKSZU9");
        useraccesslevel.setLevelIcon("j4OnPDKeCWIdy4qZGTiVG3Whmyse8OmVOldx4TZqNhlwscElXt");
        UserAccessLevel UserAccessLevelTest = new UserAccessLevel();
        if (isSave) {
            UserAccessLevelTest = useraccesslevelRepository.save(useraccesslevel);
            map.put("UserAccessLevelPrimaryKey", useraccesslevel._getPrimarykey());
        }
        userdetail.setGenTempOneTimeAuth(1);
        userdetail.setIsDeleted(1);
        userdetail.setLastAuthChangeDate(new java.sql.Timestamp(1483948534202l));
        userdetail.setMultiFactorAuthEnabled(1);
        userdetail.setUserAccessDomainId((java.lang.String) UserAccessDomainTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setUserAccessCode(41596);
        userdetail.setAllowMultipleLogin(1);
        userdetail.setChangeAuthNextLogin(1);
        userdetail.setAuthExpiryDate(new java.sql.Timestamp(1483948534244l));
        userdetail.setIsLocked(1);
        userdetail.setUserAccessLevelId((java.lang.String) UserAccessLevelTest._getPrimarykey()); /* ******Adding refrenced table data */
        userdetail.setAuthAlgo("O7tajVN8gcW3oj1RSR0SDOEfRoMNZdXWkdXrbl0p7XHTBEDy0i");
        userdetail.setSessionTimeout(2566);
        java.util.List<PassRecovery> listOfPassRecovery = new java.util.ArrayList<PassRecovery>();
        PassRecovery passrecovery = new PassRecovery();
        Question question = new Question();
        question.setQuestionIcon("n1clYwM1BDpxvNkZhx1nCJP9J3qAaURPsBxsaXbcWFS5bQUam9");
        question.setQuestion("EIYNX733bSaHteBAzyU7gZoAYYFIHby7UlgSv2i7VNfzVBhsB9");
        question.setQuestionDetails("QepAhucViCVepUsHo2LCpDZyjmfSVWqLUC1Z2tvVMXyRxxdOrn");
        question.setLevelid(10);
        Question QuestionTest = new Question();
        if (isSave) {
            QuestionTest = questionRepository.save(question);
            map.put("QuestionPrimaryKey", question._getPrimarykey());
        }
        passrecovery.setUserDetail(userdetail);
        passrecovery.setQuestionId((java.lang.String) QuestionTest._getPrimarykey()); /* ******Adding refrenced table data */
        passrecovery.setAnswer("QAnFpMJEwT3ZKb4F8KicsGkojbpudSYQ4HwTwmDhYYo6EtnMkt");
        listOfPassRecovery.add(passrecovery);
        userdetail.addAllPassRecovery(listOfPassRecovery);
        UserData userdata = new UserData();
        userdata.setUserDetail(userdetail);
        userdata.setOneTimePassword("GQpLH6IFSrLgz3PRJUKTJgxxzF1MP2x4");
        userdata.setPassword("f5X8k6hfSv2vzwEA3jcSVryZ12B2g3FfpanmGJj7hHN91DH4f0");
        userdata.setOneTimePasswordExpiry(5);
        userdata.setLast5Passwords("d0Egh9he1wcDrqJ39zBEsbTrEeN6CHYJtygqOnKY6cW7SvUpkO");
        userdata.setOneTimePasswordGenDate(new java.sql.Timestamp(1483948534504l));
        userdetail.setUserData(userdata);
        UserDetail UserDetailTest = new UserDetail();
        if (isSave) {
            UserDetailTest = userdetailRepository.save(userdetail);
            map.put("UserDetailPrimaryKey", userdetail._getPrimarykey());
        }
        OrderMain ordermain = new OrderMain();
        ordermain.setUserId((java.lang.String) UserDetailTest._getPrimarykey()); /* ******Adding refrenced table data */
        ordermain.setGrandTotal(-1790.0d);
        ordermain.setOrderDate(new java.sql.Timestamp(1483948534556l));
        java.util.List<OrderDetail> listOfOrderDetail = new java.util.ArrayList<OrderDetail>();
        OrderDetail orderdetail = new OrderDetail();
        orderdetail.setQty(2147483647);
        Item item = new Item();
        item.setImg("eLoCTq2jqHwUpUKz1QhsVO6BgpUYDzlUzDEscTEVWfpkBTpUdo");
        item.setPrice(-1020.0d);
        item.setStock(2147483647);
        Product product = new Product();
        product.setProductIdDesc("ZlTXdecE8qFvGBkqCAXuUVjYES6yTkKvkjKgEP7sTfhaXF8pLQ");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        item.setImg("AMmwQiNFLbrBYqemQ8aOCrM0b9qKyQWGAU8DLCmLlOnEMRBsCG");
        item.setPrice(6700.0d);
        item.setStock(2147483647);
        item.setProductId((java.lang.String) ProductTest._getPrimarykey()); /* ******Adding refrenced table data */
        item.setItemName("d3QLTFgHSCSfllLFfGhYkRISLy246JNRqyaZR2aWpSh1rU9v9o");
        Item ItemTest = new Item();
        if (isSave) {
            ItemTest = itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        }
        orderdetail.setQty(2147483647);
        orderdetail.setOrderMain(ordermain);
        orderdetail.setItemId((java.lang.String) ItemTest._getPrimarykey());
        orderdetail.setSubTotal(3500.0d);
        listOfOrderDetail.add(orderdetail);
        ordermain.addAllOrderDetail(listOfOrderDetail);
        ordermain.setEntityValidator(entityValidator);
        return ordermain;
    }

    @Test
    public void test1Save() {
        try {
            OrderMain ordermain = createOrderMain(true);
            ordermain.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            ordermain.isValid();
            ordermainRepository.save(ordermain);
            map.put("OrderMainPrimaryKey", ordermain._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private UserDetailRepository<UserDetail> userdetailRepository;

    @Autowired
    private UserAccessDomainRepository<UserAccessDomain> useraccessdomainRepository;

    @Autowired
    private UserAccessLevelRepository<UserAccessLevel> useraccesslevelRepository;

    @Autowired
    private QuestionRepository<Question> questionRepository;

    @Autowired
    private ItemRepository<Item> itemRepository;

    @Autowired
    private ProductRepository<Product> productRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("OrderMainPrimaryKey"));
            OrderMain ordermain = ordermainRepository.findById((java.lang.String) map.get("OrderMainPrimaryKey"));
            ordermain.setVersionId(1);
            ordermain.setGrandTotal(2000.0d);
            ordermain.setOrderDate(new java.sql.Timestamp(1483948534749l));
            ordermain.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            ordermainRepository.update(ordermain);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("OrderMainPrimaryKey"));
            ordermainRepository.findById((java.lang.String) map.get("OrderMainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByuserId() {
        try {
            java.util.List<OrderMain> listofuserId = ordermainRepository.findByUserId((java.lang.String) map.get("UserDetailPrimaryKey"));
            if (listofuserId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("OrderMainPrimaryKey"));
            ordermainRepository.remove((java.lang.String) map.get("OrderMainPrimaryKey")); /* Deleting refrenced data */
            itemRepository.remove((java.lang.String) map.get("ItemPrimaryKey")); /* Deleting refrenced data */
            productRepository.remove((java.lang.String) map.get("ProductPrimaryKey")); /* Deleting refrenced data */
            userdetailRepository.remove((java.lang.String) map.get("UserDetailPrimaryKey")); /* Deleting refrenced data */
            questionRepository.remove((java.lang.String) map.get("QuestionPrimaryKey")); /* Deleting refrenced data */
            useraccesslevelRepository.remove((java.lang.String) map.get("UserAccessLevelPrimaryKey")); /* Deleting refrenced data */
            useraccessdomainRepository.remove((java.lang.String) map.get("UserAccessDomainPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateOrderMain(EntityTestCriteria contraints, OrderMain ordermain) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            ordermain.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            ordermain.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            ordermain.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            ordermainRepository.save(ordermain);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 1, "grandTotal", 1.140879992592548E19d));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                OrderMain ordermain = createOrderMain(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = ordermain.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        ordermain.setGrandTotal(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateOrderMain(contraints, ordermain);
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
