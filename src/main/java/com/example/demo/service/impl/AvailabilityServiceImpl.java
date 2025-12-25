@Service
@Transactional
public class AvailabilityServiceImpl implements AvailabilityService {

    private final AvailabilityRepository availabilityRepository;
    private final EmployeeRepository employeeRepository; // add this

    // Existing constructor (keep)
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = null;
    }

    // **Constructor required by test**
    public AvailabilityServiceImpl(AvailabilityRepository availabilityRepository,
                                   EmployeeRepository employeeRepository) {
        this.availabilityRepository = availabilityRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeAvailability create(EmployeeAvailability availability) {
        if (availabilityRepository.findByEmployee_IdAndAvailableDate(
                availability.getEmployee().getId(), availability.getAvailableDate()).isPresent()) {
            throw new IllegalArgumentException("Availability record already exists for this employee and date");
        }
        return availabilityRepository.save(availability);
    }

    @Override
    public EmployeeAvailability update(Long id, EmployeeAvailability availability) {
        if (id != null) {
            EmployeeAvailability existing = availabilityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Availability record not found"));
            existing.setAvailable(availability.getAvailable());
            return availabilityRepository.save(existing);
        }
        throw new IllegalArgumentException("ID cannot be null");
    }

    @Override
    public void delete(Long id) {
        if (id != null) {
            EmployeeAvailability availability = availabilityRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Availability record not found"));
            availabilityRepository.delete(availability);
        }
    }

    @Override
    public List<EmployeeAvailability> getByDate(LocalDate date) {
        return availabilityRepository.findByAvailableDateAndAvailable(date, true);
    }
}
