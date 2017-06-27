package tvz.naprednaJava.rozi.AutoServis.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.envers.Audited;

import lombok.Data;
import lombok.EqualsAndHashCode;
import tvz.naprednaJava.rozi.AutoServis.enums.ReservationStatus;

@Entity
@Audited
@Table(name = "reservations")
@Data
@EqualsAndHashCode(callSuper=false)
public class Reservation extends BaseObject implements Serializable {

	private static final long serialVersionUID = 8505137694213251310L;

	@Column
	@Enumerated(EnumType.STRING)
	private ReservationStatus reservationStatus;
	
	@Column
	private LocalDateTime repairStartDate;
	
	@Column
	private LocalDateTime estimatedRepairEndDate;

	@ManyToOne(cascade = CascadeType.ALL)
	private Client client;

	@ManyToOne(cascade = CascadeType.ALL)
	private Employee repairman;

	@ManyToOne(cascade = CascadeType.ALL)
	private Station station;

	@OneToOne(mappedBy = "reservation")
	private Receipt receipt;
}
