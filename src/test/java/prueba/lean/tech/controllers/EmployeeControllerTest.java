package prueba.lean.tech.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import prueba.lean.tech.dtos.EmployeeBaseDto;
import prueba.lean.tech.dtos.EmployeeDto;
import prueba.lean.tech.dtos.PersonDto;
import prueba.lean.tech.dtos.PositionDto;
import prueba.lean.tech.dtos.PositionEmployeesDto;
import prueba.lean.tech.entitys.Employee;
import prueba.lean.tech.entitys.Position;
import prueba.lean.tech.filter.EmployeeFilter;
import prueba.lean.tech.mapper.EmployeeMapper;
import prueba.lean.tech.repository.EmployeeRepository;
import prueba.lean.tech.repository.PersonRepository;
import prueba.lean.tech.repository.PositionRepository;

/**
 *
 * @author oscarenriquecortesmedina
 */
public class EmployeeControllerTest {

	@Mock
	private PersonRepository personRepository;

	@Mock
	private EmployeeRepository employeeRepository;

	@Mock
	private PositionRepository positionRepository;

	@Mock
	private EmployeeMapper employeeMapper;

	@Mock
	private EmployeeFilter employeeFilter;

	@InjectMocks
	private EmployeeController instance;

	@BeforeEach
	void given() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test of findAll method, of class EmployeeController.
	 */
	@Test
	public void testFindAll() {
		System.out.println("findAll");
		final List<Position> expResult = new ArrayList<>();
		expResult.add(new Position());
		final List<PositionEmployeesDto> empleados = new ArrayList<>();
		final PositionEmployeesDto positionEmp = new PositionEmployeesDto();
		final EmployeeBaseDto employeeBaseDto = new EmployeeBaseDto();
		employeeBaseDto.setSalary(Long.MAX_VALUE);
		final List<EmployeeBaseDto> empleadosDto = new ArrayList<>();
		empleadosDto.add(employeeBaseDto);
		positionEmp.setEmployees(empleadosDto);
		empleados.add(positionEmp);

		when(this.positionRepository.findAll())
			.thenReturn(expResult);

		when(this.employeeMapper.convertToListPDto(Mockito.anyList())).thenReturn(empleados);

		final ResponseEntity<List<EmployeeDto>> result = instance.findAll();
		assertEquals(expResult.size(), result.getBody().size());

	}

	@Test
	public void testFindAllNoEmployees() {
		System.out.println("findAll");
		final List<Position> expResult = new ArrayList<>();
		expResult.add(new Position());
		final List<PositionEmployeesDto> empleados = new ArrayList<>();

		empleados.add(new PositionEmployeesDto());

		when(this.positionRepository.findAll())
			.thenReturn(expResult);

		when(this.employeeMapper.convertToListPDto(Mockito.anyList())).thenReturn(empleados);

		final ResponseEntity<List<EmployeeDto>> result = instance.findAll();
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());

	}

	/**
	 * Test of findAllFilter method, of class EmployeeController.
	 */
	@Test
	public void testFindAllFilter() {
		System.out.println("findAllFilter");
		final Optional<String> positionName = null;
		final Optional<String> employeeName = null;

		final List<Employee> expResult = new ArrayList<>();
		expResult.add(new Employee());

		final List<EmployeeDto> employees = new ArrayList<>();
		employees.add(new EmployeeDto());

		when(this.employeeRepository.findAll(ArgumentMatchers.any(Specification.class)))
			.thenReturn(expResult);

		when(this.employeeMapper.convertToListDto(ArgumentMatchers.anyList()))
			.thenReturn(employees);

		final ResponseEntity<List<EmployeeDto>> result = instance.findAllFilter(positionName, employeeName);
		assertEquals(expResult.size(), result.getBody().size());
	}

	/**
	 * Test of create method, of class EmployeeController.
	 */
	@Test
	public void testCreate() {
		System.out.println("create");
		final EmployeeDto employeeDto = new EmployeeDto();

		when(employeeMapper.convertToEntity(Mockito.any(EmployeeDto.class)))
			.thenReturn(new Employee());
		final ResponseEntity result = instance.create(employeeDto);

		verify(employeeRepository).save(Mockito.any(Employee.class));
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	/**
	 * Test of UPDATE method, of class EmployeeController.
	 */
	@Test
	public void testUpdateNoPerson() {
		System.out.println("UPDATE");
		final EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(1L);

		final Optional emp = Optional.of(employeeDto);

		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(emp);

		final ResponseEntity result = instance.update(employeeDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}

	/**
	 * Test of UPDATE method, of class EmployeeController.
	 */
	@Test
	public void testUpdateNoPosition() {
		System.out.println("UPDATE");
		final EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(1L);
		employeeDto.setPerson(new PersonDto());

		final Optional emp = Optional.of(employeeDto);

		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(emp);

		final ResponseEntity result = instance.update(employeeDto);
		assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, result.getStatusCode());
	}

	/**
	 * Test of UPDATE method, of class EmployeeController.
	 */
	@Test
	public void testUpdate() {
		System.out.println("UPDATE");
		final EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setId(1L);
		employeeDto.setPerson(new PersonDto());
		employeeDto.setPosition(new PositionDto());
		final Optional emp = Optional.of(new Employee());

		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(emp);

		final ResponseEntity result = instance.update(employeeDto);
		assertEquals(HttpStatus.OK, result.getStatusCode());
	}

	/**
	 * Test of delete method, of class EmployeeController.
	 */
	@Test
	public void testDeleteBadRequest() {
		System.out.println("delete");
		final Long idEmployee = Long.MAX_VALUE;

		final ResponseEntity result = instance.delete(idEmployee);

		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
	}

	/**
	 * Test of delete method, of class EmployeeController.
	 */
	@Test
	public void testDelete() {
		System.out.println("delete");
		final Long idEmployee = Long.MAX_VALUE;

		when(employeeRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Employee()));

		final ResponseEntity result = instance.delete(idEmployee);

		assertEquals(HttpStatus.OK, result.getStatusCode());
	}
}
