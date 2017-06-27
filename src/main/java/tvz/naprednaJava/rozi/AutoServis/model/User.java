package tvz.naprednaJava.rozi.AutoServis.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.Audited;
import tvz.naprednaJava.rozi.AutoServis.enums.UserStatus;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Audited
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends BaseObject implements Serializable {

	private static final long serialVersionUID = -4819287527605601001L;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@Column
	private String firstName;

	@Column
	private String lastName;

	@Column
	@Enumerated(EnumType.STRING)
	private UserStatus status;

	@ManyToOne(cascade = CascadeType.ALL)
	private Role role;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "employee")
	private Employee employee;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "client")
	private Client client;
	
	public User() {
		super();
	}
	
	public User(String firstName, String lastName, String email, String username, String password, UserStatus status) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.status = status;
	} 

	public void setUserStatus(UserStatus userStatus) {
		this.status = userStatus;
	}
}
