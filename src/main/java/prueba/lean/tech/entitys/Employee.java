package prueba.lean.tech.entitys;

import javax.persistence.*;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "person_id")
	private Person person;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = false)
	@JoinColumn(name = "position_id")
	private Position position;

	@Column(name = "salary")
	private Long salary;

	public Employee() {
		super();
	}

	public long getId() {
		return id;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(final Person person) {
		this.person = person;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(final Position position) {
		this.position = position;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(final Long salary) {
		this.salary = salary;
	}

}
