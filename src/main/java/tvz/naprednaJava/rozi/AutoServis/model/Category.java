package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = "categories")
@Data
@EqualsAndHashCode(callSuper=false)
public class Category extends BaseObject implements Serializable {

	private static final long serialVersionUID = -5365917946372058189L;

	@Column(unique = true, nullable = false)
	private String name;

	@Column(nullable = true)
	@Lob
	private String description;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
	private Collection<Item> items;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "categories")
	private Collection<RepairService> repairServices;
}
