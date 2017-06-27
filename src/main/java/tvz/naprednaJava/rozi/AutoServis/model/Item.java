package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tvz.naprednaJava.rozi.AutoServis.enums.Status;

@Entity
@Audited
@Table(name = "items")
@Data
@EqualsAndHashCode(callSuper=false)
public class Item extends BaseObject implements Serializable {

	private static final long serialVersionUID = -2248185070419923235L;

	@Column
	private String name;

	@Column
	private BigDecimal pricePerUnit;

	@Column
	private int unitsInStock;

	@Lob
	@Column(nullable = true)
	private String description;

	@ManyToOne(cascade = CascadeType.ALL)
	private Manufacturer manufacturer;

	@ManyToMany
	@JoinTable(name = "items_categories",
		joinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Collection<Category> categories;

	@ManyToMany(mappedBy = "items")
	private Collection<Receipt> receipts;

	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public Item() {
		super();
	}
	
	public Item(String name, BigDecimal pricePerUnit, int unitsInStock, String description, Manufacturer manufacturer) {
		super();
		this.name=name;
		this.pricePerUnit=pricePerUnit;
		this.unitsInStock=unitsInStock;
		this.description=description;
		this.manufacturer=manufacturer;
		this.status=Status.ACTIVE;
	}
}
