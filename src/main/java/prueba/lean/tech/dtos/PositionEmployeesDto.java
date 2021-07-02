package prueba.lean.tech.dtos;

import java.util.List;

public class PositionEmployeesDto extends PositionDto {

	private List<EmployeeBaseDto> employees;

	public List<EmployeeBaseDto> getEmployees() {
		return employees;
	}

	public void setEmployees(final List<EmployeeBaseDto> employees) {
		this.employees = employees;
	}

}
