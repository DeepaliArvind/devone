package com.app.server.service.appbasicsetup.userrolemanagement;
import com.app.server.service.EntityTestCriteria;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.TestExecutionListeners;
import com.app.server.repository.appbasicsetup.userrolemanagement.RolesRepository;
import com.app.shared.appbasicsetup.userrolemanagement.Roles;
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
import com.app.shared.appbasicsetup.userrolemanagement.RoleMenuBridge;
import com.app.shared.appbasicsetup.userrolemanagement.AppMenus;
import com.app.server.repository.appbasicsetup.userrolemanagement.AppMenusRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = { com.app.config.JPAConfig.class, com.app.config.WebConfigExtended.class })
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@TestExecutionListeners({ org.springframework.test.context.support.DependencyInjectionTestExecutionListener.class, org.springframework.test.context.support.DirtiesContextTestExecutionListener.class, org.springframework.test.context.transaction.TransactionalTestExecutionListener.class })
public class RolesTestCase extends EntityTestCriteria {

    /**
     * RolesRepository Variable
     */
    @Autowired
    private RolesRepository<Roles> rolesRepository;

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

    private Roles createRoles(Boolean isSave) throws Exception {
        Roles roles = new Roles();
        roles.setRoleName("LBAUasPOYOJBA47NIEHYDMkBHY7EIKPIOOgpYJZrU5r9a2FzL2");
        roles.setRoleIcon("BKsQrAyiQMqLWzcVfoM4mrFRQ8Ty2kkwuw3QKUrjULYTeIs7Dz");
        roles.setRoleHelp("jYntGdGTKCwD0rZoQgMm4ReSX4xMWmkvZgKR0ZgIPYtcoHzDMP");
        roles.setRoleDescription("1LuMxGN6Jko0HcgWrUVIizUhFoQZNhzdYKhw6UaNodDiEoWNHz");
        java.util.List<RoleMenuBridge> listOfRoleMenuBridge = new java.util.ArrayList<RoleMenuBridge>();
        RoleMenuBridge rolemenubridge = new RoleMenuBridge();
        rolemenubridge.setIsWrite(true);
        AppMenus appmenus = new AppMenus();
        appmenus.setMenuAccessRights(11);
        appmenus.setExpiryDate(new java.sql.Timestamp(1483948528731l));
        appmenus.setAutoSave(true);
        appmenus.setAppType(2);
        appmenus.setMenuHead(true);
        appmenus.setRefObjectId("4DuEEFGwxONq2UG8Kygl02WQup7x2YI17bhCPsw7N2MCg0ldYw");
        appmenus.setAppId("T37gJ18SZJftXL0Q1jhhDcEweCu2rGVsU1D5nnn6fhNqUrNp0R");
        appmenus.setStartDate(new java.sql.Timestamp(1483948528731l));
        appmenus.setMenuIcon("O0VFIVeG9vi20LflqFUhtd6PqB3oG3TocNu5nUgxQA2dba1fyA");
        appmenus.setUiType("sVA");
        appmenus.setMenuCommands("21ReQ2Pji9MTeANh3HQTtxCnoMcFyxyc0ZFms9HIXkkgpl0ZJv");
        appmenus.setGoLiveDate(new java.sql.Timestamp(1483948528731l));
        appmenus.setMenuLabel("gmMFJzthS7gd1BV3pn9Adtl2oDRpzId1EHBQYU2ta6OIHKy10n");
        appmenus.setMenuTreeId("kH2geKgMxfOUNbe8XiOFyPll7ZBtSXMappoEnAqqrbYx4GRFJO");
        appmenus.setMenuAction("gcn3T2vGihbsDru4Ildc8FTKhcefvMAZE5mpu7uCXLS6Ff0VTU");
        appmenus.setMenuDisplay(true);
        AppMenus AppMenusTest = new AppMenus();
        if (isSave) {
            AppMenusTest = appmenusRepository.save(appmenus);
            map.put("AppMenusPrimaryKey", appmenus._getPrimarykey());
        }
        rolemenubridge.setIsWrite(true);
        rolemenubridge.setMenuId((java.lang.String) AppMenusTest._getPrimarykey());
        rolemenubridge.setIsRead(true);
        rolemenubridge.setIsExecute(true);
        rolemenubridge.setRoles(roles);
        listOfRoleMenuBridge.add(rolemenubridge);
        roles.addAllRoleMenuBridge(listOfRoleMenuBridge);
        roles.setEntityValidator(entityValidator);
        return roles;
    }

