package prueba.lean.tech.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import prueba.lean.tech.dtos.EmployeeDto;
import prueba.lean.tech.dtos.PositionEmployeesDto;
import prueba.lean.tech.entitys.Employee;
import prueba.lean.tech.entitys.Person;
import prueba.lean.tech.entitys.Position;
import prueba.lean.tech.filter.EmployeeFilter;
import prueba.lean.tech.mapper.EmployeeMapper;
import prueba.lean.tech.repository.EmployeeRepository;
import prueba.lean.tech.repository.PersonRepository;
import prueba.lean.tech.repository.PositionRepository;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

	@Autowired
	private PersonRepository personRepository;

	@Autowired
	private EmployeeRepository employeeRepository;

	@Autowired
	private PositionRepository positionRepository;

	@Autowired
	private EmployeeMapper employeeMapper;

	@Autowired
	private EmployeeFilter employeeFilter;

	@GetMapping()
	@RequestMapping("/all/bySalary")
	public ResponseEntity<List<EmployeeDto>> findAll() {

		ResponseEntity response = null;

		try {
			final List<PositionEmployeesDto> listaUsuarios = this.employeeMapper.convertToListPDto(this.positionRepository.findAll());

			listaUsuarios.forEach(usuario -> {

				usuario.getEmployees().sort((emp1, emp2) -> emp2.getSalary().compareTo(emp1.getSalary()));

			});

			response = ResponseEntity
				.ok()
				.body(listaUsuarios);
		} catch (final Exception exception) {
			response = ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
		}

		return response;

	}

	@GetMapping()
	@RequestMapping("/all/filter")
	public ResponseEntity<List<EmployeeDto>> findAllFilter(@RequestParam(name = "position", required = false) final Optional<String> positionName,
		@RequestParam(name = "name", required = false) final Optional<String> employeeName) {

		ResponseEntity response = null;

		try {
			final List<Employee> listaUsuarios = this.employeeRepository.findAll(
				employeeFilter.filterByEmployeeNameAndPositionName(employeeName, positionName));

			response = ResponseEntity
				.ok()
				.body(this.employeeMapper.convertToListDto(listaUsuarios));
		} catch (final Exception exception) {
			response = ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
		}

		return response;

	}

	@PostMapping()
	@RequestMapping(path = "/create", consumes = "application/json", produces = "application/json")
	public ResponseEntity create(final @RequestBody EmployeeDto employeeDto) {

		ResponseEntity response = null;

		try {
			final Employee employee = employeeMapper.convertToEntity(employeeDto);

			this.employeeRepository.save(employee);

			response = new ResponseEntity(HttpStatus.OK);
		} catch (final Exception exception) {
			response = ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
		}

		return response;

	}

	@PostMapping()
	@RequestMapping(path = "/update", consumes = "application/json", produces = "application/json")
	public ResponseEntity update(final @RequestBody EmployeeDto employeeDto) {

		ResponseEntity response = null;

		try {

			final Employee employee = employeeRepository.findById(employeeDto.getId()).get();

			employee.setSalary(employeeDto.getSalary());

			if (employeeDto.getPerson().getId() != null && !employeeDto.getPerson().getId().equals(employee.getPerson().getId())) {
				final Person person = personRepository.getById(employeeDto.getPerson().getId());
				employee.setPerson(person);
			}

			if (employeeDto.getPerson().getId() != null && !employeeDto.getPosition().getId().equals(employee.getPosition().getId())) {
				final Position position = positionRepository.getById(employeeDto.getPosition().getId());
				employee.setPosition(position);
			}

			this.employeeRepository.save(employee);
			response = new ResponseEntity(HttpStatus.OK);
		} catch (final Exception exception) {
			response = ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
		}

		return response;

	}

	@DeleteMapping()
	@RequestMapping("/delete/{idEmployee}")
	public ResponseEntity delete(final @PathVariable Long idEmployee) {

		ResponseEntity response = null;

		try {
			final Optional<Employee> employee = employeeRepository.findById(idEmployee);

			if (employee.isPresent()) {
				employee.get().setPosition(null);
				employeeRepository.delete(employee.get());
				response = new ResponseEntity(HttpStatus.OK);
			} else {
				response = new ResponseEntity(HttpStatus.BAD_REQUEST);
			}

		} catch (final Exception exception) {
			response = ResponseEntity
				.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(exception.getMessage());
		}

		return response;

	}

}
