package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tvz.naprednaJava.rozi.AutoServis.enums.Status;

@Entity
@Audited
@Table(name = "repair_services")
@Data
@EqualsAndHashCode(callSuper=false)
public class RepairService extends BaseObject implements Serializable {

	private static final long serialVersionUID = -2160025339950079687L;

	@Column
	private String name;

	@Column
	private BigDecimal pricePerHour;

	@Lob
	@Column(nullable = true)
	private String description;

	@ManyToMany
	@JoinTable(name = "services_categories",
		joinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "category_id", referencedColumnName = "id"))
	private Collection<Category> categories;

	@ManyToMany(mappedBy = "repairServices")
	private Collection<Receipt> receipts;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Status status;
	
	public RepairService() {
		super();
	}
	
	public RepairService(String name, BigDecimal pricePerHour, String description) {
		super();
		this.name=name;
		this.pricePerHour=pricePerHour;
		this.description=description;
		this.status=Status.ACTIVE;
	}
}