    @Test
    public void test1Save() {
        try {
            Roles roles = createRoles(true);
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.ADD);
            roles.isValid();
            rolesRepository.save(roles);
            map.put("RolesPrimaryKey", roles._getPrimarykey());
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Autowired
    private AppMenusRepository<AppMenus> appmenusRepository;

    @Test
    public void test2Update() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            Roles roles = rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
            roles.setRoleName("abydbNTKeN2g7dGiii3fJao1uBGgfKkKZZ6TpEQgZHhZm5misl");
            roles.setVersionId(1);
            roles.setRoleIcon("0hayUNRsW2TriXvdpcqc4EMCqbdpwlq2ThTuEOAHATAlNbvxxe");
            roles.setRoleHelp("DQsuqJsJmCIO5loX8EtFy5QYV0UVjNqR6c4pPi0lP8p11TnPOt");
            roles.setRoleDescription("IGdXcEqTgaYUFmnw78UTvvCIGJtB8kfcx0O2Av3ZUGisRSygEb");
            roles.setEntityAudit(1, "xyz", RECORD_TYPE.UPDATE);
            rolesRepository.update(roles);
        } catch (java.lang.Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test3FindById() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.findById((java.lang.String) map.get("RolesPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    @Test
    public void test6Delete() {
        try {
            Assert.assertNotNull(map.get("RolesPrimaryKey"));
            rolesRepository.remove((java.lang.String) map.get("RolesPrimaryKey")); /* Deleting refrenced data */
            appmenusRepository.remove((java.lang.String) map.get("AppMenusPrimaryKey"));
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
    }

    private void validateRoles(EntityTestCriteria contraints, Roles roles) throws Exception {
        if (contraints.getRuleType() == MIN_MAX) {
            roles.isValid();
        } else if (contraints.getRuleType() == NOT_NULL) {
            roles.isValid();
        } else if (contraints.getRuleType() == REGEX) {
            roles.isValid();
        } else if (contraints.getRuleType() == UNIQUE) {
            rolesRepository.save(roles);
        }
    }

    private List<EntityTestCriteria> addingListOfFieldForNegativeTesting() {
        List<EntityTestCriteria> entityConstraints = new java.util.ArrayList<EntityTestCriteria>();
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 1, "RoleName", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 2, "roleName", "cDM3LCppekqDPo76eVGFX6nMep4RV6Hvz8vt5ITTmCFS9aHSRJfbIe5xvCDDTx9oZO3PKCVqrAWvl0UGe79wOrJ57s8VYW37tyusvmZGpKWnhC1D8AoQMDWuo2T3S5UFRycQIKBQm3mfFyiUePUXY41pIRtjmmNnXRTbLeaom1cJq9i5lJlxMiPnVNCsAZJujSBjmO6OzliTLSu19FtlZ02qhD0pg1XA6mX91uru3ccRE9tbKnfYnrPrENtaLwfTd"));
        entityConstraints.add(new EntityTestCriteria(NOT_NULL, 3, "RoleDescription", null));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 4, "roleDescription", "0nKHj33OPyuF3KdjNJoOb68CUzpB2MnnzXe5XNwGTSROSDvxAbJwjHj3n48KMZP4qJKFPB4JTICZaBQZsmcaqECG1CMPQnHXOhwJtsOtUnPudqtu4Xbg45DpOeMQf1qjmCq5RtGW7R76LO6gXEIaEbumDqidJVxR7Yb3GpJBbsasZvQVlwUX0YTYXsvlOQIZor5ckJ7D3EoY33OqpJqbQZweDbdge5r74xPAX53Emr2M2R4UDHkwfxDiIHG25IzLG"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 5, "roleIcon", "0uSjeiNe4B4TZmEyjKB7075rHhUjAFn80kADDUaKHNRuInh9vrkX1a0k0Cs05BY3Q"));
        entityConstraints.add(new EntityTestCriteria(MIN_MAX, 6, "roleHelp", "T6g9y84I6fsdet9uqdZ2FsBalu00qjHIPhqpEgznt87YDjbVV1X9tVETN4wBra3s1rvx3txKPrBuA4AZCKdhA6tQliw5kEZrlIREAyx9u0o9s8dv08qrheHzHTLXX34ODCkZ4t7ZSjyR40GvXOqBqnHKp0JNXQlbpL9TK2TQGaUYmjCKNsUYFwSvKpk8bSfdVU7H44iusI40Bh9kNW870EK6HgnLRgVa7GWI0uFWKLbyO7KvFBeVDUkxMrKrW7Ugo"));
        return entityConstraints;
    }

    @Test
    public void test5NegativeTesting() throws NoSuchMethodException, SecurityException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, Exception {
        int failureCount = 0;
        for (EntityTestCriteria contraints : this.entityConstraint) {
            try {
                Roles roles = createRoles(false);
                java.lang.reflect.Field field = null;
                if (!contraints.getFieldName().equalsIgnoreCase("CombineUniqueKey")) {
                    field = roles.getClass().getDeclaredField(contraints.getFieldName());
                }
                switch(((contraints.getTestId()))) {
                    case 0:
                        break;
                    case 1:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 2:
                        roles.setRoleName(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 3:
                        field.setAccessible(true);
                        field.set(roles, null);
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 4:
                        roles.setRoleDescription(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 5:
                        roles.setRoleIcon(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
                        failureCount++;
                        break;
                    case 6:
                        roles.setRoleHelp(contraints.getNegativeValue().toString());
                        validateRoles(contraints, roles);
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
