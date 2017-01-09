package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
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
public class AppMenusTestCase extends EntityTestCriteria {

    /**
     * AppMenusRepository Variable
     */
    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

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

    private AppMenus createAppMenus(Boolean isSave) throws Exception {
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(10);
        appmenus.setExpiryDate(new java.sql.Timestamp(1483948529374l));
        appmenus.setAutoSave(true);
        appmenus.setAppType(1);
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("47TIx50VED25CIi3ikwuhsHdFo1f8kOXGZX53VqVHPkKpddIez");
        appmenus.setAppId("unxSotubUk3q0GMoh4xUb6KDwsLU2jiz83zKIGGaqs2vPxyeUT");
        appmenus.setStartDate(new java.sql.Timestamp(1483948529375l));
        appmenus.setMenuIcon("ipVap88g3BX1SSiMDgdlPjnbjZYeX3X0a4mPFcpEBiDAChAWlt");
        appmenus.setUiType("swx");
        appmenus.setMenuCommands("wfGm5WLB8B5NiNJTGXQLFSFVusMP86qhMkgewxmOY8FociJRiZ");
        appmenus.setGoLiveDate(new java.sql.Timestamp(1483948529375l));
        appmenus.setMenuLabel("tej4DsLIuznx1Aya7zA0CCMlGlWcozDrFWiIu0hFZAv99NRSpH");
        appmenus.setMenuTreeId("kizOjOagb0dEBQREl8AOa9pDZTURs4dvmcA3pnNvK1Q4ptJmh1");
        appmenus.setMenuAction("GeTCsIj72eP0AfAxtGa0tFJsTJDs3oML9z7pnvXEl2poGVcLpt");
        appmenus.setMenuDisplay(true);
        appmenus.setEntityValidator(entityValidator);
        return appmenus;
    }

