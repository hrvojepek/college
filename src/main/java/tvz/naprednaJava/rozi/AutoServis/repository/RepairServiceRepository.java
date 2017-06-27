package tvz.naprednaJava.rozi.AutoServis.repository;

import java.math.BigDecimal;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import tvz.naprednaJava.rozi.AutoServis.enums.Status;
import tvz.naprednaJava.rozi.AutoServis.model.RepairService;

public interface RepairServiceRepository extends JpaRepository<RepairService, Long> {

	Collection<RepairService> findByName(String name);

	Collection<RepairService> findByNameLike(String name);

	Collection<RepairService> findByNameStartingWith(String name);

	Collection<RepairService> findByNameEndingWith(String name);

	Collection<RepairService> findByPricePerHour(BigDecimal pricePerHour);

	Collection<RepairService> findByPricePerHourBetween(BigDecimal pricePerHour1, BigDecimal pricePerHour2);

	Collection<RepairService> findByPricePerHourLessThan(BigDecimal pricePerHour);

	Collection<RepairService> findByPricePerHourGreaterThan(BigDecimal pricePerHour);

	Collection<RepairService> findAllByStatus(Status status);
}
