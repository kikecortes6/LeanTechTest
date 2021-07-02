package prueba.lean.tech.mapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ParseException;
import org.springframework.stereotype.Service;
import prueba.lean.tech.dtos.EmployeeDto;
import prueba.lean.tech.dtos.PositionEmployeesDto;
import prueba.lean.tech.entitys.Employee;
import prueba.lean.tech.entitys.Position;
import prueba.lean.tech.repository.PositionRepository;

@Service
public class EmployeeMapper {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PositionRepository positionRepository;

	public EmployeeDto convertToDto(final Employee employee) {
		return modelMapper.map(employee, EmployeeDto.class);
	}

	public Employee convertToEntity(final EmployeeDto employeeDto) throws ParseException {

		final Employee employee = modelMapper.map(employeeDto, Employee.class);

		if (employeeDto.getPosition().getId() != null) {
			final Optional<Position> position = positionRepository.findById(employeeDto.getPosition().getId());
			if (position.isPresent()) {
				employee.setPosition(position.get());
			}
		}

		return employee;

	}

	public List<EmployeeDto> convertToListDto(final List<Employee> employees) {

		return employees
			.stream()
			.map(employee -> this.convertToDto(employee))
			.collect(Collectors.toList());

	}

	public List<PositionEmployeesDto> convertToListPDto(final List<Position> positions) {

		return positions
			.stream()
			.map(employee -> modelMapper.map(employee, PositionEmployeesDto.class))
			.collect(Collectors.toList());

	}

}
