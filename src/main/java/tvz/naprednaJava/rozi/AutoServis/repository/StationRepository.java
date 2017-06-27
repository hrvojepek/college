package tvz.naprednaJava.rozi.AutoServis.repository;

import java.time.LocalTime;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.model.Station;

public interface StationRepository extends JpaRepository<Station, Long> {

	Station findByName(String name);

	Station findByAddress(String address);

	Station findByGeolocation(String geolocation);

	Collection<Station> findByOpenFrom(LocalTime openFrom);

	Collection<Station> findByOpenFromBefore(LocalTime openFrom);

	Collection<Station> findByOpenFromAfter(LocalTime openFrom);

	Collection<Station> findByOpenFromBetween(LocalTime openFrom1, LocalTime openFrom2);

	Collection<Station> findByOpenUntil(LocalTime openUntil);

	Collection<Station> findByOpenUntilBefore(LocalTime openUntil);

	Collection<Station> findByOpenUntilAfter(LocalTime openUntil);

	Collection<Station> findByOpenUntilBetween(LocalTime openUntil1, LocalTime openUntil2);

	Collection<Station> findAllByStatus(Status status);
}
