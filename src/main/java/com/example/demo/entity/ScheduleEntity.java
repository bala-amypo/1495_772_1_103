@Entity
public class GeneratedShiftSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;

    private Long employeeId;

    private Long shiftTemplateId;

    private String shiftName;

    // getters & setters
}
