package tvz.naprednaJava.rozi.AutoServis.service;

import java.time.LocalDateTime;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.naprednaJava.rozi.AutoServis.enums.ReservationStatus;
import tvz.naprednaJava.rozi.AutoServis.model.Reservation;
import tvz.naprednaJava.rozi.AutoServis.model.Station;
import tvz.naprednaJava.rozi.AutoServis.model.User;
import tvz.naprednaJava.rozi.AutoServis.repository.ReservationRepository;

@Service
@Transactional
public class ReservationService {

	private static final Logger logger = LoggerFactory.getLogger(ReservationService.class);

	@Autowired
	private ReservationRepository reservationRepository;

	public Reservation getById(Long id) {
		return reservationRepository.findOne(id);
	}

	public Collection<Reservation> getAll() {
		return (Collection<Reservation>) reservationRepository.findAll();
	}

	public Collection<Reservation> getAllWithStatus(ReservationStatus reservationStatus) {
		return reservationRepository.findAllByReservationStatus(reservationStatus);
	}

	public Collection<Reservation> getAllWithStartDateGreaterThan(LocalDateTime repairStartDate) {
		return reservationRepository.findAllByRepairStartDateGreaterThan(repairStartDate);
	}

	public Collection<Reservation> getAllWithStatusAndStartDateGreaterThan(ReservationStatus reservationStatus, LocalDateTime repairStartDate) {
		return reservationRepository.findAllByReservationStatusAndRepairStartDateGreaterThan(reservationStatus, repairStartDate);
	}

	public Collection<Reservation> getAllWithStartDateLessThan(LocalDateTime repairStartDate) {
		return reservationRepository.findAllByRepairStartDateLessThan(repairStartDate);
	}

	public Collection<Reservation> getAllWithStatusAndStartDateLessThan(ReservationStatus reservationStatus, LocalDateTime repairStartDate) {
		return reservationRepository.findAllByReservationStatusAndRepairStartDateLessThan(reservationStatus, repairStartDate);
	}

	public Collection<Reservation> getAllWithStartDateBetween(LocalDateTime repairStartDate1, LocalDateTime repairStartDate2) {
		return reservationRepository.findAllByRepairStartDateBetween(repairStartDate1, repairStartDate2);
	}

	public Collection<Reservation> getAllWithStatusAndStartDateBetween(ReservationStatus reservationStatus, LocalDateTime repairStartDate1,
			LocalDateTime repairStartDate2) {
		return reservationRepository.findAllByReservationStatusAndRepairStartDateBetween(reservationStatus, repairStartDate1, repairStartDate2);
	}

	public Collection<Reservation> getAllWithEstimatedRepairEndDateGreaterThan(LocalDateTime estimatedRepairEndDate) {
		return reservationRepository.findAllByEstimatedRepairEndDateGreaterThan(estimatedRepairEndDate);
	}

	public Collection<Reservation> getAllWithStatusAndEstimatedRepairEndDateGreaterThan(ReservationStatus reservationStatus,
			LocalDateTime estimatedRepairEndDate) {
		return reservationRepository.findAllByReservationStatusAndEstimatedRepairEndDateGreaterThan(reservationStatus, estimatedRepairEndDate);
	}

	public Collection<Reservation> getAllWithEstimatedRepairEndDateLessThan(LocalDateTime estimatedRepairEndDate) {
		return reservationRepository.findAllByEstimatedRepairEndDateLessThan(estimatedRepairEndDate);
	}

	public Collection<Reservation> getAllWithStatusAndEstimatedRepairEndDateLessThan(ReservationStatus reservationStatus,
			LocalDateTime estimatedRepairEndDate) {
		return reservationRepository.findAllByReservationStatusAndEstimatedRepairEndDateLessThan(reservationStatus, estimatedRepairEndDate);
	}

	public Collection<Reservation> getAllWithEstimatedRepairEndDateBetween(LocalDateTime estimatedRepairEndDate1, LocalDateTime estimatedRepairEndDate2) {
		return reservationRepository.findAllByEstimatedRepairEndDateBetween(estimatedRepairEndDate1, estimatedRepairEndDate2);
	}

	public Collection<Reservation> findAllByReservationStatusAndEstimatedRepairEndDateBetween(ReservationStatus reservationStatus,
			LocalDateTime estimatedRepairEndDate1, LocalDateTime estimatedRepairEndDate2) {
		return reservationRepository.findAllByReservationStatusAndRepairStartDateBetween(reservationStatus, estimatedRepairEndDate1, estimatedRepairEndDate2);
	}

	public Collection<Reservation> getAllByCustomer(User customer) {
		return reservationRepository.findAllByClient(customer);
	}

	public Collection<Reservation> getAllByRepairman(User repairman) {
		return reservationRepository.findAllByRepairman(repairman);
	}

	public Collection<Reservation> getAllByServiceStation(Station serviceStation) {
		return reservationRepository.findAllByStation(serviceStation);
	}

	public Collection<Reservation> getAllByServiceStationAndRepairStartDateBetween(Station serviceStation, LocalDateTime repairStartDate1,
			LocalDateTime repairStartDate2) {
		return reservationRepository.findAllByStationAndRepairStartDateBetween(serviceStation, repairStartDate1, repairStartDate2);
	}

	@Transactional(readOnly = false)
	public Reservation create(Reservation reservation) {
		return reservationRepository.saveAndFlush(reservation);
	}

	@Transactional(readOnly = false)
	public Reservation update(Reservation reservation) {
		return reservationRepository.saveAndFlush(reservation);
	}

	@Transactional(readOnly = false)
	public boolean delete(Reservation reservation) {
		try {
			reservationRepository.delete(reservation);
		} catch (Exception e) {
			logger.debug(String.format("Error deleteing reservation with id %d. Reasone: %s", reservation.getId(), e.getMessage()));
			return false;
		}
		logger.debug(String.format("Reservation with id %d successfully deleted.", reservation.getId()));
		return true;
	}
}
