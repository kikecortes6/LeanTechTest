package prueba.lean.tech.dtos;

import java.io.Serializable;

public class PersonDto implements Serializable {

	private static final long serialVersionUID = 233178757581L;

	private Long id;

	private String name;

	private String lastName;

	private String address;

	private Long cellphone;

	private String cityName;

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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(final String address) {
		this.address = address;
	}

	public Long getCellphone() {
		return cellphone;
	}

	public void setCellphone(final Long cellphone) {
		this.cellphone = cellphone;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(final String cityName) {
		this.cityName = cityName;
	}

}
