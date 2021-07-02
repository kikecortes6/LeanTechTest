package prueba.lean.tech.dtos;

import java.io.Serializable;

public class PositionDto implements Serializable {

	private static final long serialVersionUID = 233178757581L;

	private Long id;

	private String name;

	public Long getId() {
		return id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

}
