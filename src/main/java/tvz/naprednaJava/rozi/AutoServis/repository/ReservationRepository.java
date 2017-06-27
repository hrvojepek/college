package tvz.naprednaJava.rozi.AutoServis.repository;

import java.time.LocalDateTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.naprednaJava.rozi.AutoServis.enums.ReservationStatus;
import tvz.naprednaJava.rozi.AutoServis.model.Reservation;
import tvz.naprednaJava.rozi.AutoServis.model.Station;
import tvz.naprednaJava.rozi.AutoServis.model.User;

/**
 * Created by Hrvoje
 */
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

	Collection<Reservation> findAllByReservationStatus(ReservationStatus reservationStatus);

	Collection<Reservation> findAllByRepairStartDateGreaterThan(LocalDateTime repairStartDate);

	Collection<Reservation> findAllByReservationStatusAndRepairStartDateGreaterThan(ReservationStatus reservationStatus, LocalDateTime repairStartDate);

	Collection<Reservation> findAllByRepairStartDateLessThan(LocalDateTime repairStartDate);

	Collection<Reservation> findAllByReservationStatusAndRepairStartDateLessThan(ReservationStatus reservationStatus, LocalDateTime repairStartDate);

	Collection<Reservation> findAllByRepairStartDateBetween(LocalDateTime repairStartDate1, LocalDateTime repairStartDate2);

	Collection<Reservation> findAllByReservationStatusAndRepairStartDateBetween(ReservationStatus reservationStatus, LocalDateTime repairStartDate1,
			LocalDateTime repairStartDate2);

	Collection<Reservation> findAllByEstimatedRepairEndDateGreaterThan(LocalDateTime EstimatedRepairEndDate);

	Collection<Reservation> findAllByReservationStatusAndEstimatedRepairEndDateGreaterThan(ReservationStatus reservationStatus,
			LocalDateTime EstimatedRepairEndDate);

	Collection<Reservation> findAllByEstimatedRepairEndDateLessThan(LocalDateTime EstimatedRepairEndDate);

	Collection<Reservation> findAllByReservationStatusAndEstimatedRepairEndDateLessThan(ReservationStatus reservationStatus,
			LocalDateTime EstimatedRepairEndDate);

	Collection<Reservation> findAllByEstimatedRepairEndDateBetween(LocalDateTime EstimatedRepairEndDate1, LocalDateTime EstimatedRepairEndDate2);

	Collection<Reservation> findAllByReservationStatusAndEstimatedRepairEndDateBetween(ReservationStatus reservationStatus,
			LocalDateTime EstimatedRepairEndDate1, LocalDateTime EstimatedRepairEndDate2);

	Collection<Reservation> findAllByClient(User customer);

	Collection<Reservation> findAllByRepairman(User repairman);

	Collection<Reservation> findAllByStation(Station serviceStation);

	Collection<Reservation> findAllByStationAndRepairStartDateBetween(Station serviceStation, LocalDateTime repairStartDate1,
			LocalDateTime repairStartDate2);

}