    @Test
    public void test1Save() {
        try {
            AppMenus appmenus = createAppMenus(true);
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            appmenus.isValid();
            appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            AppMenus appmenus = appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
            appmenus.setMenuAccessRights(1);
            appmenus.setExpiryDate(new java.sql.Timestamp(1483948529389l));
            appmenus.setAppType(1);
            appmenus.setVersionId(1);
            appmenus.setRefObjectId("xPU0GpPgIv2qXULMveUg5fDmCiduTlFlaztqJX0iLxRQHdTp0s");
            appmenus.setAppId("SDDKC27nDUfqb5NsCw4rrVJjGd0KUlBPCT5QAAU1T5R3Aj743a");
            appmenus.setStartDate(new java.sql.Timestamp(1483948529394l));
            appmenus.setMenuIcon("EAP6nvBUgUS7l0GQvVQ4WaM3TdMCKNbcqfc0acdtLHBP9cCYAB");
            appmenus.setUiType("wLH");
            appmenus.setMenuCommands("IKIVcUNvng2OW7A9p6AhgOuAN6KzwHuq17LcngmFriL9GqGZs9");
            appmenus.setGoLiveDate(new java.sql.Timestamp(1483948529398l));
            appmenus.setMenuLabel("H8WOlNotAKMwMXFK3aNWotNdKOGHIBc2w0Tpq10NQuMnb1sxYS");
            appmenus.setMenuTreeId("xsSjN1MJvLUFT1m2WcsyhyvLQ5WL3akoy5s7LoHDSVZmSxlv41");
            appmenus.setMenuAction("IFNCPk7Qg06h5IRsVkZKlw9iPWqswhwq6iVtqb0GDxRihuOSAC");
            appmenus.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            appmenusRepository.update(appmenus);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.findById((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("AppMenusPrimaryKey"));
            appmenusRepository.remove((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateAppMenus(EntityTestCriteria contraints, AppMenus appmenus) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            appmenus.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            appmenusRepository.save(appmenus);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "menuTreeId", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "menuTreeId", "rA46BlyK6tvVxnjES193sdcN3b5XYoRxMrbm49LW6vwSvAsniV2kyzyZOw2LI2JtuwlIWdiTpke4vPHUqLViAejm9B4BRHtS3WJnz0zNRsSaT8HHfv9xfH4rSAB80tXZ38TTjoCXQtdu9tfTBguFcb2xhTxkpJsgOSFOFG0BgUwMFqAEjhMGelw5AQIiDoRfB5Xwdri6L52CXWEdrukI0XeluuF9HQ7RE1ZzAPP3OsIyUrEtU9f7eDCMlTM7gO6ij"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 3, "menuIcon", "a2SRyhnNlZKGYLvrk8NvHCeIjV4bvnd6lXrQdTliiO0KyfER4IIU6f88t1GNXFsx0c5aXHLHbvChDzE2WArQ1dGCE6LQUOUNgXCpxoF61Qw6GjXfaB6gbwt660hd4vvajGSvFt2uCT2U5cEYC6E0NDIh58wDsxyQz5Ogzekdys0JUpXzHDbtanKlroVroE6fsn6E4FxRy3OoPHeOdsY4fncgnarPCPU1kOXKmWoCrGJMshS9gBFyFHRIoRM4ePTlK"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "menuAction", "CCs8XLyXGF8vxZV40D6UMk1WxNm1GcI5gPBruxRep7vl0kWlIvwZj6phU4B1zSEmWTgtt464jXzx9npxe1lVkORArf51dcRVVHMVU5HUXSLtoWjuxpRICpj8W7BXZdlLhRIh5S89rsyTn4lyoDeMeO5b1U9oBPFLIPUWLmRbRyPjxlr3UxMA7n0NlZc5OrTAsjPTQSGekTHlIxuB0FdBtnkKSGm0nhMpLVqwcBSLA3I1kTiAYniC726rgFQoaIqP2"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "menuCommands", "sed4q73BM9AxEph3HPpwHC7zMLT4Q9tsdcBFYleg0nr35HcM1lOG4f2MB3iCG2SV8"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 6, "menuDisplay", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 7, "menuDisplay", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 8, "menuHead", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 9, "menuHead", true));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 10, "menuLabel", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 11, "menuLabel", "ORNsYuHtnwWPPL7K9wnoeCxWLxKOcfO6PLB58dGpsXYKfPamPiIT1gc7cC3rlO6k0GY2dUyOWc5qEfzOeohTnVAqPrgHg4KwGrZfEIzxAD30053sq8Q7PIlaVHJ3pdMRIWnDttv9xWAW7Lg9PhhbbE5XMOdejZwLPHKAcpwOEKGSuC8bM68DneLu9KbvWxyqBTOvuGljkT1bJWGSKN3F0aLLmfXTFGPXtVAwovKyYa0rvz6SVBK1nxFf1uIDncjgg"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 12, "uiType", "b7u2"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 13, "refObjectId", "BNAToQ1NeqOHztiAvSAQafmoHAoysONjIF1Bx72K8mIYzkOsk4N3N4EwuBxzuu3PViyTaGHpZvhBsSTVujbNKIYODcnyzNxMe99MHBOG9UjxGWusWhMyegeUsC61n0DHJYwaArTify5O54MdFX55fBrqAt4NWCRWh9PQf1DxVJjSTDCfL8nFY05I2J2BuzqS8eMQyEtbQJuzkzqvKpKl6BPac405FNFGf5TIP05GMQSy8pL7hYK3DQNuyQl2RHO7L"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 14, "menuAccessRights", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 15, "menuAccessRights", 20));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 16, "appType", 4));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 17, "appId", "JaKKkJeJrRcyomlLeW8VGygrWb2KnFwjGPcJxYacnR97BeCokVeiCe3P0irZn3tApYZsv9AWvpXnk9gYOHF1TcxcV726Re7v2RhopFknFtwUedhueqfCO5o99GkAv0yNXzV3WKCZJktRqPJuimlvbYYj4MjkXAKN1yUeN3WbRMR30Me2AsJksIRu5EUkpilgsLNm0gRXR821LbejHmB8ikeO8g6SdVKU20dx7WvXSe65WtVoIZw9b1nezpYnjNf6r"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 18, "autoSave", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 19, "autoSave", true));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                AppMenus appmenus = createAppMenus(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = appmenus.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 2:
                        appmenus.setMenuTreeId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 3:
                        appmenus.setMenuIcon(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 4:
                        appmenus.setMenuAction(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 5:
                        appmenus.setMenuCommands(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 6:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 7:
                        break;
                    case 8:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 9:
                        break;
                    case 10:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 11:
                        appmenus.setMenuLabel(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 12:
                        appmenus.setUiType(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 13:
                        appmenus.setRefObjectId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 14:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 15:
                        appmenus.setMenuAccessRights(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 16:
                        appmenus.setAppType(Integer.parseInt(contraints.getNegativeValue().toString()));
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 17:
                        appmenus.setAppId(contraints.getNegativeValue().toString());
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 18:
                        field.setAccessible(true);
                        field.set(appmenus, null);
                        validateAppMenus(contraints, appmenus);
                        failureCount++;
                        break;
                    case 19:
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
