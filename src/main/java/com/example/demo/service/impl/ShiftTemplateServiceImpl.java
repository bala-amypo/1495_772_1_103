@Service
@Transactional
public class ShiftTemplateServiceImpl implements ShiftTemplateService {

    private final ShiftTemplateRepository shiftTemplateRepository;
    private final DepartmentRepository departmentRepository; // add this

    // Existing constructor (keep for backward compatibility)
    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = null; // optional
    }

    // **Constructor required by test**
    public ShiftTemplateServiceImpl(ShiftTemplateRepository shiftTemplateRepository,
                                    DepartmentRepository departmentRepository) {
        this.shiftTemplateRepository = shiftTemplateRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public ShiftTemplate create(ShiftTemplate template) {
        if (!template.getEndTime().isAfter(template.getStartTime())) {
            throw new IllegalArgumentException("End time must be after start time");
        }

        if (shiftTemplateRepository.findByTemplateNameAndDepartment_Id(
                template.getTemplateName(), template.getDepartment().getId()).isPresent()) {
            throw new IllegalArgumentException("Template with name is not unique in department");
        }

        return shiftTemplateRepository.save(template);
    }

    @Override
    public List<ShiftTemplate> getByDepartment(Long departmentId) {
        if (departmentId == null) {
            return shiftTemplateRepository.findAll();
        }
        return shiftTemplateRepository.findByDepartment_Id(departmentId);
    }

    @Override
    public List<ShiftTemplate> getAll() {
        return shiftTemplateRepository.findAll();
    }
}
