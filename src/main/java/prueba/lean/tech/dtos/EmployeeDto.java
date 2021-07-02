package prueba.lean.tech.dtos;

public class EmployeeDto extends EmployeeBaseDto {

	private PositionDto position;

	public PositionDto getPosition() {
		return position;
	}

	public void setPosition(final PositionDto position) {
		this.position = position;
	}

}
