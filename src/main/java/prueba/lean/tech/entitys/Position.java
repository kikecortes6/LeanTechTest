package prueba.lean.tech.entitys;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "position")
public class Position {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "position", fetch = FetchType.LAZY)
	private List<Employee> employees;

	public List<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(final List<Employee> employees) {
		this.employees = employees;
	}

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
