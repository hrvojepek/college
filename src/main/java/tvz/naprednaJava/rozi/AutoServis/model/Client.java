package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Audited
@Table(name = "clients")
@Data
@EqualsAndHashCode(callSuper=false)
public class Client extends BaseObject implements Serializable {

	private static final long serialVersionUID = 6704416517535997982L;

	@OneToOne(mappedBy = "client")
	private User user;

	@OneToMany(mappedBy = "client")
	private Collection<Reservation> reservationClient;

	@OneToMany(mappedBy = "client")
	private Collection<Receipt> receiptCustomer;
}
