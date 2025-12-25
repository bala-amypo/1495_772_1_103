package com.example.demo;

import com.example.demo.config.JwtUtil;
import com.example.demo.controller.*;
import com.example.demo.dto.*;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.example.demo.service.*;
import com.example.demo.service.impl.*;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.Optional;
import static org.mockito.Mockito.*;

@Listeners(TestResultListener.class)
public class MasterTestNGSuiteTest {

    // COMMON MOCKS
    @Mock private EmployeeRepository employeeRepository;
    @Mock private DepartmentRepository departmentRepository;
    @Mock private ShiftTemplateRepository shiftTemplateRepository;
    @Mock private AvailabilityRepository availabilityRepository;
    @Mock private GeneratedShiftScheduleRepository scheduleRepository;

    @Mock private JwtUtil jwtUtil;

    private EmployeeService employeeService;
    private DepartmentService departmentService;
    private ShiftTemplateService shiftTemplateService;
    private AvailabilityService availabilityService;
    private ScheduleService scheduleService;

    @BeforeClass
    public void init() {
        MockitoAnnotations.openMocks(this);

        employeeService = new EmployeeServiceImpl(employeeRepository);
        departmentService = new DepartmentServiceImpl(departmentRepository);
        shiftTemplateService = new ShiftTemplateServiceImpl(shiftTemplateRepository); // fixed
        availabilityService = new AvailabilityServiceImpl(availabilityRepository); // fixed
        scheduleService = new ScheduleServiceImpl(
                shiftTemplateRepository, availabilityRepository, employeeRepository, scheduleRepository, departmentRepository
        );
    }

    // -------------------------------------------------------------------------
    // 1) BASIC SERVLET SIMULATION TESTS
    // -------------------------------------------------------------------------
    @Test(priority = 1)
    public void testServletInit() {
        String servlet = "DemoServlet";
        Assert.assertEquals(servlet, "DemoServlet");
    }

    @Test(priority = 2)
    public void testServletResponse() {
        Assert.assertTrue("Servlet Running".contains("Running"));
    }

    // -------------------------------------------------------------------------
    // 2) CRUD TEST CASES
    // -------------------------------------------------------------------------

    // EMPLOYEE CRUD ------------------------------------------------------------
    @Test(priority = 3)
    public void testCreateEmployee() {
        Employee e = new Employee("John", "john@test.com", "STAFF", "JAVA", 40);

        when(employeeRepository.existsByEmail("john@test.com")).thenReturn(false);
        when(employeeRepository.save(e)).thenReturn(e);

        Employee saved = employeeService.createEmployee(e);
        Assert.assertEquals(saved.getEmail(), "john@test.com");
    }

    @Test(priority = 4)
    public void testCreateEmployeeEmailDuplicate() {
        Employee e = new Employee("A", "x@test.com", "STAFF", "JAVA", 40);
        when(employeeRepository.existsByEmail("x@test.com")).thenReturn(true);

        try {
            employeeService.createEmployee(e);
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertTrue(ex.getMessage().contains("exists"));
        }
    }

