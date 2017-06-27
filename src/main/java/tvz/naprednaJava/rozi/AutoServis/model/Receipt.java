package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = "receipts")
@Data
@EqualsAndHashCode(callSuper=false)
public class Receipt extends BaseObject implements Serializable {

	private static final long serialVersionUID = 6217471459261409836L;

	@Column
	private BigDecimal total;

	@ManyToMany
	@JoinTable(name = "receipts_items",
		joinColumns = @JoinColumn(name = "receipt_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "item_id", referencedColumnName = "id"))
	private Collection<Item> items;

	// need to modify this. We need info for how many hours each service was performed
	@ManyToMany
	@JoinTable(name = "receipts_repair_services",
		joinColumns = @JoinColumn(name = "receipt_id", referencedColumnName = "id"),
		inverseJoinColumns = @JoinColumn(name = "repair_service_id", referencedColumnName = "id"))
	private Collection<RepairService> repairServices;

	@ManyToOne(cascade = CascadeType.ALL)
	private Station station;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "reservation")
	private Reservation reservation;

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee biller;

	@ManyToOne(cascade = CascadeType.ALL)
	private Client client;
}
