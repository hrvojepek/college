package tvz.naprednaJava.rozi.AutoServis.service;

import java.time.LocalTime;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.model.Station;
import tvz.naprednaJava.rozi.AutoServis.repository.StationRepository;

@Service
@Transactional
public class StationService {

	@Autowired
	private StationRepository stationRepository;

	public Station getById(Long id) {
		return stationRepository.getOne(id);
	}

	public Station getByName(String name) {
		return stationRepository.findByName(name);
	}

	public Station getByAddress(String address) {
		return stationRepository.findByAddress(address);
	}

	public Station getByGeolocation(String geolocation) {
		return stationRepository.findByGeolocation(geolocation);
	}

	public Collection<Station> getByOpenFrom(LocalTime openFrom) {
		return stationRepository.findByOpenFrom(openFrom);
	}

	public Collection<Station> getByOpenFromBefore(LocalTime openFrom) {
		return stationRepository.findByOpenFromBefore(openFrom);
	}

	public Collection<Station> getByOpenFromAfter(LocalTime openFrom) {
		return stationRepository.findByOpenFromAfter(openFrom);
	}

	public Collection<Station> getByOpenFromBetween(LocalTime openFrom1, LocalTime openFrom2) {
		return stationRepository.findByOpenFromBetween(openFrom1, openFrom2);
	}

	public Collection<Station> getByOpenUntil(LocalTime openUntil) {
		return stationRepository.findByOpenUntil(openUntil);
	}

	public Collection<Station> getByOpenUntilBefore(LocalTime openUntil) {
		return stationRepository.findByOpenUntilBefore(openUntil);
	}

	public Collection<Station> getByOpenUntilAfter(LocalTime openUntil) {
		return stationRepository.findByOpenUntilAfter(openUntil);
	}

	public Collection<Station> getByOpenUntilBetween(LocalTime openUntil1, LocalTime openUntil2) {
		return stationRepository.findByOpenUntilBetween(openUntil1, openUntil2);
	}

	public Collection<Station> getAll() {
		return stationRepository.findAll();
	}

	public Collection<Station> getAllActive() {
		return stationRepository.findAllByStatus(Status.ACTIVE);
	}

	@Transactional(readOnly = false)
	public Station create(Station station) {
		return stationRepository.saveAndFlush(station);
	}

	@Transactional(readOnly = false)
	public Station update(Station station) {
		// TODO implement custom logic
		return stationRepository.saveAndFlush(station);
	}

	@Transactional(readOnly = false)
	public boolean delete(Station station) {
		station.setStatus(Status.DELETED);
		if (stationRepository.saveAndFlush(station) != null) {
			return true;
		}
		return false;
	}
}
