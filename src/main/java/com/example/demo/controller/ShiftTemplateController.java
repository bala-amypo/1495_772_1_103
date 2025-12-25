@RestController
@RequestMapping("/api/templates")
public class ShiftTemplateController {

    private final ShiftTemplateService shiftTemplateService;
    private final DepartmentRepository departmentRepository; // add this

    // Existing constructor
    public ShiftTemplateController(ShiftTemplateService shiftTemplateService) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = null;
    }

    // **Constructor required by test**
    public ShiftTemplateController(ShiftTemplateService shiftTemplateService,
                                   DepartmentRepository departmentRepository) {
        this.shiftTemplateService = shiftTemplateService;
        this.departmentRepository = departmentRepository;
    }

    @PostMapping("/department/{departmentId}")
    public ResponseEntity<ShiftTemplate> create(@PathVariable Long departmentId,
                                                @RequestBody ShiftTemplate template) {
        return ResponseEntity.ok(shiftTemplateService.create(template));
    }

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<List<ShiftTemplate>> getByDepartment(@PathVariable Long departmentId) {
        return ResponseEntity.ok(shiftTemplateService.getByDepartment(departmentId));
    }

    @GetMapping
    public ResponseEntity<List<ShiftTemplate>> list() {
        return ResponseEntity.ok(shiftTemplateService.getAll());
    }
}
