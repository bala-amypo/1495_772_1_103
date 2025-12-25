@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;
    private final EmployeeRepository employeeRepository; // add this

    // Existing constructor (keep)
    public AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
        this.employeeRepository = null;
    }

    // **Constructor required by test**
    public AvailabilityController(AvailabilityService availabilityService,
                                  EmployeeRepository employeeRepository) {
        this.availabilityService = availabilityService;
        this.employeeRepository = employeeRepository;
    }

    @PostMapping("/{employeeId}")
    public ResponseEntity<EmployeeAvailability> create(@PathVariable Long employeeId,
                                                       @RequestBody EmployeeAvailability availability) {
        return ResponseEntity.ok(availabilityService.create(availability));
    }

    @GetMapping("/date/{date}")
    public ResponseEntity<List<EmployeeAvailability>> byDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return ResponseEntity.ok(availabilityService.getByDate(localDate));
    }
}
