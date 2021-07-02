package prueba.lean.tech.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import prueba.lean.tech.dtos.EmployeeDto;
import prueba.lean.tech.dtos.PositionDto;
import prueba.lean.tech.dtos.PositionEmployeesDto;
import prueba.lean.tech.entitys.Employee;
import prueba.lean.tech.entitys.Position;
import prueba.lean.tech.repository.PositionRepository;

/**
 *
 * @author oscarenriquecortesmedina
 */
public class EmployeeMapperTest {

	@Mock
	private ModelMapper modelMapper;

	@Mock
	private PositionRepository positionRepository;

	@InjectMocks
	private EmployeeMapper instance;

	@BeforeEach
	void given() {
		MockitoAnnotations.initMocks(this);
	}

	/**
	 * Test of convertToDto method, of class EmployeeMapper.
	 */
	@Test
	public void testConvertToDto() {
		System.out.println("convertToDto");
		final Employee employee = new Employee();
		final EmployeeDto expResult = new EmployeeDto();

		when(modelMapper.map(Mockito.any(Employee.class), Mockito.any()))
			.thenReturn(expResult);

		final EmployeeDto result = instance.convertToDto(employee);
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertToEntity method, of class EmployeeMapper.
	 */
	@Test
	public void testConvertToEntity() {
		System.out.println("convertToEntity");
		final EmployeeDto employeeDto = new EmployeeDto();
		employeeDto.setPosition(new PositionDto());
		final Employee expResult = new Employee();

		when(modelMapper.map(Mockito.any(EmployeeDto.class), Mockito.any()))
			.thenReturn(expResult);

		final Employee result = instance.convertToEntity(employeeDto);
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertToEntity method, of class EmployeeMapper.
	 */
	@Test
	public void testConvertToEntityWithPositionId() {
		System.out.println("convertToEntity");
		final EmployeeDto employeeDto = new EmployeeDto();
		final PositionDto positionDto = new PositionDto();
		positionDto.setId(Long.MIN_VALUE);
		employeeDto.setPosition(positionDto);
		final Employee expResult = new Employee();

		when(modelMapper.map(Mockito.any(EmployeeDto.class), Mockito.any()))
			.thenReturn(expResult);

		when(positionRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(new Position()));

		final Employee result = instance.convertToEntity(employeeDto);
		assertEquals(expResult, result);
	}

	/**
	 * Test of convertToListDto method, of class EmployeeMapper.
	 */
	@Test
	public void testConvertToListDto() {
		System.out.println("convertToListDto");
		final List<Employee> employees = new ArrayList<>();
		employees.add(new Employee());

		when(modelMapper.map(Mockito.any(Employee.class), Mockito.any()))
			.thenReturn(new EmployeeDto());

		final List<EmployeeDto> result = instance.convertToListDto(employees);
		assertEquals(1, result.size());
	}

	/**
	 * Test of convertToListPDto method, of class EmployeeMapper.
	 */
	@Test
	public void testConvertToListPDto() {
		System.out.println("convertToListPDto");
		final List<Position> positions = new ArrayList<>();
		positions.add(new Position());

		when(modelMapper.map(Mockito.any(PositionEmployeesDto.class), Mockito.any()))
			.thenReturn(new PositionEmployeesDto());

		final List<PositionEmployeesDto> result = instance.convertToListPDto(positions);
		assertEquals(1, result.size());
	}

}
