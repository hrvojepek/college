package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = "permissions")
@Data
@EqualsAndHashCode(callSuper=false)
public class Permission extends BaseObject implements Serializable {

	private static final long serialVersionUID = 2753988931399793970L;

	@Column(unique = true, nullable = false)
	private String name;

	@ManyToMany(mappedBy = "permissions")
	private Set<Role> roles;
}