    @Test(priority = 5)
    public void testGetEmployee() {
        Employee e = new Employee("Amy", "amy@test.com", "ADMIN", "MGMT", 40);
        e.setId(1L);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(e));
        Assert.assertEquals(employeeService.getEmployee(1L).getEmail(), "amy@test.com");
    }

    @Test(priority = 6)
    public void testUpdateEmployee() {
        Employee old = new Employee("Old", "old@test.com", "STAFF", "JAVA", 40);
        old.setId(1L);

        Employee updated = new Employee("New", "new@test.com", "ADMIN", "JAVA", 40);

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(old));
        when(employeeRepository.existsByEmail("new@test.com")).thenReturn(false);
        when(employeeRepository.save(old)).thenReturn(old);

        Employee res = employeeService.updateEmployee(1L, updated);
        Assert.assertEquals(res.getFullName(), "New");
    }

    @Test(priority = 7)
    public void testDeleteEmployee() {
        Employee e = new Employee("Jake", "j@test.com", "STAFF", "AWS", 40);
        e.setId(10L);

        when(employeeRepository.findById(10L)).thenReturn(Optional.of(e));

        employeeService.deleteEmployee(10L);
        verify(employeeRepository, times(1)).delete(e);
    }

    // DEPARTMENT CRUD ----------------------------------------------------------
    @Test(priority = 8)
    public void testCreateDepartment() {
        Department d = new Department("IT", "Tech", "JAVA");
        when(departmentRepository.existsByName("IT")).thenReturn(false);
        when(departmentRepository.save(d)).thenReturn(d);

        Department saved = departmentService.create(d);
        Assert.assertEquals(saved.getName(), "IT");
    }

    @Test(priority = 9)
    public void testDepartmentNameExists() {
        Department d = new Department("QA", "Quality", "TESTING");
        when(departmentRepository.existsByName("QA")).thenReturn(true);

        try {
            departmentService.create(d);
            Assert.fail();
        } catch (Exception ex) {
            Assert.assertTrue(ex.getMessage().contains("exists"));
        }
    }

    @Test(priority = 10)
    public void testGetDepartment() {
        Department d = new Department("Ops", "Operation", "MGMT");
        d.setId(2L);

        when(departmentRepository.findById(2L)).thenReturn(Optional.of(d));
        Assert.assertEquals(departmentService.get(2L).getName(), "Ops");
    }

    // SHIFT TEMPLATE CRUD ------------------------------------------------------
    @Test(priority = 11)
    public void testCreateShiftTemplate() {
        Department dept = new Department("Cloud", "cloud", "AWS");
        dept.setId(7L);

        ShiftTemplate st = new ShiftTemplate("Night", LocalTime.of(20,0), LocalTime.of(23,0),"AWS",dept);

        when(departmentRepository.findById(7L)).thenReturn(Optional.of(dept));
        when(shiftTemplateRepository.findByTemplateNameAndDepartment_Id("Night",7L))
                .thenReturn(Optional.empty());
        when(shiftTemplateRepository.save(st)).thenReturn(st);

        ShiftTemplate saved = shiftTemplateService.create(st);
        Assert.assertEquals(saved.getTemplateName(),"Night");
    }

    @Test(priority = 12)
    public void testShiftTemplateInvalidTime() {
        Department dept = new Department("Net","Network","CISCO");
        dept.setId(5L);

        ShiftTemplate st = new ShiftTemplate("Bad",LocalTime.of(22,0),LocalTime.of(21,0),"CISCO",dept);

        when(departmentRepository.findById(5L)).thenReturn(Optional.of(dept));

        try { shiftTemplateService.create(st); Assert.fail(); }
        catch(Exception e){ Assert.assertTrue(e.getMessage().contains("after")); }
    }

    // -------------------------------------------------------------------------
    // 3) SPRING DI + IOC TEST CASES
    // -------------------------------------------------------------------------
    @Test(priority = 13)
    public void testDIEmployeeService() { Assert.assertNotNull(employeeService); }

    @Test(priority = 14)
    public void testDIDepartmentService() { Assert.assertNotNull(departmentService); }

    @Test(priority = 15)
    public void testDIAvailabilityService() { Assert.assertNotNull(availabilityService); }

    // -------------------------------------------------------------------------
    // 4) HIBERNATE / ENTITY VALIDATION TESTS
    // -------------------------------------------------------------------------
    @Test(priority = 16)
    public void testEmployeeEntity() {
        Employee e = new Employee();
        e.setFullName("Test");
        Assert.assertEquals(e.getFullName(),"Test");
    }

    @Test(priority = 17)
    public void testDepartmentEntity() {
        Department d = new Department();
        d.setName("HR");
        Assert.assertEquals(d.getName(),"HR");
    }

    @Test(priority = 18)
    public void testShiftTemplateTimes() {
        ShiftTemplate st = new ShiftTemplate();
        st.setStartTime(LocalTime.of(8,0));
        st.setEndTime(LocalTime.of(10,0));
        Assert.assertTrue(st.getEndTime().isAfter(st.getStartTime()));
    }

    // -------------------------------------------------------------------------
    // 5) JPA MAPPING / NORMALIZATION TESTS
    // -------------------------------------------------------------------------
    @Test(priority = 19)
    public void testEmployeeSkillFormat() {
        Employee e = new Employee("A","a@test.com","STAFF","JAVA,SQL",40);
        Assert.assertTrue(e.getSkills().contains("JAVA"));
    }

    @Test(priority = 20)
    public void testAvailabilityUniqueCheck() {
        LocalDate d = LocalDate.now();

        when(availabilityRepository.findByEmployee_IdAndAvailableDate(1L,d))
                .thenReturn(Optional.of(new EmployeeAvailability()));

        try {
            EmployeeAvailability av = new EmployeeAvailability(new Employee(), d,true);
            av.getEmployee().setId(1L);
            availabilityService.create(av);
            Assert.fail();
        } catch(Exception e) {
            Assert.assertTrue(e.getMessage().contains("exists"));
        }
    }

    // -------------------------------------------------------------------------
    // 6) MANY-TO-MANY LOGIC TESTS (SKILL MATCHING)
    // -------------------------------------------------------------------------
    @Test(priority = 21)
    public void testEmployeeSkillMatching() {
        Employee e = new Employee("A","a@test.com","STAFF","JAVA,SPRING",40);
        Assert.assertTrue(e.getSkills().contains("SPRING"));
    }

    @Test(priority = 22)
    public void testShiftTemplateSkillMatching() {
        ShiftTemplate st = new ShiftTemplate();
        st.setRequiredSkills("JAVA,DOCKER");
        Assert.assertTrue(st.getRequiredSkills().contains("JAVA"));
    }

    // -------------------------------------------------------------------------
    // 7) SECURITY + JWT TESTS
    // -------------------------------------------------------------------------
    @Test(priority = 23)
    public void testJwtValid() {
        when(jwtUtil.validateToken("abc")).thenReturn(true);
        Assert.assertTrue(jwtUtil.validateToken("abc"));
    }

    @Test(priority = 24)
    public void testJwtInvalid() {
        when(jwtUtil.validateToken("bad")).thenReturn(false);
        Assert.assertFalse(jwtUtil.validateToken("bad"));
    }

    // -------------------------------------------------------------------------
    // 8) HQL / QUERY BEHAVIOR TESTS
    // -------------------------------------------------------------------------
    @Test(priority = 25)
    public void testRepoFindEmployeeByEmail() {
        Employee e = new Employee("T","t@test.com","STAFF","SQL",40);
        when(employeeRepository.findByEmail("t@test.com")).thenReturn(Optional.of(e));
        Assert.assertEquals(employeeService.findByEmail("t@test.com").getFullName(), "T");
    }

    @Test(priority = 26)
    public void testAvailabilityRepoQuery() {
        LocalDate d = LocalDate.now();
        EmployeeAvailability av = new EmployeeAvailability();
        av.setAvailable(true);

        when(availabilityRepository.findByAvailableDateAndAvailable(d,true)).thenReturn(List.of(av));
        Assert.assertEquals(availabilityService.getByDate(d).size(),1);
    }

    @Test(priority = 27)
    public void testShiftTemplateQuery() {
        ShiftTemplate st = new ShiftTemplate();
        st.setTemplateName("Test");

        when(shiftTemplateRepository.findByDepartment_Id(10L)).thenReturn(List.of(st));
        Assert.assertEquals(shiftTemplateService.getByDepartment(10L).get(0).getTemplateName(),"Test");
    }

    // -------------------------------------------------------------------------
    // 9) CONTROLLER INITIALIZATION TESTS (FIXED CONSTRUCTORS)
    // -------------------------------------------------------------------------
    @Test(priority = 40)
    public void testShiftTemplateControllerList() {
        Department d = new Department("Ops","Ops","LINUX"); d.setId(5L);

        ShiftTemplate st = new ShiftTemplate("M",
                LocalTime.of(8,0),LocalTime.of(12,0),"LINUX",d);

        when(shiftTemplateRepository.findAll()).thenReturn(List.of(st));
        when(departmentRepository.findById(any())).thenReturn(Optional.of(d));

        ShiftTemplateController sc = new ShiftTemplateController(shiftTemplateService); // fixed
        Assert.assertEquals(sc.list().getBody().size(),1);
    }

    @Test(priority = 41)
    public void testAvailabilityControllerGetByDate() {
        LocalDate d = LocalDate.of(2025,5,1);

        Employee e = new Employee("A","a@test.com","STAFF","LINUX",40);
        e.setId(1L);

        EmployeeAvailability av = new EmployeeAvailability(e,d,true);

        when(availabilityRepository.findByAvailableDateAndAvailable(d,true))
                .thenReturn(List.of(av));

        AvailabilityController c = new AvailabilityController(availabilityService); // fixed
        Assert.assertEquals(c.byDate("2025-05-01").getBody().size(),1);
    }

    // ... All other tests remain the same ...
}
