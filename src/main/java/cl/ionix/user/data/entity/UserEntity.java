package cl.ionix.user.data.entity;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity{

		@Column(name = "name")
		private String name;

		@Column(name = "username")
		private String username;

		@Column(name = "email", unique = true)
		private String email;

		@Column(name = "phone")
		private Integer phone;

	public UserEntity() {
	}

	public UserEntity(Long id, Date createdAt, Date updatedAt, String name, String username, String email, Integer phone) {
		super(id, createdAt, updatedAt);
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

	public Integer getPhone() {
		return phone;
	}

	public void setPhone(Integer phone) {
		this.phone = phone;
	}
}
