package cl.ionix.user.controller.dto;

public class ResponseUserDto {
	private String name;
	private String username;
	private String email;
	private int phone;

	public ResponseUserDto() {
	}

	public ResponseUserDto(String name, String username, String email, int phone) {
		this.name = name;
		this.username = username;
		this.email = email;
		this.phone = phone;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getPhone() {
		return phone;
	}

	public void setPhone(int phone) {
		this.phone = phone;
	}
}

