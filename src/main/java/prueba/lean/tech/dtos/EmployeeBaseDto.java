package prueba.lean.tech.dtos;

import java.io.Serializable;

public class EmployeeBaseDto implements Serializable {

	private static final long serialVersionUID = -6652804233178757581L;

	private Long id;

	private Long salary;

	private PersonDto person;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(final Long salary) {
		this.salary = salary;
	}

	public PersonDto getPerson() {
		return person;
	}

	public void setPerson(final PersonDto person) {
		this.person = person;
	}

}
