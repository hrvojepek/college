package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = "employees")
@Data
@EqualsAndHashCode(callSuper=false)
public class Employee extends BaseObject implements Serializable {

	private static final long serialVersionUID = 1594399215990143769L;

	@OneToOne(mappedBy = "employee")
	private User user;

	@OneToOne(mappedBy = "manager")
	private Station managerOfStation;

	@ManyToOne(cascade = CascadeType.ALL)
	private Station employeeOfStation;

	@OneToMany(mappedBy = "biller")
	private Collection<Receipt> receiptBiller;

	@OneToMany(mappedBy = "repairman")
	private Collection<Reservation> reservationRepairmen;
}
