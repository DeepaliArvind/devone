package com.app.server.service.shoppingcontext.retail;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.shoppingcontext.retail.ItemRepository;
import com.app.shared.shoppingcontext.retail.Item;
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
import com.app.shared.shoppingcontext.retail.Product;
import com.app.server.repository.shoppingcontext.retail.ProductRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class ItemTestCase extends EntityTestCriteria {

    /**
     * ItemRepository Variable
     */
    @Autowired
    private ItemRepository<Item> itemRepository;

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

    private Item createItem(Boolean isSave) throws Exception {
        Product product = new Product();
        product.setProductIdDesc("dYzXexEzLDkixxB5jyFneL25cWk686eCfrWuvFMMu3v2Z2JRdp");
        Product ProductTest = new Product();
        if (isSave) {
            ProductTest = productRepository.save(product);
            map.put("ProductPrimaryKey", product._getPrimarykey());
        }
        Item item = new Item();
        item.setImg("0WQuRlt95bD3hxSbWWc3tLPJIK21bchr138BcfaViCUdiCSmOw");
        item.setPrice(-100.0d);
        item.setStock(2147483647);
        item.setProductId((java.lang.String) ProductTest._getPrimarykey());
        item.setItemName("R7Yis3S7fGfKX2lvrOZzMWQtEvd4QYByQ6EDZGMAqgxKafbjwn");
        item.setEntityValidator(entityValidator);
        return item;
    }

    @Test
    public void test1Save() {
        try {
            Item item = createItem(true);
            item.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            item.isValid();
            itemRepository.save(item);
            map.put("ItemPrimaryKey", item._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private ProductRepository<Product> productRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("ItemPrimaryKey"));
            Item item = itemRepository.findById((java.lang.String) map.get("ItemPrimaryKey"));
            item.setImg("biIuBF5lZ2qom48EhCLllNmXHKJCHFbbFfcyIA3zFZmPsfUVOQ");
            item.setVersionId(1);
            item.setPrice(-2200.0d);
            item.setStock(2147483647);
            item.setItemName("oonB7lenT9BNYAi46asOSQvsEwTfqBvSHmObZDryhrjQCKLrZT");
            item.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            itemRepository.update(item);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3findByproductId() {
        try {
            java.util.List<Item> listofproductId = itemRepository.findByProductId((java.lang.String) map.get("ProductPrimaryKey"));
            if (listofproductId.size() == 0) {
                Assert.fail("Query did not return any records.");
            }
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("ItemPrimaryKey"));
            itemRepository.findById((java.lang.String) map.get("ItemPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("ItemPrimaryKey"));
            itemRepository.remove((java.lang.String) map.get("ItemPrimaryKey")); /* Deleting refrenced data */
            productRepository.remove((java.lang.String) map.get("ProductPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateItem(EntityTestCriteria contraints, Item item) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            item.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            item.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            item.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            itemRepository.save(item);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "itemName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "itemName", "cKOEAPlFWnYaRIo40REh3xbcAR6J5NIXzzsMcIkG1AUALu7J51llDlQPKELBDftA5Xibu4o0pgMP4rMUekw76c73W1hQDtlVA0apBwLJAJXDjsgif6J5yJbNzwJ4MwCYZWMEn5BK8aK8xlgm4AtNyv5y0ASFBftQfgIQntq5Vtjzfmb755y7HM77IwszPyTDpa4nTEVHelsG3mXsobS5hTh71y1TBZvDfkacaHay2Jp2Wa9CIklL9uWoCyoVldLdG"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "img", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "img", "w1KoeELTH4jdHag1eKvlbhXdXXk8FchaoMlxEFj5RECAX0CXMyyas9KS6EyDZFJzIAKAqyNAhbfE6uRemiq4B9YqPvkRmCyr5sGl4iiQK9DT9XHssKuFkbjhccaDpuFGbXrep5BSAn13jrvLaPCKOSaCLAXG4KjuIiqwlabWbZrFONFwQMTPa5nWLj6hV6Py92Jytg9KlzpQU6UzWdXNkYZUx9BqxhJIq4K2eawMi8vaBs40F2tuMby33zE5xmoUp"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 5, "stock", null));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "price", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "price", 1.8118061640685183E19d));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Item item = createItem(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = item.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 2:
                        item.setItemName(contraints.getNegativeValue().toString());
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 4:
                        item.setImg(contraints.getNegativeValue().toString());
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 5:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(item, null);
                        validateItem(contraints, item);
                        failureCount++;
                        break;
                    case 7:
                        item.setPrice(Double.valueOf(contraints.getNegativeValue().toString()));
                        validateItem(contraints, item);
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
